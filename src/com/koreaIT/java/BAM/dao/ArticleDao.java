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

}
