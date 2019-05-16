package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.JoinVO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class JoinDAO {

	//관리 선생님 등록
	public boolean getTeacherRegiste(JoinVO jvo) throws Exception {

		String sql = "insert into teacher " + "(t_code, t_id, t_pw, t_name, t_subject)" + " values " + "(teacher_seq.nextval, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean joinSucess = false;

		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, jvo.getT_id()); //아이디
			pstmt.setString(2, jvo.getT_pw()); //비밀번호
			pstmt.setString(3, jvo.getT_name()); //담임선생님 이름
			pstmt.setString(4, jvo.getT_subject());//담당과목
			
			
			
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("관리 선생님 등록");
				alert.setHeaderText(jvo.getT_name() + " 등록.");
				alert.setContentText("관리 선생님 등록 성공!!");
				alert.showAndWait();
				joinSucess = true;
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("관리 선생님 등록");
				alert.setHeaderText("선생님 등록 실패.");
				alert.setContentText("등록 실패!!!");
				alert.showAndWait();
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터 베이스와의 연결에 사용 되었던 오브젝트 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return joinSucess;
	}
	
	// 아이디 중복 체크
	public boolean getIdOverlap(String idOverlap) throws Exception {

		String sql = "select * from teacher where t_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean idOverlapResult = false;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, idOverlap);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				idOverlapResult = true;  // 중복된 아이디 있다
			}
			
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와 연결에 사용되었던 오브젝트를 해제
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return idOverlapResult;
	}
}
