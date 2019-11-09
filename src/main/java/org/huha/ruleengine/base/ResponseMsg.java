package org.huha.ruleengine.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.huha.ruleengine.common.GlobalResponseStatusEnum;

import java.io.Serializable;

/**
 * Description
 * <br> 全局响应体
 *
 * @author WangKun
 * @date 2019/10/31
 **/
@Data
@NoArgsConstructor
public class ResponseMsg<T> implements Serializable {

    private static final long serialVersionUID = -8715395618433573339L;

    private String retCode = GlobalResponseStatusEnum.SUCCESS.getCode();

    private String retDesc = GlobalResponseStatusEnum.SUCCESS.getName();

    private T responseBody;

    public ResponseMsg(T responseBody) {
        this.responseBody = responseBody;
    }

    public ResponseMsg(String retCode, String retDesc) {
        this.retCode = retCode;
        this.retDesc = retDesc;
    }

    public ResponseMsg(String retCode, String retDesc, T responseBody) {
        this.retCode = retCode;
        this.retDesc = retDesc;
        this.responseBody = responseBody;
    }

    public static ResponseMsg getSuccessResponseMsg(String retDesc) {
        return new ResponseMsg(GlobalResponseStatusEnum.SUCCESS.getCode(), retDesc);
    }
}
