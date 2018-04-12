package com.lsg.member.dao;

import com.lsg.member.vo.MemberVO;

public interface MemberDao {
	
	public int insertMember(MemberVO memberVO);
	
	public MemberVO selectOneMember(MemberVO memberVO);
	
	public MemberVO selectOneMemberByNo(int no);
	
	public int updateMember(MemberVO memberVO);
	
	public int deleteMember(int no);
	
	public int selectCountMemberId(String id);
	
	public String selectSalt(String id);

}
