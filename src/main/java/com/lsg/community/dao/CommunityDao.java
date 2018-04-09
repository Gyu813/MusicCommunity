package com.lsg.community.dao;

import java.util.List;

import com.lsg.community.vo.CommunityVO;

public interface CommunityDao {
	
	public List<CommunityVO> selectAllCommunities();
	
	public CommunityVO selectOneCommunity(int no);
	
	public int insertCommunity(CommunityVO communityVO);
	
	public int increaseViewCount(int no);
	
	public int increaseRecommendCount(int no);
	
	public int updateCommunity(CommunityVO communityVO);
	
	public int deleteCommunity(int no);

}
