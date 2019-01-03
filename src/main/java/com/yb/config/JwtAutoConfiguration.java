package com.yb.config;

import com.yb.model.JwtConfig;
import com.yb.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:创建starter组件至少需要一个自动配置类,这个就是自动配置类
 * 也就是引入此starter组件依赖的时候,就会把相关的想要自动配置的bean实例
 * 化好,可以直接在引入此组件依赖的工程使用,直接@Autowired注入这里实例化的Bean即可
 * author yangbiao
 * date 2019/1/2
 */
@Configuration
@ConditionalOnClass(JwtService.class)
@EnableConfigurationProperties(JwtConfig.class)
public class JwtAutoConfiguration {

    @Autowired
    private JwtConfig jwtConfig;

    //@ConditionalOnClass，当classpath下发现该类的情况下进行自动配置。
    //@ConditionalOnMissingBean，当Spring Context中不存在该Bean时。
    //@ConditionalOnProperty(prefix = "jwt",value = "enabled",havingValue = "true")，jwt.enabled=true时

    /**
     * 配置相关的bean到容器,以便注入使用
     */
    @Bean
    @ConditionalOnMissingBean(JwtService.class)//容器没有此Bean就创建
    @ConditionalOnProperty(prefix = "jwt", value = "enabled", matchIfMissing = true)
    JwtService jwtService() {
        return new JwtService(jwtConfig.getSecret(), jwtConfig.getIss(),
                Long.valueOf(jwtConfig.getExpired()).longValue());
    }

}