package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;

@WebServlet("/mdelete.lo")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService mservice = new MemberService();
		int result = mservice.deleteMember(request.getParameter("id"));
		if(result > 0) {
			//기존에 생성되어 있던 세션을 만료
			HttpSession session = request.getSession(false);
			session.invalidate();
		}
		//index 페이지로 이동
		response.sendRedirect("index.jsp");
	}


}
