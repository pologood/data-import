package com.bj58.chr.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bj58.chr.data.handler.NewHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@WebAppConfiguration
public class RuleTest {

	@Resource
	private NewHandler newHandler;

	@Test
	public void save() {
	}
	
	public static void main(String args[]){
//	    Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DAY_OF_YEAR, -8);
//        for (int i = 0; i < 8; i++) {
//            cal.add(Calendar.DAY_OF_YEAR, 1);
//            System.out.println(DateFormatUtils.format(cal, "yyyy-MM-dd"));
//        }
        
        Date date = DateUtils.addDays(Calendar.getInstance().getTime(), -1);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sf.format(date));
	}

}
