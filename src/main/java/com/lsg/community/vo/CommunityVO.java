package com.lsg.community.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lsg.member.vo.MemberVO;

public class CommunityVO {

	private int no;
	private int userNo;
	private String title;
	private String body;
	private String writeDate;
	private int viewCount;
	private int recommendCount;
	private String requestIp;
	private MultipartFile file;
	private String displayFilename;
	private List<CommentVO> commentList;
	private MemberVO memberVO;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getRecommendCount() {
		return recommendCount;
	}

	public void setRecommendCount(int recommendCount) {
		this.recommendCount = recommendCount;
	}

	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getDisplayFilename() {
		return displayFilename;
	}

	public void setDisplayFilename(String displayFilename) {
		this.displayFilename = displayFilename;
	}

	public List<CommentVO> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentVO> commentList) {
		this.commentList = commentList;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

}
