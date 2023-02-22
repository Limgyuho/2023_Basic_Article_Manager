package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.MemberController;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class App {

	// 스태틱 변수는 스태틱만 참조 가능하다
	private List<Article> articles;
	private List<Member> members;

	App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void run() {
		System.out.println("== 프로그램 시작 ==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);

		int lastArticleId = 3;

		while (true) {

			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (cmd.equals("exit")) {
				break;
			} else if (cmd.equals("member join")) {
				memberController.doJoin();
			} else if (cmd.equals("article write")) {
				articleController.dowrite();
			} else if (cmd.startsWith("article list")) {
				articleController.showList(cmd);
			} else if (cmd.startsWith("article detail ")) {
				articleController.showdetail(cmd);
			} else if (cmd.startsWith("article delete ")) {
				articleController.doDelete(cmd);
			} else if (cmd.startsWith("article modify ")) {
				articleController.doModify(cmd);
			}

			else {
				System.out.println("존재하지 않는 명령어 입니다");
			}

		}

		System.out.println("== 프로그램 끝 ==");

		sc.close();

	}

//		private int articleIndexbyId(int id) {
//			int i = 0;
//			
//			for (Article article : articles) {
//
//				if (article.id == id) {
//					return i;
//				}
//				i++;
//			} 
//			return -1;
//		}

	private void makeTestData() {
		System.out.println("게시물 데트스 데이터를 생성합니다");
		articles.add(new Article(1, Util.getDate(), "제목1", "테스트", 10));
		articles.add(new Article(2, Util.getDate(), "제목2", "테스트", 20));
		articles.add(new Article(3, Util.getDate(), "제목3", "테스트", 30));
	}
}
