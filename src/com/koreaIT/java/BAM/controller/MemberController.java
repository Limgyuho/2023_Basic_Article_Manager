package com.koreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class MemberController extends Controller {

	private List<Member> members;
	private Scanner sc;
	private int lastMemberId;
	public int memberid;

	public MemberController(Scanner sc) {
		this.members = new ArrayList<>();
		this.sc = sc;
		this.lastMemberId = 3;
		
	}

	public void doAction(String cmd, String methodName) {

		switch (methodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		case "profile":
			doprofile();
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

		if (isLogined()) {
			System.out.println("로그아웃 후 이용해주세요");
			return;
		}

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

//		if(loginedMember == null) {
//			System.out.println("로그인 상태가 아닙니다 로그인을 해주세요");
//			return;
//		}
//		else if(loginedMember != null) {
//			System.out.println("로그인상태 입니다");
//			return;
//		}
//
		if (isLogined()) {
			System.out.println("로그인상태 입니다");
			return;
		}

		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();

		// 로그인후 로그아웃 되지 않게 저장할수 있게 하는 공간을 만들어 인자로 저장한
		Member member = getMemberByLoginId(loginId);

		// 아이디가 없는 경우 일치하는것이 없는 경우
		if (member == null) {
			System.out.println("존재하지 않는 아이디 입니다");
			return;
		}

		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호가 일치 하지 않습니다");
			return;
		}

		// 로그인시 정보를 저장
		loginedMember = member;

		System.out.printf("로그인 성공! %s님 환영합니다\n", member.name);

	}

	private void doprofile() {
		if (isLogined() == true) {
			System.out.println("==내정보==");
			System.out.printf("로그인 아이디: %s\n", loginedMember.loginId);
			System.out.printf("이름 : %s\n", loginedMember.name);
			System.out.printf("회원 비밀번호 : %s\n", loginedMember.loginPw);
			System.out.printf("회원 번호 : %d\n", loginedMember.id);
			System.out.printf("가입날짤 : %s\n", loginedMember.regDate);
			return;
		}
		this.loginedMember = null;
		System.out.println("로그인후 이용해주세요");
	}
///

///
	private void doLogout() {
		if (isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		this.loginedMember = null;
		System.out.println("로그아웃 성공");
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

		// 효율적으로 하기 위해 아래에서 호출한다
		Member member = getMemberByLoginId(loginId);
//
//		for (Member member : members) {
//			if (member.loginId.equals(loginId)) {
//
//				return false;
//			}
//		}

		// 있는지 없지 판단
		if (member == null) {
			return true;
		}

		return false;
	}

	// int id,String regDate, String loginId, String loginPw,String
	// loginPwChk,String name
	public void makeTestData() {
		System.out.println("회원 데이터를 생성합니다");
		members.add(new Member(1, Util.getDate(), "아이디1", "비밀번호1", "비밀번호확인1", "이름1"));
		members.add(new Member(2, Util.getDate(), "아이디2", "비밀번호2", "비밀번호확인2", "이름2"));
		members.add(new Member(3, Util.getDate(), "아이디3", "비밀번호3", "비밀번호확인3", "이름3"));
	}

}
