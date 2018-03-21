package com.lsg.community.vo;

import com.lsg.member.vo.MemberVO;

public class CommentVO {

	private int no;
	private String commentBody;
	private int like;
	private int hate;
	private MemberVO memberVO;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getCommentBody() {
		return commentBody;
	}

	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getHate() {
		return hate;
	}

	public void setHate(int hate) {
		this.hate = hate;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

}
