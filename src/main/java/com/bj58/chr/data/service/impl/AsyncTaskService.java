package com.bj58.chr.data.service.impl;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.bj58.chr.data.utils.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.bj58.chr.CV.constans.CvOperTypeEnum;
import com.bj58.chr.common.HttpUtils;
import com.bj58.chr.common.JSONUtils;
import com.bj58.chr.common.web.page.ParamUtils;
import com.bj58.chr.data.esb.MessageSend;
import com.bj58.chr.data.esb.message.CvPostMessage;
import com.bj58.chr.data.model.CVContract;
import com.bj58.chr.data.model.CVencrypt;
import com.bj58.chr.data.model.CvIdCoId;
import com.bj58.chr.data.model.SeekerUser;
import com.bj58.chr.data.model.CVContract.Data;
import com.bj58.chr.data.service.IAsyncTaskService;
import com.bj58.chr.data.service.ICVencryptService;
import com.bj58.chr.data.service.IConfigService;
import com.bj58.chr.data.service.IContractService;
import com.bj58.chr.data.service.ICvIdCoIdService2;
import com.bj58.chr.data.service.ICvInfoBoService;
import com.bj58.chr.data.service.ISeekerUserService;
import com.bj58.chr.data.service.ISeekersCallBackService;
import com.bj58.chr.data.service.IStatService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * 
 * 
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 下午5:06:02
 * @see
 * @since
 */
@Service
public class AsyncTaskService implements IAsyncTaskService {

	private final static Logger LOG = LoggerFactory.getLogger(AsyncTaskService.class);

	private final static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(1000);

	private final static ExecutorService executorService = Executors.newFixedThreadPool(5);

	private boolean start = false;

	private final static ScheduledExecutorService importTaskService = Executors
			.newSingleThreadScheduledExecutor(new NamedThreadFactory("time-"));

	@Resource
	private IContractService contractService;

	@Resource(name = "cvsMongoTemplate")
	private MongoTemplate cvsMongoTemplate;

	@Resource(name = "cvMessageSend")
	private MessageSend cvMessageSend;

	@Resource
	private ICVencryptService cVencryptService;

	@Resource
	private IConfigService configService;

	@Resource
	private ICvInfoBoService cvInfoBoService;

	@Resource
	private ISeekersCallBackService seekersCallBackService;

	@Resource
	private ISeekerUserService seekerUserService;

	@Resource
	private ICvIdCoIdService2 cvIdCoIdService2;

	@Resource
	private IStatService statService;

	/**
	 * 更新简历时间
	 */
	@Override
	public void updateTimeByCvid(String cvId, String uid, String coid, Date date) {
		LOG.info("update  " + cvId + " to time" + DateFormatUtils.format(date, "yyyy-MM-dd"));
		long time = QDStringUtils.getCvTime(date.getTime());
		Query query = Query.query(Criteria.where("_id").is(cvId));
		Update update = new Update();
		update.set("lRefTime", time);
		cvsMongoTemplate.updateFirst(query, update, "cvInfoBo");
		/** 更新contract updateTime */
		CVContract contract = contractService.findByCoId(coid);
		if (contract != null) {
			contract.setUpdateTime(date);
			contract.setCoid(coid);
			contractService.update(contract);
		} else {
			if (StringUtils.isNotEmpty(uid)) {// 刷新用户的时候，发现用户不存在，就拉用户到contract;
				contract = contractService.findByUid(uid);
				if (contract == null) {// 5是代表我们库有的，只刷新了
					Map<String, String> map = seekerUserService.getUserInfo(uid);
					contract = new CVContract();
					contract.setCoid(coid);
					contract.setUid(uid);
					contract.setStatus(4);
					Data data = new Data();
					data.setEmails(map.get("email"));
					data.setNamemobiles(map.get("mobile"));
					contract.setData(data);
					Calendar calendar = Calendar.getInstance();
					calendar.set(2016, 0, 0, 0, 0, 0);
					Date d = calendar.getTime();
					contract.setCreatedTime(d);
					contract.setUpdateTime(new Date());
					contract.setLoginTime(d);
					contractService.save(contract);
				}
			}
		}

		cvMessageSend.send(new CvPostMessage(cvId, "", "", "refreshCvById", CvOperTypeEnum.REFRESH.getId(), ""));
		/** 58dp logger **/
		Logger58dp.getInstance().cvLogger(cvId, time, "U", "");
	}

