package cn.zeroeden.permissioncontrol.controller;

import cn.zeroeden.permissioncontrol.model.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Zero
 * @time: 2022/12/30
 * @description:
 */


@RestController
@RequestMapping("/two")
public class TwoController {

    @GetMapping(name = "TwoController-ONE")
    public Result getMethod(){
        System.out.println("--------[TwoController]getMethod()方法");
        return Result.SUCCESS("[TwoController]-getMethod()");
    }


    @PostMapping(name = "TwoController-TWO")
    public Result postMethod(){
        System.out.println("--------[TwoController]postMethod()方法");
        return Result.SUCCESS("[TwoController]-postMethod()");
    }

    @PutMapping(name = "TwoController-THREE")
    public Result putMethod(){
        System.out.println("--------[TwoController]putMethod()方法");
        return Result.SUCCESS("[TwoController]-putMethod()");
    }

    @DeleteMapping(name = "TwoController-FOUR")
    public Result deleteMethod(){
        System.out.println("--------[TwoController]deleteMethod()方法");
        return Result.SUCCESS("[TwoController]-deleteMethod()");
    }
}
