package model;

public class MUserVO {
	private int uNum; // 유저 pk 1++
	private String uId; // 유저 아이디
	private String uPw; // 유저 패스워드
	private String uName; // 유저 이름
	private int uLastMusic; // 유저가 들은 마지막 곡에대한 pk
	public int getuNum() {
		return uNum;
	}
	public void setuNum(int uNum) {
		this.uNum = uNum;
	}
	
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuPw() {
		return uPw;
	}
	public void setuPw(String uPw) {
		this.uPw = uPw;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public int getuLastMusic() {
		return uLastMusic;
	}
	public void setuLastMusic(int uLastMusic) {
		this.uLastMusic = uLastMusic;
	}
	@Override
	public String toString() {
		return "MUserVO [uNum=" + uNum + ", uID=" + uId + ", uPw=" + uPw + ", uName=" + uName + ", uLastMusic="
				+ uLastMusic + "]";
	}
	
	
}