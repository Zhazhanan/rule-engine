package org.huha.ruleengine.demo.faapplication.dto;

/**
 * 规则常量类
 *
 * @author chendongdong
 * @date 2019-02-14 17:27:01
 **/
public class RuleConstant {

    /**
     * 申请信息推送ES模块
     */
    public static final String BIZ_MODULE_TYPE = "applylibPushEsModule";

    public static final String HIST_DATA_CRITERIA_MODULE = "histDataCriteriaModule";

    /**
     * 索引结构大类(object-对象;nested-嵌套结构(数组))
     */
    public static final String OBJECT = "object";
    public static final String NESTED = "nested";
    /**
     * 字段类型
     * text-字符串<支持分词>
     */
    public static final String TEXT = "text";


    /**
     * 接口类型(A-申请库;C-欺诈库)
     */
    public final static String APPLY_BASE = "A";
    public final static String FRAUD_BASE = "C";

    /**
     * 规则类别(1-数据库字段;2-数据库值;3-申请字段值)
     */
    public final static String DATA_BASE_FIELD = "1";

    public final static String DATA_BASE_VALUE = "2";

    public final static String APPLY_FIELD_VALUE = "3";

    /**
     * 运算符
     */
    public final static String EQUAL = "=";

    public final static String NOT_EQUAL = "<>";

    public final static String GREATER_THAN = ">";

    public final static String GREATER_THAN_OR_EQUAL = ">=";

    public final static String LESS_THAN = "<";

    public final static String LESS_THAN_OR_EQUAL = "<=";

    public final static String IN = "IN";

    public final static String NOT_IN = "NOT IN";

    public final static String IS_NOT_NULL = "IS NOT NULL";


    /**
     * like模糊查询
     */
    public final static String LIKE = "LIKE";

    /**
     * partial部分匹配
     */
    public final static String PARTIAL = "PARTIAL";

    /**
     * fuzzy模糊检索
     * 模糊查询查找在模糊度中指定的最大编辑距离内的所有可能的匹配项，然后检查术语字典，以找出在索引中实际存在待检索的关键词。
     */
    public final static String FUZZY_MATCH = "FUZZY MATCH";

    public final static String NOT_FUZZY_MATCH = "NOT FUZZY MATCH";


    /**
     * 逻辑运算符
     */
    public final static String AND = "AND";

    public final static String OR = "OR";

    /**
     * 左括号
     */
    public final static String LEFT_BRACKET = "(";

    /**
     * 右括号
     */
    public final static String RIGHT_BRACKET = ")";

    /**
     * 左中括号
     */
    public final static String LEFT_SQUARE_BRACKET = "[";

    /**
     * 右中括号
     */
    public final static String RIGHT_SQUARE_BRACKET = "]";

    /**
     * 日期范围过滤字段
     */
    public final static String DATE_RANGE_FILTER_FIELD = "application.intoInputDate";

    /**
     * 是否标识
     */
    public final static String YES = "1";
    public final static String NO = "0";

    /**
     * 申请信息(类别编码)
     */
    public final static String APPLICATION = "application";

    /**
     * 申请人信息(类别编码)
     */
    public final static String APPLICANT = "applicant";

    /**
     * 预警/过期
     */
    public final static String APPLICATION_WARN_EXPIRED = "application.warnExpired";

    /**
     * 已分配用户ID
     */
    public final static String USER_ID = "userId";

    /**
     * 进件编码
     */
    public final static String APPLICATION_NUMBER = "applicationNumber";

    /**
     * 匹配类型
     */
    public final static String CATEGORY_MATCHED = "categoryMatched";

    /**
     * 建议索引分词使用ik_max_word,
     * 搜索分词器根据需要选择可以使用ik_smart
     */
    public final static String IK_MAX_WORD_ANALYZER = "ik_max_word";
    public final static String IK_SMART_ANALYZER = "ik_smart";


    /**
     * 问号
     */
    public final static String QUESTION_MARK = "?";

    /**
     * 星号
     */
    public final static String STAR_MARK = "*";

    /**
     * 英文句号
     */
    public final static String ENGLISH_FULL_STOP = ".";

    /**
     * 井号键
     */
    public final static String POUND_MARK = "#";

    /**
     * 分隔符逗号
     */
    public final static String COMMA = ",";

    /**
     * 百分号
     */
    public final static String PERCENT_MARK = "%";

    /**
     * 连字符
     */
    public final static String HYPHENS_MARK = "-";

    /**
     * 下划线
     */
    public final static String UNDER_LINE = "_";

    /**
     * 匹配标识(0-尚未匹配;1-匹配成功;2-匹配失败)
     */
    public final static String NOT_YET_MATCH = "0";
    public final static String MATCHED_SUCCESS = "1";
    public final static String MATCHED_FAILED = "2";

    /**
     * 触发规则
     */
    public final static String TRIGGERED_RULES = "triggeredRules";

    /**
     * 字段统计别名后缀
     */
    public static final String FIELD_COUNT_ALIAS_SUFFIX = "cnt";

    /**
     * 规则定义表ID
     */
    public static final String RULE_DEF_ID = "ruleDefId";


    /**
     * ID
     */
    public static final String ID = "id";

    /**
     * keyIdSet
     */
    public static final String KEY_ID_SET = "keyIdSet";

    /**
     * keyId
     */
    public static final String KEY_ID = "keyId";

    /**
     * 触碰规则明细列表
     */
    public static final String TRIGGER_RULE_LIST = "triggerRuleList";


    /**
     * 匹配进件ID
     */
    public static final String MATCHED_INTO_ID = "matchedIntoId";

