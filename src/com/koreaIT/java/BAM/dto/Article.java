package com.koreaIT.java.BAM.dto;
//데이터 트랜스폼 오브젝트 = vo와 유사한 의미이다

public class Article extends Dto{
	//public을 통하여 다른 패키지와 연동이 가능하다
	public	int id;
	public String title;
	public String body;
	public String regDate;
	public int hit;
	

	public Article(int id,String regDate, String title, String body) {
		//함수 호출 생성자 에서만 가능 하며 생성자의 첫번째 줄만 가능하다
		this(id,regDate,title,body,0);
	}
	public Article(int id,String regDate, String title, String body, int hit) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.hit = hit;
	}
	public void increase() {
		this.hit++;
	}
	
}

