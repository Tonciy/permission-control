package cn.zeroeden.permissioncontrol.controller;

import cn.zeroeden.permissioncontrol.constant.ResultCode;
import cn.zeroeden.permissioncontrol.entity.User;
import cn.zeroeden.permissioncontrol.exception.CommonException;
import cn.zeroeden.permissioncontrol.model.Result;
import cn.zeroeden.permissioncontrol.service.UserService;
import cn.zeroeden.permissioncontrol.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author: Zero
 * @time: 2022/12/30
 * @description: 登录控制层
 */


@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private UserService userService;

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.user.prefix}")
    private String redisKeyPrefix;

    @Value("${jwt.config.ttl}")
    private Long time = 1800L;

    @PostMapping
    public Result login(String username,  String password) throws CommonException {
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            throw new CommonException(ResultCode.REQUEST_PARARMETER_MISS);
        }
        User user = userService.findByUsername(username);
        if(user == null){
            // 不存在此用户,登录失败
            return new Result(ResultCode.USERNAME_PASSWORD_ERROR);
        }else{
            // 比对密码
            if(password.equals(user.getPassword())){
                // 登录成功,存储当前用户到Redis里(设置存活时间) 签发token
                redisTemplate.opsForValue().set(redisKeyPrefix + user.getId(), user, time, TimeUnit.SECONDS);
                String token = jwtUtils.createJwt(user.getId(), user.getUsername(), null);
                return Result.SUCCESS(token);
            }else{
                // 密码错误
                return new Result(ResultCode.USERNAME_PASSWORD_ERROR);
            }
        }
    }
}
