package org.huha.ruleengine.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description
 * <br> 业务编码与描述
 *
 * @author WangKun
 * @date 2019/10/29
 **/
@Getter
@AllArgsConstructor
public enum RuleEngineExceptionEnum {

    /**
     * 格式:错误编码(错误编码,错误描述)
     */
    E100001("E2001", "规则匹配异常");

    /**
     * 错误编码
     */
    private String code;
    /**
     * 错误描述
     */
    private String msg;


}
