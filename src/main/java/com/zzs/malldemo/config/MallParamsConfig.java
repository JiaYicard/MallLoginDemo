package com.zzs.malldemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MallParamsConfig {
    @Value("${mall.appId}")
    public String appId;
    @Value("${mall.appAsecret}")
    public String appAsecret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppAsecret() {
        return appAsecret;
    }

    public void setAppAsecret(String appAsecret) {
        this.appAsecret = appAsecret;
    }
}
