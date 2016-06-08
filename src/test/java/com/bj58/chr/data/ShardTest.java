package com.bj58.chr.data;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bj58.chr.common.web.page.ParamUtils;
import com.bj58.chr.data.model.CvIdCoId;
import com.bj58.chr.data.service.ICvIdCoIdService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月4日 下午4:20:06
 * @see
 * @since
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@WebAppConfiguration
public class ShardTest {

	@Resource
	private ICvIdCoIdService cvIdCoIdService;

	static CvIdCoId cv;

	static {
		cv = new CvIdCoId();
		cv.setCvId("ttttt");
		cv.setCoId("admin");
	}

	@Test
	public void find() {
		System.out.println(cvIdCoIdService.findById(cv));
	}

	@Test
	public void save() {
		cvIdCoIdService.save(cv);
	}

	@Test
	public void update() {
		cvIdCoIdService.update(cv);
	}

	@Test
	public void delete() {
		cvIdCoIdService.remove(cv);
	}

	@Test
	public void list() {
		cvIdCoIdService.list(ParamUtils.createParam().add("cv", cv).get());
	}

}
