package com.zzs.malldemo.vo;

import com.zzs.malldemo.entity.WxWatermark;
import lombok.Data;

@Data
public class WxJSONObjectParamsVo {
    //private WxLoginReturnParams wxLoginReturnParams;

    private String openId;
    private String nickName;
    private String gender;
    private String language;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;

//    @JsonProperty(value = "watermark")
    private WxWatermark watermark;
}
