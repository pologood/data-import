package com.bj58.chr.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bj58.chr.common.JSONUtils;
import com.bj58.chr.data.model.CVContract;
import com.bj58.chr.data.service.IContractService;
import com.bj58.chr.data.utils.QDStringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@WebAppConfiguration
public class UpdateContractTest {
    
    private final String FILEPATH="d:\\contract.json";
    
    
    @Resource
    private IContractService contractService;
    /**
     * 
     * @author:sunlingao@58.com
     * @desc:
     * @date:2016年4月13日
     * void
     */
    @Test
    public void testUpdateContract() throws Exception{
        String content = FileUtils.readFileToString(new File(FILEPATH));
        CVContract qdContract = JSONUtils.readValue(content, CVContract.class);
        
        System.out.println(qdContract.getData().getNewEmail());
        System.out.println(qdContract.getData().getNewName());
        System.out.println(qdContract.getData().getNewMobile());
        
        qdContract.setCoid("000a9be5-e1a2-4444-b7a5-82967e75cf4d");
        contractService.saveOrUpdate(qdContract);
    }
    
    @Test
    public void getContract(){
        CVContract contract = contractService.findByCoId("000a9be5-e1a2-4444-b7a5-82967e75cf4d");
        if(contract!=null){
            System.out.println(JSONUtils.writeValue(contract));
        }
    }
    
    @Test
    public void findContract(){
        final String fileStr= "D:\\04270430.txt";
        BufferedReader br = null;
        int count= 0,may = 0,four =0 ,isnull = 0;
        try{
        FileReader fr = new FileReader(new File(fileStr));
        br = new BufferedReader(fr, 1024*5);
        String tmp = "";
        while( (tmp = br.readLine())!=null ){
            CVContract contract = contractService.findByCoId(tmp);
            if(contract == null){
                isnull++;
            }else{
                if(QDStringUtils.getCvTime(contract.getCreatedTime().getTime())<=1462031999){
                    four++;
                }else{
                    may++;
                }
                System.out.println(contract.toString());
            }
            count++;
        }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (IOException e) {}
        }
        System.out.println(String.format("may total is %s,four total is %s,isnull is %s,count total is %s", may,four,isnull,count));
    }

}
