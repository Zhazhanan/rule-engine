package org.huha.ruleengine.esindex;

import lombok.Getter;

/**
 * Description
 * <br> ES数据类型
 *
 * @author WangKun
 * @date 2019/11/01
 **/
@Getter
public enum EsDataTypes {
    STRING_TEXT("text"),
    STRING_KEYWORD("keyword"),
    NUMERIC_LONG("long")
    // TODO: 2019-11-01 05:40 待补充数据类型
    ;


    private String type;

    EsDataTypes(String type) {
        this.type = type;
    }
}
