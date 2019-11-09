package org.huha.ruleengine.demo.faapplication.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.huha.ruleengine.demo.faapplication.dto.FaApplicationDTO;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

/**
 * 进件申请VO
 *
 * @author WangKun
 * @date 2019-01-29 11:00
 **/
@ApiModel(value = "进件申请VO")
public class FaApplicationVO implements Serializable {

    private static final long serialVersionUID = 8384694685715855680L;

    public FaApplicationVO() {
    }

    public FaApplicationVO(FaApplicationDTO faApplicationDTO) {
        if (null != faApplicationDTO) {
            this.id = faApplicationDTO.getId();
            this.applicationNumber = faApplicationDTO.getApplicationNumber();
            this.intoCreateTime = faApplicationDTO.getIntoCreateTime();
            this.createTime = faApplicationDTO.getCreateTime();
            this.actionDate = faApplicationDTO.getActionDate();
            this.fraudAlert = faApplicationDTO.getFraudAlert();
            this.triggeredRules = faApplicationDTO.getTriggeredRules();
            this.fraudScore = faApplicationDTO.getFraudScore();
            this.actionTaken = faApplicationDTO.getActionTaken();
            this.fraudType = faApplicationDTO.getFraudType();
            this.decisionReason = faApplicationDTO.getDecisionReason();
            this.decision = faApplicationDTO.getDecision();
            this.amountLimit = faApplicationDTO.getAmountLimit();
            this.userId = faApplicationDTO.getUserId();
            this.fraudAlertTeam = faApplicationDTO.getFraudAlertTeam();
            this.fraudAlertUserId = faApplicationDTO.getFraudAlertUserId();
            this.customerSource = faApplicationDTO.getCustomerSource();
            this.appProductType = faApplicationDTO.getAppProductType();
            this.apprProductType = faApplicationDTO.getApprProductType();
            this.loanType = faApplicationDTO.getLoanType();
            this.auditStatus = faApplicationDTO.getAuditStatus();
            this.agreeCode = faApplicationDTO.getAgreeCode();
            this.refuseReason = faApplicationDTO.getRefuseReason();
            this.intoCreditTime = faApplicationDTO.getIntoCreditTime();
            this.creditStart = faApplicationDTO.getCreditStart();
            this.creditEnd = faApplicationDTO.getCreditEnd();
            this.creUserId = faApplicationDTO.getCreUserId();
            this.creditLastUserCode = faApplicationDTO.getCreditLastUserCode();
            this.creditLastUserName = faApplicationDTO.getCreditLastUserName();
            this.auditConclusion = faApplicationDTO.getAuditConclusion();
            this.manageStoreCode = faApplicationDTO.getManageStoreCode();
            this.acceptStoreCode = faApplicationDTO.getAcceptStoreCode();

            if (null != faApplicationDTO.getCaseNumber()) {
                caseNumberList = new ArrayList<>();
                // 切割字符串，list+map 数据格式存储，便于前端处理
                String[] split = StringUtils.split(faApplicationDTO.getCaseNumber(), ",");
                for (int i = 0; i < split.length; i++) {
                    Map<String, String> map = new HashMap(1);
                    map.put("caseNumber", split[i]);
                    caseNumberList.add(map);
                }
            }
            this.callType = faApplicationDTO.getCallType();
            this.customerManagerCode = faApplicationDTO.getCustomerManagerCode();
            this.customerServiceCode = faApplicationDTO.getCustomerServiceCode();
            this.customerManagerName = faApplicationDTO.getCustomerManagerName();
            this.customerServiceName = faApplicationDTO.getCustomerServiceName();
            this.acceptStoreName = faApplicationDTO.getAcceptStoreName();
            this.customerManagerMobilePhone = faApplicationDTO.getCustomerManagerMobilePhone();
            this.manageStoreName = faApplicationDTO.getManageStoreName();
            this.customerServiceMobilePhone = faApplicationDTO.getCustomerServiceMobilePhone();
        }
    }

