package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/minsert.lo")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberInsertServlet() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService mservice = new MemberService();
		//전달 받은 파라미터 값을 변수에 담아 새로운 객체를 생성한다.
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		int result = mservice.insertMember(new Member(id, passwd, name, email));
		if(result > 0) {
			response.sendRedirect("index.jsp");
		} else { }
	}

}
