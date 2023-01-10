package cn.zeroeden.permissioncontrol;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.zeroeden.permissioncontrol.dao")
public class PermissionControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(PermissionControlApplication.class, args);
    }

}
