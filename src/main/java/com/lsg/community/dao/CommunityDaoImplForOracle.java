package com.lsg.community.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.lsg.community.vo.CommunityPagerVO;
import com.lsg.community.vo.CommunityVO;

public class CommunityDaoImplForOracle extends SqlSessionDaoSupport implements CommunityDao {

	@Override
	public List<CommunityVO> selectAllCommunities(CommunityPagerVO communityPagerVO) {
		return getSqlSession().selectList("CommunityDao.selectAllCommunities", communityPagerVO);
	}

	@Override
	public CommunityVO selectOneCommunity(int no) {
		return getSqlSession().selectOne("CommunityDao.selectOneCommunity", no);
	}

	@Override
	public int insertCommunity(CommunityVO communityVO) {
		return getSqlSession().insert("CommunityDao.insertCommunity", communityVO);
	}

	@Override
	public int increaseViewCount(int no) {
		return getSqlSession().update("CommunityDao.increaseViewCount", no);
	}

	@Override
	public int increaseRecommendCount(int no) {
		return getSqlSession().update("CommunityDao.increaseRecommendCount", no);
	}

	@Override
	public int updateCommunity(CommunityVO communityVO) {
		return getSqlSession().update("CommunityDao.updateCommunity", communityVO);
	}

	@Override
	public int deleteCommunity(int no) {
		return getSqlSession().delete("CommunityDao.deleteCommunity", no);
	}

	@Override
	public int deleteMyCommunities(int memberNo) {
		return getSqlSession().delete("CommunityDao.deleteMyCommunities", memberNo);
	}

	@Override
	public int selectAllCommunityCount(CommunityPagerVO communityPagerVO) {
		return getSqlSession().selectOne("CommunityDao.selectAllCommunityCount", communityPagerVO);
	}

}
