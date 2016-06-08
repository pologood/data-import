package com.bj58.chr.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bj58.chr.common.ConstantUtils;
import com.bj58.chr.common.JSONUtils;
import com.bj58.chr.data.esb.message.CallBackMessage;
import com.bj58.chr.data.model.CVContent;
import com.bj58.chr.data.model.qdcv.CVModel;
import com.bj58.chr.data.model.yccv.JobTypeForExper;
import com.bj58.chr.data.model.yccv.RegionalismBo;

import org.apache.commons.codec.binary.Base64;

/**
 * BSON转换实体类
 * @author sunlingao@58.com
 * @date 2016年4月5日
 */
public class ConvertTest {
    
    private final String FilePath="d:\\content.json";
    
    public CVModel Test() throws Exception{
        String str = FileUtils.readFileToString(new File(FilePath));
//      content.setContent(getMatcherStr(str,"NumberLong\\((\\d+)\\)"));
//      CVModel cvModel = JSONUtils.readValue(content.getContent(), CVModel.class);
//      String jsonStr = JSONUtils.writeValue(cvModel);
      
      String debase64 = URLDecoder.decode(str, ConstantUtils.CHARASET);
      debase64 = new String(Base64.decodeBase64(debase64.replaceAll("\\s", "+")), ConstantUtils.CHARASET);
      CVModel cvModel = JSONUtils.readValue(debase64, CVModel.class);
      return cvModel;
    }
    
    @Test
    public void convertTest() throws Exception{
//        CVContent content = new CVContent();
        String str = FileUtils.readFileToString(new File(FilePath));
//        content.setContent(getMatcherStr(str,"NumberLong\\((\\d+)\\)"));
//        CVModel cvModel = JSONUtils.readValue(content.getContent(), CVModel.class);
//        String jsonStr = JSONUtils.writeValue(cvModel);
        
        String debase64 = URLDecoder.decode(str, ConstantUtils.CHARASET);
        debase64 = new String(Base64.decodeBase64(debase64.replaceAll("\\s", "+")), ConstantUtils.CHARASET);
        CVModel cvModel = JSONUtils.readValue(debase64, CVModel.class);
        System.out.println(JSONUtils.writeValue(cvModel));
    }
    
    @Test
    public void convertMessage(){
        CallBackMessage message = new CallBackMessage();
        message.setCvid("5717670157861b683d4a565a");
        message.setEmail("61669789@qq.com");
        message.setMobile("13333333333");
        message.setUid("8af0b0e400671757fb7aaa59i");
        message.setUname("梁");
        message.setPwd("o5xjp7");
        JobTypeForExper exper = new JobTypeForExper();
        exper.setBigId("1001");exper.setBigName("计算机/互联网/通信/电子");exper.setCategoryId("1002");exper.setCategoryName("计算机软件");
        exper.setJobName("高级软件工程师");exper.setJobNameId("1004");
        message.setJob(exper);
        RegionalismBo region = new RegionalismBo();
        region.setProvId("16");region.setProvName("江苏");region.setCityId("169");region.setCityName("南京");
        message.setRegion(region);
        System.out.println(JSONUtils.writeValue(message));
    }
    
    @Test
    public void TestMatcherStr(){
        String str = "\"politics_stat\" : NumberLong(2),\"birthday\" : NumberLong(633801600)";
//        String str = "\"politics_stat\" : NumberLong(2),";
        Pattern pattern = Pattern.compile("NumberLong\\((\\d+)\\)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            String  findStr = matcher.group(1);
            str = str.replaceFirst("NumberLong\\(\\d+\\)", findStr);
        }
        System.out.println(str);
    }
    
    public static String getMatcherStr(String str,String regex){
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            String  findStr = matcher.group(1);
            str = str.replaceFirst(regex, findStr);
        }
        return str;
    }
    
    @Test
    public void findContract(){
        Map<String,Integer> map = new HashMap<String,Integer>();
        final String fileStr= "D:\\04270430.txt";
        try{
        FileReader fr = new FileReader(new File(fileStr));
        BufferedReader br = new BufferedReader(fr, 1024*5);
        int count = 0;
        String tmp = "";
        while( (tmp = br.readLine())!=null ){
            System.out.println(tmp);
            count++;
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
