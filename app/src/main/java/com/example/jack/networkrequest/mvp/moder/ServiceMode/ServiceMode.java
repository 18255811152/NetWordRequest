package com.example.jack.networkrequest.mvp.moder.ServiceMode;

import com.example.jack.view.bean.TestServiceIp;
import com.example.jack.view.mvp.moder.BaseModer;
import com.example.jack.view.mvp.service.Service;
import com.example.jack.view.net.RetrofitHelper;

import rx.Subscriber;
import rx.Subscription;


public class ServiceMode extends BaseModer implements IServiceMode {
    @Override
    public Subscription getServiceId(Subscriber subscriber, String ip) {
        rx.Observable<TestServiceIp> observable = RetrofitHelper.getService(Service.class).getServiceIp(ip).map(new HttpResultFunc<TestServiceIp>());
        return toSubscribe(observable, subscriber);
    }
}
