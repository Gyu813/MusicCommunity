package com.lsg.reply.vo;

import com.lsg.community.vo.CommunityVO;
import com.lsg.member.vo.MemberVO;

public class ReplyVO {

	private int level;
	private int no;
	private int memberNo;
	private int communityNo;
	private String body;
	private String registDate;
	private int parentReplyNo;
	private int loveCount;
	private int hateCount;
	private MemberVO memberVO;
	private CommunityVO communityVO;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getCommunityNo() {
		return communityNo;
	}

	public void setCommunityNo(int communityNo) {
		this.communityNo = communityNo;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getRegistDate() {
		return registDate;
	}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}

	public int getParentReplyNo() {
		return parentReplyNo;
	}

	public void setParentReplyNo(int parentReplyNo) {
		this.parentReplyNo = parentReplyNo;
	}

	public int getLoveCount() {
		return loveCount;
	}

	public void setLoveCount(int loveCount) {
		this.loveCount = loveCount;
	}

	public int getHateCount() {
		return hateCount;
	}

	public void setHateCount(int hateCount) {
		this.hateCount = hateCount;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public CommunityVO getCommunityVO() {
		return communityVO;
	}

	public void setCommunityVO(CommunityVO communityVO) {
		this.communityVO = communityVO;
	}

}
