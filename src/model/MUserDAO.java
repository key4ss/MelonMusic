package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MUserDAO {
	Connection conn;
	PreparedStatement pstmt;

	final String sql_insert = "INSERT INTO MUSER VALUES((SELECT NVL(MAX(UNUM),0) +1 FROM MUSER),?,?,?,0)";
	final String sql_selectOne = "SELECT * FROM MUSER WHERE UNUM = ?";
	final String sql_update = "UPDATE MUSER SET ULASTMUSIC = ? WHERE UNUM = ?";
	final String sql_login = "SELECT * FROM MUSER WHERE USERID=?";
	final String sql_delete = "DELETE FROM MUSER WHERE UNUM=?";

	public boolean insert(MUserVO vo) { // 아이디 비밀번호 이름을 입력받고 MUSER 테이블에 insert
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_insert);
			pstmt.setString(1, vo.getuId());
			pstmt.setString(2, vo.getuPw());
			pstmt.setString(3, vo.getuName());
			int res = pstmt.executeUpdate();
			if(res>=1) {
				//				System.out.println("	로그 : user insert가 1개이상 진행됨");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//			System.out.println("	로그 : user insert 실행 중 에러 발생");
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		//		System.out.println("	로그 : user insert가 진행되지 않음");
		return false;
	}

	public MUserVO selectOne(MUserVO vo) { // pk값을 받아오면
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_selectOne);
			pstmt.setInt(1, vo.getuNum());
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				if(rs.getInt("UNUM") == vo.getuNum()) { // pk와 일치하는 데이터를 찾고
					MUserVO data = new MUserVO();
					data.setuId(rs.getString("USERID"));
					data.setuPw(rs.getString("UPW"));
					data.setuName(rs.getString("UNAME"));
					data.setuLastMusic(rs.getInt("ULASTMUSIC"));
					//				System.out.println("	로그 : user selectOne 실행 성공");
					return data; // 해당 데이터 리턴
				}
			}else {
				//				System.out.println("	로그 : user selectOne 실행 실패");
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//			System.out.println("	로그 : user selectOne 실행 중 에러 발생");
			return null;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return null;
	}

	public boolean update(MUserVO vo) { // 최근들은 노래 정보(숫자)와 pk를 받아옴
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_update);
			pstmt.setInt(1, vo.getuLastMusic()); // 노래정보를 담아주고
			pstmt.setInt(2, vo.getuNum()); // pk를 담아준 뒤에
			int res = pstmt.executeUpdate(); // 업데이트 진행
			if(res>=1) { // 진행이 1개이상 되었다면
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

	public MUserVO login(MUserVO vo) { // 로그인 성공시 VO넘겨주고 실패시 null 넘겨주고
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_login);
			pstmt.setString(1, vo.getuId()); // 입력받은 아이디에 정보가 존재하는지
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("UPW").equals(vo.getuPw())) { // 그 정보가 보내준 pw랑 일치하는지
					MUserVO data=new MUserVO();
					data.setuNum(rs.getInt("UNUM"));
					data.setuId(rs.getString("USERID"));
					data.setuPw(rs.getString("UPW"));
					data.setuName(rs.getString("UNAME"));
					data.setuLastMusic(rs.getInt("ULASTMUSIC"));
					System.out.println("로그: 로그인 성공!");
					return data; // 일치하면 해당 데이터 값 담아줘서 리턴
				}
				//				System.out.println("	로그: 비밀번호 불일치로 로그인 실패...");
				return null;
			}
			//			System.out.println("	로그: 회원정보없음 | 로그인 실패...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//			System.out.println("	로그: 로그인 시 오류발생");
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return null;
	}

//	public boolean hasId(MUserVO vo) { // data 넘겨주면 pk로 해당 DB에 존재하는지 확인 t/f
//		//		selectOne으로 구현 불가능?
//		return false;
//	}

	public boolean delete(MUserVO vo) { // pk 받아오면 pk로 삭제
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_delete);
			pstmt.setInt(1, vo.getuNum()); // 받아온 pk 값 넣어주고
			int res = pstmt.executeUpdate(); // delete 실행
			if(res>=1) { // 1개 이상 진행되었다면
				//				System.out.println("	로그 : user delete가 1개이상 진행됨");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//			System.out.println("	로그 : user delete 실행 중 에러 발생");
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		//		System.out.println("	로그 : user delete가 진행되지 않음");
		return false;
	}
}