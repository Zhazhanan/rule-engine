package org.huha.ruleengine.demo.faapplication.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Description
 * <br> 进件列表查询条件vo
 * 查询结果信息通过ES查询
 *
 * @author lichengqiang
 * @date 2019-04-23
 **/
public class QueryParamsApplicationVO implements Serializable {

    @ApiModelProperty(value = "进件编号")
    private String applicationNumber;

    @ApiModelProperty(value = "进件时间范围")
    private List<String> intoCreateTime;

    @ApiModelProperty(value = "人工判定结果(K-确认欺诈;S-疑似欺诈;F-清白)")
    private String actionTaken;

    @ApiModelProperty(value = "警告时间")
    private List<String> warningTime;

    @ApiModelProperty(value = "过期时间")
    private List<String> expiryTime;

    @ApiModelProperty(value = "欺诈告警（C清白,S疑似,H高危)）")
    private String fraudAlert;

    @ApiModelProperty(value = "欺诈种类")
    private String fraudType;

    @ApiModelProperty(value = "决定原因")
    private String decisionReason;

    @ApiModelProperty(value = "用户")
    private List<String> userId;

    @ApiModelProperty(value = "姓名")
    private String custName;

    @ApiModelProperty(value = "证件号码")
    private String cardId;

    @ApiModelProperty(value = "电话号码")
    private String mobilePhone;

    @ApiModelProperty(value = "预警/过期 0:过期;1:预警;2:待定申请")
    private Integer warnExpired;

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public List<String> getIntoCreateTime() {
        return intoCreateTime;
    }

    public void setIntoCreateTime(List<String> intoCreateTime) {
        this.intoCreateTime = intoCreateTime;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public List<String> getWarningTime() {
        return warningTime;
    }

    public void setWarningTime(List<String> warningTime) {
        this.warningTime = warningTime;
    }

    public List<String> getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(List<String> expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getFraudAlert() {
        return fraudAlert;
    }

    public void setFraudAlert(String fraudAlert) {
        this.fraudAlert = fraudAlert;
    }

    public String getFraudType() {
        return fraudType;
    }

    public void setFraudType(String fraudType) {
        this.fraudType = fraudType;
    }

    public String getDecisionReason() {
        return decisionReason;
    }

    public void setDecisionReason(String decisionReason) {
        this.decisionReason = decisionReason;
    }

    public List<String> getUserId() {
        return userId;
    }

    public void setUserId(List<String> userId) {
        this.userId = userId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Integer getWarnExpired() {
        return warnExpired;
    }

    public void setWarnExpired(Integer warnExpired) {
        this.warnExpired = warnExpired;
    }

    /**
     * Description 构造函数
     * <br>
     *
     * @date 2019-05-18
     * @author yishiyong
     **/
    public QueryParamsApplicationVO() {
        super();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QueryParamsApplicationVO{");
        sb.append("applicationNumber='").append(applicationNumber);
        sb.append(", intoCreateTime=").append(intoCreateTime);
        sb.append(", actionTaken='").append(actionTaken).append('\'');
        sb.append(", warningTime=").append(warningTime);
        sb.append(", expiryTime=").append(expiryTime);
        sb.append(", fraudAlert='").append(fraudAlert).append('\'');
        sb.append(", fraudType='").append(fraudType).append('\'');
        sb.append(", decisionReason='").append(decisionReason).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", custName='").append(custName).append('\'');
        sb.append(", cardId='").append(cardId).append('\'');
        sb.append(", mobilePhone='").append(mobilePhone).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
