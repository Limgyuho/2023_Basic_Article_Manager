package com.koreaIT.java.BAM.dto;

public class Member extends Dto{
	//public을 통하여 다른 패키지와 연동이 가능하다

	public String loginId;
	public String loginPw;
	public String loginPwChk;
	public String name;
	
	

	public Member(int id,String regDate, String loginId, String loginPw,String loginPwChk,String name) {
		this.id = id;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.loginPwChk = loginPwChk;
		this.regDate = regDate;
		this.name = name;
	}

	
}