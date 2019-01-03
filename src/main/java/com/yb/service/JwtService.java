package com.yb.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * Description:Jwt生成解析服务
 * author yangbiao
 * date 2019/1/2
 */
public class JwtService {

    private String jwtSecret;

    private String iss;

    private long expired;

    public JwtService(String jwtSecret, String iss, long expired) {
        this.jwtSecret = jwtSecret;
        this.iss = iss;
        this.expired = expired;
    }

    /**
     * 生成jwtToken信息
     * @return
     */
    public String createToken() {
        String jwtToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, DatatypeConverter.parseBase64Binary(jwtSecret))
                .setExpiration(new Date(System.currentTimeMillis() + expired))
                .setNotBefore(new Date(System.currentTimeMillis()))
                .setIssuer(iss)
                .compact();
        return jwtToken;
    }

    /**
     * 解析jwtToken信息
     * @param jwtToken
     * @return
     */
    public Claims parseToken(String jwtToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
                    .requireIssuer(iss)
                    .parseClaimsJws(jwtToken)
                    .getBody();
            return claims;
        } catch (ExpiredJwtException e) {
            return null;
        }
    }
}