    /**
     * 案件编号列表
     */
    public static final String CASE_NUMBER_LIST = "caseNumberList";


    /**
     * 人工判定结果
     */
    public static final String ACTION_TAKEN = "actionTaken";

    /**
     * 判定日期
     */
    public static final String ACTION_DATE = "actionDate";

    /**
     * 欺诈种类
     */
    public static final String FRAUD_TYPE = "fraudType";

    /**
     * 决定原因
     */
    public static final String DECISION_REASON = "decisionReason";

    /**
     * 审批额度
     */
    public static final String AMOUNT_LIMIT = "amountLimit";

    /**
     * 命中总数
     */
    public static final String TOTAL_HITS = "totalHits";

    /**
     * 元数据模式
     */
    public static final String SOURCE_MODE = "1";

    /**
     * script脚本模式
     */
    public static final String SCRIPT_MODE = "2";

    /**
     * 日期范围字段列表
     */
    public static final String DATE_RANGE_FIELDS = "ANTF_DATE_RANGE_FIELDS";


    /**
     * 操作符检索字段列表
     */
    public static final String OPERATOR_SEARCH_FIELDS = "ANTF_OPERATOR_SEARCH_FIELDS";


    /**
     * 排序
     */
    public static final String SORT = "sort";

    /**
     * 升序
     */
    public static final String ASC = "asc";

    /**
     * 降序
     */
    public static final String DESC = "desc";

    /**
     * 新增标识
     */
    public static final String INSERT = "insert";

    /**
     * 修改标识
     */
    public static final String UPDATE = "update";

    /**
     * 默认排序字段
     */
    public static final String DEFAULT_SORT_FIELD = "application.intoCreateTime";

    /**
     * 申请信息-案件编号
     */
    public static final String APPLICATION_CASE_NUMBER = "application.caseNumber";

    /**
     * 申请信息-进件编号
     */
    public static final String APPLICATION_APPLICATION_NUMBER = "application.applicationNumber";

    /**
     * 申请信息-判定结果
     */
    public static final String APPLICATION_ACTION_TAKEN = "application.actionTaken";

    /**
     * 申请信息-判定日期
     */
    public static final String APPLICATION_ACTION_DATE = "application.actionDate";


    /**
     * 申请信息-已分配用户ID
     */
    public static final String APPLICATION_USER_ID = "application.userId";


    /**
     * 申请人信息-证件号码
     */
    public static final String APPLICANT_CARD_ID = "applicant.cardId";


    /**
     * 全局参数
     */
    public static final String GLOBAL_PARAMETERS = "globalParameters";


    /**
     * 全局手机号
     */
    public static final String GLOBAL_PHONE_NUMBER = "globalPhoneMumber";

    /**
     * 全局关键词
     */
    public static final String GLOBAL_KEY_WORD = "globalKeyWord";


    /**
     * 申请人信息-出生日期
     */
    public static final String APPLICANT_BIRTHDAY = "applicant.birthday";

    /**
     * 证件号码
     */
    public static final String CARD_ID = "cardId";

    /**
     * 索引状态-初始化
     */
    public static final String INDEX_INIT_STATUS = "1";

    /**
     * 索引状态-已创建
     */
    public static final String INDEX_CREATED_STATUS = "2";

    /**
     * null值
     */
    public static final String NULL_VALUE = "null";

    /**
     * 匹配模块关系(0-不存在;1-申请库编码;2-数据库类别编码;3-等于两者)
     */
    public static final String MATCHED_MODULE_RELATION = "matchedModuleRelation";

    /**
     * 类别编码同[申请库类别编码]与[数据库类别编码]的关系
     * (0-不存在;1-申请库编码;2-数据库类别编码;3-等于两者)
     */
    public final static String NONE_CATEGORY = "0";
    public final static String CURRENT_BASE_CATEGORY = "1";
    public final static String DATA_BASE_CATEGORY = "2";
    public final static String BOTH_CATEGORY = "3";

    /**
     * 预警/过期：0-过期标识; 1-预警标识; 2-待定申请标识; 3-已判定申请
     */
    public static final int EXPIRED_FLAG_0 = 0;
    public static final int WARN_FLAG_1 = 1;
    public static final int PENDING_APPLY_FLAG_2 = 2;
    public static final int DECIDED_APPLY_FLAG_3 = 3;

    /**
     * 规则模板化变量名称
     */
    public static final String DATA_LIST = "dataList";
    public static final String DATA = "data";
    public static final String APPLY_JSON = "applyJson";
    public static final String START_DATE_RANGE = "startDateRange";
    public static final String END_DATE_RANGE = "endDateRange";


    /**
     * 排除记录特殊处理字段-住宅电话/其他电话/单位电话/联系电话
     */
    public static final String WORK_INFOS = "workInfos";
    public static final String CONTACT_INFOS = "contactInfos";
    public static final String TEL_PHONE = "telphone";
    public static final String FULL_TEL_PHONE = "fullTelphone";
    public static final String OTHER_TELEPHONE = "otherTelephone";
    public static final String FULL_OTHER_TELEPHONE = "fullOtherTelephone";
    public static final String J_PHONE = "jPhone";
    public static final String J_FULL_PHONE = "jFullPhone";
    public static final String CON_PHONE = "conPhone";
    public static final String CON_FULL_PHONE = "conFullPhone";

    /**
     * 触发规则匹配模块
     */
    public static final String CURRENT_FIELD_LIST = "currentFieldList";
    public static final String DATA_BASE_FIELD_LIST = "dataBaseFieldList";


}