	/**
	 * 更新简历联系方式
	 */
	@Override
	public void updateContract(final String coid) {
		initTask();
		try {
			queue.put(coid);
		} catch (InterruptedException e) {
			LOG.error("error", e);
		}

	}

	private int id = 0;

	private int dbIndex = 0;

	// @Override
	// public void updateContractNotGet() {
	// LOG.info("start.......");
	// for (int i = 0; i < 30; i++) {
	// String table = "cv_idcoid_" + i;
	// id = 0;
	// dbIndex = i;
	// boolean run = true;
	// LOG.info(table);
	// while (run) {
	// try {
	// List<CvIdCoId> list = cvIdCoIdService2
	// .list(ParamUtils.createParam().add("table", table).add("id", id).get());
	// if (CollectionUtils.isEmpty(list)) {
	// run = false;
	// } else {
	// for (CvIdCoId cvIdCoId : list) {
	// CVContract contract = contractService.findByCoId(cvIdCoId.getCoId());
	// // 判断这个用户是否是有加密记录
	// if (contract == null) {
	// LOG.info("get :" + cvIdCoId.getCoId());
	// updateContract(cvIdCoId.getCoId());
	// }
	//
	// if (cvIdCoId.getId() > id) {
	// id = cvIdCoId.getId();
	// }
	// }
	// }
	// id++;
	// } catch (Exception e) {
	// LOG.error("contract", e);
	// }
	// }
	// }
	// }

	@Override
	public void updateContractDb(final int db) {
		id = 0;
		dbIndex = db;
		boolean run = true;
		String table = "cv_idcoid_" + dbIndex;
		LOG.info(table);
		while (run) {
			try {
				List<CvIdCoId> list = cvIdCoIdService2
						.list(ParamUtils.createParam().add("table", table).add("id", id).get());
				if (CollectionUtils.isEmpty(list)) {
					run = false;
				} else {
					for (CvIdCoId cvIdCoId : list) {
						updateContract(cvIdCoId.getCoId());
						if (cvIdCoId.getId() > id) {
							id = cvIdCoId.getId();
						}
					}
				}
				id++;
			} catch (Exception e) {
				LOG.error("contract", e);
			}
		}
	}

	private void initTask() {
		if (!start) {
			start = true;
			LOG.info("start task");
			for (int i = 0; i < 5; i++) {
				executorService.submit(new Runnable() {
					@Override
					public void run() {
						while (true) {
							try {
								String coid = queue.poll(10, TimeUnit.MINUTES);
								if (coid != null) {
									LOG.info("get form queue:" + coid);
									updateContractImpl(coid);
								}
							} catch (InterruptedException e) {
								LOG.error("error", e);
							}
						}
					}
				});
			}
		}
	}

	public int getId() {
		return id;
	}

	public int getDbIndex() {
		return dbIndex;
	}

	@Override
	public CVContract updateContractImpl(String coid) {
		CVContract qdContract = null;
		try {
			String content = HttpUtils.doGet(configService.getByName("qd.contact") + coid);
			if (!StringUtils.isEmpty(content) && !JSONUtils.readValue(content, Map.class).get("status").equals("0")) {
				qdContract = JSONUtils.readValue(content, CVContract.class);
				if (qdContract != null) {
					LogUtils.contract(qdContract.toString());
				}
				if (qdContract != null && StringUtils.isNotEmpty(qdContract.getData().getNewMobile())) {// 更新联系方式
					qdContract.setCoid(coid);
					LOG.info(String.format("===coid is %s linkInfo is %s===", coid, qdContract));
					// 更新简历
					// find uid
					SeekerUser user = seekerUserService.findByCoId(coid);

					cvInfoBoService.updateForContract(qdContract.getData().getNewMobile(),
							qdContract.getData().getNewEmail(), qdContract.getData().getNewName(), coid);
					qdContract.setUid(user.getUid());// 添加的时候添加用户uid
					contractService.saveOrUpdate(qdContract);
					statService.contract();

					if (user != null) {
						// 更新用户信息
						seekersCallBackService.updateSeekersLink(user.getUid(), qdContract.getData().getNewEmail(),
								qdContract.getData().getNewMobile());
					}
				}
			} else {
				LogUtils.error(coid);
				statService.contractError();
			}
		} catch (Exception e) {// 异常后，记录下来
			LOG.error("get erorr", e);
			LogUtils.error(coid);
			statService.contractError();
		}
		return qdContract;
	}