    @ApiModelProperty(value = "主键id")
    private String id;
    @ApiModelProperty(value = "进件编号")
    private String applicationNumber;
    @ApiModelProperty(value = "进件录入时间/申请时间")
    private Timestamp intoCreateTime;
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;
    @ApiModelProperty(value = "人工判定时间（判定日期）")
    private Date actionDate;
    @ApiModelProperty(value = "欺诈告警（C清白,S疑似,H高危)）")
    private String fraudAlert;
    @ApiModelProperty(value = "触发规则")
    private String triggeredRules;
    @ApiModelProperty(value = "欺诈分数")
    private Short fraudScore;
    @ApiModelProperty(value = "人工判定结果(K-确认欺诈;S-疑似欺诈;F-清白)")
    private String actionTaken;
    @ApiModelProperty(value = "欺诈种类编码")
    private String fraudType;
    @ApiModelProperty(value = "决定原因编码")
    private String decisionReason;
    @ApiModelProperty(value = "审批结论")
    private String decision;
    @ApiModelProperty(value = "审批额度")
    private String amountLimit;
    @ApiModelProperty(value = "调查员id")
    private Long userId;
    @ApiModelProperty(value = "分配组")
    private String fraudAlertTeam;
    @ApiModelProperty(value = "分配组成员ID")
    private String fraudAlertUserId;
    @ApiModelProperty(value = "客户来源")
    private String customerSource;
    @ApiModelProperty(value = "申请产品名称")
    private String appProductType;
    @ApiModelProperty(value = "申请产品(信审后)")
    private String apprProductType;
    @ApiModelProperty(value = "借款类型")
    private String loanType;
    @ApiModelProperty(value = "进件状态")
    private String auditStatus;
    @ApiModelProperty(value = "通过代码")
    private String agreeCode;
    @ApiModelProperty(value = "拒绝原因（信审、门店、系统自动拒绝）")
    private String refuseReason;
    @ApiModelProperty(value = "进入信审时间")
    private Timestamp intoCreditTime;
    @ApiModelProperty(value = "信审开始日期")
    private Date creditStart;
    @ApiModelProperty(value = "信审结束日期")
    private Date creditEnd;
    @ApiModelProperty(value = "初审姓名")
    private String creUserId;
    @ApiModelProperty(value = "终审ID")
    private String creditLastUserCode;
    @ApiModelProperty(value = "终审姓名")
    private String creditLastUserName;
    @ApiModelProperty(value = "终审审核备注")
    private String auditConclusion;
    @ApiModelProperty(value = "管理门店ID")
    private String manageStoreCode;
    @ApiModelProperty(value = "进件门店ID")
    private String acceptStoreCode;
    @ApiModelProperty(value = "调用类型")
    private String callType;
    @ApiModelProperty(value = "案件号码")
    private List<Map<String, String>> caseNumberList;

    @ApiModelProperty(value = "客户经理ID")
    private String customerManagerCode;
    @ApiModelProperty(value = "客服ID")
    private String customerServiceCode;
    @ApiModelProperty(value = "客户经理姓名")
    private String customerManagerName;
    @ApiModelProperty(value = "客服姓名")
    private String customerServiceName;
    @ApiModelProperty(value = "进件门店名称")
    private String acceptStoreName;
    @ApiModelProperty(value = "客户经理手机号")
    private String customerManagerMobilePhone;
    @ApiModelProperty(value = "管理门店名称")
    private String manageStoreName;
    @ApiModelProperty(value = "客服手机号")
    private String customerServiceMobilePhone;
//    @ApiModelProperty(value = "规则定义信息")
//    private List<RuRulesDefinitionVO> ruRulesDefinitions;
    @ApiModelProperty(value = "修改人")
    private Long modifyBy;

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

    public Timestamp getCreateTime() {
        if (createTime == null) {
            return null;
        }
        return (Timestamp) createTime.clone();
    }

