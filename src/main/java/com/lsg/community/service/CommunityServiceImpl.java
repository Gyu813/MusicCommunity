package com.lsg.community.service;

import java.util.List;

import com.lsg.community.dao.CommunityDao;
import com.lsg.community.vo.CommunityVO;

public class CommunityServiceImpl implements CommunityService {

	private CommunityDao communityDao;

	public void setCommunityDao(CommunityDao communityDao) {
		this.communityDao = communityDao;
	}

	@Override
	public List<CommunityVO> getAllCommunities() {
		return communityDao.selectAllCommunities();
	}

	@Override
	public CommunityVO getOneCommunity(int id) {
		return communityDao.selectOneCommunity(id);
	}

	@Override
	public boolean createCommunity(CommunityVO communityVO) {
		return communityDao.insertCommunity(communityVO) > 0;
	}
	
	
}
