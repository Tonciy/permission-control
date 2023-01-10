package cn.zeroeden.permissioncontrol.interceptor;



import cn.zeroeden.permissioncontrol.constant.ResultCode;
import cn.zeroeden.permissioncontrol.entity.Permission;
import cn.zeroeden.permissioncontrol.entity.User;
import cn.zeroeden.permissioncontrol.exception.CommonException;
import cn.zeroeden.permissioncontrol.utils.JwtUtils;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;
import java.util.List;

/**
 * @author: Zero
 * @time: 2022/12/28
 * @description:
 */

public class RequestInterceptor implements HandlerInterceptor {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private JwtUtils jwtUtils;


    @Value("${redis.user.prefix}")
    private String redisKeyPrefix;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取token
        String authorization = request.getHeader("Authorization");
        // 2. 验证token  (不为null 且 开头为"Bearer ",请求的时候是以"Bearer "开头,后面再接token实际值-业界统一这样做,也不知道为啥)
        if (!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer ")) {
            String token = authorization.replace("Bearer ", "");
            Claims claims = null;
            try {
                claims = jwtUtils.parseJwt(token);
            } catch (ExpiredJwtException e) {
                e.printStackTrace();
                throw new CommonException(ResultCode.TOKEN_LOSE_EFFICACY); // token失效
            } catch (UnsupportedJwtException e) {
                e.printStackTrace();
                throw new CommonException("不支持的token");
            } catch (MalformedJwtException e) {
                e.printStackTrace();
                throw new CommonException("token解析失败");
            } catch (SignatureException e) {
                e.printStackTrace();
                throw new CommonException("token签名验证失败");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                throw new CommonException("token非法参数");
            }
            if (claims != null) {
                // 已登录
                // 从Redis中获取用户,从而获取权限信息
                User user = (User) redisTemplate.opsForValue().get(redisKeyPrefix + claims.getId());
                List<Permission> permissions = null;
                if (user != null) {
                    permissions = user.getPermissions();
                } else {
                    // Redis出问题,导致保存的已经登录的用户信息没了(注意不是登录时间失效了)
                    throw new CommonException(ResultCode.SERVER_ERROR);
                }
                // 通过注解反射获取每个API接口的唯一标识符
                //  --在这里的是唯一标识符是在Controller的方法上的@RequestMapping的name属性标明的，数据库的API也有
                //  --可以自己自定义注解接口来实现（这样获取时比较容易），使用Restful风格时推荐使用,
                //  -- 使用了Restful风格但是没有统一使用@RequestMapping的话那就根据请求类型来获取注解
                HandlerMethod h = (HandlerMethod) handler;
                //  获取接口上的@RequestMapping注解
                Object annotation = null;
                // 获取请求类型
                String method = request.getMethod().toUpperCase();
                String name = null;    // 表示目标接口处的唯一标识符
                boolean pass = false; // 表示最终是否有权限访问此接口
                switch (method) {
                    case "GET":
                        annotation = h.getMethodAnnotation(GetMapping.class);
                        name = ((GetMapping) annotation).name();
                        break;
                    case "POST":
                        annotation = h.getMethodAnnotation(PostMapping.class);
                        name = ((PostMapping) annotation).name();
                        break;
                    case "DELETE":
                        annotation = h.getMethodAnnotation(DeleteMapping.class);
                        name = ((DeleteMapping) annotation).name();
                        break;
                    case "PUT":
                        annotation = h.getMethodAnnotation(PutMapping.class);
                        name = ((PutMapping) annotation).name();
                        break;
                    default:
                        throw new CommonException(ResultCode.REQUEST_METHOD_NOT_SUPPORT);
                }
                if (permissions != null && !StringUtils.isEmpty(name)) { //如需权限限定时使用开放此句即可
                    for (Permission permission : permissions) {
                        if (permission.getApiIdentify() != null && permission.getApiIdentify().equals(name)) {
                            // 具有访问权限
                            pass = true;
                            break;
                        }
                    }
                }
                if (pass) { //
                    // 表示具有访问权限
                    return true;
                } else {
                    // 无访问权限
                    throw new CommonException(ResultCode.UNAUTHORISE);
                }

            }
        }
        // 未登录/token格式不对
        throw new CommonException(ResultCode.UNAUTHENTICATED);
    }

}
