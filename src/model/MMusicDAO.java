package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class MMusicDAO {
	Connection conn;
	PreparedStatement pstmt;

	final String sql_insert = "INSERT INTO MMUSIC VALUES((SELECT NVL(MAX(MNUM),0) +1 FROM MMUSIC),?,?,0)";
	final String sql_selectAll = "SELECT * FROM MMUSIC ORDER BY MHIT DESC";
	final String sql_selectAll1 = "SELECT * FROM MMUSIC WHERE MTITLE LIKE '%'||?||'%' ORDER BY MHIT DESC";
	final String sql_selectAll2 = "SELECT * FROM MMUSIC WHERE MARTIST LIKE '%'||?||'%' ORDER BY MHIT DESC";
	final String sql_selectOne = "SELECT * FROM MMUSIC WHERE MNUM = ?";
	final String sql_rand = "SELECT * FROM (SELECT A.*,ROWNUM AS RNUM FROM (SELECT * FROM MMUSIC ORDER BY MHIT DESC) A WHERE ROWNUM<=5) WHERE RNUM>=1";
	final String sql_sample="SELECT COUNT(*) AS CNT FROM MMUSIC";
	final String sql_update="UPDATE MMUSIC SET MHIT=MHIT+1 WHERE MNUM = ?";

	public boolean insert(MMusicVO vo) { // 크롤링 할때 사용되는 메소드 pk빼고 다받아옴
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_insert);
			pstmt.setString(1, vo.getmTitle());
			pstmt.setString(2, vo.getmArtist());
			int res = pstmt.executeUpdate();
			if(res>=1) {
				//				System.out.println("	로그 : music insert가 1개이상 진행됨");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//			System.out.println("	로그 : music insert 실행 중 에러 발생");
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		//		System.out.println("	로그 : music insert가 진행되지 않음");
		return false;
	}

	public ArrayList<MMusicVO> selectAll(MMusicVO vo){ // 검색출력 2가지 / 전체출력 세가지 기능
		ArrayList<MMusicVO> datas = new ArrayList<MMusicVO>();
		conn=JDBCUtil.connect();
		try {
			if(vo.getmTitle()!=null) {
				pstmt=conn.prepareStatement(sql_selectAll1);
				pstmt.setString(1, vo.getmTitle());
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()) {
					MMusicVO data=new MMusicVO();
					data.setmNum(rs.getInt("MNUM"));
					data.setmTitle(rs.getString("MTITLE"));
					data.setmArtist(rs.getString("MARTIST"));
					data.setmHit(rs.getInt("MHIT"));
					datas.add(data);
				}
				return datas;
			}
			if(vo.getmArtist()!=null) {
				pstmt=conn.prepareStatement(sql_selectAll2);
				pstmt.setString(1, vo.getmArtist());
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()) {
					MMusicVO data=new MMusicVO();
					data.setmNum(rs.getInt("MNUM"));
					data.setmTitle(rs.getString("MTITLE"));
					data.setmArtist(rs.getString("MARTIST"));
					data.setmHit(rs.getInt("MHIT"));
					datas.add(data);
				}
				return datas;
			}
			pstmt=conn.prepareStatement(sql_selectAll);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				MMusicVO data=new MMusicVO();
				data.setmNum(rs.getInt("MNUM"));
				data.setmTitle(rs.getString("MTITLE"));
				data.setmArtist(rs.getString("MARTIST"));
				data.setmHit(rs.getInt("MHIT"));
				datas.add(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		if(datas.size()==0) {
			return null;
		}
		return datas;
	}

	public MMusicVO selectOne(MMusicVO vo){ // pk입력받으면 그 데이터 리턴 없다면 null
		conn = JDBCUtil.connect();
		MMusicVO data = new MMusicVO();
		try {
			pstmt = conn.prepareStatement(sql_selectOne);
			pstmt.setInt(1, vo.getmNum());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt("MNUM") == vo.getmNum()) {
					data.setmTitle(rs.getString("MTITLE"));
					data.setmArtist(rs.getString("MARTIST"));
					data.setmHit(rs.getInt("MHIT"));
					//				System.out.println("	로그 : user selectOne 실행 성공");
					return data;
				}else {
					//				System.out.println("	로그 : user selectOne 실행 실패");
					return null;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//			System.out.println("	로그 : user selectOne 실행 중 에러 발생");
			return null;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return data;
	}
	public MMusicVO randomSelectOne(MMusicVO vo) { // lastsong 번호 입력받으면 해당번호 제외한 random번 노래정보리턴
		//		ArrayList<Integer> datas=new ArrayList<Integer>();
		//		for(int i=0;i<5;i++) {
		//			datas.add(i, selectAll(vo).get(i).getmNum());
		//		}
		//		int r = 0;
		//		while(true) { //// lastmusic 이 1 2 3 4 5가 아니면 같은 곡이라도 진행이 될거같다??
		//			r = (int) Math.floor(Math.random()*datas.size());
		//			if(r == vo.getmNum()) {
		//				continue; // lastsong의 번호와 같다면 다시반복
		//			}else {
		//				break; // 아니면 반복그만
		//			}
		//		}
		ArrayList<MMusicVO> datas=new ArrayList<MMusicVO>();
		conn = JDBCUtil.connect();
		Random rd = new Random();
		try {
			pstmt=conn.prepareStatement(sql_rand);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				MMusicVO data=new MMusicVO();
				data.setmNum(rs.getInt("MNUM"));
				data.setmTitle(rs.getString("MTITLE"));
				data.setmArtist(rs.getString("MARTIST"));
				data.setmHit(rs.getInt("MHIT"));
				datas.add(data);
			}
			int r = 0;
			while(true) { //// lastmusic 이 1 2 3 4 5가 아니면 같은 곡이라도 진행이 될거같다??
				r = rd.nextInt(5);	
				if(datas.get(r).getmNum() == vo.getmNum()) {
					continue; // lastsong의 번호와 같다면 다시반복
				}else {
					break; // 아니면 반복그만
				}
			}
			return datas.get(r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return null;
	}

	public boolean update(MMusicVO vo) { // pk넘겨주면 hit + 1
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_update);
			pstmt.setInt(1, vo.getmNum());
			int res = pstmt.executeUpdate();
			if(res>=1) {
				//				System.out.println("	로그 : user update가 1개이상 진행됨");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//			System.out.println("	로그 : user update 실행 중 에러 발생");
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		//		System.out.println("	로그 : user update가 진행되지 않음");
		return false;
	}

	public boolean hasSample(MMusicVO vo) { // 수업내용대로
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_sample);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			int cnt=rs.getInt("CNT");
			if(cnt>=1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}

}
