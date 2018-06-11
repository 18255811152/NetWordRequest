package com.example.jack.networkrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jack.networkrequest.base.IBaseView;
import com.example.jack.networkrequest.mvp.preserter.ServicePreserter;
import com.example.jack.networkrequest.mvp.view.IIPView;

public class MainActivity extends AppCompatActivity implements IIPView {
    private ServicePreserter servicePreserter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        servicePreserter = new ServicePreserter(this, this);
        servicePreserter.getServiceIp("63.223.108.42");
    }

    @Override
    public void onResult(Object o) {


    }
}
