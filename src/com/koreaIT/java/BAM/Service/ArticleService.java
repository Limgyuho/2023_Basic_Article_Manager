package com.koreaIT.java.BAM.Service;

import java.util.List;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Article;

public class ArticleService {


	public List<Article> getprintArticles(String searchKeyworld) {
		return Container.articleDao.getprintArticles(searchKeyworld);
	}

}
