package org.huha.ruleengine.esindex.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

/**
 * Description
 * <br> es索引创建请求类
 *
 * @author WangKun
 * @date 2019/11/01
 **/
@Data
@NoArgsConstructor
public class IndexVO implements Serializable {
    private static final long serialVersionUID = 515041064912032951L;

    /**
     * e.g.
     * indexName : idx_location
     */
    @NonNull
    private String indexName;
    private IndexMapping indexMapping;


}
