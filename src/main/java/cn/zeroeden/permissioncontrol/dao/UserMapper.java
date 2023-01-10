package cn.zeroeden.permissioncontrol.dao;

import cn.zeroeden.permissioncontrol.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: Zero
 * @time: 2022/12/28
 * @description:
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
