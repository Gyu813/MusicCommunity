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
	public CommunityVO getOneCommunity(int no) {
		return communityDao.selectOneCommunity(no);
	}

	@Override
	public boolean createCommunity(CommunityVO communityVO) {
		return communityDao.insertCommunity(communityVO) > 0;
	}

	@Override
	public boolean increaseViewCount(int no) {
		return communityDao.increaseViewCount(no) > 0;
	}

	@Override
	public boolean increaseRecommendCount(int no) {
		return communityDao.increaseRecommendCount(no) > 0;
	}

	@Override
	public boolean modifyCommunity(CommunityVO communityVO) {
		return communityDao.updateCommunity(communityVO) > 0;
	}

	@Override
	public boolean removeCommunity(int no) {
		return communityDao.deleteCommunity(no) > 0;
	}
	
	
}
