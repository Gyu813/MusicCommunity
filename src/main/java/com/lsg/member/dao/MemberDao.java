package com.lsg.member.dao;

import com.lsg.member.vo.MemberVO;

public interface MemberDao {
	
	public int insertMember(MemberVO memberVO);
	
	public MemberVO selectOneMember(MemberVO memberVO);

}
