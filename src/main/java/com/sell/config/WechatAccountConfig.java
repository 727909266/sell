package com.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by huhaoran on 2018/12/10 0010.
 */

@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
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
}
