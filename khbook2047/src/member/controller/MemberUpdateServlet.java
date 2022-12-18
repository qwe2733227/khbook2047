package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/mupdate.lo")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//기존의 생성된 세션과 세션에 담겨있던 "member" 객체를 불러온다.
		HttpSession session = request.getSession(false);
		Member m =  (Member)session.getAttribute("member");
		
		MemberService mservice = new MemberService();
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		PrintWriter out = response.getWriter();
		
		if(m != null && m.getId().equals(id)) { //만약 ID값이 기존값과 일치한다면 수정 실행
			m.setPasswd(passwd);
			m.setName(name);
			m.setEmail(email);
			if(mservice.updateMember(m) > 0) {
				session.setAttribute("member", m);
				response.sendRedirect("index.jsp");
			} else {
				out.append("<script>alert('회원 정보 수정 오류!\n 관리자에게 문의하세요!);</script>");
			}
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/error/errorPage.jsp");
			request.setAttribute("msg", "회원 정보 수정 오류 발생!!");
			view.forward(request, response);
		}
		out.flush();
		out.close();
	}

}
