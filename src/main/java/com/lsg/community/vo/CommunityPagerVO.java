package com.lsg.community.vo;

import io.github.seccoding.web.pager.annotations.EndRow;
import io.github.seccoding.web.pager.annotations.StartRow;

public class CommunityPagerVO {

	private int pageNo = -1;

	@StartRow
	private int startNumber;

	@EndRow
	private int endNumber;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}

	public int getEndNumber() {
		return endNumber;
	}

	public void setEndNumber(int endNumber) {
		this.endNumber = endNumber;
	}

}
