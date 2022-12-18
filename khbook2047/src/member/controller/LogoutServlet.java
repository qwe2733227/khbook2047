package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout.lo")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션을 request를 통해 받아올 때, 기존에 생성 되어 있지 않다면.
		// 새로 만드는 것을 원치 않기 때문에 false 값을 인자로 한다.
		HttpSession session = request.getSession(false);
		
		//만약 세션이 null이 아니라면 해당 세션을 만료 시켜 세션 정보를 없앤다.
		if(session != null) session.invalidate();
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
