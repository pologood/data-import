package com.bj58.chr.data.service.impl;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.bj58.chr.common.ConstantUtils;
import com.bj58.chr.common.web.Status;
import com.bj58.chr.data.model.Rule;
import com.bj58.chr.data.model.template.MongoCountMethod;
import com.bj58.chr.data.model.template.MongoQueryMethod;
import com.bj58.chr.data.service.IRuleHandlerService;
import com.bj58.chr.data.utils.NamedThreadFactory;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月7日 下午1:54:38
 * @see
 * @since
 */
@Service
public class RuleHandlerService implements IRuleHandlerService {

	private final static Logger LOG = LoggerFactory.getLogger(RuleHandlerService.class);

	private Configuration cfg;

	private StringTemplateLoader stringTemplateLoader;

	private final static ExecutorService executorService = new ThreadPoolExecutor(1, 10, 0L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>(), new NamedThreadFactory("test-thread"), new DiscardPolicy());

	@Resource(name = "cvsMongoTemplate")
	private MongoTemplate cvsMongoTemplate;

	@Resource(name = "userMongoTemplate")
	private MongoTemplate userTemplate;

	@Override
	public void setTemplate(String name, String templateSource) {
		if (stringTemplateLoader.findTemplateSource(name) == null) {
			updateTemplate(name, templateSource);
		}
	}

	@Override
	public void updateTemplate(String name, String templateSource) {
		stringTemplateLoader.putTemplate(name, templateSource);
	}

	@Override
	public void deleteTemplate(Serializable id) {
		stringTemplateLoader.deleteTemplate(String.valueOf(id));
	}

	@Override
	public void clearTemplate() {
		stringTemplateLoader.clearTemplateSource();
	}

	@Override
	@PostConstruct
	public void init() {
		cfg = new Configuration(Configuration.VERSION_2_3_24);
		stringTemplateLoader = new StringTemplateLoader();
		cfg.setTemplateLoader(stringTemplateLoader);
		cfg.setNumberFormat("#");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setSharedVariable("cvMongo", new MongoQueryMethod(cvsMongoTemplate));
		cfg.setSharedVariable("cvCount", new MongoCountMethod(cvsMongoTemplate));
		cfg.setSharedVariable("userMongo", new MongoQueryMethod(userTemplate));
		cfg.setSharedVariable("userCount", new MongoCountMethod(userTemplate));
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	@Override
	public Status<String> handle(final Map<String, Object> root, final Rule rule) {
		root.put("rule", rule);
		final String id = String.valueOf(rule.getId());
		if (rule.isTest()) {// 假如参与测试
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					String testId = "test" + id;
					updateTemplate(testId, rule.getAbtest());
					String html = process(testId, root);
					LOG.info("run result :" + html);
				}
			});
		}
		setTemplate(id, rule.getTemplate());
		String html = process(id, root);
		rule.setResult(html);
		return rule.getResult();
	}

	@Override
	public Status<String> handle(Rule rule) {
		return handle(new HashMap<String, Object>(), rule);
	}

	@Override
	public String process(String name, Map<String, Object> root) {
		StringWriter out = null;
		try {
			Template template = cfg.getTemplate(name);
			if (template != null) {
				out = new StringWriter();// 得到的是json
				template.process(root, out);
			}
			return out == null ? "" : out.getBuffer().toString();
		} catch (Exception e) {
			LOG.error("process rule error", e);
			return ConstantUtils.getException(e);
		}
	}

}
