package com.example.jack.networkrequest.base;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

public abstract class BasePreserter<T extends IBaseView> {
    protected Context mContext;
    protected T mView;
    protected List<Subscription> subList = new ArrayList<>();

    public BasePreserter(Context mContext, T view) {
        this.mContext = mContext;
        this.mView = view;
    }

    /**
     * 绑定View
     */
    public void onAttch(T view) {
        this.mView = view;

    }

    public void ondetach() {
        mView = null;
    }

    public void onDestory() {
        this.mContext = null;
        mView = null;
        clearSubscription();
    }

    void clearSubscription() {
        for (Subscription subscription : subList) {
            if (subscription != null && !subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }
}
