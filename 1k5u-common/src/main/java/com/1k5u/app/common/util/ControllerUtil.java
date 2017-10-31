package com.zhenyulaw.jf.common.util;

public class ControllerUtil {

	public static PageParam getPageParam(Integer totleCount, PageParam pageParam) {
		PageParam page = new PageParam();
		page.setRecordCount(totleCount);

		page.setPageCount(totleCount / pageParam.getPageCount() + 1);

		page.setPageSize(pageParam.getPageCount());
		page.setStartIndex(pageParam.getStartIndex());

		page.setPageNumber(pageParam.getStartIndex() / pageParam.getPageCount() + 1);

		return page;

	}
}
