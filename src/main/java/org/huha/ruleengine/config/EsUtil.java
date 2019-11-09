package org.huha.ruleengine.config;

import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Description
 * <br> es 工具类
 *
 * @author WangKun
 * @date 2019/10/31
 **/
public class EsUtil {

    /**
     * Description
     * <br>
     *
     * @param queryBuilder
     * @param from         开始搜索的结果索引
     * @param size         返回的搜索匹配数
     * @param timeout      查询超时
     * @author WangKun
     * @date 2019/10/31
     **/
    public static SearchSourceBuilder initSearchSourceBuilder(QueryBuilder queryBuilder, int from, int size, int timeout) {

        //使用默认选项创建 SearchSourceBuilder 。
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //设置查询对象。可以使任何类型的 QueryBuilder
        sourceBuilder.query(queryBuilder);
        sourceBuilder.from(from);
        sourceBuilder.size(size);
        sourceBuilder.timeout(new TimeValue(timeout, TimeUnit.SECONDS));
        return sourceBuilder;
    }

    /**
     * Description
     * <br> es查询构造
     *
     * @param queryBuilder
     * @author WangKun
     * @date 2019/10/31
     **/
    public static SearchSourceBuilder initSearchSourceBuilder(QueryBuilder queryBuilder) {
        return initSearchSourceBuilder(queryBuilder, 0, 10, 60);
    }
}
