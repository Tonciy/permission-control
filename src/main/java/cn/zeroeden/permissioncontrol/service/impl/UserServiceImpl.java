package cn.zeroeden.permissioncontrol.service.impl;

import cn.zeroeden.permissioncontrol.dao.*;
import cn.zeroeden.permissioncontrol.entity.*;
import cn.zeroeden.permissioncontrol.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Zero
 * @time: 2022/12/30
 * @description:
 */


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public User findByUsername(String username) {
        // 查询用户基本信息
        LambdaQueryWrapper<User> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(User::getUsername, username);
        User user = userMapper.selectOne(wrapper1);
        if(user != null){
            // 查询用户对应角色信息
            LambdaQueryWrapper<UserRole> wrapper2 = new LambdaQueryWrapper<>();
            wrapper2.eq(UserRole::getUserId, user.getId());
            UserRole userRole = userRoleMapper.selectOne(wrapper2);
            LambdaQueryWrapper<Role> wrapper3 = new LambdaQueryWrapper<>();
            wrapper3.eq(Role::getId, userRole.getRoleId());
            Role role = roleMapper.selectOne(wrapper3);
            if(role != null){
                user.setRole(role);
                // 查询用户对应权限信息
                LambdaQueryWrapper<RolePermission> wrapper4 = new LambdaQueryWrapper<>();
                wrapper4.eq(RolePermission::getRoleId, role.getId());
                List<RolePermission> rolePermissions = rolePermissionMapper.selectList(wrapper4);
                List<Permission> permissions = new ArrayList<>();
                for (RolePermission rolePermission : rolePermissions) {
                    Permission permission = permissionMapper.selectById(rolePermission.getPermissionId());
                    permissions.add(permission);
                }
                user.setPermissions(permissions);
            }
        }
        return user;


    }
}

