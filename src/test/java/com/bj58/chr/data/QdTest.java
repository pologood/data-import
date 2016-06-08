package com.bj58.chr.data;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.bj58.chr.data.service.IAsyncQdSourceService;
import com.bj58.chr.data.service.impl.AsyncQdSourceService;

public class QdTest {
    
    private IAsyncQdSourceService async = new AsyncQdSourceService();
    
    @Test
    public void test(){
        async.startAsync();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
