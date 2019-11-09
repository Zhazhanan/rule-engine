package org.huha.ruleengine.demo.faapplication.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 *
 * @author xujunqi
 * @date 2019/1/28 16:15
 */
public class SearchParams<T> implements Serializable {
    @Valid
    @NotNull
    private T dto;

    public T getDto() {
        return dto;
    }

    public void setDto(T dto) {
        this.dto = dto;
    }
}
