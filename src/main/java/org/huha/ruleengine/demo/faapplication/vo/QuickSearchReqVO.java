package org.huha.ruleengine.demo.faapplication.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Map;

/**
 * Description
 * <br> 定义快搜查询请求VO类
 *
 * @author chendongdong
 * @date 2019/04/02
 **/
@ApiModel(value = "快搜查询请求VO类")
public class QuickSearchReqVO implements Serializable {


    private static final long serialVersionUID = 3494111779708282643L;

    /**
     * 接口类型(A-申请库;C-欺诈库)
     */
    @NotBlank(message = "接口类型不能为空")
    @ApiModelProperty(value = "接口类型(A-申请库;C-欺诈库)")
    private String interfaceType;

    @ApiModelProperty(value = "检索参数")
    private Map<String, Object> searchParamsMap;

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public Map<String, Object> getSearchParamsMap() {
        return searchParamsMap;
    }

    public void setSearchParamsMap(Map<String, Object> searchParamsMap) {
        this.searchParamsMap = searchParamsMap;
    }

    @Override
    public String toString() {
        return "QuickSearchReqVO{" +
                "interfaceType='" + interfaceType + '\'' +
                ", searchParamsMap=" + searchParamsMap +
                '}';
    }

    public QuickSearchReqVO() {
    }

    public QuickSearchReqVO(Map<String, Object> searchParamsMap) {
        this.searchParamsMap = searchParamsMap;
    }
}
