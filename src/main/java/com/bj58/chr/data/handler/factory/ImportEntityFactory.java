package com.bj58.chr.data.handler.factory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bj58.chr.data.handler.ImportHandler;
import com.bj58.chr.data.model.kv.KVEntity;
import com.bj58.chr.data.model.kv.KVGroup;
import com.bj58.chr.data.service.IKVEntityService;
import com.bj58.chr.data.utils.Constant;
import com.bj58.chr.data.utils.QDStringUtils;
import com.bj58.chr.data.xls.Area;
import com.bj58.chr.data.xls.CompanyType;
import com.bj58.chr.data.xls.Degrees;
import com.bj58.chr.data.xls.Industry;
import com.bj58.chr.data.xls.Job;
import com.bj58.chr.data.xls.Language;
import com.google.common.base.Joiner;

/**
 * 
 * @author sunlingao@58.com
 * @date 2016年4月19日
 */
@Component("importEntityFactory")
public class ImportEntityFactory {

	@Resource
	private IKVEntityService kVEntityService;

	public void setkVEntityService(IKVEntityService kVEntityService) {
		this.kVEntityService = kVEntityService;
	}

	private final Map<Class<?>, ImportHandler<?>> handlerMap;

	private ImportEntityFactory() {
		Map<Class<?>, ImportHandler<?>> tempMap = new HashMap<>();
		tempMap.put(Industry.class, industryHandler);
		tempMap.put(Job.class, jobHandler);
		tempMap.put(Degrees.class, degreeHandler);
		tempMap.put(Area.class, areaHandler);
		tempMap.put(CompanyType.class, companyTypeHandler);
		tempMap.put(Language.class, languageTypeHandler);
		handlerMap = Collections.unmodifiableMap(tempMap);
	}

	private final ImportHandler<Job> jobHandler = new AbstractImportHandler<Job>() {
		@Override
		public KVEntity convert(KVGroup group, Job job) {
			KVEntity kvEntity = new KVEntity();
			kvEntity.setkVGroup(group);
			kvEntity.setQdId(job.getQdId());
			kvEntity.setQdName(job.getQdName());
			kvEntity.setYcId(Joiner.on(Constant.SPLIT_SYMBOL).join(
                    QDStringUtils.addStringIsNotBlank(job.getYcThreeId(), job.getYcSecondId(), job.getYcFristId())));
			kvEntity.setYcName(Joiner.on(Constant.SPLIT_SYMBOL).join(
                    QDStringUtils.addStringIsNotBlank(job.getYcThreeName(), job.getYcSecondName(), job.getYcFirstName())));
			return kvEntity;
		}
	};

	private final ImportHandler<Degrees> degreeHandler = new AbstractImportHandler<Degrees>() {
		@Override
		public KVEntity convert(KVGroup group, Degrees degees) {
			KVEntity kvEntity = new KVEntity();
			kvEntity.setkVGroup(group);
			kvEntity.setQdId(degees.getQdId());
			kvEntity.setQdName(degees.getQdName());
			kvEntity.setYcId(degees.getYcId());
			kvEntity.setYcName(degees.getYcName());
			return kvEntity;
		}
	};

	private final ImportHandler<Area> areaHandler = new AbstractImportHandler<Area>() {
		@Override
		public KVEntity convert(KVGroup group, Area area) {
			KVEntity kvEntity = new KVEntity();
			kvEntity.setkVGroup(group);
			kvEntity.setQdId(area.getQdId());
			kvEntity.setQdName(area.getQdName());
			kvEntity.setYcId(Joiner.on(Constant.SPLIT_SYMBOL).join(
					QDStringUtils.addStringIsNotBlank(area.getYcDistrictId(), area.getYcCityId(), area.getYcProvId())));
			kvEntity.setYcName(Joiner.on(Constant.SPLIT_SYMBOL).join(QDStringUtils
					.addStringIsNotBlank(area.getYcDistrictName(), area.getYcCityName(), area.getYcProvName())));
			return kvEntity;
		}
	};

	private final ImportHandler<CompanyType> companyTypeHandler = new AbstractImportHandler<CompanyType>() {
		@Override
		public KVEntity convert(KVGroup group, CompanyType type) {
			KVEntity kvEntity = new KVEntity();
			kvEntity.setkVGroup(group);
			kvEntity.setQdId(type.getQdId());
			kvEntity.setQdName(type.getQdName());
			kvEntity.setYcId(type.getYcId());
			kvEntity.setYcName(type.getYcName());
			return kvEntity;
		}
	};

	private final ImportHandler<Industry> industryHandler = new AbstractImportHandler<Industry>() {
		@Override
		public KVEntity convert(KVGroup group, Industry industry) {
			KVEntity kvEntity = new KVEntity();
			kvEntity.setkVGroup(group);
			kvEntity.setQdId(industry.getQdId());
			kvEntity.setQdName(industry.getQdName());
			kvEntity.setYcId(Joiner.on(Constant.SPLIT_SYMBOL).join(industry.getYcId2(),industry.getYcId()));
			kvEntity.setYcName(Joiner.on(Constant.SPLIT_SYMBOL).join(industry.getYcName2(),industry.getYcName()));
			return kvEntity;
		}
	};
	
	  private final ImportHandler<Language> languageTypeHandler = new AbstractImportHandler<Language>() {
	      @Override
	      public KVEntity convert(KVGroup group, Language language) {
	          KVEntity kvEntity = new KVEntity();
	          kvEntity.setkVGroup(group);
	          kvEntity.setQdId(language.getQdId());
	          kvEntity.setQdName(language.getQdName());
	          kvEntity.setYcId(Joiner.on(Constant.SPLIT_SYMBOL).join(QDStringUtils.addStringIsNotBlank(language.getYcListId(),language.getYcRootId())));
	          kvEntity.setYcName(Joiner.on(Constant.SPLIT_SYMBOL).join(QDStringUtils.addStringIsNotBlank(language.getYcListName(),language.getYcRootName())));
	          return kvEntity;
	      }
	 };

	public abstract class AbstractImportHandler<T> implements ImportHandler<T> {
		public abstract KVEntity convert(KVGroup group, T t);

		public void execute(KVGroup group, T t) {
			saveOrUpdate(convert(group, t));
		}

		public void saveOrUpdate(KVEntity entity) {
			kVEntityService.saveOrUpdate(entity);
		}

	}

	@SuppressWarnings("unchecked")
	public <T> ImportHandler<T> getImportFactory(Class<T> cla) {
		return (ImportHandler<T>) handlerMap.get(cla);
	}
}
