package com.example.jack.networkrequest;

/**
 * 常量
 */

public interface ConstantValue {
    /**
     * 接口失败，抛出异常
     * 页面有刷新或者加载更多，或者页面要在抛出异常处理一些事物
     */
    String EVENT_TYPE_NETWORK_EXCEPTION = "EVENT_TYPE_NETWORK_EXCEPTION";
    /**
     * 刷新我的界面用户数据
     */
    String EVENT_TYPE_REFRESH_MINE_DATA = "EVENT_TYPE_REFRESH_MINE_DATA";
}
