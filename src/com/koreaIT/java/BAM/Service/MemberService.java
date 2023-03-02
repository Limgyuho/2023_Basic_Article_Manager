package com.koreaIT.java.BAM.Service;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dao.MemberDao;
import com.koreaIT.java.BAM.dto.Member;

public class MemberService {

	private MemberDao memberDao;

	public MemberService() {
		this.memberDao = Container.memberDao;
	}

	public int lastArticleId() {
		return memberDao.lastArticleId();
	}

	public void add(Member member) {

		memberDao.add(member);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public boolean loginIdDupChk(String loginId) {
		return Container.memberDao.loginIdDupChk(loginId);
	}

	public String getWriterName(int memberid) {
		return Container.memberDao.getWriterName(memberid);
	}

	

}
