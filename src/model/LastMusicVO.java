package model;

public class LastMusicVO {
	private int lNum;
	private int mNum;
	private int uNum;
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