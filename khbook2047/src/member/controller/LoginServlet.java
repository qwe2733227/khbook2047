package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/login.lo")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//사용자의 정보를 JSON 형식으로 전달하기 위해 ContentType 변경
		response.setContentType("application/json; charset=UTF-8");
		MemberService mservice = new MemberService();
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		JSONObject job = new JSONObject();
		//입력받은 사용자의 ID와 비밀번호를 인자로 하여 Service의 loginMember()호출
		Member m = mservice.loginMember(id, passwd);
		if(m != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", m);
			job.put("result", "OK" );
			job.put("name", m.getName());
		} else {
			job.put("result", "fail");
		}
		PrintWriter out  = response.getWriter();
		out.println(job.toJSONString());
		out.flush();
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
