package com.koreaIT.java.BAM.Service;

import java.util.List;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dao.ArticleDao;
import com.koreaIT.java.BAM.dto.Article;

public class ArticleService {


	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = Container.articleDao;
	}
	
	
	public List<Article> getprintArticles(String searchKeyworld) {
		return Container.articleDao.getprintArticles(searchKeyworld);
	}

	public int lastArticleId() {
		return Container.articleDao.lastArticleId();
	}

	//리턴이 필요 없는 것이라 타입은 void이다
	public void add(Article article) {
		
		Container.articleDao.add(article);
	}

	public Article articlebyId(int id) {
		return Container.articleDao.articlebyId(id);
	}

	public void remove(Article foundArticle) {

		articleDao.remove(foundArticle);
	}


	public void ArticleModify(Article foundArticle, String title, String body) {

		Container.articleDao.articleModify(foundArticle, title, body);
	}

}
