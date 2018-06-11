package com.example.jack.networkrequest.mvp.service;

import com.example.jack.view.bean.TestServiceIp;
import com.example.jack.view.net.HttpResult;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    ////?ip=63.223.108.42
    @GET("/service/getIpInfo.php")
    rx.Observable<HttpResult<TestServiceIp>> getServiceIp(
            @Query("ip") String ip);
}
