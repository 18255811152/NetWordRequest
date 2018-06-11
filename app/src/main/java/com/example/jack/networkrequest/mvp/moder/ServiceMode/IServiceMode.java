package com.example.jack.networkrequest.mvp.moder.ServiceMode;

import rx.Subscriber;
import rx.Subscription;

public interface IServiceMode {
    Subscription getServiceId(Subscriber subscriber, String ip);
}
