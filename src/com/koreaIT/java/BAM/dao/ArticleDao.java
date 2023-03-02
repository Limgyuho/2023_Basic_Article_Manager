package com.koreaIT.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;

public class ArticleDao extends Dao {
	
	private List<Article> articles;

	public ArticleDao() {
		this.articles = new ArrayList<>();
	}

	public void add(Article article) {

		articles.add(article);
		lastId++;
	}

	public List<Article> getprintArticles(String searchKeyworld) {
		if (searchKeyworld.length() > 0 ) {
			System.out.println("검색어 : " + searchKeyworld);
			// 검색어가 있으면 비어있는 객체에 추가한다
			//
			
			List<Article> printArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchKeyworld)) {
					// 제목의 게시물이 추가 된 상태 이다
					printArticles.add(article);
				}
			}

			return printArticles;

		}
		return articles;
	}
	

	public Article articlebyId(int id) {
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

	public void remove(Article foundArticle) {
		articles.remove(foundArticle);		
	}

	public void articleModify(Article foundArticle, String title, String body) {

		foundArticle.title = title;
		foundArticle.body = body;
	}

}
