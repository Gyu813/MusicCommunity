package com.lsg.community.service;

import java.util.List;

import com.lsg.community.vo.CommunityVO;

public interface CommunityService {
	
	public List<CommunityVO> getAllCommunities();
	
	public CommunityVO getOneCommunity(int no);
	
	public boolean createCommunity(CommunityVO communityVO);
	
	public boolean increaseViewCount(int no);
	
	public boolean increaseRecommendCount(int no);

}
