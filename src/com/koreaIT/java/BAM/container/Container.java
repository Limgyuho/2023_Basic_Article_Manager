package com.koreaIT.java.BAM.container;

import com.koreaIT.java.BAM.Service.ArticleService;
import com.koreaIT.java.BAM.Service.MemberService;
import com.koreaIT.java.BAM.dao.ArticleDao;
import com.koreaIT.java.BAM.dao.MemberDao;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	public static ArticleService articleService;
	public static MemberService memberService;
	
	//서비스 는 컨테이너에 만들어ㅈ
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		articleService = new ArticleService();
		memberService = new MemberService();
	}
}


