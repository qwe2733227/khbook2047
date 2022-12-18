package member.model.dao;

import static common.jdbc.JdbcTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.model.vo.Member;
import common.jdbc.JdbcTemplate;

public class MemberDao {
	public MemberDao() {}
	//사용자 로그인 메소드
	//매개변수로 받은 ID와 PASSWD로 DataBase에 접속하여 일치하는 데이터 조회
	public Member loginMember(Connection con, String id, String passwd) {
		Member m = null;
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		String query = "SELECT * FROM TEST_MEMBER WHERE ID = ?AND PASSWD=?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id); //첫번째 '?'에 id 값 대입
			pstmt.setString(2, passwd); //두번째 '?'에 passwd 값 대입
			rset = pstmt.executeQuery();
			if(rset.next()) { //첫 열은 head 컬럼이므로 next()로 실제 컬럼값을 가져온다.
				m = new Member();
				m.setId(rset.getString("ID")); //받아온 ID 컬럼 값을 member 변수에 대입
				m.setPasswd(rset.getString("passwd"));
				m.setName(rset.getString("NAME"));
				m.setEmail(rset.getString("EMAIL"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { //ResultSet과 PreparedStatement 리소스를 반환
			close(rset);
			close(pstmt);
		}
		return m; //조회하여 가져온 Member 객체를 반환한다.
		// ID값의 중복을 조회하는 메소드 
		public int dupIdChk(Connection con, String id) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int result = 0;
			//id로 테이블을 조회하여 있으면 1 이상 , 없으면 0인 쿼리 작성
			String query = "SELECT COUNT(*) FROM TEST_MEMBER WHERE ID=?";
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, id);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					result = rset.getInt(1); //rset의 첫 컬럼의 숫자값을 가져온다.
				}
			}catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return result;
		}
		//DateBase에 Member 객체를 추가하는 메소드
		public int insertMember(Connection con, Member m) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "INSERT INTO TEST_MEMBER VALUES(?,?,?,?)";
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, m.getId());
				pstmt.setString(2, m.getPasswd());
				pstmt.setString(3, m.getName());
				pstmt.setString(4, m.getEmail());
				//executeupdate()는 실행 결과를 반영된 행의 개수로 리턴하므로
				// 1 이상은 실행 성공, 0이하(구문 에러 포함)는 실패이다.
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			return result;
		}
		//기존 사용자의 정보를 수정하여 DataBase의 데이터를 수정하는 메소드
		public int updateMember(Connection con, Member m) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "UPDATE TEST_MEMBER SET PASSWD =?, NAME=?, EMAIL=?,WHERE ID=?";
			
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, m.getPasswd());
				pstmt.setString(2, m.getName());
				pstmt.setString(3, m.getEmail());
				pstmt.setString(4, m.getId());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			return result;
		}
	}
}
