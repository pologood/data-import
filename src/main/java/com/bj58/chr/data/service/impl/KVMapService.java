package com.bj58.chr.data.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bj58.chr.common.web.page.ParamUtils;
import com.bj58.chr.data.model.kv.KVEntity;
import com.bj58.chr.data.model.kv.KVName;
import com.bj58.chr.data.service.IKVEntityService;
import com.bj58.chr.data.service.IKVMapService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 下午10:38:40
 * @see
 * @since
 */
@Service
public class KVMapService implements IKVMapService {

	@Resource
	private IKVEntityService kVEntityService;
	
	@Override
	public KVName getIdValue(int groupId, int qdId) {
		return get(groupId,
				kVEntityService.list(ParamUtils.createParam().add("kVGroupId", groupId).add("qdId", qdId).get()));

	}
	
	@Override
	public KVName getNameValue(int groupId, int qdName) {
		return get(groupId,
				kVEntityService.list(ParamUtils.createParam().add("kVGroupId", groupId).add("qdName", qdName).get()));
	}

	private KVName get(int groupId, List<KVEntity> kVEntitys) {
		if (kVEntitys != null && kVEntitys.size() == 1) {
			KVEntity kVEntity = kVEntitys.get(0);
			return new KVName(kVEntity.getYcId(), kVEntity.getYcName());
		}
		if (groupId > 0) {
			return get(0,
					kVEntityService.list(ParamUtils.createParam().add("kVGroupId", groupId).add("root", 1).get()));
		}
		return null;
	}

}
