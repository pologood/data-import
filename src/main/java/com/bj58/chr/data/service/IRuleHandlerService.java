package com.bj58.chr.data.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import com.bj58.chr.common.web.Status;
import com.bj58.chr.data.model.Rule;

import freemarker.template.TemplateException;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月7日 下午1:54:27
 * @see
 * @since
 */
public interface IRuleHandlerService {

	/**
	 * 
	 */
	void init();

	/**
	 * @param rule
	 * @return
	 */
	Status<String> handle(Rule rule);

	/**
	 * @param id
	 * @param root
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	String process(String id, Map<String, Object> root) throws TemplateException, IOException;

	/**
	 * 
	 */
	void clearTemplate();

	/**
	 * @param id
	 */
	void deleteTemplate(Serializable id);

	/**
	 * @param name
	 * @param templateSource
	 */
	void updateTemplate(String name, String templateSource);

	/**
	 * @param name
	 * @param templateSource
	 */
	void setTemplate(String name, String templateSource);

	/**
	 * @param root
	 * @param rule
	 * @return
	 */
	Status<String> handle(Map<String, Object> root, Rule rule);

}
