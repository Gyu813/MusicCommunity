package com.lsg.member.service;

import com.lsg.member.vo.MemberVO;

public interface MemberService {
	
	public boolean createMember(MemberVO memberVO);
	
	public MemberVO getOneMember(MemberVO memberVO);
	
}
