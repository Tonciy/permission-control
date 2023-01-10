package cn.zeroeden.permissioncontrol.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: Zero
 * @time: 2022/12/28
 * @description: 权限
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_permission")
public class Permission implements Serializable {
    private String id;
    private String permissionName;
    private String description;
    private String apiIdentify;
    private String apiMethodType;
    private String apiUrl;
}

