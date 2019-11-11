package org.huha.ruleengine.demo.faworking.service;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.huha.ruleengine.config.ElasticEntity;
import org.huha.ruleengine.config.EsService;
import org.huha.ruleengine.demo.faworking.dto.FaWorkingDTO;
import org.huha.ruleengine.demo.faworking.vo.FaWorkingRequest;
import org.huha.ruleengine.exception.ElasticSearchExceptionEnum;
import org.huha.ruleengine.exception.RuleEngineException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FaWorkingService Tester.
 *
 * @author wangkun
 * @version 1.0
 * @since <pre>11/05/2019</pre>
 */
@SpringBootTest
public class FaWorkingServiceTest {

    @Autowired
    private FaWorkingService faWorkingService;

    @Autowired
    private EsService esService;
    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * Method: queryFaApplicationByAppNum(String applicationNumber)
     */
    @Test
    public void testQueryFaApplicationByAppNum() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: searchByPaging()
     */
    @Test
    public void testSearchByPaging() throws Exception {
        List<FaWorkingDTO> list = faWorkingService.searchByPaging();
//        list.forEach(v->System.out.println(JSON.toJSONString(v)));

        List<ElasticEntity> collect = list.stream().map(v ->
                ElasticEntity.builder().id(Long.toString(v.getId())).data(v).build()).collect(Collectors.toList());

//        ElasticEntity build = ElasticEntity.builder().id(Long.toString(list.get(0).getId())).data(list.get(0)).build();
//        esService.insertOrUpdateOne("workinfos",build);
        System.out.println(collect.size());
        esService.insertBatch("workinfos",collect);
    }

    @Test
    public void test200915() throws Exception {
        FaWorkingRequest request = new FaWorkingRequest();
        List<FaWorkingDTO> search = faWorkingService.search(request, 0,10);
        System.out.println(search);
    }

    @Test
    public void test175558() throws IOException {
        String indexName = "workinfos";

        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.settings(Settings.builder().put("index.number_of_shards", 2)
                .put("index.number_of_replicas", 0));
        request.mapping(mapping, XContentType.JSON);

        CreateIndexResponse res = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        if (!res.isAcknowledged()) {
            throw new RuleEngineException(ElasticSearchExceptionEnum.E1000.getMsg());
        }
    }

    String mapping = "{\n" +
            "    \"properties\":\n" +
            "    {\n" +
            "        \"id\":\n" +
            "        {\n" +
            "            \"type\": \"long\"\n" +
            "        },\n" +
            "        \"applicationNumber\":\n" +
            "        {\n" +
            "            \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"jName\":\n" +
            "        {\n" +
            "            \"type\": \"text\",\n" +
            "            \"term_vector\": \"with_positions_offsets\",\n" +
            "            \"analyzer\": \"ik_max_word\",\n" +
            "            \"fielddata\": true\n" +
            "        },\n" +
            "        \"jAddr\":\n" +
            "        {\n" +
            "            \"type\": \"text\",\n" +
            "            \"term_vector\": \"with_positions_offsets\",\n" +
            "            \"analyzer\": \"ik_max_word\",\n" +
            "            \"fielddata\": true\n" +
            "        },\n" +
            "        \"jAddrAreacode\":\n" +
            "        {\n" +
            "            \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"jAddrPost\":\n" +
            "        {\n" +
            "            \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"jPhoneAreaCode\":\n" +
            "        {\n" +
            "            \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"jPhone\":\n" +
            "        {\n" +
            "            \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"jType\":\n" +
            "        {\n" +
            "            \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"seniority\":\n" +
            "        {\n" +
            "            \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"jEnterT\":\n" +
            "        {\n" +
            "            \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"jDept\":\n" +
            "        {\n" +
            "            \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"jPosition\":\n" +
            "        {\n" +
            "            \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"jPayType\":\n" +
            "        {\n" +
            "            \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"createTime\":\n" +
            "        {\n" +
            "            \"type\": \"date_nanos\"\n" +
            "        },\n" +
            "        \"createTimeLong\":\n" +
            "        {\n" +
            "            \"type\": \"long\"\n" +
            "        },\n" +
            "        \"jMonPayT\":\n" +
            "        {\n" +
            "            \"type\": \"integer\"\n" +
            "        },\"faId\":\n" +
            "        {\n" +
            "            \"type\": \"long\"\n" +
            "        },\n" +
            "        \"jDetailAddr\":\n" +
            "        {\n" +
            "            \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"jFullName\":\n" +
            "        {\n" +
            "            \"type\": \"text\",\n" +
            "            \"term_vector\": \"with_positions_offsets\",\n" +
            "            \"analyzer\": \"ik_max_word\",\n" +
            "            \"fielddata\": true\n" +
            "        },\n" +
            "        \"jFullPhone\":\n" +
            "        {\n" +
            "            \"type\": \"keyword\"\n" +
            "        }\n" +
            "    }\n" +
            "}";


} 
