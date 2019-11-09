package org.huha.ruleengine.esindex.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Description
 * <br> Created by WangKun on 2019/11/04
 * <br>
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryVo {

    private String idxName;
    private String className;
    private Map<String, Map<String, Object>> query;
}
