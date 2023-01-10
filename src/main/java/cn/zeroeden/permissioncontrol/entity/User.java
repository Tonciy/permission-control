package cn.zeroeden.permissioncontrol.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Zero
 * @time: 2022/12/28
 * @description: 用户
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_user")
public class User implements Serializable {

    private String id;
    private String username;
    private String password;

    @TableField(exist = false)
    private Role role;
    @TableField(exist = false)
    private List<Permission> permissions;
}

