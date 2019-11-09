package org.huha.ruleengine.demo.faapplication.vo;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 查询总包装类
 *
 * @author xujunqi
 * @date 2019/1/28 16:02
 */
public class QueryReqCheckBean<T> implements Serializable {

    private static final long serialVersionUID = 6838526421128124395L;

    /**
     * 分页参数
     */
    @NotNull
    private PageParameter pageParameter;

    @Valid
    @NotNull
    private SearchParams<T> searchParams;

    /**
     * 排序表达式,比如 a desc,b asc
     */
    private String sortExp = null;

    public PageParameter getPageParameter() {
        return pageParameter;
    }

    public void setPageParameter(PageParameter pageParameter) {
        this.pageParameter = pageParameter;

    }

    public SearchParams<T> getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(SearchParams<T> searchParams) {
        this.searchParams = searchParams;
    }

    public String getSortExp() {
        return sortExp;
    }

    public void setSortExp(String sortExp) {
        this.sortExp = sortExp;
    }

    public Map<String, Object> buildParams() {
        Map<String, Object> params = new HashMap<>(16);

        T dto = null;
        if (searchParams != null) {
            dto = searchParams.getDto();
        }
        params.put("dto", dto);

        if (pageParameter != null) {
            pageParameter = new PageParameter();
        }
        params.put("page", pageParameter);

        if (sortExp != null) {
            params.put("sortExp", sortExp);
        }
        return params;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("QueryReqBean [pageParameter=");
        builder.append(pageParameter);
        builder.append(", searchParams=");
        builder.append(searchParams);
        builder.append(", sortExp=");
        builder.append(sortExp);
        builder.append("]");
        return builder.toString();
    }

}
