package com.zzs.malldemo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class MallParamsConfig {
    @Value("${mall.appId}")
    public String appId;
    @Value("${mall.appAsecret}")
    public String appAsecret;

}
