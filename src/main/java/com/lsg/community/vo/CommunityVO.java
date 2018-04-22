package com.lsg.community.vo;

import java.io.File;
import java.io.IOException;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import com.lsg.member.vo.MemberVO;

public class CommunityVO {

	private int no;
	private int memberNo;
	
	@NotEmpty(message="제목을 입력해 주세요!")
	private String title;
	
	@NotEmpty(message="내용을 입력해 주세요!")
	private String body;
	
	private String writeDate;
	private int viewCount;
	private int recommendCount;
	private String requestIp;
	private MultipartFile file;
	private String displayFilename;
	private MemberVO memberVO;

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
		if ( displayFilename == null ) {
			displayFilename = "";
		}
		return displayFilename;
	}

	public void setDisplayFilename(String displayFilename) {
		if ( displayFilename == null ) {
			displayFilename = "";
		}
		this.displayFilename = displayFilename;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	
	public String save() {
		if ( file != null && !file.isEmpty() ) {
			displayFilename = file.getOriginalFilename();
			
			File newFile = new File("D:/uploadFiles/" + file.getOriginalFilename());
			try {
				file.transferTo(newFile);
				return newFile.getAbsolutePath();
			} catch (IllegalStateException ise) {
				throw new RuntimeException(ise.getMessage(), ise);
			} catch (IOException ioe) {
				throw new RuntimeException(ioe.getMessage(), ioe);
			}
		}
		
		return null;
	}

}
