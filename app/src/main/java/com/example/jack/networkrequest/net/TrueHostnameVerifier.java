package com.example.jack.networkrequest.net;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;


public class TrueHostnameVerifier implements HostnameVerifier {
    public String domain;
                       /*
                        * 关于这个接口的说明，官方有文档描述：
                        * This is an extended verification option that implementers can provide.
                        * It is to be used during a handshake if the URL's hostname does not match the
                        * peer's identification hostname.
                        *
                        * 使用HTTPDNS后URL里设置的hostname不是远程的主机名(如:m.taobao.com)，与证书颁发的域不匹配，
                        * Android HttpsURLConnection提供了回调接口让用户来处理这种定制化场景。
                        * 在确认HTTPDNS返回的源站IP与Session携带的IP信息一致后，您可以在回调方法中将待验证域名替换为原来的真实域名进行验证。
                        *
                        */

    public TrueHostnameVerifier(String domain) {
        this.domain = domain;
    }
    public TrueHostnameVerifier() {
    }

    @Override
    public boolean verify(String hostname, SSLSession session) {
//        if(TextUtils.isEmpty(domain)) {
//            domain = DataCenter.getInstance().getDomain();
//        }
        return HttpsURLConnection.getDefaultHostnameVerifier().verify(domain, session);
    }
}
