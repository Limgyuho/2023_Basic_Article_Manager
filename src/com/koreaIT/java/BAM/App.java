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

			
			if(cmdBits.length ==1) {
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
				controller =  articleController;
			} else {
				System.out.println("존재하지 않는 명령어 입니다");
				continue;
			}

			controller.doAction(cmd, methodName);

		}

		System.out.println("== 프로그램 끝 ==");

		sc.close();

	}

}
