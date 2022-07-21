package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LastMusicDAO {
	Connection conn;
	PreparedStatement pstmt;
	final String sql_insert = "INSERT INTO LASTMUSIC VALUES((SELECT NVL(MAX(LNUM),0) +1 FROM LASTMUSIC),?,?)";
//	final String sql_selectAll = "SELECT * FROM (SELECT A.*,ROWNUM AS RNUM FROM (SELECT * FROM LASTMUSIC ORDER BY LNUM DESC) A WHERE ROWNUM<=5) WHERE RNUM>=1";
	final String sql_join = "SELECT * FROM (SELECT MAX(A.LNUM) B, A.MNUM FROM (SELECT * FROM LASTMUSIC WHERE UNUM = ?) A GROUP BY A.MNUM) A, "
			+ "MMUSIC M WHERE A.MNUM=M.MNUM ORDER BY B DESC";
	final String sql_delete = "DELETE FROM LASTMUSIC WHERE UNUM = ?";
	
	public boolean insert(LastMusicVO vo) { // 유저가 곡을 들을때마다 들었던 정보가 추가됨
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_insert);
			pstmt.setInt(1, vo.getmNum()); // 첫번째 물음표에 Music의 pk를 담아줌
			pstmt.setInt(2, vo.getuNum()); // 두번째 물음표에 User의 pk를 담아줌
			int res = pstmt.executeUpdate(); 
			if(res>=1) { // insert가 된게 1건 이상이라면
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return false; // 진행되지 않았다면
	}
//	
//	public ArrayList<LastMusicVO> selectAll(LastMusicVO vo){ // 해당 기능이 사용되지 않을것 같아 주석처리
//		ArrayList<LastMusicVO> datas = new ArrayList<LastMusicVO>();
//		conn=JDBCUtil.connect();
//		try {
//			pstmt=conn.prepareStatement(sql_selectAll);
//			ResultSet rs=pstmt.executeQuery();
//			while(rs.next()) {
//				LastMusicVO data=new LastMusicVO();
//				data.setlNum(rs.getInt("LNUM"));
//				data.setmNum(rs.getInt("MNUM"));
//				data.setuNum(rs.getInt("UNUM"));
//				datas.add(data);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		} finally {
//			JDBCUtil.disconnect(pstmt, conn);
//		}
//		if(datas.size()==0) {
//			return null;
//		}
//		return datas;
//	}
	
	public ArrayList<MMusicVO> lastFive(LastMusicVO vo){ // 해당 유저가 최근 들었던 곡을 5개만 가져오는 메소드
		ArrayList<MMusicVO> list = new ArrayList<MMusicVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_join);
			pstmt.setInt(1, vo.getuNum());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				MMusicVO data = new MMusicVO(); // 음악정보 객체 생성
				data.setmNum(rs.getInt("MNUM")); // mnum에 mnum칼럼의 정보를 담아주고
				data.setmTitle(rs.getString("MTITLE")); //mtitle에 mtitle칼럼의 정보를 담아주고
				data.setmArtist(rs.getString("MARTIST")); // martist에 martist칼럼의 정보를 담아주고
				data.setmHit(rs.getInt("MHIT")); // mhit에 mhit칼럼의 정보를 담아준뒤
				list.add(data); // 해당 data를 list에 추가
				if(list.size() == 5) { // list요소가 5개가 되면
					break; // 종료
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		if(list.size()==0) { // 데이터가 추가되지 않았다면
			return null; // 그만하고 null return
		}
		return list; // 데이터가 추가되었다면 그 리스트를 return
	}
	public boolean delete(LastMusicVO vo) { // 유저가 삭제되면 그 유저의 정보도 삭제되는 메소드
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_delete);
			pstmt.setInt(1, vo.getuNum()); // 첫번째 물음표에 unum 정보를 담아주고
			int rs = pstmt.executeUpdate(); // 쿼리문 실행
			if(rs >= 1) { // 진행이 1개이상 되었다면
				return true; // 리턴
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return false; // if문을 만나지못했다면
	}
}