package model;

public class LastMusicVO {
	private int lNum; // 실행되었던 곡들에 대한 정보
	private int mNum; // 어떤 음악을 들었는지를 알려주는 music의 pk정보가 담김
	private int uNum; // 어떤 유저가 들었는지를 알려주는 user의 pk정보가 담김
	public int getlNum() {
		return lNum;
	}
	public void setlNum(int lNum) {
		this.lNum = lNum;
	}
	public int getmNum() {
		return mNum;
	}
	public void setmNum(int mNum) {
		this.mNum = mNum;
	}
	public int getuNum() {
		return uNum;
	}
	public void setuNum(int uNum) {
		this.uNum = uNum;
	}
	@Override
	public String toString() {
		return "LastMusicVO [lNum=" + lNum + ", mNum=" + mNum + ", uNum=" + uNum + "]";
	}
}