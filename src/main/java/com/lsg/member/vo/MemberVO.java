package com.lsg.member.vo;

import javax.validation.constraints.NotEmpty;

public class MemberVO {

	private int no;
	
	@NotEmpty(message="아이디를 입력해주세요!")
	private String id;
	
	@NotEmpty(message="비밀번호를 입력해주세요!")
	private String password;
	
	@NotEmpty(message="닉네임을 입력해주세요!")
	private String nickname;
	
	private String registDate;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRegistDate() {
		return registDate;
	}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}

}
