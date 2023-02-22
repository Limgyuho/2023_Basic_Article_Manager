package com.koreaIT.java.BAM.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static String getDate() {
		Date date = new Date(); // 실행한 날짜와 시간을 가지고 있는 객체 생성
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		return formatter.format(date);
	}
}





