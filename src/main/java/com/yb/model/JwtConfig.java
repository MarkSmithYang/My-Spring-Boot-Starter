package com.yb.model;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description:
 * author yangbiao
 * date 2019/1/2
 */
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
 public static final Logger log = LoggerFactory.getLogger(JwtConfig.class);

    private String iss;

    private String secret;

    private String expired;

    //如果觉得这样麻烦,可以写一个applicaton配置文件配置默认配置,那样就不会报错了,
    //但是让用的人知道问题是必要的
    public String getIss() {
        if(StringUtils.isNotBlank(iss)){
            return iss;
        }else {
            log.info("application缺少jwt.iss=为前缀的签发者配置");
            return "";
        }
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getSecret() {
        if(StringUtils.isNotBlank(secret)){
            return secret;
        }else {
            log.info("application缺少jwt.secret=为前缀的秘钥配置");
            return "";
        }
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getExpired() {
        if(StringUtils.isNotBlank(expired)){
            return expired;
        }else {
            log.info("application缺少jwt.expired=为前缀的过期时间配置");
            return "0";
        }
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

}
