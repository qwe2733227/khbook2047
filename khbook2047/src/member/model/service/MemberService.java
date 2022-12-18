package member.model.service;

import java.sql.Connection;

import common.jdbc.JdbcTemplate;
import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {
	public MemberService() {}
	
	//로그인 시 Member 객체를 받아오는 메소드
	public Member loginMember(String id, String passwd) {
		Connection con = JdbcTemplate.getConnection();
		Member m = new MemberDao().loginMember(con, id, passwd);
		close(con);
		return m;
	}
	//ID 중복 체크를 위한 메소드
	public int dupIdChk(String id) {
		Connection con = JdbcTemplate.getConnection();
		int result = new MemberDao().dupIdChk(con,id);
		return result;
	}
	
	//Member 객체를 추가하는 메소드
	public int insertMember(Member m) {
		Connection con = JdbcTemplate.getConnection();
		int result = new MemberDao().insertMember(con,m);
		if(result > 0) commit(con);
		else rollback(con);
		close(con);
		
		return result;
	}
	
	//기존 Member 객체의 정보를 수정하는 메소드
	public int updateMember(Member m) {
		Connection con = JdbcTemplate.getConnection();
		int result = new MemberDao().updateMember(con, m);
		
		if(result > 0) commit(con);
		else rollback(con);
		close(con);
		
		return result;
	}
	//멤버 객체의 삭제를 요청하는 메소드
	public int deleteMember(String id) {
		Connection con = JdbcTemplate.getConnection();
		int result = new MemberDao().deleteMember(con, id);
		
		if(result > 0) commit(con);
		else rollback(con);
		close(con);
		
		return result;
	}
}

