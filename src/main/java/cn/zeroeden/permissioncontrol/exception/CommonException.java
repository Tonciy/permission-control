package cn.zeroeden.permissioncontrol.exception;

import cn.zeroeden.permissioncontrol.constant.ResultCode;

/**
 * @author: Zero
 * @time: 2022/12/28
 * @description:
 */

public class CommonException extends Exception{
    public CommonException(String context) {
        super(context);
    }

    public CommonException(ResultCode context) {
        super(context.message());
    }
}
