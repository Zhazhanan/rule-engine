package org.huha.ruleengine.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description
 * <br> Created by WangKun on 2019/10/30
 * <br>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ElasticEntity<T> {

    private String id;
    private T data;
}
