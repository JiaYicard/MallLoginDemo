package com.zzs.malldemo.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzs.malldemo.config.MallParamsConfig;
import com.zzs.malldemo.vo.LoginParams;
import com.zzs.malldemo.vo.WxJSONObjectParamsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;

/**
 * @author mountain
 * @date 2020/12/25 11:28
 */
@RestController
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MallParamsConfig mallParamsConfig;

    @PostMapping("/user")
    public LoginParams login(String jsCode) throws IOException {
        String uri = "https://api.weixin.qq.com/sns/jscode2session?appid=" + mallParamsConfig.getAppId() + "&secret=" + mallParamsConfig.getAppAsecret() + "&js_code=%s&grant_type=authorization_code";
        ResponseEntity<String> respString = restTemplate.getForEntity(String.format(uri, jsCode), String.class);
        String body = respString.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        LoginParams loginParams = objectMapper.readValue(body, LoginParams.class);
        return loginParams;
    }

    /**
     * 解密
     * @param encryptedData 加密数据
     * @param iv    加密算法的初始向量
     * @param sessionKey    会话密钥
     * @return
     * @throws Exception
     */
    @GetMapping("/decodeUserInfo")
    public WxJSONObjectParamsVo decodeUserInfo(String encryptedData, String iv, String sessionKey) throws Exception {
        encryptedData = encryptedData.replaceAll(" ", "+");
        System.out.println(encryptedData);
        sessionKey = sessionKey.replaceAll(" ", "+");
        System.out.println(sessionKey);
        String jsonStr = new String("");
        WxJSONObjectParamsVo wxJSONObjectParamsVo = null;
        try {
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] encryptedByte = base64Decoder.decodeBuffer(encryptedData);
            byte[] sessionKeyByte = base64Decoder.decodeBuffer(sessionKey);
            byte[] ivByte = base64Decoder.decodeBuffer(iv);

            SecretKeySpec keySpec = new SecretKeySpec(sessionKeyByte, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivByte);

            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
            byte[] doFinal = cipher.doFinal(encryptedByte);
            jsonStr = new String(doFinal);
            wxJSONObjectParamsVo = JSON.parseObject(jsonStr, WxJSONObjectParamsVo.class);
            System.out.println("wxJSONObjectParamsVo" + wxJSONObjectParamsVo);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return wxJSONObjectParamsVo;
    }
}
