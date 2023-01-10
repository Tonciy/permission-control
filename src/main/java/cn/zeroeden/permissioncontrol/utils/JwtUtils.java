package cn.zeroeden.permissioncontrol.utils;

import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.SignatureException;
import java.util.Date;
import java.util.Map;

/**
 * @author: Zero
 * @time: 2022/12/28
 * @description:
 */
@Data
@ConfigurationProperties("jwt.config")
@Component
public class JwtUtils {
    /**
     * 加密私钥
     */
    private String key;
    /**
     * 有限时间，单位-秒
     */
    private Long ttl;

    /**
     * 生成token
     * @param id 存储的用户id
     * @param name 存储的用户名
     * @param map  存储的额外数据
     * @return token
     */
    public String createJwt(String id, String name, Map<String, Object> map) {
        // 计算失效时间
        long now = System.currentTimeMillis();
        long exp = now + ((ttl != null && ttl.longValue() > 0) ? ttl * 1000 : 0);
        // 创建
        JwtBuilder builder = Jwts.builder().setId(id) // 存储id
                .setSubject(name)                       // 存储
                .setIssuedAt(new Date())         // 发布时间
                .signWith(SignatureAlgorithm.HS256, key);  // 设置加密算法以及私钥
        // builder.setClaims(map); 这样好像会直接取代原本已经赋值的id，subject那些值（也就是在上面赋值的全没有了）
        if(map != null ){
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                builder.claim(entry.getKey(),entry.getValue());
            }
        }


        builder.setExpiration(new Date(exp));
        return builder.compact();
    }

    /**
     * 解析token
     * @param token token
     * @return 装载数据的实体
     */
    public Claims parseJwt(String token)throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException{
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims;
    }
}
