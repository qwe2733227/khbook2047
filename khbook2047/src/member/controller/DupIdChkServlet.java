package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

@WebServlet("/dupid.lo")
public class DupIdChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DupIdChkServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService mservice = new MemberService();
		int result = mservice.dupIdChk(request.getParameter("id"));
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			out.append("fail"); //만약 dupIdChk()의 결과값이 0 이상이면 'fail'
		} else {
			out.append("ok"); //결과값이  0 보다 크지 않으면, 'ok'를 담아서 보낸다.
		}
		out.flush();
		out.close();
	}


}
