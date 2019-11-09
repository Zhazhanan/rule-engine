package org.huha.ruleengine.demo.faapplication.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 进件申请实体类
 *
 * @author WangKun
 * @date 2019-01-29 11:44
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaApplicationDTO implements Serializable {

    private static final long serialVersionUID = -1915665296313187400L;

    private String id;

    @ApiModelProperty(value = "进件编号")
    private String applicationNumber;

    @ApiModelProperty(value = "申请类型")
    private String applicationType;

    @ApiModelProperty(value = "进件状态")
    private String auditStatus;

    @ApiModelProperty(value = "进件录入时间/申请时间")
    private Timestamp intoCreateTime;

    @ApiModelProperty(value = "家人是否知晓", example = "1")
    private String familyIsKnown;

    @ApiModelProperty(value = "客户来源")
    private String customerSource;

    @ApiModelProperty(value = "客户渠道")
    private String customerChannel;

    @ApiModelProperty(value = "审批结论")
    private String decision;

    @ApiModelProperty(value = "审批额度")
    private String amountLimit;

    @ApiModelProperty(value = "借款类型")
    private String loanType;

    @ApiModelProperty(value = "借款用途", example = "1")
    private String loanPurpose;

    @ApiModelProperty(value = "借款用途明细")
    private String loanPurposeDetail;

    @ApiModelProperty(value = "申请期限")
    private java.math.BigDecimal applyPeriod;

    @ApiModelProperty(value = "货币类型")
    private String prodMoneyType;

    @ApiModelProperty(value = "综合费率")
    private String prodServiceRate;

    @ApiModelProperty(value = "是否加急", example = "1")
    private String isEms;

    @ApiModelProperty(value = "加急费率")
    private String prodUrgentRate;

    @ApiModelProperty(value = "申请最低借款额(元)")
    private java.math.BigDecimal minAppAmount;

    @ApiModelProperty(value = "申请最高借款额(元)")
    private java.math.BigDecimal maxAppAmount;

    @ApiModelProperty(value = "月还款能力(元)")
    private java.math.BigDecimal monthPayment;

    @ApiModelProperty(value = "人工判定时间（判定日期）")
    private Date actionDate;

    @ApiModelProperty(value = "欺诈告警（C清白,S疑似,H高危)）", example = "S")
    private String fraudAlert;

    @ApiModelProperty(value = "触发规则")
    private String triggeredRules;

    @ApiModelProperty(value = "欺诈分数")
    private Short fraudScore;

    @ApiModelProperty(value = "人工判定结果(K-确认欺诈;S-疑似欺诈;F-清白)", example = "S")
    private String actionTaken;

    @ApiModelProperty(value = "欺诈种类编码")
    private String fraudType;

    @ApiModelProperty(value = "决定原因编码")
    private String decisionReason;

    @ApiModelProperty(value = "分配组")
    private String fraudAlertTeam;

    /**
     * 分配组成员ID
     */
    @ApiModelProperty(value = "分配组成员ID")
    private String fraudAlertUserId;

    /**
     * 审批产品编码(信审后)
     */
    @ApiModelProperty(value = "审批产品编码(信审后)")
    private String apprProductType;

    /**
     * 审批产品名称(信审后)
     */
    @ApiModelProperty(value = "审批产品名称(信审后)")
    private String apprProductName;

    /**
     * 通过代码
     */
    @ApiModelProperty(value = "通过代码")
    private String agreeCode;

    /**
     * 拒绝原因（信审、门店、系统自动拒绝）
     */
    @ApiModelProperty(value = "拒绝原因（信审、门店、系统自动拒绝）")
    private String refuseReason;

    /**
     * 信审开始日期
     */
    @ApiModelProperty(value = "信审开始日期")
    private Timestamp creditStart;

    /**
     * 信审结束日期
     */
    @ApiModelProperty(value = "信审结束日期")
    private Timestamp creditEnd;

    /**
     * 进入信审时间
     */
    @ApiModelProperty(value = "进入信审时间")
    private Timestamp intoCreditTime;

    /**
     * 调查员id
     */
    @ApiModelProperty(value = "调查员id")
    private Long userId;

    /**
     * 初审姓名
     */
    @ApiModelProperty(value = "初审姓名")
    private String creUserId;

    /**
     * 终审ID
     */
    @ApiModelProperty(value = "终审ID")
    private String creditLastUserCode;

    /**
     * 终审姓名
     */
    @ApiModelProperty(value = "终审姓名")
    private String creditLastUserName;

    /**
     * 终审审核备注
     */
    @ApiModelProperty(value = "终审审核备注")
    private String auditConclusion;

    /**
     * 调用类型
     */
    @ApiModelProperty(value = "调用类型")
    private String callType;

    /**
     * 业务流程节点
     */
    @ApiModelProperty(value = "业务流程节点（1-质检提交;2-信审;3-复议;4-提报）")
    private String flowSource;

    /**
     * 案件号码
     */
    @ApiModelProperty(value = "案件号码")
    private String caseNumber;

    /**
     * 匹配数量
     */
    @ApiModelProperty(value = "匹配数量")
    private Long matchingNumber;

    /**
     * 警告分钟
     */
    @ApiModelProperty(value = "警告分钟")
    private Short warningMinutes;

    /**
     * 过期分钟
     */
    @ApiModelProperty(value = "过期分钟")
    private Short expiryMinutes;

    /**
     * 客户经理ID
     */
    @ApiModelProperty(value = "客户经理ID")
    private String customerManagerCode;

    /**
     * 客户经理姓名
     */
    @ApiModelProperty(value = "客户经理姓名")
    private String customerManagerName;

    /**
     * 客户经理手机号
     */
    @ApiModelProperty(value = "客户经理手机号")
    private String customerManagerMobilePhone;

    /**
     * 客服ID
     */
    @ApiModelProperty(value = "客服ID")
    private String customerServiceCode;

    /**
     * 客服姓名
     */
    @ApiModelProperty(value = "客服姓名")
    private String customerServiceName;

    /**
     * 客服手机号
     */
    @ApiModelProperty(value = "客服手机号")
    private String customerServiceMobilePhone;

    /**
     * 进件门店ID
     */
    @ApiModelProperty(value = "进件门店ID")
    private String acceptStoreCode;

    /**
     * 管理门店ID
     */
    @ApiModelProperty(value = "管理门店ID")
    private String manageStoreCode;

    /**
     * 进件门店名称
     */
    @ApiModelProperty(value = "进件门店名称")
    private String acceptStoreName;

    /**
     * 管理门店名称
     */
    @ApiModelProperty(value = "管理门店名称")
    private String manageStoreName;

    /**
     * 进件门店所在省份
     */
    @ApiModelProperty(value = "进件门店所在省份")
    private String province;

    /**
     * 门店进件备注
     */
    @ApiModelProperty(value = "门店进件备注")
    private String remark;

    /**
     * 门店等级
     */
    @ApiModelProperty(value = "门店等级")
    private String storeLevel;

    /**
     * 还款方式
     */
    @ApiModelProperty(value = "还款方式")
    private String prodRepaymentWay;

    /**
     * 大区机构编码
     */
    @ApiModelProperty(value = "大区机构编码")
    private String areaOrgid;

    /**
     * 人员分配策略版本
     */
    @ApiModelProperty(value = "人员分配策略版本")
    private String assignmentVersion;

    /**
     * 决定时间
     */
    @ApiModelProperty(value = "决定时间")
    private Date decisionDate;

    /**
     * 数据有效性(1：有效，0：无效)
     */
    @ApiModelProperty(value = "数据有效性(1：有效，0：无效)", example = "1")
    private String validateState;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Long createBy;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private Long modifyBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Timestamp modifyTime;

    /**
     * 申请产品编码
     */
    @ApiModelProperty(value = "申请产品编码")
    private String appProductType;

    /**
     * 申请产品名称
     */
    @ApiModelProperty(value = "申请产品名称")
    private String appProductName;

    @ApiModelProperty(value = "是否锁定")
    private Boolean haveLock;

    @ApiModelProperty(value = "分配时间")
    private Timestamp assignTime;


}