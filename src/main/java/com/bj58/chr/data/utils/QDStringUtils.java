package com.bj58.chr.data.utils;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 
 * @author sunlingao@58.com
 * @date 2016年4月19日
 */
public final class QDStringUtils extends StringUtils{
    
    private final static String[] PARSE_DATE = new String[] { "yyyy-MM", "yyyy/MM","yyyy.MM", "yyyy/MM/dd",
        "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm:ss" };
    private static Map<Integer,String> salaryMap = new HashMap<Integer,String>(12);
    
    static {
        salaryMap.put(1, "0-2000");
        salaryMap.put(2, "2001-3000");
        salaryMap.put(3, "3001-4000");
        salaryMap.put(4, "4001-6000");
        salaryMap.put(5, "6001-8000");
        salaryMap.put(6, "8001-10000");
        salaryMap.put(7, "10001-15000");
        salaryMap.put(8, "15001-20000");
        salaryMap.put(9, "20001-30000");
        salaryMap.put(10, "30001-40000");
        salaryMap.put(11, "40001-50000");
        salaryMap.put(12, "50001-100000");
    }
   
    public static Object[] addStringIsNotBlank(String ...strings){
        List<String> list = new LinkedList<String>();
        for(String str:strings){
            if(!isEmpty(str)){
                list.add(str);
            }
        }
        return list.toArray();
    }
    
    public static String getSalary(String str){
        for(int key:salaryMap.keySet()){
            String params = salaryMap.get(key);
            String []arr = params.split("-");
            int min = NumberUtils.toInt(arr[0], 0);
            int max = NumberUtils.toInt(arr[1], 0);
            int now = NumberUtils.toInt(str, 0);
            if(min<= now && now <= max){
                return String.valueOf(key);
            }
        }
        return String.valueOf(13);
    } 
    
    public static long getCvTime(long time){
        return time/1000L;
    }
    
    public static Date formatDateForStr(String str){
        try {
            return DateUtils.parseDateStrictly(str, PARSE_DATE);
        } catch (ParseException e) {
        }
        return null;
    }
    
    public static boolean isMatcherStr(String str,String regex){
        if(isEmpty(str)){
            return false;
        }
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    
    public static String getImportUuid(){
        String str = UUID.randomUUID().toString();
        str = "58import"+str.substring(str.indexOf("-"));
        return str;
    }
}
