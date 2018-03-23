package com.lsg.community.dao;

import java.util.List;

import com.lsg.community.vo.CommunityVO;

public interface CommunityDao {
	
	public List<CommunityVO> selectAllCommunities();
	
	public CommunityVO selectOneCommunity(int id);
	
	public int insertCommunity(CommunityVO communityVO);

}