	/**
	 * 采集验证信息
	 */

	@Override
	public void importData() {
		importTaskService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {// 10秒检查一次
				try {
					LOG.info("run....");
					importDataImpl();
				} catch (Exception e) {
					LOG.error("get erorr", e);
				}
			}
		}, 0, 10, TimeUnit.MINUTES);
	}

	@Override
	public void stopImportData() {
		loop = false;
	}

	private volatile boolean loop = false;

	@Override
	public void importDataImpl() {
		String queryCVInfoTemp = "lRefTime";// configService.getByName("yc.select.column");
		loop = true;
		long old = 0l;
		while (loop) {
			// 从redis中获得最近一个操作的时间
			Range range = getRange(RedisHeper.getRedisHeper().getRecent(), old);
			LOG.info(range.toString());
			if (!range.over()) {// 没有超过现在时间
				RedisHeper.getRedisHeper().setRecent(range.getEnd());
				old = range.getEnd();

				Query query = new Query(Criteria.where(queryCVInfoTemp).gte(range.qStart()).lt(range.qEnd()));

				BasicDBObject bdb = new BasicDBObject();
				bdb.append("_id", 1);
				bdb.append("uid", 1);
				bdb.append("cnName", 1);
				bdb.append("mobile", 1);

				DBCursor dBCursor = cvsMongoTemplate.getCollection("cvInfoBo").find(query.getQueryObject(), bdb);
				if (dBCursor != null) {
					while (dBCursor.hasNext()) {
						DBObject dbo = dBCursor.next();
						CVencrypt cVencrypt = createEncrypt(dbo);
						if (cVencrypt != null) {
							cVencryptService.add(cVencrypt);
						}
					}
				}

			} else {
				loop = false;
			}
		}
	}

	private Range getRange(long start, long old) {
		if (start == 0l) {
			if (old == 0l) {
				Calendar now = Calendar.getInstance();
				now.add(Calendar.YEAR, -15);
				start = now.getTimeInMillis();
			} else {
				start = old;
			}
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(start);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		long end = calendar.getTimeInMillis();
		return new Range(start, end);

	}

	private CVencrypt createEncrypt(DBObject dbo) {
		CVencrypt cVencrypt = new CVencrypt();
		cVencrypt.setCvid(String.valueOf(dbo.get("_id")));
		cVencrypt.setUid(String.valueOf(dbo.get("uid")));
		String original = String.valueOf(dbo.get("cnName")) + "," + String.valueOf(dbo.get("mobile"));
		cVencrypt.setOriginal(original);
		cVencrypt.setEncrpt(QDencrypt.encrypt(original));
		return cVencrypt;
	}

	private class Range {

		private long start;
		private long end;

		public Range(long start, long end) {
			super();
			this.start = start;
			this.end = end;
		}

		public long qStart() {
			return start / 1000;
		}

		public long qEnd() {
			return end / 1000;
		}

		// public long qetStart() {
		// return start;
		// }

		public long getEnd() {
			return end;
		}

		public boolean over() {
			return System.currentTimeMillis() < end;
		}

		@Override
		public String toString() {
			return "Range [start=" + DateFormatUtils.format(start, "yyyy-MM-dd HH:mm:ss") + ", end="
					+ DateFormatUtils.format(end, "yyyy-MM-dd HH:mm:ss") + "]";
		}

	}

}
