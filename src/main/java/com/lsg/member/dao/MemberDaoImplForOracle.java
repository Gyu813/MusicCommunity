package com.lsg.member.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.lsg.member.vo.MemberVO;

public class MemberDaoImplForOracle extends SqlSessionDaoSupport implements MemberDao {

	@Override
	public int insertMember(MemberVO memberVO) {
		return getSqlSession().insert("MemberDao.insertMember", memberVO);
	}

	@Override
	public MemberVO selectOneMember(MemberVO memberVO) {
		return getSqlSession().selectOne("MemberDao.selectOneMember", memberVO);
	}

}
