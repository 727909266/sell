package com.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by huhaoran on 2018/12/10 0010.
 */

@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    /**
     * 开放平台id和秘钥
     */
    private String mpAppId;
    private String mpAppsecret;
    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户秘钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */

    private String keyPath;

    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;

    /**
     * 公众平台id
     */
    private String openAppId;
    /**
     * 公众平台秘钥
     */
    private String openAppSecret;

    /**
     * 微信模板id
     */
    private Map<String, String> templateId;
}
