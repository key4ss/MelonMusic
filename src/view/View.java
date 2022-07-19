package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.MMusicVO;

public class View {

	Scanner sc = new Scanner(System.in);
	public int action; // 사용자의 입력값으로 받을 변수생성

	public void startUser(){
		while(true) {
			//UserIndex
			System.out.println("=====Melon : 음악이 필요한순간, 멜론=====");
			System.out.println("1.   로그인");
			System.out.println("2.   회원가입");
			System.out.println("3.   종료");
			System.out.println("====================================");
			System.out.println();
			System.out.print("번호를 입력해주세요>> "); // 입력값 받기

			try { // 예외처리
				action = sc.nextInt(); // 사용자의 입력값받기
				if ((1 <= action && action <= 3)) {
					break; // 1-3 메뉴를 정확히 눌렀을 시 탈출
				} else {
					System.out.println("범위 외의 입력입니다!"); // 범위외의 입력값 입력시 출력
				}
			} catch (Exception e) {
				System.out.println("숫자만 입력하여 주세요!");
				sc.nextLine();
				continue; // 입력 오류시 재출력
			}
		}
	}

	public void startMelon(){
		while(true) {
			//MelonIndex
			System.out.println("=========== Melon Player ===========");
			System.out.println("1.   전체 노래  목록 조회");
			System.out.println("2.   노래검색");
			System.out.println("3.   노래추천");
			System.out.println("4.   마이페이지");
			System.out.println("5.   로그아웃");
			System.out.println("====================================");
			System.out.println();
			System.out.print("번호를 입력해주세요>> "); // 입력값 받기

			try { // 예외처리
				action = sc.nextInt(); // 사용자의 입력값받기
				if ((1 <= action && action <= 5)) {
					break; // 1-5 메뉴를 정확히 눌렀을 시 탈출
				} else {
					System.out.println("범위 외의 입력입니다!"); // 범위외의 입력값 입력시 출력
				}
			} catch (Exception e) {
				System.out.println("숫자만 입력하여 주세요!");
				sc.nextLine();
				continue; // 입력 오류시 재출력
			}
		}
	}

	public void startMyPage(){
		while(true) {
			//MyPageIndex
			System.out.println("============== MyPage ==============");
			System.out.println("1.   최근 들은 노래 정보");
			System.out.println("2.   회원탈퇴");
			System.out.println("3.   마이페이지 나가기");
			System.out.println("====================================");
			System.out.println();
			System.out.print("번호를 입력해주세요>> "); // 입력값 받기

			try { // 예외처리
				action = sc.nextInt(); // 사용자의 입력값받기
				if ((1 <= action && action <= 3)) {
					break; // 1-3 메뉴를 정확히 눌렀을 시 탈출
				} else {
					System.out.println("범위 외의 입력입니다!"); // 범위외의 입력값 입력시 출력
				}
			} catch (Exception e) {
				System.out.println("숫자만 입력하여 주세요!");
				sc.nextLine();
				continue; // 입력 오류시 재출력
			}
		}
	}

	public String inputString() {// 문자열 입력받는 메서드
		System.out.print("입력>> ");
		String str = sc.next();
		return str;
	}

	public int inputInt() {    // 정수 입력받는 메서드
		while(true) {
			try {
				System.out.print("입력>> ");
				int i = sc.nextInt(); // 사용자 입력값
				if (i < 0) {// 정수가 아닌 수를 입력했을 시(유효성 검사)
					System.out.println("정확히 입력해 주세요.");
					continue;
				}
				return i;
			} catch (Exception e) {
				System.out.println("숫자만 입력하여 주세요!");
				sc.nextLine();
				continue; // 입력 오류시에 메뉴판 재출력 반복.
			}
		}
	}

	public void loginPage() {
		System.out.println("로그인 페이지입니다.");
	}

	public void loginGuide() {
		System.out.println("아이디와 비밀번호를 차례대로 입력해주세요.");
	}

	public void writeId() {
		System.out.println("아이디를 입력해주세요.");
	}

	public void writePw() {
		System.out.println("비밀번호를 입력해주세요.");
	}

	public void checkLogin() {
		System.out.println("아이디 또는 비밀번호를 확인해주세요.");
	}

	public void signUpPage() {
		System.out.println("회원가입 메뉴입니다.");
	}

	public void signUpGuide() {
		System.out.println("아이디, 비밀번호, 이름을 차례대로 입력해주세요.");
	}

