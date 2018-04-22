package com.lsg.member.service;

import com.lsg.community.dao.CommunityDao;
import com.lsg.member.dao.MemberDao;
import com.lsg.member.vo.MemberVO;
import com.lsg.reply.dao.ReplyDao;
import com.lsg.util.SHA256Util;

public class MemberServiceImpl implements MemberService {
	
	private MemberDao memberDao;
	private CommunityDao communityDao;
	private ReplyDao replyDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void setCommunityDao(CommunityDao communityDao) {
		this.communityDao = communityDao;
	}
	
	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	@Override
	public boolean createMember(MemberVO memberVO) {
		
		// SALT 생성 (암호화)
		String salt = SHA256Util.generateSalt();
		memberVO.setSalt(salt);
		
		String password = memberVO.getPassword();
		password = SHA256Util.getEncrypt(password, salt);
		memberVO.setPassword(password);
		
		return memberDao.insertMember(memberVO) > 0;
	}

	@Override
	public MemberVO getOneMember(MemberVO memberVO) {
		
		// 1. MemberVO : id로 SALT 가져오기
		String salt = memberDao.selectSalt(memberVO.getId());
		if ( salt == null ) {
			salt = "";
		}
		
		// 2. SALT로 암호화
		String password = memberVO.getPassword();
		password = SHA256Util.getEncrypt(password, salt);
		memberVO.setPassword(password);
		
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
		communityDao.deleteMyCommunities(no);
		replyDao.deleteReplyByMemberNo(no);
		return memberDao.deleteMember(no) > 0;
	}

	@Override
	public boolean readCountMemberId(String id) {
		return memberDao.selectCountMemberId(id) > 0;
	}
	
}
