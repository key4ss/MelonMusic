package controller;

import java.util.ArrayList;

import model.LastMusicDAO;
import model.LastMusicVO;
import model.MMusicDAO;
import model.MMusicVO;
import model.MUserDAO;
import model.MUserVO;
import view.View;

public class Controller {
	private MUserDAO uDAO;
	private MMusicDAO mDAO;
	private LastMusicDAO lDAO;
	private View view;
	public Controller() {
		uDAO = new MUserDAO();
		mDAO = new MMusicDAO();
		lDAO = new LastMusicDAO();
		view = new View();
		if(!mDAO.hasSample(null)) { // MMUSIC 테이블에 저장된 정보가 없다면
			Crawling.sample();
		}
	}
	//// MUserVO uVo
	//// MMusicVO mVo
	//// LastMusic lVo
	public void startApp() {
		while(true) {
			view.startUser(); // 초기화면
			if(view.action == 1) { // 로그인
				view.loginPage(); // 로그인 페이지
				view.loginGuide(); // 아이디 비밀번호 순서대로 입력
				MUserVO uVo = new MUserVO(); // 임시객체 만들어주고
				view.writeId(); // 아이디 입력 안내
				uVo.setuId(view.inputString()); // 임시객체 .uID에 입력받은 아이디 대입
				view.writePw(); // 비밀번호 입력 안내
				uVo.setuPw(view.inputString()); // 임시객체 .uPw에 입력받은 비밀번호 대입
				MUserVO data = uDAO.login(uVo);
				if(data != null) { // 성공시
					view.loginSuc(); // 로그인 성공
					while(true) { // 와일문시작
						//// MUserDAO 아이디가 존재하는 메서드 아마 불린타임?
						if(uDAO.selectOne(data) != null) { // 정보가 있으면
							view.startMelon(); // 멜론 프로그램의 시작
							if(view.action == 1) { // 전체 노래 목록 조회
								view.chartPage(); // 노래목록을 조회합니다 실행 안내
								MMusicVO mVo = new MMusicVO(); // 임시객체 만들어주고
								view.musicChart(mDAO.selectAll(mVo)); // 임시객체로 노래목록 조회
							}
							else if(view.action == 2) { // 노래검색
								view.searchMusicPage(); // 노래 검색 메뉴입니다
								MMusicVO mVo = new MMusicVO();
								LastMusicVO lVo = new LastMusicVO();
								while(true) {
									view.writeTA(); // 곡 명이나 가수명을 입력하여 주세요 출력
									int i = view.inputInt();
									if(i == 1) { // 곡명으로 검색
										view.searchTitle(); // 곡명으로 검색합니다 출력
										String a = view.inputString();
										view.searchResult(a); // a로 검색한 결과입니다 출력
										mVo.setmTitle(a); // 검색한 a를 v에 setmTitle
										view.musicChart(mDAO.selectAll(mVo)); // a로 검색한 결과 값
										if(mDAO.selectAll(mVo).size() == 0) { // 검색한 결과가 없다면 다시
											continue;
										}
										view.musicPlayer();
										view.choiceMusic(); // 원하는 노래 번호를 입력해주세요 출력
										mVo.setmNum(mDAO.selectAll(mVo).get(view.inputInt()-1).getmNum());
										if(mDAO.update(mVo)) { // 조회수 +1
											view.listenMusic(); // 듣는중
											data.setuLastMusic(mVo.getmNum()); // 최근 들은 노래 정보를 사용자 입력값으로 set
											uDAO.update(data);
											lVo.setmNum(mVo.getmNum());
											lVo.setuNum(data.getuNum());
											lDAO.insert(lVo);
											break;
										}
									}
									else if(i == 2){ // 가수명으로 검색
										view.searchArtist(); // 가수명으로 검색합니다 출력
										String a = view.inputString();
										view.searchResult(a); // a로 검색한 결과입니다 출력
										mVo.setmArtist(a); // 검색한 a를 v에 setmArtist
										view.musicChart(mDAO.selectAll(mVo)); // a로 검색한 결과 값
										if(mDAO.selectAll(mVo).size() == 0) { // 검색한 결과가 없다면 다시
											continue;
										}
										view.musicPlayer();
										view.choiceMusic(); // 원하는 노래 번호를 입력해주세요 출력
										mVo.setmNum(mDAO.selectAll(mVo).get(view.inputInt()-1).getmNum());
										if(mDAO.update(mVo)) { // 조회수 +1
											view.listenMusic(); // 듣는중
											data.setuLastMusic(mVo.getmNum()); // 최근 들은 노래 정보를 사용자 입력값으로 set
											uDAO.update(data);
											lVo.setmNum(mVo.getmNum());
											lVo.setuNum(data.getuNum());
											lDAO.insert(lVo);
											break;
										}
									}
									else { // 1, 2 입력이 아닐시
										/////////////////////////// 다시입력해주세요
										continue;
									}
								}
							}
							else if(view.action == 3) { // 노래추천
								view.musicRecPage(); // 노래 추천 메뉴입니다 출력
								MMusicVO mVo = new MMusicVO();
								MMusicVO mVo2 = new MMusicVO();
								LastMusicVO lVo = new LastMusicVO();
								mVo.setmNum(data.getuLastMusic());
								//								mDAO.randomSelectOne(v); // MUser uLastmusic 넘겨줌
								mVo2 = mDAO.randomSelectOne(mVo);
								view.musicInfo(mVo2); // MUser uLastmusic 넘겨주고 랜덤노래 데이터 출력
								view.musicPlayer();
								if(mDAO.update(mVo2)) { // 조회수 +1
									view.listenMusic(); // 듣는중
									data.setuLastMusic(mVo2.getmNum()); // 최근 들은 노래 정보를 사용자 입력값으로 set
									uDAO.update(data);
									lVo.setmNum(mVo2.getmNum());
									lVo.setuNum(data.getuNum());
									lDAO.insert(lVo);
								}
							}
							else if(view.action == 4) { // 마이페이지
								while(true) {
									view.startMyPage(); // 마이페이지 시작 화면
									MMusicVO mVo = new MMusicVO();
									LastMusicVO lVo = new LastMusicVO();
									lVo.setuNum(data.getuNum());
									if(view.action == 1) { // 최근 들은 노래 정보보기
										view.showLastMusic(); // 최근 들은 노래정보를 출력합니다 출력
										//// 배열에서 mNum을 뽑아서 출력해야하는데 
										//// 출력부분에 vo를 받고
										//// selectAll이 아니고 selectOne을 해서 size만큼???
//										for(int i = 0; i < lDAO.selectAll(lVo).size(); i++) {
//											mVo.setmNum(lDAO.selectAll(lVo).get(i).getmNum());
//											view.musicInfo(mDAO.selectOne(mVo));
//										}
										ArrayList<MMusicVO> datas= lDAO.lastFive(lVo);
										if(datas == null) {
											view.noExistMusic();
											continue;
										}
										view.musicChart(datas);
									}
									else if(view.action == 2) { // 회원탈퇴
										view.userDeletePage(); // 회원 탈퇴 메뉴입니다 출력
										view.checkDelete(); // 탈퇴하시겠습니까 Y/N 출력
										String str=view.inputYN();
										if(str.equals("Y")) { // Y 입력시
											uDAO.delete(data); // 삭제
											view.deleteSuc();
											break;
										}
										else if(str.equals("N")){ // N 입력시
											view.deleteFail();
										}
										else {
											/////////////////// 잘못입력
										}
									}
									else {
										view.endMyPage(); // 마이페이지 종료 출력
										break;
									}
								}
							}
							else if(view.action == 5) { // 로그아웃
								view.logOutSuc(); // 로그아웃합니다 출력
								break;
							}	
						}
						else { // 정보가 없으면
							view.noExistUser(); // 사용자 정보가 존재하지 않음
							break;
						}	
					} // 와일문 종료
				}
				else { // 실패시
					view.loginFail(); // 로그인 실패
				}
			}
			else if(view.action == 2) { // 회원가입
				view.signUpPage(); // 회원가입 페이지
				view.signUpGuide(); // 아이디, 비밀번호, 이름 순서대로 입력해달라는 안내 문구
				MUserVO uVo = new MUserVO();
				view.writeId(); // 아이디 입력 안내
				uVo.setuId(view.inputString());
				view.writePw(); // 비밀번호 입력 안내
				uVo.setuPw(view.inputString());
				view.writeName(); // 이름 입력 안내
				uVo.setuName(view.inputString());
				//// 아이디 중복 긴가민가해서 적어둠
				if(uDAO.insert(uVo)) {// 해당 객체로 insert 진행
					view.memberSuc(); // 성공
				}
				else {
					view.memberFail(); // 실패
				}
			}
			else if(view.action == 3) { // 종료
				view.endUser();
				break;
			}
		}

	}

}