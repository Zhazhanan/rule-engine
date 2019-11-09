package org.huha.ruleengine.demo.faapplication.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.huha.ruleengine.demo.faapplication.dto.FaApplicationDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 进件申请列表VO
 *
 * @author WangKun
 * @date 2019-01-29 11:00
 **/
@ApiModel(value = "进件申请列表VO")
public class FaApplicationListVO implements Serializable {

    private static final long serialVersionUID = 8384694685715855680L;

    public FaApplicationListVO() {
    }

    public FaApplicationListVO(FaApplicationDTO faApplicationDTO) {
        this.id = faApplicationDTO.getId();
        this.applicationNumber = faApplicationDTO.getApplicationNumber();
        this.intoCreateTime = faApplicationDTO.getIntoCreateTime();
        this.fraudScore = faApplicationDTO.getFraudScore();
        this.matchingNumber = faApplicationDTO.getMatchingNumber();
        this.triggeredRules = faApplicationDTO.getTriggeredRules();
        this.actionTaken = faApplicationDTO.getActionTaken();
        this.fraudAlertTeam = faApplicationDTO.getFraudAlertTeam();
        this.fraudAlertUserId = faApplicationDTO.getFraudAlertUserId();
        this.decision = faApplicationDTO.getDecision();
        this.fraudAlert = faApplicationDTO.getFraudAlert();
        this.callType = faApplicationDTO.getCallType();
        this.applyPeriod = faApplicationDTO.getApplyPeriod();
        this.userId = faApplicationDTO.getUserId();
        this.caseNumber = faApplicationDTO.getCaseNumber();
        this.warningMinutes = faApplicationDTO.getWarningMinutes();
        this.expiryMinutes = faApplicationDTO.getExpiryMinutes();
        this.haveLock = faApplicationDTO.getHaveLock();
    }

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "进件编号")
    private String applicationNumber;

    @ApiModelProperty(value = "进件录入时间/申请时间")
    private Timestamp intoCreateTime;

    @ApiModelProperty(value = "欺诈分数")
    private Short fraudScore;

    @ApiModelProperty(value = "匹配数量")
    private Long matchingNumber;

    @ApiModelProperty(value = "触发规则")
    private String triggeredRules;

    @ApiModelProperty(value = "人工判定结果(K-确认欺诈;S-疑似欺诈;F-清白)")
    private String actionTaken;

    @ApiModelProperty(value = "分配组")
    private String fraudAlertTeam;

    @ApiModelProperty(value = "分配组成员ID")
    private String fraudAlertUserId;

    @ApiModelProperty(value = "审批结论")
    private String decision;

    @ApiModelProperty(value = "姓名")
    private String custName;

    @ApiModelProperty(value = "证件号码")
    private String cardId;

    @ApiModelProperty(value = "申请期限")
    private BigDecimal applyPeriod;

    @ApiModelProperty(value = "调查员id")
    private Long userId;

    @ApiModelProperty(value = "调查员名称")
    private String userName;

    @ApiModelProperty(value = "案件号码")
    private String caseNumber;

    @ApiModelProperty(value = "警告分钟")
    private Short warningMinutes;

    @ApiModelProperty(value = "过期分钟")
    private Short expiryMinutes;

    @ApiModelProperty(value = "欺诈告警（C清白,S疑似,H高危)）")
    private String fraudAlert;

    @ApiModelProperty(value = "调用类型")
    private String callType;

    @ApiModelProperty(value = "是否锁定")
    private Boolean haveLock;

    @ApiModelProperty(value = "电话号码")
    private String mobilePhone;

    @ApiModelProperty(value = "欺诈种类")
    private String fraudType;

    @ApiModelProperty(value = "决定原因")
    private String decisionReason;

    @ApiModelProperty(value = "客户经理ID")
    private String customerManagerCode;

    @ApiModelProperty(value = "进件时间-开始时间")
    private String beginTime;

    @ApiModelProperty(value = "进件时间-结束时间")
    private String endTime;

    @ApiModelProperty(value = "进件时间范围")
    private List<String> datePicker;

    @ApiModelProperty(value = "预警/过期 0:过期;1:预警;2:待定申请")
    private Integer warnExpired;

    @ApiModelProperty(value = "分配标识 1：分配给自己 0：分配给他人")
    private Integer assignee;

    @ApiModelProperty(value = "决定原因描述")
    private String reasonName;

    @ApiModelProperty(value = "人工判定时间（判定日期）")
    private Date actionDate;

    @ApiModelProperty(value = "审批额度")
    private String amountLimit;

    @ApiModelProperty(value = "全局手机号码")
    private String globalPhoneMumber;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public Timestamp getIntoCreateTime() {
        if (intoCreateTime == null) {
            return null;
        }
        return (Timestamp) intoCreateTime.clone();
    }

    public void setIntoCreateTime(Timestamp intoCreateTime) {
        if (intoCreateTime == null) {
            this.intoCreateTime = null;
        } else {
            this.intoCreateTime = (Timestamp) intoCreateTime.clone();
        }
    }

    public Short getFraudScore() {
        return fraudScore;
    }

    public void setFraudScore(Short fraudScore) {
        this.fraudScore = fraudScore;
    }

    public Long getMatchingNumber() {
        return matchingNumber;
    }

    public void setMatchingNumber(Long matchingNumber) {
        this.matchingNumber = matchingNumber;
    }

    public String getTriggeredRules() {
        return triggeredRules;
    }

    public void setTriggeredRules(String triggeredRules) {
        this.triggeredRules = triggeredRules;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public String getFraudAlertTeam() {
        return fraudAlertTeam;
    }

    public void setFraudAlertTeam(String fraudAlertTeam) {
        this.fraudAlertTeam = fraudAlertTeam;
    }

    public String getFraudAlertUserId() {
        return fraudAlertUserId;
    }

    public void setFraudAlertUserId(String fraudAlertUserId) {
        this.fraudAlertUserId = fraudAlertUserId;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getFraudAlert() {
        return fraudAlert;
    }

    public void setFraudAlert(String fraudAlert) {
        this.fraudAlert = fraudAlert;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
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

    public BigDecimal getApplyPeriod() {
        return applyPeriod;
    }

    public void setApplyPeriod(BigDecimal applyPeriod) {
        this.applyPeriod = applyPeriod;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public Short getWarningMinutes() {
        return warningMinutes;
    }

    public void setWarningMinutes(Short warningMinutes) {
        this.warningMinutes = warningMinutes;
    }

    public Short getExpiryMinutes() {
        return expiryMinutes;
    }

    public void setExpiryMinutes(Short expiryMinutes) {
        this.expiryMinutes = expiryMinutes;
    }

    public Boolean getHaveLock() {
        return haveLock;
    }

    public void setHaveLock(Boolean haveLock) {
        this.haveLock = haveLock;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
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

    public String getCustomerManagerCode() {
        return customerManagerCode;
    }

    public void setCustomerManagerCode(String customerManagerCode) {
        this.customerManagerCode = customerManagerCode;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<String> getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(List<String> datePicker) {
        this.datePicker = datePicker;
    }

    public Integer getWarnExpired() {
        return warnExpired;
    }

    public void setWarnExpired(Integer warnExpired) {
        this.warnExpired = warnExpired;
    }

    public Integer getAssignee() {
        return assignee;
    }

    public void setAssignee(Integer assignee) {
        this.assignee = assignee;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

    public Date getActionDate() {
        if (actionDate == null) {
            return null;
        }
        return (Date) actionDate.clone();
    }

    public void setActionDate(Date actionDate) {
        if (actionDate == null) {
            this.actionDate = null;
        } else {
            this.actionDate = (Date) actionDate.clone();
        }
    }

    public String getAmountLimit() {
        return amountLimit;
    }

    public void setAmountLimit(String amountLimit) {
        this.amountLimit = amountLimit;
    }

    public String getGlobalPhoneMumber() {
        return globalPhoneMumber;
    }

    public void setGlobalPhoneMumber(String globalPhoneMumber) {
        this.globalPhoneMumber = globalPhoneMumber;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FaApplicationListVO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", applicationNumber='").append(applicationNumber).append('\'');
        sb.append(", intoCreateTime=").append(intoCreateTime);
        sb.append(", fraudScore=").append(fraudScore);
        sb.append(", matchingNumber=").append(matchingNumber);
        sb.append(", triggeredRules='").append(triggeredRules).append('\'');
        sb.append(", actionTaken='").append(actionTaken).append('\'');
        sb.append(", fraudAlertTeam='").append(fraudAlertTeam).append('\'');
        sb.append(", fraudAlertUserId='").append(fraudAlertUserId).append('\'');
        sb.append(", decision='").append(decision).append('\'');
        sb.append(", custName='").append(custName).append('\'');
        sb.append(", cardId='").append(cardId).append('\'');
        sb.append(", applyPeriod=").append(applyPeriod);
        sb.append(", userId=").append(userId);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", caseNumber='").append(caseNumber).append('\'');
        sb.append(", warningMinutes=").append(warningMinutes);
        sb.append(", expiryMinutes=").append(expiryMinutes);
        sb.append(", fraudAlert='").append(fraudAlert).append('\'');
        sb.append(", callType='").append(callType).append('\'');
        sb.append(", haveLock=").append(haveLock);
        sb.append(", mobilePhone='").append(mobilePhone).append('\'');
        sb.append(", fraudType='").append(fraudType).append('\'');
        sb.append(", decisionReason='").append(decisionReason).append('\'');
        sb.append(", customerManagerCode='").append(customerManagerCode).append('\'');
        sb.append(", beginTime='").append(beginTime).append('\'');
        sb.append(", endTime='").append(endTime).append('\'');
        sb.append(", datePicker=").append(datePicker);
        sb.append(", warnExpired=").append(warnExpired);
        sb.append(", assignee=").append(assignee);
        sb.append(", reasonName=").append(reasonName);
        sb.append(", actionDate=").append(actionDate);
        sb.append(", amountLimit=").append(amountLimit);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Description
     * <br> 对象属性复制
     *
     * @param faApplicationDTO
     * @date 2019-04-03
     * @author lichengqiang
     **/
//    public static FaApplicationListVO copy(FaApplicationDTO faApplicationDTO) {
//        FaApplicationListVO faApplicationListVO = new FaApplicationListVO();
//        BeanUtils.copyProperties(faApplicationDTO, faApplicationListVO);
//        return faApplicationListVO;
//    }
}