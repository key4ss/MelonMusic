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

	public void loginPage() { //로그인 페이지 안내 출력
		System.out.println("로그인 페이지입니다.");
	}

	public void loginGuide() { //로그인 페이지에서 아이디와 비밀번호 입력을 위한 문구 출력
		System.out.println("아이디와 비밀번호를 차례대로 입력해주세요.");
	}

	public void writeId() { // 아이디 입력을 위한 문구 출력
		System.out.println("아이디를 입력해주세요.");
	}

	public void writePw() { // 비밀번호 입력을 위한 문구 출력
		System.out.println("비밀번호를 입력해주세요.");
	}

	public void checkLogin() { // 아이디 또는 비밀번호가 올바르지 않은 경우 문구 출력
		System.out.println("아이디 또는 비밀번호를 확인해주세요.");
	}

	public void signUpPage() { // 회원 가입 페이지 안내 출력
		System.out.println("회원가입 메뉴입니다.");
	}

	public void signUpGuide() { // 회원 가입 페이지에서 아이디, 비밀번호, 이름을 입력받기 위한 문구 출력
		System.out.println("아이디, 비밀번호, 이름을 차례대로 입력해주세요.");
	}

	public void writeName() { // 이름을 입력 받는 문구 출력
		System.out.println("이름을 입력해주세요.");
	}

	public void endUser() { // UserIndex 종료시 문구 출력
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

	public void noExistUser() { // 사용자 정보가 존재하지 않을 시 출력할 문구
		System.out.println("사용자 정보가 존재하지 않아 시작화면으로 돌아갑니다.");
	}

	public void chartPage() { // 노래 목록을 출력할 시에 나오는 문구
		System.out.println("노래 목록을 조회합니다.");
		System.out.println("======= Melon Top 100 =======");
	}

	public void musicChart(ArrayList<MMusicVO> datas) { // 노래 목록을 출력할 양식
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

	public void searchMusicPage() { // 노래 검색 메뉴 안내 문구 출력
		System.out.println("노래 검색 메뉴입니다.");
	}

	public void writeTA() { // 노래 검색 메뉴 선택 시 검색 방법 택하는 문구 출력
		System.out.println("노래를 검색할 방법을 선택해주세요.");
		System.out.println("1. 제목");
		System.out.println("2. 가수");
	}

	public void searchResult(String str) { // 특정 단어로 노래 검색 성공 시 결과 출력한는 메뉴
		System.out.println(str+"로 검색한 결과입니다.");
	}

	public void noExistMusic() { // 노래 정보가 존재하지 않을 시 출력하는 문구
		System.out.println("노래 정보가 존재하지 않아 이전화면으로 돌아갑니다.");
	}

	public void choiceMusic() { // 출력되는 리스트 중 선택하는 특정 값을 입력을 받기 위한 안내 문구
		System.out.println("원하시는 노래의 번호를 입력해주세요.");
	}

	public void listenMusic() { // 노래를 선택하여 감상할 때 출력되는 문구
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

	public void musicRecPage() { // 노래 추천 메뉴 안내 문구
		System.out.println("노래 추천 메뉴입니다.");
	}
	
	public void musicPlayer() { // 음악 플레이어 모양
        System.out.println("1:00  ─|──────────── 5:57");
        System.out.println("   ⇄   ◁◁   II   ▷▷   ↻   ");
        System.out.println("Volume: ■ ■ ■ ■ ■ □ □ □");
        System.out.println();
    }
	
	public void musicInfo(MMusicVO vo) { // 노래의 정보를 출력하는 문구
		System.out.println("\n"
				+ "─♪───────────────────────");
		System.out.println(vo.getmTitle()+"\n"
				+vo.getmArtist()+"\t\t"+"♬:"+vo.getmHit());
		System.out.println("───────────────────────♪─"+"\n");
	}

	public void endMelon() { //MelonIndex를 종료할때 나오는 문구
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

	public void showLastMusic() { // 최근에 들은 노래 정보 메뉴 안내 문구
		System.out.println("최근에 들은 노래 정보메뉴입니다.");
	}

	public void userDeletePage() { // 회원탈퇴메뉴 안내 문구
		System.out.println("회원탈퇴메뉴입니다.");
	}

	public void checkDelete() { // 회원탈퇴시 확인 문구 이후 InputY/N에서 Upper로 대/소문자 구별없이 받음
		System.out.println("정말로 탈퇴하시겠습니까? Y/N");
	}

	public void deleteSuc() { // 회원탈퇴 성공시 출력 문구
		System.out.println("회원탈퇴가 완료되었습니다.");
	}

	public void deleteFail() { // 회원탈퇴 실패시 출력 문구
		System.out.println("회원탈퇴에 실패하셨습니다.");
	}

	public void endMyPage() { // MyPageIndex가 종료될때 나오는 문구
		System.out.println("마이페이지가 종료 됩니다.");
	}

	public void loginSuc() { // 로그인 성공 문구
		System.out.println("로그인 되었습니다.");
	}

	public void loginFail() { // 로그인 실패 문구
		System.out.println("로그인에 실패하셨습니다.");
	}

	public void memberSuc() { // 회원가입 성공 문구
		System.out.println("회원가입이 완료되었습니다.");
	}

	public void memberFail() { // 회원가입 실패 문구
		System.out.println("회원가입에 실패하셨습니다.");
	}

	public String inputYN() {// 문자열 입력받고 대문자로 치환
		System.out.print("Y / N 입력>> ");
		String str = sc.next();
		String str2 = str.toUpperCase();
		return str2;
	}

	public void searchTitle() { // 노래 검색 시 곡명으로 검색 선택시 출력 문구
		System.out.println("곡명으로 검색합니다.");
	}

	public void searchArtist() { // 노래 검색 시 가수명으로 검색 선택시 출력 문구
		System.out.println("가수명으로 검색합니다.");
	}

	public void logOutSuc() { // 로그아웃 성공 문구
		System.out.println("로그아웃합니다.");
	}

	public void logOutFail() { // 로그아웃 실패 문구
		System.out.println("로그아웃에 실패하였습니다!");
	}
}