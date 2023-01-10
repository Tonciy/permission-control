package cn.zeroeden.permissioncontrol.service;

import cn.zeroeden.permissioncontrol.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {

    /**
     * 根据用户名查询用户(包含角色/权限)
     * @param username
     * @return
     */
    User findByUsername(String username);
}
