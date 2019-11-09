package org.huha.ruleengine.demo.faworking.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 工作信息-申请库 实体类
 *
 * @author WangKun
 * @date 2019-02-12 11:16
 **/
@ApiModel(value = "工作信息-申请库")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaWorkingDTO implements Serializable {

    private static final long serialVersionUID = 5270508401846053445L;
    @ApiModelProperty(value = "主键id")
    private Long id;

    private Long faId;

    @ApiModelProperty(value = "进件编号")
    private String applicationNumber;

    @ApiModelProperty(value = "工作单位全称")
    private String jName;

    @ApiModelProperty(value = "工作单位地址")
    private String jAddr;

    @ApiModelProperty(value = "工作单位地址所在地区编码")
    private String jAddrAreacode;

    @ApiModelProperty(value = "工作单位邮政编码")
    private String jAddrPost;

    @ApiModelProperty(value = "单位电话区号")
    private String jPhoneAreaCode;

    @ApiModelProperty(value = "单位电话")
    private String jPhone;

    @ApiModelProperty(value = "单位性质")
    private String jType;

    @ApiModelProperty(value = "工龄")
    private Integer seniority;

    @ApiModelProperty(value = "进入该单位时间")
    private String jEnterT;

    @ApiModelProperty(value = "所在部门")
    private String jDept;

    @ApiModelProperty(value = "担任职务")
    private String jPosition;

    @ApiModelProperty(value = "数据有效性(1：有效，0：无效)")
    private Integer validateState;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "修改人")
    private Long modifyBy;

    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @ApiModelProperty(value = "创建时间")
    private Long createTimeLong;

    @ApiModelProperty(value = "修改时间")
    private Timestamp modifyTime;

    @ApiModelProperty(value = "工资发放形式")
    private String jPayType;

    @ApiModelProperty(value = "每月发薪日期")
    private Integer jMonPayT;

    @ApiModelProperty(value = "工作详细地址")
    private String jDetailAddr;

    @ApiModelProperty(value = "工作单位完整名称")
    private String jFullName;

    @ApiModelProperty(value = "工作完整电话（区号-号码）")
    private String jFullPhone;

    @ApiModelProperty(value = "工作单位全称(分词)")
    private String jNameIk;

    @ApiModelProperty(value = "工作单位地址(分词)")
    private String jAddrIk;
}