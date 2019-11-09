package org.huha.ruleengine.esindex.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * Description
 * <br> Created by WangKun on 2019/11/01
 * <br>
 **/
@Data
@NoArgsConstructor
public class IndexMapping implements Serializable {
    private static final long serialVersionUID = -739586697408946931L;
    /**
     * e.g.
     * dynamic : false
     * properties :"{\n" +
     * "  \"properties\": {\n" +
     * "    \"message\": {\n" +
     * "      \"type\": \"text\"\n" +
     * "    }\n" +
     * "  }\n" +
     * "}"
     */
    private boolean dynamic = false;
    private Map<String, Map<String, Object>> properties;
}
