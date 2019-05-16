package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.StudentVO;

public class StudentDAO {
	//1 신규 학생 등록
	public StudentVO getStudentregiste(StudentVO sVo)throws Exception{
		//2 데이터 처리를 위한 sql문
		StringBuffer sql = new StringBuffer();
		sql.append("insert into student");
		sql.append(
				"no, s_name, s_year, s_ban, s_number, s_gender, s_phone , s_emergency, s_experience, s_email, s_image");
		sql.append("values (student_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		sql.append("insert into badmintonplay");
		sql.append(
				"c_costfree, c_level, c_time, c_startdate, c_enddate");
		sql.append("values ?, ?, ?, ?, ?");
		
		Connection con=null;
		PreparedStatement pstmt=null;
		StudentVO retval=null;
	try {
		
	
		//3.DBUtil.getConnection()의 메소드로 데이터베이스와 연결
		con=DBUtil.getConnection();
		
		//4.입력받은 정보를 처리하기 위해 sql문장을 생성
		pstmt= con.prepareStatement(sql.toString());
		pstmt.setString(1, sVo.getS_name());
		pstmt.setInt(2, sVo.getS_year());
		pstmt.setInt(3, sVo.getS_ban());
		pstmt.setInt(4, sVo.getS_number());
		pstmt.setString(5,sVo.getS_gender());
		pstmt.setString(6, sVo.getS_phone());
		pstmt.setString(7, sVo.getS_emergency());
		pstmt.setString(8, sVo.getC_costfree());
		pstmt.setInt(9, sVo.getC_time());
		pstmt.setString(10, sVo.getS_experience());
		pstmt.setString(11, sVo.getC_level());
		pstmt.setString(12, sVo.getC_startdate());
		pstmt.setString(13, sVo.getC_enddate());
		pstmt.setString(14, sVo.getS_email());
		pstmt.setString(15, sVo.getS_image());
		
		//5.sql문을 수행후 처리결과를 얻어옴
		int i = pstmt.executeUpdate();
		
		retval=new StudentVO();
		
	}catch(SQLException e) {
		System.out.println("e=["+e+"]");
	}catch(Exception e) {
		System.out.println("e=["+e+"]");
	}finally {
		try {
			//6데이터 베이스와의 연결에 사용되었던 오브젝트를 해제
			if(pstmt!=null)
				pstmt.close();
			if(con!=null)
				con.close();
			
		}catch(SQLException e) {
			
		}
	}
return retval;
}
	//학생 전체 리스트
	public ArrayList<StudentVO> getStudentTotal(){
		ArrayList<StudentVO> list= new ArrayList<StudentVO>();
		StringBuffer sql= new StringBuffer();
		sql.append("select no, s_name, s_year, s_ban, s_number, s_gender, ");
		sql.append("s_phone, s_emergency, c_costfree, c_time, s_experience, c_level, ");
		sql.append("c_startdate, c_enddate, s_email, s_image from student, badmintonplay order by no desc");

			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs =null;
			StudentVO sVo=null;
			try {
				con=DBUtil.getConnection();
				pstmt = con.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					sVo=new StudentVO();
					sVo.setS_no(rs.getInt("S_no"));
					sVo.setS_name(rs.getString("S_name"));
					sVo.setS_year(rs.getInt("S_year"));
					sVo.setS_ban(rs.getInt("S_ban"));
					sVo.setS_number(rs.getInt("S_number"));
					sVo.setS_gender(rs.getString("S_gender"));
					sVo.setS_phone(rs.getString("S_phone"));
					sVo.setS_emergency(rs.getString("S_emergency"));
					sVo.setC_costfree(rs.getString("C_costfree"));
					sVo.setC_time(rs.getInt("C_time"));
					sVo.setS_experience(rs.getString("S_experience"));
					sVo.setC_level(rs.getString("C_level"));
					sVo.setC_startdate(rs.getString("C_startdate"));
					sVo.setC_enddate(rs.getString("C_enddate"));
					sVo.setS_email(rs.getString("S_email"));
					sVo.setS_image(rs.getString("S_image"));
					
					list.add(sVo);
					
				}
			}catch(SQLException se) {
				System.out.println(se);
			}catch(Exception e) {
				System.out.println(e);
			}finally {
				try {
					if(rs!=null)
						rs.close();
					if(pstmt!=null)
						pstmt.close();
					if(con!=null)
						con.close();
				}catch(SQLException se) {
					
				}
			}
			return list;
		
	}
}