package cn.zeroeden.permissioncontrol.controller;

import cn.zeroeden.permissioncontrol.model.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Zero
 * @time: 2022/12/30
 * @description:
 */

@RestController
@RequestMapping("/one")
public class OneController {

    @GetMapping(name = "OneController-ONE")
    public Result getMethod(){
        System.out.println("--------[OneController]getMethod()方法");
        return Result.SUCCESS("[OneController]-getMethod()");
    }


    @PostMapping(name = "OneController-TWO")
    public Result postMethod(){
        System.out.println("--------[OneController]postMethod()方法");
        return Result.SUCCESS("[OneController]-postMethod()");
    }

    @PutMapping(name = "OneController-THREE")
    public Result putMethod(){
        System.out.println("--------[OneController]putMethod()方法");
        return Result.SUCCESS("[OneController]-putMethod()");
    }

    @DeleteMapping(name = "OneController-FOUR")
    public Result deleteMethod(){
        System.out.println("--------[OneController]deleteMethod()方法");
        return Result.SUCCESS("[OneController]-deleteMethod()");
    }
}

