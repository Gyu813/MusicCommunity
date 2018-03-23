package com.lsg.comment.vo;

import com.lsg.community.vo.CommunityVO;
import com.lsg.member.vo.MemberVO;

public class CommentVO {

	private int no;
	private int memberNo;
	private int communityNo;
	private String body;
	private String registDate;
	private int parentCommentNo;

	private MemberVO memberVO;
	private CommunityVO communityVO;
	private CommentVO commentVO;

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

	public int getParentCommentNo() {
		return parentCommentNo;
	}

	public void setParentCommentNo(int parentCommentNo) {
		this.parentCommentNo = parentCommentNo;
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

	public CommentVO getCommentVO() {
		return commentVO;
	}

	public void setCommentVO(CommentVO commentVO) {
		this.commentVO = commentVO;
	}

}
