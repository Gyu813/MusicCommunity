package com.lsg.community.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.lsg.community.vo.CommunityVO;

public class CommunityDaoImplForOracle extends SqlSessionDaoSupport implements CommunityDao {

	@Override
	public List<CommunityVO> selectAllCommunities() {
		return getSqlSession().selectList("CommunityDao.selectAllCommunities");
	}

	@Override
	public CommunityVO selectOneCommunity(int id) {
		return getSqlSession().selectOne("CommunityDao.selectOneCommunity", id);
	}

	@Override
	public int insertCommunity(CommunityVO communityVO) {
		return getSqlSession().insert("CommunityDao.insertCommunity", communityVO);
	}

}