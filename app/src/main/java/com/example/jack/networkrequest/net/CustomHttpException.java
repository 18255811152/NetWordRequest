package com.example.jack.networkrequest.net;

import android.content.Context;

import com.example.jack.view.R;
import com.example.jack.view.base.BaseApplication;


/**
 * 403：无权限访问
 * 404：请求链接或页面找不到
 * 500：代码错误
 * 502：运维服务问题
 * 600：session过期
 * 601：需要输入安全密码
 * 602：服务忙
 * 603：域名不存在
 * 604：临时域名过期
 * 605：ip被限制
 * 606：被强制踢出
 * 607：站点维护
 * 608：重复请求
 * 609：站点不存在
 * Created by benson on 18-1-8.
 */

public class CustomHttpException extends RuntimeException {

    public CustomHttpException(int code) {
        throw new RuntimeException(getCustomHttpException(code));
    }

    private String getCustomHttpException(int code) {
        Context context = BaseApplication.getContext();
        switch (code) {
            case 403:
                return context.getString(R.string.http_code_400);
            case 404:
                return context.getString(R.string.http_code_404);
            case 500:
                return context.getString(R.string.http_code_500);
            case 502:
                return context.getString(R.string.http_code_502);
            case 600:
//                ActivityUtil.gotoLogin();
//                RxBus.get().post(ConstantValue.EVENT_TYPE_LOGOUT, "logout");
//                DataCenter.getInstance().setLogin(false);
                return context.getString(R.string.http_code_600);
            case 601:
                return context.getString(R.string.http_code_601);
            case 602:
                return context.getString(R.string.http_code_602);
            case 603:
                return context.getString(R.string.http_code_603);
            case 604:
                return context.getString(R.string.http_code_604);
            case 605:
                return context.getString(R.string.http_code_605);
            case 606:
//                ActivityUtil.logout();
                return context.getString(R.string.http_code_606);
            case 607:
                return context.getString(R.string.http_code_607);
            case 608:
                return context.getString(R.string.http_code_608);
            case 609:
                return context.getString(R.string.http_code_609);
                default:
        }
        return context.getString(R.string.http_code_default, code);
    }


}
