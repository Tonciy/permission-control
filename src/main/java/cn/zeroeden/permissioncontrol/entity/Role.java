package cn.zeroeden.permissioncontrol.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: Zero
 * @time: 2022/12/28
 * @description: 角色
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_role")
public class Role implements Serializable {
    private String id;
    private String roleName;
    private String description;
}
