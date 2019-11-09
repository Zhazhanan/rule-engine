package org.huha.ruleengine.exception;

/**
 * Description
 * <br> 规则引擎异常类
 *
 * @author WangKun
 * @date 2019/10/29
 **/
public class RuleEngineException extends RuntimeException {
    public RuleEngineException() {
    }

    public RuleEngineException(String message) {
        super(message);
    }

    public RuleEngineException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuleEngineException(Throwable cause) {
        super(cause);
    }

    public RuleEngineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
