package com.bj58.chr.data.service.impl;

import java.util.Calendar;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.bj58.chr.common.MtDateUtils;
import com.bj58.chr.data.model.Stat;
import com.bj58.chr.data.service.IStatService;
import com.bj58.chr.data.utils.RedisHeper;
import com.google.common.collect.Maps;

import redis.clients.jedis.Response;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月9日 下午2:19:07
 * @see
 * @since
 */
@Service
public class StatService implements IStatService {

	public final static String ASK_ALL = "ask_all";

	public final static String ASK_NEW = "ask_new";

	public final static String ASK_NEW_FULL = "ask_new_full";

	public final static String ASK_NEW_M = "ask_new_m";

	public final static String ASK_NEW_NO = "ask_new_no";

	public final static String ASK_NEW_REJECT = "ask_new_reject";

	public final static String ASK_NEW_ERROR = "ask_new_error";

	public final static String ASK_OLD = "ask_old";

	public final static String ASK_OLD_ERROR = "ask_old_error";

	public final static String ASK_OLD_FULL = "ask_old_full";

	public final static String ASK_OLD_M = "ask_old_m";

	public final static String ASK_OLD_NO = "ask_old_no";

	public final static String ASK_OLD_REJECT = "ask_old_reject";

	public final static String ASK_REJECT = "ask_reject";

	public final static String IMPORT_NEW = "import_new";

	public final static String IMPORT_OLD = "import_old";

	public final static String IMPORT_JX_ERROR = "import_jx_error";

	public final static String IMPORT_NEW_ERROR = "import_new_error";

	public final static String IMPORT_OLD_ERROR = "import_old_error";

	public final static String CONTRACT_GET = "contract_get";

	public final static String CONTRACT_ERROR = "contract_error";
	
	public final static String IMPORT_SYNC = "import_sync";

	private RedisHeper redisHeper = RedisHeper.getRedisHeper();

	@Resource
	private MongoTemplate mongoTemplate;

	private int day;
	private long next;

