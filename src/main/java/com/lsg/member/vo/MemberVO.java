package com.lsg.member.vo;

import java.io.File;
import java.io.IOException;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {

	private int no;
	
	@NotEmpty(message="아이디를 입력해주세요!")
	private String id;
	
	@NotEmpty(message="비밀번호를 입력해주세요!")
	private String password;
	
	@NotEmpty(message="닉네임을 입력해주세요!")
	private String nickname;
	
	private String selfIntroduction;
	private String registDate;
	private MultipartFile profileFile;
	private String profileFilename;
	private String modifyDate;
	private String salt;

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

	public String getSelfIntroduction() {
		if ( selfIntroduction == null ) {
			selfIntroduction = "";
		}
		return selfIntroduction;
	}

	public void setSelfIntroduction(String selfIntroduction) {
		this.selfIntroduction = selfIntroduction;
	}

	public String getRegistDate() {
		return registDate;
	}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}

	public MultipartFile getProfileFile() {
		return profileFile;
	}

	public void setProfileFile(MultipartFile profileFile) {
		this.profileFile = profileFile;
	}

	public String getProfileFilename() {
		if ( profileFilename == null ) {
			profileFilename = "";
		}
		return profileFilename;
	}

	public void setProfileFilename(String profileFilename) {
		if ( profileFilename == null ) {
			profileFilename = "";
		}
		this.profileFilename = profileFilename;
	}
	
	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String save() {
		
		if ( profileFile != null && !profileFile.isEmpty() ) {
			profileFilename = profileFile.getOriginalFilename();
			
			File newFile = new File("D:/uploadProfiles/" + profileFile.getOriginalFilename());
			try {
				profileFile.transferTo(newFile);
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
