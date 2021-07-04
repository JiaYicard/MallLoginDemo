package com.zzs.malldemo.vo;

/**
 * @author mountain
 * @date 2020/12/25 16:27
 */
public class LoginParams {

    private String openid;

    private String session_key;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }
}
