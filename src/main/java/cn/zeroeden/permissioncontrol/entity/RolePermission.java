package cn.zeroeden.permissioncontrol.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Zero
 * @time: 2022/12/28
 * @description: 角色-权限 映射
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_role_permission")
public class RolePermission {
    private String roleId;
    private String permissionId;
}