	public String getKey(String key) {
		long now = System.currentTimeMillis();
		if (now > next) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(now);
			try {
				String id = MtDateUtils.formatYMD(MtDateUtils.prefDate(calendar.getTime()));
				Map<String, Object> value = getALLData(day, id);
				Stat stat = new Stat();
				stat.setId(id);
				stat.setMap(value);
				mongoTemplate.save(stat);
			} catch (Exception e) {
			}
			day = calendar.get(Calendar.DAY_OF_MONTH);
			next = MtDateUtils.nextNeatDay(calendar).getTime();
		}
		return key + "_" + day;
	}

	public String getKey(String key, int day) {
		return key + "_" + day;
	}

	@Override
	public long askAll() {
		return redisHeper.incr(getKey(ASK_ALL));
	}

	@Override
	public String askAll(int day) {
		return getKey(ASK_ALL, day);
	}

	@Override
	public long askNew() {
		return redisHeper.incr(getKey(ASK_NEW));
	}

	@Override
	public String askNew(int day) {
		return getKey(ASK_NEW, day);
	}

	@Override
	public long askNewError() {
		return redisHeper.incr(getKey(ASK_NEW_ERROR));
	}

	@Override
	public String askNewError(int day) {
		return getKey(ASK_NEW_ERROR, day);
	}

	@Override
	public long askOld() {
		return redisHeper.incr(getKey(ASK_OLD));
	}

	@Override
	public String askOld(int day) {
		return getKey(ASK_OLD, day);
	}

	@Override
	public long askOldError() {
		return redisHeper.incr(getKey(ASK_OLD_ERROR));
	}

	@Override
	public String askOldError(int day) {
		return getKey(ASK_OLD_ERROR, day);
	}

	@Override
	public long askReject() {
		return redisHeper.incr(getKey(ASK_OLD_ERROR));
	}

	@Override
	public String askReject(int day) {
		return getKey(ASK_REJECT, day);
	}

	@Override
	public long importNew() {
		return redisHeper.incr(getKey(IMPORT_NEW));
	}

	@Override
	public String importNew(int day) {
		return getKey(IMPORT_NEW, day);
	}

	@Override
	public long importOld() {
		return redisHeper.incr(getKey(IMPORT_OLD));
	}

	@Override
	public String importOld(int day) {
		return getKey(IMPORT_OLD, day);
	}

	@Override
	public long importNewError() {
		return redisHeper.incr(getKey(IMPORT_NEW_ERROR));
	}

	@Override
	public String importNewError(int day) {
		return getKey(IMPORT_NEW_ERROR, day);
	}

	@Override
	public long importOldError() {
		return redisHeper.incr(getKey(IMPORT_OLD_ERROR));
	}

	@Override
	public String importOldError(int day) {
		return getKey(IMPORT_OLD_ERROR, day);
	}

	@Override
	public long importjxError() {
		return redisHeper.incr(getKey(IMPORT_JX_ERROR));
	}

	@Override
	public String importjxError(int day) {
		return getKey(IMPORT_JX_ERROR, day);
	}

	@Override
	public long contract() {
		return redisHeper.incr(getKey(CONTRACT_GET));
	}

	@Override
	public String contract(int day) {
		return getKey(CONTRACT_GET, day);
	}

	@Override
	public long contractError() {
		return redisHeper.incr(getKey(CONTRACT_ERROR));
	}

	@Override
	public String contractError(int day) {
		return getKey(CONTRACT_ERROR, day);
	}

	@Override
	public long askNewFull() {
		return redisHeper.incr(getKey(ASK_NEW_FULL));
	}

	@Override
	public String askNewFull(int day) {
		return getKey(ASK_NEW_FULL, day);
	}

	@Override
	public long askNewM() {
		return redisHeper.incr(getKey(ASK_NEW_M));
	}

	@Override
	public String askNewM(int day) {
		return getKey(ASK_NEW_M, day);
	}

	@Override
	public long askNewNo() {
		return redisHeper.incr(getKey(ASK_NEW_NO));
	}

	@Override
	public String askNewNo(int day) {
		return getKey(ASK_NEW_NO, day);
	}

	@Override
	public long askNewReject() {
		return redisHeper.incr(getKey(ASK_NEW_REJECT));
	}

	@Override
	public String askNewReject(int day) {
		return getKey(ASK_NEW_REJECT, day);
	}

	@Override
	public long askOldFull() {
		return redisHeper.incr(getKey(ASK_OLD_FULL));
	}

	@Override
	public String askOldFull(int day) {
		return getKey(ASK_OLD_FULL, day);
	}

	@Override
	public long askOldM() {
		return redisHeper.incr(getKey(ASK_OLD_M));
	}

	@Override
	public String askOldM(int day) {
		return getKey(ASK_OLD_M, day);
	}

	@Override
	public long askOldNo() {
		return redisHeper.incr(getKey(ASK_OLD_NO));
	}

	@Override
	public String askOldNo(int day) {
		return getKey(ASK_OLD_NO, day);
	}

	@Override
	public long askOldReject() {
		return redisHeper.incr(getKey(ASK_OLD_REJECT));
	}

	@Override
	public String askOldReject(int day) {
		return getKey(ASK_OLD_REJECT, day);
	}

	@Override
	public Map<String, Object> getALLData(int day, String time) {
		Map<String, Object> map = Maps.newHashMap();

		Map<String, Response<String>> valueMap = redisHeper.getIncr(askAll(day), askNew(day), askNewError(day),
				askOld(day), askOldError(day), importNew(day), importOld(day), importNewError(day), importOldError(day),
				importjxError(day), askReject(day), contract(day), contractError(day), askNewFull(day), askNewM(day),
				askNewNo(day), askNewReject(day), askOldFull(day), askOldM(day), askOldNo(day), askOldReject(day));

		map.put(ASK_ALL, valueMap.get(askAll(day)).get());

		map.put(ASK_NEW, valueMap.get(askNew(day)).get());

		map.put(ASK_NEW_ERROR, valueMap.get(askNewError(day)).get());

		map.put(ASK_OLD, valueMap.get(askOld(day)).get());
		map.put(ASK_OLD_ERROR, valueMap.get(askOldError(day)).get());
		map.put(IMPORT_NEW, valueMap.get(importNew(day)).get());
		map.put(IMPORT_OLD, valueMap.get(importOld(day)).get());
		map.put(IMPORT_NEW_ERROR, valueMap.get(importNewError(day)).get());
		map.put(IMPORT_OLD_ERROR, valueMap.get(importOldError(day)).get());
		map.put(IMPORT_JX_ERROR, valueMap.get(importjxError(day)).get());
		map.put(ASK_REJECT, valueMap.get(askReject(day)).get());
		map.put(CONTRACT_GET, valueMap.get(contract(day)).get());
		map.put(CONTRACT_ERROR, valueMap.get(contractError(day)).get());

		map.put(ASK_NEW_FULL, valueMap.get(askNewFull(day)).get());
		map.put(ASK_NEW_M, valueMap.get(askNewM(day)).get());
		map.put(ASK_NEW_NO, valueMap.get(askNewNo(day)).get());
		map.put(ASK_NEW_REJECT, valueMap.get(askNewReject(day)).get());

		map.put(ASK_OLD_FULL, valueMap.get(askOldFull(day)).get());
		map.put(ASK_OLD_M, valueMap.get(askOldM(day)).get());
		map.put(ASK_OLD_NO, valueMap.get(askOldNo(day)).get());
		map.put(ASK_OLD_REJECT, valueMap.get(askOldReject(day)).get());
		map.put("date", time);

		return map;
	}

    @Override
    public void importSync(int num) {
        Calendar calendar = Calendar.getInstance();
        String key = getKey(IMPORT_SYNC, calendar.get(Calendar.DAY_OF_MONTH));
        int total = NumberUtils.toInt(RedisHeper.getRedisHeper().get(key), 0);
        RedisHeper.getRedisHeper().set(key, total+num);
    }

    @Override
    public String askImportSync(int day) {
        return RedisHeper.getRedisHeper().get(getKey(IMPORT_SYNC, day));
    }

}
