package com.lsg.member.service;

import com.lsg.member.dao.MemberDao;
import com.lsg.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public boolean createMember(MemberVO memberVO) {
		return memberDao.insertMember(memberVO) > 0;
	}

	@Override
	public MemberVO getOneMember(MemberVO memberVO) {
		return memberDao.selectOneMember(memberVO);
	}
	
}
