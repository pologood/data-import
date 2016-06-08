package com.bj58.chr.data.handler.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import com.bj58.chr.data.handler.SyncHandler;
import com.bj58.chr.data.model.yccv.CvInfoBo;
import com.bj58.chr.data.service.IConfigService;
import com.bj58.chr.data.service.ICvInfoBoService;
import com.bj58.chr.data.service.ISeekerUserService;
import com.bj58.chr.data.utils.QDStringUtils;

@Service("syncCvHandler")
public class SyncCvHandler implements SyncHandler<CvInfoBo> {

	@Resource
	private ISeekerUserService userService;

	@Resource
	private IConfigService configService;

	@Resource
	private ICvInfoBoService cvInfoBoService;

	private final static long D365 = 365 * 24 * 60 * 60;

	@Override
	public UserStatusEnum getUserStatus(CvInfoBo t) {
		if (t == null || StringUtils.isEmpty(t.getMobile()))
			return null;
		Map user = userService.getUserIsExistForMobile(t.getMobile(), t.getInfoId());
		if (user == null)
			return UserStatusEnum.NULL;
		long regTime = NumberUtils.toLong(String.valueOf(user.get("regTime")), 0);
		long time = NumberUtils.toLong(String.valueOf(user.get("lLogin")), 0);
		UserStatusEnum status = null;
		long now = QDStringUtils.getCvTime(System.currentTimeMillis());

		if (now - time > D365) {
			if (now - regTime > D365) {
				status = UserStatusEnum.ALLXP;
			} else {
				status = UserStatusEnum.LOGINXP;
			}
		} else {
			status = UserStatusEnum.NOTXP;
		}
		status.setUid(String.valueOf(user.get("_id")));
		return status;
	}

	@Override
	public CvInfoBo update(CvInfoBo t) {
//		 final int cvCount = 5;
//		 int count = cvInfoBoService.getCvCountForUid(t.getUid());
//		 /**如果简历大于上限 更新操作*/
//		 if(count>=cvCount){
//		 return null;
//		 }else{
//		 return t;
//		 }
		 return t;
	}

}
