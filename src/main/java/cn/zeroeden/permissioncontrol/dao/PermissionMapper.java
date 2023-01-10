package cn.zeroeden.permissioncontrol.dao;

import cn.zeroeden.permissioncontrol.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
}
