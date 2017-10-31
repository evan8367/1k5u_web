package com.zhenyulaw.jf.common.util;

import java.io.Serializable;

/**
 */
public class PageParam implements Serializable {

	/**
	 */
	private static final long serialVersionUID = -5383945201604723875L;

	/**
	 * 每页大小
	 */
	private Integer pageSize;

	/**
	 * 当前页码
	 */
	private Integer pageNumber;

	/**
	 * 总记录数
	 */
	private Integer recordCount;

	/**
	 * 开始记录数
	 */
	private Integer startIndex;

	/**
	 * 总页数
	 */
	private Integer pageCount;

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageSize() {
		if(pageSize == null)
			pageSize = 15;
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getStartIndex() {
		if(startIndex == null)
			startIndex = 0;
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

}
