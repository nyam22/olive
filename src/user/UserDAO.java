package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DatabaseUtil;

public class UserDAO {

	/* 로그인 */
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getString(1).equals(userPassword)) {
					return 1;
				} else {
					return 0;
				}
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {	// 접근 자원 해제
			try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		return -2;		
	}
	
	/* 회원가입 */
	public int Join(UserDTO user) {
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, false)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(1, user.getUserPassword());
			pstmt.setString(1, user.getUserEmail());
			pstmt.setString(1, user.getUserEmailHash());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {	// 접근 자원 해제
			try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		return -1;		// 회원가입 실패
	}
	
	/* 이메일 확인 */
	public boolean getUserEmailChecked(String userID) {
		String SQL = "SELECT userEmailChecked FROM USER WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getBoolean(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {	// 접근 자원 해제
			try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		return false;		
	}
	
	/* 이메일 확인 업데이트 */
	public boolean setUserEmailChecked(String userID) {
		String SQL = "UPDATE USER SET userEmailChecked = true WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {	// 접근 자원 해제
			try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		return false;	
	}
	
	/* 이메일 조회 */
	public String getUserEmail(String userID) {
		String SQL = "SELECT userEmail FROM USER WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {	// 접근 자원 해제
			try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		return null;		
	}
}