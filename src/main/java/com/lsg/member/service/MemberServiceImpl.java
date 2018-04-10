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
	
	@Override
	public MemberVO getOneMemberByNo(int no) {
		return memberDao.selectOneMemberByNo(no);
	}

	@Override
	public boolean modifyMyInfo(MemberVO memberVO) {
		return memberDao.updateMember(memberVO) > 0;
	}

	@Override
	public boolean dropMember(int no) {
		return memberDao.deleteMember(no) > 0;
	}

	@Override
	public boolean readCountMemberId(String id) {
		return memberDao.selectCountMemberId(id) > 0;
	}
	
}