    public void setCreateTime(Timestamp createTime) {
        if (createTime == null) {
            this.createTime = null;
        } else {
            this.createTime = (Timestamp) createTime.clone();
        }
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

    public String getFraudAlert() {
        return fraudAlert;
    }

    public void setFraudAlert(String fraudAlert) {
        this.fraudAlert = fraudAlert;
    }

    public String getTriggeredRules() {
        return triggeredRules;
    }

    public void setTriggeredRules(String triggeredRules) {
        this.triggeredRules = triggeredRules;
    }

    public Short getFraudScore() {
        return fraudScore;
    }

    public void setFraudScore(Short fraudScore) {
        this.fraudScore = fraudScore;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
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

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getAmountLimit() {
        return amountLimit;
    }

    public void setAmountLimit(String amountLimit) {
        this.amountLimit = amountLimit;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(String customerSource) {
        this.customerSource = customerSource;
    }

    public String getAppProductType() {
        return appProductType;
    }

    public void setAppProductType(String appProductType) {
        this.appProductType = appProductType;
    }

    public String getApprProductType() {
        return apprProductType;
    }

    public void setApprProductType(String apprProductType) {
        this.apprProductType = apprProductType;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAgreeCode() {
        return agreeCode;
    }

    public void setAgreeCode(String agreeCode) {
        this.agreeCode = agreeCode;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public Timestamp getIntoCreditTime() {
        if (intoCreditTime == null) {
            return null;
        }
        return (Timestamp) intoCreditTime.clone();
    }

    public void setIntoCreditTime(Timestamp intoCreditTime) {
        if (intoCreditTime == null) {
            this.intoCreditTime = null;
        } else {
            this.intoCreditTime = (Timestamp) intoCreditTime.clone();
        }
    }

    public Date getCreditStart() {
        if (creditStart == null) {
            return null;
        }
        return (Date) creditStart.clone();
    }

    public void setCreditStart(Date creditStart) {
        if (creditStart == null) {
            this.creditStart = null;
        } else {
            this.creditStart = (Date) creditStart.clone();
        }
    }

    public Date getCreditEnd() {
        if (creditEnd == null) {
            return null;
        }
        return (Date) creditEnd.clone();
    }

    public void setCreditEnd(Date creditEnd) {
        if (creditEnd == null) {
            this.creditEnd = null;
        } else {
            this.creditEnd = (Date) creditEnd.clone();
        }
    }

    public String getCreUserId() {
        return creUserId;
    }

    public void setCreUserId(String creUserId) {
        this.creUserId = creUserId;
    }

    public String getCreditLastUserCode() {
        return creditLastUserCode;
    }

    public void setCreditLastUserCode(String creditLastUserCode) {
        this.creditLastUserCode = creditLastUserCode;
    }

    public String getCreditLastUserName() {
        return creditLastUserName;
    }

    public void setCreditLastUserName(String creditLastUserName) {
        this.creditLastUserName = creditLastUserName;
    }

    public String getAuditConclusion() {
        return auditConclusion;
    }

    public void setAuditConclusion(String auditConclusion) {
        this.auditConclusion = auditConclusion;
    }

    public String getManageStoreCode() {
        return manageStoreCode;
    }

    public void setManageStoreCode(String manageStoreCode) {
        this.manageStoreCode = manageStoreCode;
    }

    public String getAcceptStoreCode() {
        return acceptStoreCode;
    }

    public void setAcceptStoreCode(String acceptStoreCode) {
        this.acceptStoreCode = acceptStoreCode;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public List<Map<String, String>> getCaseNumberList() {
        return caseNumberList;
    }

    public void setCaseNumberList(List<Map<String, String>> caseNumberList) {
        this.caseNumberList = caseNumberList;
    }

    public String getCustomerManagerCode() {
        return customerManagerCode;
    }

    public void setCustomerManagerCode(String customerManagerCode) {
        this.customerManagerCode = customerManagerCode;
    }

    public String getCustomerServiceCode() {
        return customerServiceCode;
    }

    public void setCustomerServiceCode(String customerServiceCode) {
        this.customerServiceCode = customerServiceCode;
    }

    public String getCustomerManagerName() {
        return customerManagerName;
    }

    public void setCustomerManagerName(String customerManagerName) {
        this.customerManagerName = customerManagerName;
    }

    public String getCustomerServiceName() {
        return customerServiceName;
    }

    public void setCustomerServiceName(String customerServiceName) {
        this.customerServiceName = customerServiceName;
    }

    public String getAcceptStoreName() {
        return acceptStoreName;
    }

    public void setAcceptStoreName(String acceptStoreName) {
        this.acceptStoreName = acceptStoreName;
    }

    public String getCustomerManagerMobilePhone() {
        return customerManagerMobilePhone;
    }

    public void setCustomerManagerMobilePhone(String customerManagerMobilePhone) {
        this.customerManagerMobilePhone = customerManagerMobilePhone;
    }

    public String getManageStoreName() {
        return manageStoreName;
    }

    public void setManageStoreName(String manageStoreName) {
        this.manageStoreName = manageStoreName;
    }

    public String getCustomerServiceMobilePhone() {
        return customerServiceMobilePhone;
    }

    public void setCustomerServiceMobilePhone(String customerServiceMobilePhone) {
        this.customerServiceMobilePhone = customerServiceMobilePhone;
    }


    public Long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Override
    public String toString() {
        return "FaApplicationVO{" +
                "id=" + id +
                ", applicationNumber='" + applicationNumber + '\'' +
                ", intoCreateTime='" + intoCreateTime + '\'' +
                ", createTime=" + createTime +
                ", actionDate=" + actionDate +
                ", fraudAlert='" + fraudAlert + '\'' +
                ", triggeredRules='" + triggeredRules + '\'' +
                ", fraudScore=" + fraudScore +
                ", actionTaken='" + actionTaken + '\'' +
                ", fraudType='" + fraudType + '\'' +
                ", decisionReason='" + decisionReason + '\'' +
                ", decision='" + decision + '\'' +
                ", amountLimit='" + amountLimit + '\'' +
                ", userId=" + userId +
                ", fraudAlertTeam='" + fraudAlertTeam + '\'' +
                ", fraudAlertUserId='" + fraudAlertUserId + '\'' +
                ", customerSource='" + customerSource + '\'' +
                ", appProductType='" + appProductType + '\'' +
                ", apprProductType='" + apprProductType + '\'' +
                ", loanType='" + loanType + '\'' +
                ", auditStatus='" + auditStatus + '\'' +
                ", agreeCode='" + agreeCode + '\'' +
                ", refuseReason='" + refuseReason + '\'' +
                ", intoCreditTime='" + intoCreditTime + '\'' +
                ", creditStart=" + creditStart +
                ", creditEnd=" + creditEnd +
                ", creUserId='" + creUserId + '\'' +
                ", creditLastUserCode='" + creditLastUserCode + '\'' +
                ", creditLastUserName='" + creditLastUserName + '\'' +
                ", auditConclusion='" + auditConclusion + '\'' +
                ", manageStoreCode='" + manageStoreCode + '\'' +
                ", acceptStoreCode='" + acceptStoreCode + '\'' +
                ", callType='" + callType + '\'' +
                ", caseNumberList='" + caseNumberList + '\'' +
                ", customerManagerCode='" + customerManagerCode + '\'' +
                ", customerServiceCode='" + customerServiceCode + '\'' +
                ", customerManagerName='" + customerManagerName + '\'' +
                ", customerServiceName='" + customerServiceName + '\'' +
                ", acceptStoreName='" + acceptStoreName + '\'' +
                ", customerManagerMobilePhone='" + customerManagerMobilePhone + '\'' +
                ", manageStoreName='" + manageStoreName + '\'' +
                ", customerServiceMobilePhone='" + customerServiceMobilePhone + '\'' +
                ", modifyBy='" + modifyBy +
                '}';
    }
}