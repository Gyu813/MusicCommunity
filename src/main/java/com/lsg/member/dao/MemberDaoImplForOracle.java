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
	
	@Override
	public MemberVO selectOneMemberByNo(int no) {
		return getSqlSession().selectOne("MemberDao.selectOneMemberByNo", no);
	}

	@Override
	public int updateMember(MemberVO memberVO) {
		return getSqlSession().update("MemberDao.updateMember", memberVO);
	}

	@Override
	public int deleteMember(int no) {
		return getSqlSession().delete("MemberDao.deleteMember", no);
	}

	@Override
	public int selectCountMemberId(String id) {
		return getSqlSession().selectOne("MemberDao.selectCountMemberId", id);
	}

	@Override
	public String selectSalt(String id) {
		return getSqlSession().selectOne("MemberDao.selectSalt", id);
	}

}
