package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.Controller;
import com.koreaIT.java.BAM.controller.MemberController;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class App {

	public void run() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);

		articleController.makeTestData();
		memberController.makeTestData();

		while (true) {

			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (cmd.equals("exit")) {
				break;
			}

			// cmd를 한번에 묶어서 찾아내기 위해서 가져 온다
			// 명령어가 어떤 걸로 들어 왔는지 확인 한다 조건문으로
			String[] cmdBits = cmd.split(" ");

			if (cmdBits.length == 1) {
				System.out.println("명령어를 확인해주세요");
				continue;
			}

			String controllerName = cmdBits[0];
			String methodName = cmdBits[1];

			Controller controller = null;

			// member와 같을때
			if (controllerName.equals("member")) {
				// memberController에게 권한 넘기기기
				controller = memberController;
			}
			// article와 같을때
			else if (controllerName.equals("article")) {
				// articleController에게 권한 넘기기기
				controller = articleController;
			} else {
				System.out.println("존재하지 않는 명령어 입니다");
				continue;
			}

			String doactionName = controllerName + "/" + methodName;
//			String[] cmdBits = cmd.split(" ");
			// 해당 공간이 즉 변수가 같다면 그런데 로그인 불펼요한 기능에 따라 나눈다
			// methodName은 뒤에오는 에 name 은 앞에 오는애
			// controllerName은 앞에오는 애

			// 명령어를 먼저 판단하고 로그인 여부 판단하기
			switch (doactionName) {
			case "member/logout":
			case "member/profile":
			case "article/delete":			
			case "article/modify":
			case "article/write":
				// Controller는 설계도에게 일을 시킨다
				if (Controller.isLogined() == false) {
					System.out.println("로그인 후후 이용해주세요");
					
					// 스위치문,반복문,조건문에서 continue는 빠져나가거나 올라간다
				
					continue;
				}
				
				// 스위치문은 반드시 종료를 해주어야 한다 해주지 않으면 아래의 모든 케이스들을 판단한다
				
				break;
				
			case "member/join":
			case "member/login":

				if (Controller.isLogined()) {
					System.out.println("로그아웃 후후 이용해주세요");
					continue;
				}
				break;

			}

//			default : 둘 다 해당되지 않는 경우 default 코드를 실행한다. 
//			default 절은 반드시 존재해야 하는 것은 아니며 필요할 때만 선언 가능하다. (생략가능)

//			switch(doaction) {
//			case "article list":
//			case "articel write":
//				if (Controller.isLogined() != false) {
//					System.out.println("로그인 상태입니다");
//					continue;
//				}
//				break;
//		}

			controller.doAction(cmd, methodName);

		}

		System.out.println("== 프로그램 끝 ==");

		sc.close();

	}

}
