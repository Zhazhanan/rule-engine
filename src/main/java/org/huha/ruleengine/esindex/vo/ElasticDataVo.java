package org.huha.ruleengine.esindex.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.huha.ruleengine.config.ElasticEntity;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ElasticDataVo<T> {
    private String idxName;
    private ElasticEntity elasticEntity;
}
