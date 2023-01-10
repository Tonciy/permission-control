package cn.zeroeden.permissioncontrol.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Zero
 * @time: 2022/12/28
 * @description: 用户-角色映射
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_user_role")
public class UserRole {
    private String userId;
    private String roleId;
}
