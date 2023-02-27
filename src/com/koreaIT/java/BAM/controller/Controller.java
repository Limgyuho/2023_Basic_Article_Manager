package com.koreaIT.java.BAM.controller;

import static com.koreaIT.java.BAM.controller.Controller.loginedMember;

import com.koreaIT.java.BAM.dto.Member;

public abstract class Controller {
	
	//공유제로 사용 되게 끔 스테틱을 사용 한다
	public static Member loginedMember;
	
	//추상 메서드, 추상클래스 와 구상 메서드
	//스태틱 메서드는 고정적인 역할을 해주며 직접 일을 수행한다
	public static boolean isLogined() {
		return loginedMember != null;
	}
	
	public abstract void doAction(String cmd, String methodName); 
	
	public abstract void makeTestData();
}
