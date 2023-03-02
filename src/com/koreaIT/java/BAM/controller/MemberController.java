package com.koreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.Service.MemberService;
import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class MemberController extends Controller {

	private Scanner sc;
	private MemberService memberService;

	public MemberController(Scanner sc) {
		this.memberService = Container.memberService;
		this.sc = sc;

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

	private void doJoin() {

		int id = memberService.lastArticleId();
		String regDate = Util.getDate();

		String loginId = null;

		;
		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (memberService.loginIdDupChk(loginId) == false) {
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
		memberService.add(member);

		System.out.printf("%s 회원님 환영 합니다\n", loginId);

	}

	private void doLogin() {

		Member member = null;
		String loginPw = null;

		while (true) {

			System.out.printf("로그인 아이디 : ");
			String loginId = sc.nextLine();

			if (loginId.trim().length() == 0) {
				System.out.println("로그인 아이디를 입력해주세요");
				continue;
			}

			while (true) {
				System.out.printf("로그인 비밀번호 : ");
				loginPw = sc.nextLine();

				if (loginPw.trim().length() == 0) {
					System.out.println("로그인 아이디를 입력해주세요");
					continue;
				}
				break;
			}

			// 로그인후 로그아웃 되지 않게 저장할수 있게 하는 공간을 만들어 인자로 저장한
			member = memberService.getMemberByLoginId(loginId);

			// 아이디가 없는 경우 일치하는것이 없는 경우
			if (member == null) {
				System.out.println("존재하지 않는 아이디 입니다");
				return;
			}

			if (member.loginPw.equals(loginPw) == false) {
				System.out.println("비밀번호가 일치 하지 않습니다");
				return;
			}
			break;
		}

		// 로그인시 정보를 저장
		loginedMember = member;

		System.out.printf("로그인 성공! %s님 환영합니다\n", member.name);

	}

	private void doLogout() {
		loginedMember = null;
		System.out.println("로그아웃 되었습니다");
	}

	private void doprofile() {

		System.out.println("==내정보==");
		System.out.printf("로그인 아이디: %s\n", loginedMember.loginId);
		System.out.printf("이름 : %s\n", loginedMember.name);
		System.out.printf("회원 비밀번호 : %s\n", loginedMember.loginPw);
		System.out.printf("회원 번호 : %d\n", loginedMember.id);
		System.out.printf("가입날짤 : %s\n", loginedMember.regDate);

	}

	public void makeTestData() {
		System.out.println("회원 데이터를 생성합니다");
		memberService.add(new Member(memberService.lastArticleId(), Util.getDate(), "아이디1", "비밀번호1", "비밀번호확인1", "이름1"));
		memberService.add(new Member(memberService.lastArticleId(), Util.getDate(), "아이디2", "비밀번호2", "비밀번호확인2", "이름2"));
		memberService.add(new Member(memberService.lastArticleId(), Util.getDate(), "아이디3", "비밀번호3", "비밀번호확인3", "이름3"));

	}

}
