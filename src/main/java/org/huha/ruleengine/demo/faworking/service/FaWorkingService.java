package org.huha.ruleengine.demo.faworking.service;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.huha.ruleengine.config.EsService;
import org.huha.ruleengine.demo.faworking.dao.FaWorkingMapper;
import org.huha.ruleengine.demo.faworking.dto.FaWorkingDTO;
import org.huha.ruleengine.demo.faworking.vo.FaWorkingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 工作信息-申请库 业务层
 *
 * @author WangKun
 * @date 2019-02-12 11:15
 **/
@Service
public class FaWorkingService {

    private static final Logger logger = LoggerFactory.getLogger(FaWorkingService.class);

    @Autowired
    private FaWorkingMapper mapper;
    @Autowired
    private EsService esService;


    /**
     * 根据进件编号查询工作信息
     *
     * @param applicationNumber 进件编号
     * @author lichengqiang
     * @date 2019-03-01
     **/
    public List<FaWorkingDTO> queryFaApplicationByAppNum(String applicationNumber) throws Exception {
        return mapper.findFaWorkingByAppNum(applicationNumber);
    }

    public List<FaWorkingDTO> searchByPaging() throws Exception {
        return mapper.searchByPaging();
    }

    public List<FaWorkingDTO> search(FaWorkingRequest request, int pageNum, int pageSize) throws Exception {

        FieldSortBuilder order = SortBuilders.fieldSort("id").order(SortOrder.ASC);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        wrapQueryBuilder(boolQueryBuilder, request);

        searchSourceBuilder.query(boolQueryBuilder)
                .from(pageNum)
                .size(pageSize);

        searchSourceBuilder
//                .sort(order)
                .sort("id", SortOrder.ASC);

        System.out.println(searchSourceBuilder);
        List<FaWorkingDTO> list = esService.search("workinfos", searchSourceBuilder, FaWorkingDTO.class);
        return list;
    }

    public void wrapQueryBuilder(BoolQueryBuilder boolQueryBuilder, FaWorkingRequest request) {
        if (!StringUtils.isEmpty(request.getJNameIk())) {
            boolQueryBuilder
                    .filter(QueryBuilders.matchQuery("jNameIk",request.getJNameIk()));
        }
        if (!StringUtils.isEmpty(request.getJName())) {
            boolQueryBuilder
                    .filter(QueryBuilders.matchQuery("jName", request.getJName()));
        }
        if (!StringUtils.isEmpty(request.getJAddrIk())) {
            boolQueryBuilder
                    .filter(QueryBuilders.termQuery("jAddrIk", request.getJAddrIk()));
        }
        if (!StringUtils.isEmpty(request.getJPhoneAreaCode())) {
            boolQueryBuilder
                    .filter(QueryBuilders.termQuery("jPhoneAreaCode", request.getJPhoneAreaCode()));
        }
        if (!StringUtils.isEmpty(request.getJPhone())) {
            boolQueryBuilder
                    .filter(QueryBuilders.termQuery("jPhone", request.getJPhone()));
        }
        if (!StringUtils.isEmpty(request.getJType())) {
            boolQueryBuilder
                    .filter(QueryBuilders.termQuery("jType", request.getJType()));
        }
        if (!StringUtils.isEmpty(request.getJDept())) {
            boolQueryBuilder
                    .filter(QueryBuilders.termQuery("jDept", request.getJDept()));
        }
        if (!StringUtils.isEmpty(request.getJPosition())) {
            boolQueryBuilder
                    .filter(QueryBuilders.termQuery("jPosition", request.getJPosition()));
        }
        if (!StringUtils.isEmpty(request.getJEnterTStart()) || !StringUtils.isEmpty(request.getJEnterTEnd())) {
            boolQueryBuilder
                    .filter(QueryBuilders.rangeQuery("jEnterT")
                            .from(request.getJEnterTStart())
                            .to(request.getJEnterTEnd()));
        }
        if (!StringUtils.isEmpty(request.getCreateTimeStart()) || !StringUtils.isEmpty(request.getCreateTimeEnd())) {
            boolQueryBuilder
                    .filter(QueryBuilders.rangeQuery("createTimeLong")
                            .from(request.getCreateTimeStart())
                            .to(request.getCreateTimeEnd()));
        }

    }

}

