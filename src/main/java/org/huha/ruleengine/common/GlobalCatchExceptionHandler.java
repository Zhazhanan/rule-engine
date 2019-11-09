package org.huha.ruleengine.common;

import org.huha.ruleengine.base.ResponseMsg;
import org.huha.ruleengine.exception.RuleEngineException;
import org.huha.ruleengine.exception.RuleEngineExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Description
 * <br> 全局异常处理
 *
 * @author WangKun
 * @date 2019/10/29
 **/
@RestControllerAdvice
public class GlobalCatchExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalCatchExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public <T> ResponseEntity<ResponseMsg<T>> handleGlobalException(Exception ex) {
        LOGGER.error("handleGlobalException::ex = {}", ex);
        ResponseMsg responseMsg = new ResponseMsg<T>();
        responseMsg.setRetDesc(GlobalResponseStatusEnum.ERROR.getName());
        responseMsg.setRetCode(GlobalResponseStatusEnum.ERROR.getCode());
        ResponseEntity responseEntity = new ResponseEntity<ResponseMsg<T>>(responseMsg, HttpStatus.OK);
        return responseEntity;
    }

    @ExceptionHandler(RuleEngineException.class)
    public <T> ResponseEntity<ResponseMsg<T>> handleRuleEngineException(RuleEngineException ex) {
        LOGGER.error("handleRuleEngineException::ex = {}", ex);
        ResponseMsg responseMsg = new ResponseMsg<T>(RuleEngineExceptionEnum.E100001.getCode(), RuleEngineExceptionEnum.E100001.getMsg());
        return new ResponseEntity<ResponseMsg<T>>(responseMsg, HttpStatus.OK);
    }

}
