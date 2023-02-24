package com.koreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.util.Util;

public class ArticleController extends Controller{

	private List<Article> articles;
	private Scanner sc;
	private int lastArticleId;
	//전역변수로 만들어준다
	private String cmd;
	public int memberid;
	
	public ArticleController(Scanner sc) {
		this.articles = new ArrayList<>();
		this.sc = sc;
		this.lastArticleId =3;
	}

	public void doAction(String cmd,String methodName) {

		//전역변수로 만들어준다
		this.cmd = cmd;
				
		switch(methodName) {
		case "write":
			if (isLogined() ==false) {
				System.out.println("로그인 후 이용해주세요");
				return;
			}
			dowrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showdetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
			
		default:
			System.out.println("존재하지 않는 명령어 입니다");
			break;
		}	
				
	}
	private void dowrite() {

		
		int id = lastArticleId + 1;
		lastArticleId = id;

		String regDate = Util.getDate();

		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		//
		Article article = new Article(id, regDate,loginedMember.id, title, body);
		articles.add(article);

		System.out.printf("%d번 글이 생성되었습니다\n", id);

	}

	private void showList() {
		if (articles.size() == 0) {

			System.out.println("게시글이 없습니다");
			return;
		}

		// article list 검색어 를 하였을때 인덱스부터 인식한다
		// 검색어를 뽑아 온것에서 총 길이 에서 trim을 해주어야 한다
		String searchKeyworld = cmd.substring("article list".length()).trim();

		//printArticles 라는 리모컨을 만들어 준다
		
		List<Article> printArticles = new ArrayList<>(articles);
		
		

		if (searchKeyworld.length() > 0) {
			System.out.println("검색어 : " + searchKeyworld);
			//검색어가 있으면 비어있는 객체에 추가한다
			printArticles = new ArrayList<>();
			printArticles.clear();
			
			for (Article article : articles) {
				if (article.title.contains(searchKeyworld)) {
					//제목의 게시물이 추가 된 상태 이다
					printArticles.add(article);
				}
			}
			//검색 결과가 없을 경우 다시 명령을 입력하게 끔 continue를 사용한다
			if(printArticles.size()==0) {
				System.out.println("검색 결과가 존재 하지 않습니다");
				//무한 반복문 안에 있으므로 continue 가 사용 가능하다
				return;
			}

		}

		//printArticles을 순회해준다
		System.out.printf("번호	|	제목	|	날짜	|	작성자	|	조회\n");
		//향상된 for문은 역순회를 해준다
		//
		Collections.reverse(printArticles);
		for (Article article : printArticles) {

			//
			System.out.printf("%d	|	%s	| 	%s	|	%d	|	%d\n", article.id, article.title, article.regDate,article.memberid,
					article.hit);

		}		
	}

	private void showdetail() {
		// 특정 문자 찾기
		String[] cmdBits = cmd.split(" ");
		
		if (cmdBits.length ==2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);

		// 중복 기능 제거
		// 중복 제거 로직이 즉 순회가 id 를 찾아오는 로직이다
		Article foundArticle = articlebyId(id);

		if (foundArticle == null) {
			// 게시물이 없는 경우
			System.out.println("게시글이 없습니다");
			return;
		} else {

			// 조회수 기능은 함수를 이용 하는것을 추천한다
			// 게시글들 마다 구별짓기 위함이다
			// article write을 하였을때 article 객체로 조립되는 순간 개시글이 조회 되는 순간 0이 된다
			// article detail 을 하였을 때 해당 게시글의 조회수가 1증가 함수를 호출
			foundArticle.increase();
			// 게시물이 있는 경우 아래에 있는 경우가 순회안에 있으면 기능의 분리가 되지 않는다
			System.out.printf("번호 : %d\n", foundArticle.id);
			System.out.printf("날짜 : %s\n", foundArticle.regDate);
			System.out.printf("제목 : %s\n", foundArticle.title);
			System.out.printf("내용 : %s\n", foundArticle.body);
			System.out.printf("작성자 : %d\n", foundArticle.memberid);
			// 조회수 기능을 보여주는 기능을 먼저 보여준다
			System.out.printf("조회수 : %d\n", foundArticle.hit);

		}		
	}
	private Article articlebyId(int id) {
		// 중복 기능 제거
		// 찾은 문자를 순회 하는 방법
		for (Article article : articles) {

			if (article.id == id) {
				return article;
				// 게시물의 조건
			}
		}
		return null;
	}

	private void doModify() {
		// 특정 문자 찾기
		String[] cmdBits = cmd.split(" ");
		// integer는 형변환의 일종이다.
		
		
		
		if (cmdBits.length ==2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		int id = Integer.parseInt(cmdBits[2]);

		// modify는 순회를 인덱스로 하기 때문에 인덱스로 만든다

		Article foundArticle = articlebyId(id);

		// 게시물이 없는 경우-
		if (foundArticle == null) {
			System.out.println("게시글이 없습니다");
			return;
		}
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		// 변경된 값을 리모컨의 내부에 넣어준다
		foundArticle.title = title;
		foundArticle.body = body;

		System.out.printf("%d번 게시글을 수정하였습니다\n", id);		
	}

	private void doDelete() {
		// 특정 문자 찾기
		String[] cmdBits = cmd.split(" ");
		// integer는 형변환의 일종이다.
		
		if (cmdBits.length ==2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		
		int id = Integer.parseInt(cmdBits[2]);

		// 명령어 맨뒤에 오는 것을 기준으로 한다
		// 0에서 거꾸로 가야 하기 때문에 -1을 해준다
		Article foundArticle = articlebyId(id);

		// 게시물이 없는 경우-
		if (foundArticle == null) {
			System.out.println("게시글이 없습니다");
			return;
		}

		// articles리스트의 remove 기능을 사용하여 제거한다
		// 값에서 인덱스를 찾기
		articles.remove(articles.indexOf(foundArticle));
		System.out.printf("%d 게시글이 삭제 되었습니다\n", id);		
	}



	public void makeTestData() {
		System.out.println("게시물 데트스 데이터를 생성합니다");
		articles.add(new Article(1, Util.getDate(), 1,"제목1", "테스트", 10));
		articles.add(new Article(2, Util.getDate(), 2,"제목2", "테스트", 20));
		articles.add(new Article(3, Util.getDate(), 3,"제목3", "테스트", 30));
	}
}



