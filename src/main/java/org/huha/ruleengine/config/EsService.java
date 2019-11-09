package org.huha.ruleengine.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.huha.ruleengine.esindex.vo.IndexVO;
import org.huha.ruleengine.exception.ElasticSearchExceptionEnum;
import org.huha.ruleengine.exception.RuleEngineException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description
 * <br> es业务层
 *
 * @author WangKun
 * @date 2019/11/01
 **/
@Service
public class EsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsService.class);

    @Autowired
    RestHighLevelClient restHighLevelClient;

    String mapping = "{\n" +
            "    \"properties\":\n" +
            "    {\n" +
            "        \"antiToHumanInfos\":\n" +
            "        {\n" +
            "            \"type\": \"nested\",\n" +
            "            \"properties\":\n" +
            "            {\n" +
            "                \"applicationNumber\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"createBy\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"createByRoleId\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"createByRoleName\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"createTime\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"faId\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"id\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"reason\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"remark\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                }\n" +
            "            }\n" +
            "        },\n" +
            "        \"applicant\":\n" +
            "        {\n" +
            "            \"properties\":\n" +
            "            {\n" +
            "                \"afterTaxMonthlyIncome\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"age\":\n" +
            "                {\n" +
            "                    \"type\": \"long\"\n" +
            "                },\n" +
            "                \"ageOfApplicant\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"applicationNumber\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"birthday\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"cardEndT\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"cardId\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"cardStartT\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"cardTerm\":\n" +
            "                {\n" +
            "                    \"type\": \"long\"\n" +
            "                },\n" +
            "                \"cardType\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"childCount\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"currentAddr\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"currentAreacode\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"currentPost\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"custName\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"email\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"faId\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"famMonPay\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"fullOtherTelephone\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"fullTelphone\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"hDegree\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"hMonPayment\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"hasChild\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"hasTelephone\":\n" +
            "                {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"fields\":\n" +
            "                    {\n" +
            "                        \"keyword\":\n" +
            "                        {\n" +
            "                            \"type\": \"keyword\",\n" +
            "                            \"ignore_above\": 256\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n" +
            "                \"homePartner\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"homeType\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"hometownAddr\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"hometownAreacode\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"hometownPost\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"id\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"inCityYear\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"increaseRate\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"isLong\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"lnMaxCreditAmount\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"marryStatus\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"maxCreditAmount\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"mobilePhone\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"nation\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"otherEmail\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"otherTelephone\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"otherTelephoneAreaCode\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"qq\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"remainingRepaymentPeriod\":\n" +
            "                {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"fields\":\n" +
            "                    {\n" +
            "                        \"keyword\":\n" +
            "                        {\n" +
            "                            \"type\": \"keyword\",\n" +
            "                            \"ignore_above\": 256\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n" +
            "                \"sex\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"telephoneAreaCode\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"telphone\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"wechat\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                }\n" +
            "            }\n" +
            "        },\n" +
            "        \"application\":\n" +
            "        {\n" +
            "            \"properties\":\n" +
            "            {\n" +
            "                \"acceptStoreCode\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"acceptStoreName\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"actionDate\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"actionTaken\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"agreeCode\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"amountLimit\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"appProductName\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"appProductType\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"applicationNumber\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"applicationType\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"applyPeriod\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"apprProductName\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"apprProductType\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"areaOrgid\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"assignTime\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"assignmentVersion\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"auditConclusion\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"auditStatus\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"callType\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"caseNumber\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"creUserId\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"createBy\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"createTime\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"creditEnd\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"creditLastUserCode\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"creditLastUserName\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"creditStart\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"customerChannel\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"customerManagerCode\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"customerManagerMobilePhone\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"customerManagerName\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"customerServiceCode\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"customerServiceMobilePhone\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"customerServiceName\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"customerSource\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"decision\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"decisionDate\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"decisionReason\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"expiryMinutes\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"expiryTime\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"faId\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"familyIsKnown\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"flowSource\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"fraudAlert\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"fraudAlertTeam\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"fraudAlertUserId\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"fraudScore\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"fraudType\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"id\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"intoCreateTime\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"intoCreditTime\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"intoInputDate\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"isEms\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"loanPurpose\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"loanPurposeDetail\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"loanType\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"manageStoreCode\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"manageStoreName\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"matchingNumber\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"maxAppAmount\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"minAppAmount\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"modifyBy\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"modifyTime\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"monthPayment\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"prodMoneyType\":\n" +
            "                {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"fields\":\n" +
            "                    {\n" +
            "                        \"keyword\":\n" +
            "                        {\n" +
            "                            \"type\": \"keyword\",\n" +
            "                            \"ignore_above\": 256\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n" +
            "                \"prodRepaymentWay\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"prodServiceRate\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"prodUrgentRate\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"province\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"refuseReason\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"remark\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"storeLevel\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"triggeredRules\":\n" +
            "                {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"store\": true,\n" +
            "                    \"term_vector\": \"with_positions_offsets\",\n" +
            "                    \"analyzer\": \"ik_max_word\",\n" +
            "                    \"fielddata\": true\n" +
            "                },\n" +
            "                \"triggeredRulesConfirm\":\n" +
            "                {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"store\": true,\n" +
            "                    \"term_vector\": \"with_positions_offsets\",\n" +
            "                    \"analyzer\": \"ik_max_word\",\n" +
            "                    \"fielddata\": true\n" +
            "                },\n" +
            "                \"userId\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"validateState\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"warningMinutes\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"warningTime\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                }\n" +
            "            }\n" +
            "        },\n" +
            "        \"bankCardInfos\":\n" +
            "        {\n" +
            "            \"type\": \"nested\",\n" +
            "            \"properties\":\n" +
            "            {\n" +
            "                \"accountName\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"applicationNumber\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"bankCardAccount\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"bankCityAreacode\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"bankCode\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"bankName\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"bankProvAreacode\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"bankReservedPhone\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"faId\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"id\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"isLoanType\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"isReceiveType\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"subBranchName\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                }\n" +
            "            }\n" +
            "        },\n" +
            "        \"contactInfos\":\n" +
            "        {\n" +
            "            \"type\": \"nested\",\n" +
            "            \"properties\":\n" +
            "            {\n" +
            "                \"applicationNumber\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"conCompany\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"conFullCompanyName\":\n" +
            "                {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"store\": true,\n" +
            "                    \"term_vector\": \"with_positions_offsets\",\n" +
            "                    \"analyzer\": \"ik_max_word\",\n" +
            "                    \"fielddata\": true\n" +
            "                },\n" +
            "                \"conFullPhone\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"conHomeAddr\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"conJobDetailAddr\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"conName\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"conPhone\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"conPhoneAreaCode\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"conRelation\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"contactDuty\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"contactType\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"faId\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"id\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                }\n" +
            "            }\n" +
            "        },\n" +
            "        \"fraudDecisionLogs\":\n" +
            "        {\n" +
            "            \"type\": \"nested\",\n" +
            "            \"properties\":\n" +
            "            {\n" +
            "                \"actionDate\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"actionTaken\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"applicationNumber\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"auditStatus\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"createBy\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"createTime\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"decisionReason\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"externalLog\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"fraudScore\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"fraudType\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"id\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"internalLog\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"modifyBy\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"modifyTime\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"operateContent\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"operateTime\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"operateUserId\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"remark\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"validateState\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                }\n" +
            "            }\n" +
            "        },\n" +
            "        \"frauddDecisionLogs\":\n" +
            "        {\n" +
            "            \"properties\":\n" +
            "            {\n" +
            "                \"applicationNumber\":\n" +
            "                {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"fields\":\n" +
            "                    {\n" +
            "                        \"keyword\":\n" +
            "                        {\n" +
            "                            \"type\": \"keyword\",\n" +
            "                            \"ignore_above\": 256\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n" +
            "                \"createBy\":\n" +
            "                {\n" +
            "                    \"type\": \"long\"\n" +
            "                },\n" +
            "                \"createTime\":\n" +
            "                {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"fields\":\n" +
            "                    {\n" +
            "                        \"keyword\":\n" +
            "                        {\n" +
            "                            \"type\": \"keyword\",\n" +
            "                            \"ignore_above\": 256\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n" +
            "                \"id\":\n" +
            "                {\n" +
            "                    \"type\": \"long\"\n" +
            "                },\n" +
            "                \"modifyTime\":\n" +
            "                {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"fields\":\n" +
            "                    {\n" +
            "                        \"keyword\":\n" +
            "                        {\n" +
            "                            \"type\": \"keyword\",\n" +
            "                            \"ignore_above\": 256\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n" +
            "                \"operateContent\":\n" +
            "                {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"fields\":\n" +
            "                    {\n" +
            "                        \"keyword\":\n" +
            "                        {\n" +
            "                            \"type\": \"keyword\",\n" +
            "                            \"ignore_above\": 256\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n" +
            "                \"operateTime\":\n" +
            "                {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"fields\":\n" +
            "                    {\n" +
            "                        \"keyword\":\n" +
            "                        {\n" +
            "                            \"type\": \"keyword\",\n" +
            "                            \"ignore_above\": 256\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n" +
            "                \"operateUserId\":\n" +
            "                {\n" +
            "                    \"type\": \"long\"\n" +
            "                },\n" +
            "                \"remark\":\n" +
            "                {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"fields\":\n" +
            "                    {\n" +
            "                        \"keyword\":\n" +
            "                        {\n" +
            "                            \"type\": \"keyword\",\n" +
            "                            \"ignore_above\": 256\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n" +
            "                \"validateState\":\n" +
            "                {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"fields\":\n" +
            "                    {\n" +
            "                        \"keyword\":\n" +
            "                        {\n" +
            "                            \"type\": \"keyword\",\n" +
            "                            \"ignore_above\": 256\n" +
            "                        }\n" +
            "                    }\n" +
            "                }\n" +
            "            }\n" +
            "        },\n" +
            "        \"from\":\n" +
            "        {\n" +
            "            \"type\": \"long\"\n" +
            "        },\n" +
            "        \"houseInfos\":\n" +
            "        {\n" +
            "            \"type\": \"nested\",\n" +
            "            \"properties\":\n" +
            "            {\n" +
            "                \"applicationNumber\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"faId\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"houseAddrAreaCode\":\n" +
            "                {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"fields\":\n" +
            "                    {\n" +
            "                        \"keyword\":\n" +
            "                        {\n" +
            "                            \"type\": \"keyword\",\n" +
            "                            \"ignore_above\": 256\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n" +
            "                \"houseAddrAreacode\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"houseBuildArea\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"houseDetailAddr\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"houseLoBalance\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"houseLoYearLimit\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"houseMonthlyPayment\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"housePost\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"housePrice\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"houseProRightRate\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"housePurchasedYears\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"houseRightOfComm\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"houseType\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"id\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                }\n" +
            "            }\n" +
            "        },\n" +
            "        \"intoProcessLogInfos\":\n" +
            "        {\n" +
            "            \"type\": \"nested\",\n" +
            "            \"properties\":\n" +
            "            {\n" +
            "                \"applicationNumber\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"createBy\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"createTime\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"faId\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"id\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"modifyBy\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"modifyTime\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"operateContent\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"operateStatus\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"operateTime\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"operateUserId\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"operateUserName\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"statusName\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                }\n" +
            "            }\n" +
            "        },\n" +
            "        \"privateOwnerInfos\":\n" +
            "        {\n" +
            "            \"type\": \"nested\",\n" +
            "            \"properties\":\n" +
            "            {\n" +
            "                \"applicationNumber\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"comAddr\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"comEmpCount\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"comType\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"faId\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"id\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"registerDate\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"registeredAddr\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"registeredCapital\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"shareholdRatio\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                }\n" +
            "            }\n" +
            "        },\n" +
            "        \"workInfos\":\n" +
            "        {\n" +
            "            \"type\": \"nested\",\n" +
            "            \"properties\":\n" +
            "            {\n" +
            "                \"applicationNumber\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"faId\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"id\":\n" +
            "                {\n" +
            "                    \"type\": \"long\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"jAddr\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"jAddrAreacode\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"jAddrIk\":\n" +
            "                {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"store\": true,\n" +
            "                    \"term_vector\": \"with_positions_offsets\",\n" +
            "                    \"analyzer\": \"ik_max_word\",\n" +
            "                    \"fielddata\": true\n" +
            "                },\n" +
            "                \"jAddrPost\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"jDept\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"jDetailAddr\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"jEnterT\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"jFullName\":\n" +
            "                {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"store\": true,\n" +
            "                    \"term_vector\": \"with_positions_offsets\",\n" +
            "                    \"analyzer\": \"ik_max_word\",\n" +
            "                    \"fielddata\": true\n" +
            "                },\n" +
            "                \"jFullPhone\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"jName\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"jNameIk\":\n" +
            "                {\n" +
            "                    \"type\": \"text\",\n" +
            "                    \"store\": true,\n" +
            "                    \"term_vector\": \"with_positions_offsets\",\n" +
            "                    \"analyzer\": \"ik_max_word\",\n" +
            "                    \"fielddata\": true\n" +
            "                },\n" +
            "                \"jPhone\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"jPhoneAreaCode\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"jPosition\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"jType\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                },\n" +
            "                \"seniority\":\n" +
            "                {\n" +
            "                    \"type\": \"keyword\",\n" +
            "                    \"store\": true\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "}";


    public void createIndex(IndexVO indexvo) throws Exception {

        if (this.indexExist(indexvo.getIndexName())) {
            LOGGER.error("索引已经存在:indexVo={} ", indexvo);
            return;
        }
        CreateIndexRequest request = new CreateIndexRequest(indexvo.getIndexName());
        buildSetting(request);
        request.mapping(JSONObject.toJSONString(indexvo.getIndexMapping()), XContentType.JSON);

        CreateIndexResponse res = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        if (!res.isAcknowledged()) {
            throw new RuleEngineException(ElasticSearchExceptionEnum.E1000.getMsg());
        }
    }

    public boolean indexExist(String idxName) throws Exception {
        GetIndexRequest request = new GetIndexRequest(idxName);
        request.local(false);
        request.humanReadable(true);
        request.includeDefaults(false);
//        request.indicesOptions(IndicesOptions.lenientExpandOpen());
        return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
    }

    public void openIndex(String... idxName) throws Exception {
        OpenIndexRequest request = new OpenIndexRequest(idxName);
        request.indicesOptions(IndicesOptions.strictExpandOpen());
        OpenIndexResponse res = restHighLevelClient.indices().open(request, RequestOptions.DEFAULT);
        if (!res.isAcknowledged()) {
            throw new RuleEngineException(ElasticSearchExceptionEnum.E1001.getMsg());
        }
    }

    public void closeIndex(String... idxName) throws Exception {
        CloseIndexRequest request = new CloseIndexRequest(idxName);
        request.indicesOptions(IndicesOptions.strictExpandOpen());
        CloseIndexResponse res = restHighLevelClient.indices().close(request, RequestOptions.DEFAULT);
        if (!res.isAcknowledged()) {
            throw new RuleEngineException(ElasticSearchExceptionEnum.E1001.getMsg());
        }
    }


    /**
     * Description
     * <br> 设置分片
     *
     * @param request
     * @author WangKun
     * @date 2019/10/30
     **/
    public void buildSetting(CreateIndexRequest request) {
        request.settings(Settings.builder().put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2));
    }

    public void insertOrUpdateOne(String idxName, ElasticEntity entity) {
        IndexRequest request = new IndexRequest(idxName);
        request.id(entity.getId());
        request.source(JSON.toJSONString(entity.getData()), XContentType.JSON);
        try {
            restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void insertBatch(String idxName, List<ElasticEntity> list) {

        BulkRequest request = new BulkRequest();
        list.forEach(item -> request.add(new IndexRequest(idxName).id(item.getId())
                .source(JSON.toJSONString(item.getData()), XContentType.JSON)));
        try {
            restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void deleteBatch(String idxName, Collection<T> idList) {

        BulkRequest request = new BulkRequest();
        idList.forEach(item -> request.add(new DeleteRequest(idxName, item.toString())));
        try {
            restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param idxName index
     * @param builder 查询参数
     * @param c       结果类对象
     * @return java.util.List<T>
     * @throws
     * @author WCNGS@QQ.COM
     * @See
     * @date 2019/10/17 17:14
     * @since
     */
    public <T> List<T> search(String idxName, SearchSourceBuilder builder, Class<T> c) {
        SearchRequest request = new SearchRequest(idxName);
        request.source(builder);
        try {
            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            List<T> hitLists = Arrays.stream(hits).map(v -> JSON.parseObject(v.getSourceAsString(), c)).collect(Collectors.toList());
            return hitLists;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除index
     *
     * @param idxName
     * @return void
     * @throws
     * @author WCNGS@QQ.COM
     * @See
     * @date 2019/10/17 17:13
     * @since
     */
    public void deleteIndex(String idxName) {
        try {
            restHighLevelClient.indices().delete(new DeleteIndexRequest(idxName), RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param idxName
     * @param builder
     * @return void
     * @throws
     * @author WCNGS@QQ.COM
     * @See
     * @date 2019/10/17 17:13
     * @since
     */
    public void deleteByQuery(String idxName, QueryBuilder builder) {

        DeleteByQueryRequest request = new DeleteByQueryRequest(idxName);
        request.setQuery(builder);
        //设置批量操作数量,最大为10000
        request.setBatchSize(10000);
        request.setConflicts("proceed");
        try {
            restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
