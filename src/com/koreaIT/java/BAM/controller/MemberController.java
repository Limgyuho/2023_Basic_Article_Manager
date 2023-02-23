package com.koreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class MemberController extends Controller {

	private List<Member> members;
	private Scanner sc;
	private int lastMemberId;

	public MemberController(Scanner sc) {
		this.members = new ArrayList<>();
		this.sc = sc;
		this.lastMemberId = 0;
	}

	public void doAction(String cmd, String methodName) {

		switch (methodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다");
			break;
		}
	}

//	
//	private Article articlebyId(int id) {
//		// 중복 기능 제거
//		// 찾은 문자를 순회 하는 방법
//		for (Article article : articles) {
//
//			if (article.id == id) {
//				return article;
//				// 게시물의 조건
//			}
//		}
//		return null;
//	}

	private void doJoin() {

		int id = lastMemberId + 1;
		lastMemberId = id;
		String regDate = Util.getDate();

		String loginId = null;
		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (loginIdDupChk(loginId) == false) {
				System.out.printf("%s은(는) 이미 사용중인 아이디 입니다\n", loginId);
				continue;
			}
			System.out.printf("%s은(는) 사용가능한 아이디 입니다\n", loginId);
			break;
		}
		String loginPw = null;

		String loginPwChk = null;

		while (true) {

			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비밀번호 확인: ");
			loginPwChk = sc.nextLine();

			// 값을 하고 싶을때는 equals를 사용한다
			if (loginPw.equals(loginPwChk) == false) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			break;
		}
		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, loginId, loginPw, loginPwChk, name);
		members.add(member);

		System.out.printf("%s 회원님 환영 합니다\n", loginId);

	}
	
	private void doLogin() {
		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();

		
		//로그인후 로그아웃 되지 않게 저장할수 있게 하는 공간을 만들어 인자로 저장한
		Member member =getMemberByLoginId(loginId);
		
		//아이디가 없는 경우 일치하는것이 없는 경우
		if (member == null) {
			System.out.println("존재하지 않는 아이디 입니다");
			return;
		}
		if(member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호가 일치 하지 않습니다");
			return;
		}
		
		System.out.printf("로그인 성공! %s님 환영합니다\n",member.name);
		
	}
	private Member getMemberByLoginId(String loginId) {
		
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {

				return member;
			}
		
		}
		return null;
	}

	//
	

	private boolean loginIdDupChk(String loginId) {
		
		//효율적으로 하기 위해 아래에서 호출한다
		Member member =getMemberByLoginId(loginId);
//
//		for (Member member : members) {
//			if (member.loginId.equals(loginId)) {
//
//				return false;
//			}
//		}
		
		//있는지 없지 판단
		if(member != null) {
			return false;
		}

		return true;
	}

}
