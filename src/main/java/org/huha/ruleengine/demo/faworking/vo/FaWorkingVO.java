package org.huha.ruleengine.demo.faworking.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 工作信息-申请库VO类
 * @author lichengqiang
 * @date 2019-03-01
 **/
@ApiModel(value = "工作信息-申请库VO")
public class FaWorkingVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "进件编号")
    private String applicationNumber;
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
    @ApiModelProperty(value = "进入该单位时间")
    private String jEnterT;
    @ApiModelProperty(value = "所在部门")
    private String jDept;
    @ApiModelProperty(value = "担任职务")
    private String jPosition;
    @ApiModelProperty(value = "数据有效性(1：有效，0：无效)")
    private String validateState;
    @ApiModelProperty(value = "工作详细地址")
    private String jDetailAddr;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getjName() {
        return jName;
    }

    public void setjName(String jName) {
        this.jName = jName;
    }

    public String getjAddr() {
        return jAddr;
    }

    public void setjAddr(String jAddr) {
        this.jAddr = jAddr;
    }

    public String getjPhone() {
        return jPhone;
    }

    public void setjPhone(String jPhone) {
        this.jPhone = jPhone;
    }

    public String getjType() {
        return jType;
    }

    public void setjType(String jType) {
        this.jType = jType;
    }

    public String getjEnterT() {
        return jEnterT;
    }

    public void setjEnterT(String jEnterT) {
        this.jEnterT = jEnterT;
    }

    public String getjDept() {
        return jDept;
    }

    public void setjDept(String jDept) {
        this.jDept = jDept;
    }

    public String getjPosition() {
        return jPosition;
    }

    public void setjPosition(String jPosition) {
        this.jPosition = jPosition;
    }

    public String getValidateState() {
        return validateState;
    }

    public void setValidateState(String validateState) {
        this.validateState = validateState;
    }

    public String getjPhoneAreaCode() {
        return jPhoneAreaCode;
    }

    public void setjPhoneAreaCode(String jPhoneAreaCode) {
        this.jPhoneAreaCode = jPhoneAreaCode;
    }

    public String getjDetailAddr() {
        return jDetailAddr;
    }

    public void setjDetailAddr(String jDetailAddr) {
        this.jDetailAddr = jDetailAddr;
    }

    @Override
    public String toString() {
        return "FaWorkingVO{" +
                "id=" + id +
                ", applicationNumber='" + applicationNumber + '\'' +
                ", jName='" + jName + '\'' +
                ", jAddr='" + jAddr + '\'' +
                ", jPhoneAreaCode='" + jPhoneAreaCode + '\'' +
                ", jPhone='" + jPhone + '\'' +
                ", jType='" + jType + '\'' +
                ", jEnterT='" + jEnterT + '\'' +
                ", jDept='" + jDept + '\'' +
                ", jPosition='" + jPosition + '\'' +
                ", validateState='" + validateState + '\'' +
                ", jDetailAddr='" + jDetailAddr + '\'' +
                '}';
    }


}