	public void writeName() {
		System.out.println("이름을 입력해주세요.");
	}

	public void endUser() {
		System.out.println("잠시 후 프로그램이 종료 됩니다.");
		System.out.println("이용해주셔서 감사합니다.");
		for (int i = 0; i < 5; i++) {
			System.out.print(".");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void noExistUser() {
		System.out.println("사용자 정보가 존재하지 않아 시작화면으로 돌아갑니다.");
	}

	public void chartPage() {
		System.out.println("노래 목록을 조회합니다.");
		System.out.println("======= Melon Top 100 =======");
	}

	public void musicChart(ArrayList<MMusicVO> datas) {
		if (datas.size() == 0) { // 저장된 데이터가 없는 경우
			System.out.println("정보가 존재하지 않습니다!");
		} else {
			for(int i=0; i<datas.size(); i++) {
				System.out.println((i+1)+"번 "+datas.get(i).getmTitle()+" - "
						+datas.get(i).getmArtist()+"\t"+"♬:"+datas.get(i).getmHit());
			}
		}
		System.out.println();
	}

	public void searchMusicPage() {
		System.out.println("노래 검색 메뉴입니다.");
	}

	public void writeTA() {
		System.out.println("노래를 검색할 방법을 선택해주세요.");
		System.out.println("1. 제목");
		System.out.println("2. 가수");
	}

	public void searchResult(String str) {
		System.out.println(str+"로 검색한 결과입니다.");
	}

	public void noExistMusic() {
		System.out.println("노래 정보가 존재하지 않아 시작화면으로 돌아갑니다.");
	}

	public void choiceMusic() {
		System.out.println("원하시는 노래의 번호를 입력해주세요.");
	}

	public void listenMusic() {
		for (int i = 0; i < 3; i++) {
			System.out.println(" ~ ♩ ♪ ♬ ♪ ♬ ♩ ♩ ♬ ♪ ~");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println();
	}

	public void musicRecPage() {
		System.out.println("노래 추천 메뉴입니다.");
	}
	public void musicPlayer() {
        System.out.println("1:00  ─|──────────── 5:57");
        System.out.println("   ⇄   ◁◁   II   ▷▷   ↻   ");
        System.out.println("Volume: ■ ■ ■ ■ ■ □ □ □");
        System.out.println();
    }
	public void musicInfo(MMusicVO vo) {
		System.out.println(vo.getmTitle()+"\n"
				+vo.getmArtist()+"\t\t"+"♬:"+vo.getmHit());
	}

	public void endMelon() {
		System.out.println("잠시 후 플레이어가 종료 됩니다.");
		System.out.println("이용해주셔서 감사합니다.");
		for (int i = 0; i < 5; i++) {
			System.out.print(".");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void showLastMusic() {
		System.out.println("최근에 들은 노래 정보메뉴입니다.");
	}

	public void userDeletePage() {
		System.out.println("회원탈퇴메뉴입니다.");
	}

	public void checkDelete() { // Upper로 대/소문자 구별없이 받음
		System.out.println("정말로 탈퇴하시겠습니까? Y/N");
	}

	public void deleteSuc() {
		System.out.println("회원탈퇴가 완료되었습니다.");
	}

	public void deleteFail() {
		System.out.println("회원탈퇴에 실패하셨습니다.");
	}

	public void endMyPage() {
		System.out.println("마이페이지가 종료 됩니다.");
	}

	public void loginSuc() {
		System.out.println("로그인 되었습니다.");
	}

	public void loginFail() {
		System.out.println("로그인에 실패하셨습니다.");
	}

	public void memberSuc() {
		System.out.println("회원가입이 완료되었습니다.");
	}

	public void memberFail() {
		System.out.println("회원가입에 실패하셨습니다.");
	}

	public String inputYN() {// 문자열 입력받고 대문자로 치환
		System.out.print("Y / N 입력>> ");
		String str = sc.next();
		String str2 = str.toUpperCase();
		return str2;
	}

	public void searchTitle() {
		System.out.println("곡명으로 검색합니다.");
	}

	public void searchArtist() {
		System.out.println("가수명으로 검색합니다.");
	}

	public void logOutSuc() {
		System.out.println("로그아웃합니다.");
	}

	public void logOutFail() {
		System.out.println("로그아웃에 실패하였습니다!");
	}
}