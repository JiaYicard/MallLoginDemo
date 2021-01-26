package com.zzs.malldemo.vo;

import lombok.Data;

/**
 * @author mountain
 * @date 2020/12/25 16:27
 */
@Data
public class LoginParams {

    private String openid;

    private String session_key;
}
