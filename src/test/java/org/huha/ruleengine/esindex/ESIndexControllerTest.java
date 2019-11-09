package org.huha.ruleengine.esindex;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.PrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.huha.ruleengine.config.ElasticEntity;
import org.huha.ruleengine.config.EsService;
import org.huha.ruleengine.demo.faworking.dto.FaWorkingDTO;
import org.huha.ruleengine.esindex.vo.IndexMapping;
import org.huha.ruleengine.esindex.vo.IndexVO;
import org.huha.ruleengine.exception.ElasticSearchExceptionEnum;
import org.huha.ruleengine.exception.RuleEngineException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ESIndexController Tester.
 *
 * @author wangkun
 * @version 1.0
 * @since <pre>10/31/2019</pre>
 */
@SpringBootTest
public class ESIndexControllerTest {
    @Autowired
    private EsService esService;
    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * Method: createrIndex(@PathVariable(value = "index") String index)
     */
    @Test
    public void testIindexExist() throws Exception {
        System.out.println(esService.indexExist("test"));
        System.out.println(esService.indexExist("testindex"));
        System.out.println(esService.indexExist("123"));
    }

    /**
     * Method: createrIndex(@RequestBody IndexVO indexVO)
     */
    @Test
    public void testCreateIndexIndexVO() throws Exception {


        IndexVO indexVO = new IndexVO();
        Map<String, Map<String, Object>> mapMap = new HashMap<String, Map<String, Object>>();
        indexVO.setIndexName("user");

        Map<String, Object> mapAttribute = new HashMap<>();
        mapAttribute.put("type", EsDataTypes.STRING_TEXT.getType());

        mapMap.put("name", mapAttribute);

        IndexMapping indexMapping = new IndexMapping();
        indexMapping.setProperties(mapMap);

        indexVO.setIndexMapping(indexMapping);

        System.out.println(indexVO);
        System.out.println(JSONObject.toJSONString(indexVO.getIndexMapping()));


        esService.createIndex(indexVO);
    }

    @Test
    public void test181859() throws IOException {
        String indexName = "applicationlib";
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.settings(Settings.builder().put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2));
        request.mapping(mapping, XContentType.JSON);

        CreateIndexResponse res = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        if (!res.isAcknowledged()) {
            throw new RuleEngineException(ElasticSearchExceptionEnum.E1000.getMsg());
        }
    }

    @Test
    public void test15314() throws IOException {
        String indexName = "index";
        String mapping = "{\n" +
                "        \"properties\": {\n" +
                "            \"content\": {\n" +
                "                \"type\": \"text\",\n" +
                "                \"analyzer\": \"ik_max_word\",\n" +
                "                \"search_analyzer\": \"ik_max_word\"\n" +
                "            }\n" +
                "        }\n" +
                "}";
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.settings(Settings.builder().put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2));
        request.mapping(mapping, XContentType.JSON);

        CreateIndexResponse res = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        if (!res.isAcknowledged()) {
            throw new RuleEngineException(ElasticSearchExceptionEnum.E1000.getMsg());
        }
    }

    @Test
    public void test160158() {

        Map<String, Object> map = new HashMap<>();
        map.put("content", "中国驻洛杉矶领事馆遭亚裔男子枪击 嫌犯已自首");
        ElasticEntity elasticEntity = new ElasticEntity("1", map);
        esService.insertOrUpdateOne("index", elasticEntity);
    }

    @Test
    public void test161953() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        HighlightBuilder.Field highlightTitle =
//                new HighlightBuilder.Field("content");
//        highlightTitle.preTags("<tag1>", "<tag2>");
//        highlightTitle.postTags("<tag1>", "<tag2>");
//
//        highlightBuilder.field(highlightTitle);
//        searchSourceBuilder.highlighter(highlightBuilder);

//        MatchQueryBuilder matchQueryBuilder  = new MatchQueryBuilder ("content","国");
//        searchSourceBuilder.query(matchQueryBuilder);

//        MatchPhraseQueryBuilder matchPhraseQueryBuilder = new MatchPhraseQueryBuilder("content","美国");
        PrefixQueryBuilder matchPhraseQueryBuilder = new PrefixQueryBuilder("content", "美国");
        PrefixQueryBuilder termQueryBuilder = QueryBuilders.prefixQuery("content", "美国");
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("content", "美国");

        searchSourceBuilder.query(termQueryBuilder);
        System.out.println(searchSourceBuilder);

        List<Article> search = esService.search("index", searchSourceBuilder, Article.class);

        search.forEach(System.out::println);
    }

    @Test
    public void test185513() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        searchSourceBuilder.query(boolQueryBuilder);

        List<FaWorkingDTO> list = esService.search("workinfos", searchSourceBuilder, FaWorkingDTO.class);

    }


    /**
     * Method: del(@PathVariable(value = "index") String index)
     */
    @Test
    public void testDeleteIndex() throws Exception {
        esService.deleteIndex("testindex");
    }

    public static String mapping = "{\n" +
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


} 
