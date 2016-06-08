package com.bj58.chr.data.utils;

import java.io.FileNotFoundException;

import org.springframework.util.ResourceUtils;

import com.bj58.spat.scf.client.SCFInit;

public class SCFInitHelper {
    
    public SCFInitHelper(){
        try {
            SCFInit.init(ResourceUtils.getFile("classpath:scf.config").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
}
