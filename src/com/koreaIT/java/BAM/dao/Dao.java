package com.koreaIT.java.BAM.dao;

import com.koreaIT.java.BAM.dto.Member;

public abstract class Dao {
	
	public int lastId;
	

	public int lastArticleId() {

		// 실제로는 이곳에서 직접 하게 한다
		// 이곳에서 글번호를 하나씩 증가하는 증감식을 작성하고
		// 기본은 0으로 시작한다 언제? 아티클이 조립이 될때마다
		// 메서드와 변수 만든다 아티클이 애드 될때마다 1씨 증가 된다
		return lastId + 1;

	}
	
}
