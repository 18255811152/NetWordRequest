package com.example.jack.networkrequest.mvp.moder.ServiceMode;


import com.example.jack.networkrequest.bean.TestServiceIp;
import com.example.jack.networkrequest.mvp.moder.BaseModer;
import com.example.jack.networkrequest.mvp.service.Service;
import com.example.jack.networkrequest.net.RetrofitHelper;

import rx.Subscriber;
import rx.Subscription;


public class ServiceMode extends BaseModer implements IServiceMode {
    @Override
    public Subscription getServiceId(Subscriber subscriber, String ip) {
        rx.Observable<TestServiceIp> observable = RetrofitHelper.getService(Service.class).getServiceIp(ip).map(new HttpResultFunc<>());
        return toSubscribe(observable, subscriber);
    }
}
