package org.huha.ruleengine.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description
 * <br> elasticsearch 服务异常编码与描述
 *
 * @author WangKun
 * @date 2019/10/29
 **/
@Getter
@AllArgsConstructor
public enum ElasticSearchExceptionEnum {

    /**
     * 格式:错误编码(错误编码,错误描述)
     */
    E1000("E1000", "索引创建失败"),
    E1001("E1001", "索引打开失败"),
    E1002("E1002", "索引关闭失败");

    /**
     * 错误编码
     */
    private String code;
    /**
     * 错误描述
     */
    private String msg;


}
