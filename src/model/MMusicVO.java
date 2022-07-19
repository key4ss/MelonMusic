package model;

public class MMusicVO {
	private int mNum;
	private String mTitle;
	private String mArtist;
	private int mHit;
	public int getmNum() {
		return mNum;
	}
	public void setmNum(int mNum) {
		this.mNum = mNum;
	}
	public String getmTitle() {
		return mTitle;
	}
	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	public String getmArtist() {
		return mArtist;
	}
	public void setmArtist(String mArtist) {
		this.mArtist = mArtist;
	}
	public int getmHit() {
		return mHit;
	}
	public void setmHit(int mHit) {
		this.mHit = mHit;
	}
	@Override
	public String toString() {
		return "MMusicVO [mNum=" + mNum + ", mTitle=" + mTitle + ", mArtist=" + mArtist + ", mHit=" + mHit + "]";
	}	
}
