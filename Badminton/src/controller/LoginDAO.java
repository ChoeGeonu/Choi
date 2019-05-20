package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

	// 아이디 중복 체크
	public boolean getLogin(String loginId, String loginPassword) throws Exception {

		String sql = "select * from teacher where t_id = ? and t_pw = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean loginResult = false;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, loginPassword);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				loginResult = true; // 아이디 패스워드 일치
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터 베이스 연결에 사용 되었던 오브젝트 해제
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return loginResult;
	}

	// 선생님이름
	public String getLoginName(String loginId) throws Exception {

		String sql = "select t_name from teacher where t_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String loginName = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				loginName = rs.getString(1);
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return loginName;
	}

	// 담당과목 이름 변경할것
	public String getLoginName1(String loginId1) throws Exception {

		String sql = "select t_subject from teacher where t_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String loginName1 = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId1);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				loginName1 = rs.getString(1);
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return loginName1;
	}

}
