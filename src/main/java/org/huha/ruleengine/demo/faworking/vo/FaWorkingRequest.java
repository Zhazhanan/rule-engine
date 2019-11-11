package org.huha.ruleengine.demo.faworking.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class FaWorkingRequest implements Serializable {
    private static final long serialVersionUID = 5930243073534828839L;

    @ApiModelProperty(value = "工作单位全称")
    private String jName;
    @ApiModelProperty(value = "工作单位地址")
    private String jAddr;
    @ApiModelProperty(value = "单位电话区号")
    private String jPhoneAreaCode;
    @ApiModelProperty(value = "单位电话")
    private String jPhone;
    @ApiModelProperty(value = "单位性质")
    private String jType;
    @ApiModelProperty(value = "进入该单位时间前")
    private String jEnterTStart;
    @ApiModelProperty(value = "进入该单位时间后")
    private String jEnterTEnd;
    @ApiModelProperty(value = "所在部门")
    private String jDept;
    @ApiModelProperty(value = "担任职务")
    private String jPosition;
    @ApiModelProperty(value = "创建时间前")
    private Long createTimeStart;
    @ApiModelProperty(value = "创建时间后")
    private Long createTimeEnd;
}
