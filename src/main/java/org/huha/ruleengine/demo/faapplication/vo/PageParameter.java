package org.huha.ruleengine.demo.faapplication.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Description
 * <br>
 *
 * @author xujunqi
 * @date 2019/1/28
 **/
public class PageParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	
    public static final int DEFAULT_PAGE_SIZE = 20;
    
    @ApiModelProperty("pageSize,每页大小默认20")
	private int pageSize;
    
    @ApiModelProperty("currentPage,当前页")
    private int currentPage;
    
    @ApiModelProperty("totalPage,总页数")
    private int totalPage;
    
    @ApiModelProperty("totalCount,总个数")
    private int totalCount;

    
	public PageParameter() {
        this.currentPage = 1;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public PageParameter(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PageParameter [pageSize=");
		builder.append(pageSize);
		builder.append(", currentPage=");
		builder.append(currentPage);
		builder.append(", totalPage=");
		builder.append(totalPage);
		builder.append(", totalCount=");
		builder.append(totalCount);
		builder.append("]");
		return builder.toString();
	}
}
