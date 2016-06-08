package com.bj58.chr.data.service.impl;

import java.io.Serializable;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bj58.chr.cuser.api.constants.CuserEnumApi;
import com.bj58.chr.cuser.api.contract.login.LoginRequest;
import com.bj58.chr.cuser.api.contract.login.LoginResponse;
import com.bj58.chr.cuser.api.contract.register.RegisterRequest;
import com.bj58.chr.cuser.api.contract.register.RegisterResponse;
import com.bj58.chr.cuser.api.service.CuserLoginService;
import com.bj58.chr.cuser.api.service.CuserModifyService;
import com.bj58.chr.cuser.api.service.CuserRegisterService;
import com.bj58.chr.data.cuser.contract.CUser;
import com.bj58.chr.data.model.CvIdCoId;
import com.bj58.chr.data.model.SeekerUser;
import com.bj58.chr.data.service.ISeekerUserService;
import com.bj58.chr.data.service.ISeekersCallBackService;
import com.bj58.chr.data.utils.KeyUtils;
import com.bj58.spat.scf.client.proxy.builder.ProxyFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author sunlingao@58.com
 * @date 2016年4月8日
 */
@Service
public class SeekersCallBackService implements ISeekersCallBackService {

    private Logger logger = Logger.getLogger(SeekersCallBackService.class);

    private CuserRegisterService cuserRegisterService = ProxyFactory.create(
            CuserRegisterService.class, "tcp://cuserapi/CuserRegisterServiceImpl");
    private CuserLoginService cuserLoginService = ProxyFactory.create(CuserLoginService.class,
            "tcp://cuserapi/CuserLoginServiceImpl");
    private CuserModifyService cuserModifyService = ProxyFactory.create(CuserModifyService.class,
            "tcp://cuserapi/CuserModifyServiceImpl");
    @Resource
    private ISeekerUserService seekerUserService;

    @Override
    public Serializable createSeekers(CvIdCoId cvCo) {
        RegisterRequest request = getRegisterRequestForUser("", KeyUtils.getRandom());
        RegisterResponse response = null;
        try {
            response = cuserRegisterService.registerByEmail(request);
        } catch (Exception e) {
            logger.error("createSeekers", e);
            return null;
        }
        if (response.getReturnCode() == CuserEnumApi.ReturnCode.SUCCESS.getReturnCode()) {
            return getId(response, request, cvCo);
        }
        return null;
    }

    @Override
    public Serializable createSeekersForEmail(String email, CvIdCoId cvCo) {
        if (StringUtils.isEmpty(email)) {
            throw new NullPointerException("email is null");
        }
        RegisterRequest request = getRegisterRequestForUser("", KeyUtils.getRandom());
        request.getCuser().setEmail(email);
        RegisterResponse response = null;
        try {
            response = cuserRegisterService.registerByEmail(request);
        } catch (Exception e) {
            logger.error("createSeekersForEmail", e);
            return null;
        }
        if (response.getReturnCode() == CuserEnumApi.ReturnCode.SUCCESS.getReturnCode()) {
            return getId(response, request, cvCo);
        }
        return null;
    }

    @Override
    public Serializable createSeekersForMobile(String mobile, CvIdCoId cvCo) {
        if (StringUtils.isEmpty(mobile)) {
            throw new NullPointerException("mobile is null");
        }
        RegisterRequest request = getRegisterRequestForUser("", KeyUtils.getRandom());
        request.getCuser().setMobile(mobile);
        RegisterResponse response = null;
        try {
            response = cuserRegisterService.registerByMobile(request);
        } catch (Exception e) {
            logger.error("createSeekersForMobile", e);
            return null;
        }
        if (response.getReturnCode() == CuserEnumApi.ReturnCode.SUCCESS.getReturnCode()) {
            return getId(response, request, cvCo);
        }
        return null;
    }

    @Override
    public void updateSeekersLink(String uid, String email, String mobile) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("email", email);
        params.put("mobile", mobile);
        params.put("accountStatus", "0");
        try {
            cuserModifyService.updateByMap(uid, params);
        } catch (Exception e) {
            logger.error("updateSeekersLink", e);
        }
    }

    private RegisterRequest getRegisterRequestForUser(String name, String pwd) {
        RegisterRequest request = new RegisterRequest();
        CUser user = new CUser();
        user.setSource(12);
        user.setPw(pwd);
        user.setuName(name);
        request.setCuser(user);
        request.setContext(new ObjectMapper().createObjectNode().put("deviceID", "").toString());
        return request;
    }
    
    @Override
    public boolean getUserIsExistForMobile(String mobile,String coid){
        LoginRequest request=new LoginRequest();
        request.setFrom(CuserEnumApi.LoginFrom.M_MOBILE.getFrom());
        request.setMobile(mobile);
        LoginResponse response = null;
        try{
        response=cuserLoginService.existMobile(request);
        }catch(Exception e){
            logger.error(String.format("getUserIsExist mobile is %s", mobile), e);
        }
        if(response!=null && response.getReturnCode()==CuserEnumApi.ReturnCode.SUCCESS.getReturnCode()){
            logger.info(String.format("=========getUserForMobile mobile is %s coid is %s =====", mobile,coid));
            return true;
        }else{
            return false;
        }
    }

    /**
     * 
     * @author:sunlingao@58.com
     * @desc:获取生成用户主键,保存明文密码
     * @date:2016年4月9日
     * @param response
     * @param request
     * @param cvCo
     * @return String
     */
    private String getId(RegisterResponse response, RegisterRequest request, CvIdCoId cvCo) {
        if (response == null) {
            return "";
        }
        logger.info(String.format("create user uid is %s,pwd is %s", response.getContext()
                .getString("id"), request.getCuser().getPw()));
        seekerUserService.save(new SeekerUser(response.getContext().getString("id"), request
                .getCuser().getPw(), cvCo.getCoId()));
        return response.getContext().getString("id");
    }
    
}
