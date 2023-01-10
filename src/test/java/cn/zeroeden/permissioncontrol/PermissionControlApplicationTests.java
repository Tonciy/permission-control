package cn.zeroeden.permissioncontrol;

import cn.zeroeden.permissioncontrol.entity.User;
import cn.zeroeden.permissioncontrol.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class PermissionControlApplicationTests {


    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        User zhangsan = userService.findByUsername("zhangsan");
        System.out.println(zhangsan);
    }

}
