package model;

public class MUserVO {
	private int uNum;
	private String uId;
	private String uPw;
	private String uName;
	private int uLastMusic;
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
