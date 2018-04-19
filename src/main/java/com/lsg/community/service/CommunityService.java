package com.lsg.community.service;

import com.lsg.community.vo.CommunityPagerVO;
import com.lsg.community.vo.CommunityVO;

import io.github.seccoding.web.pager.explorer.PageExplorer;

public interface CommunityService {
	
	public PageExplorer getAllCommunities(CommunityPagerVO communityPagerVO);
	
	public CommunityVO getOneCommunity(int no);
	
	public boolean createCommunity(CommunityVO communityVO);
	
	public boolean increaseViewCount(int no);
	
	public boolean increaseRecommendCount(int no);
	
	public boolean modifyCommunity(CommunityVO communityVO);
	
	public boolean removeCommunity(int no);

}
