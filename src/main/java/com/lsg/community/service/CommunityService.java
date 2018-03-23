package com.lsg.community.service;

import java.util.List;

import com.lsg.community.vo.CommunityVO;

public interface CommunityService {
	
	public List<CommunityVO> getAllCommunities();
	
	public CommunityVO getOneCommunity(int id);
	
	public boolean createCommunity(CommunityVO communityVO);

}
