package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MoveServlet
 */
@WebServlet("/move")
public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("http://www.naver.com"); //응답헤더 설정 후 응답됨
		//RequestDispatcher rd = request.getRequestDispatcher("http://www.naver.com");
		//rd.forward(request, response);
		
		//RequestDispatcher rd = request.getRequestDispatcher("req");
		//rd.forward(request, response);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//out.print("BEFORE FORWARD");
		//RequestDispatcher rd = request.getRequestDispatcher("res");
		//rd.forward(request, response);
		//out.print("AFTER FORWARD");
		
		out.print("BEFORE INCLUDE");
		RequestDispatcher rd = request.getRequestDispatcher("res");
		rd.include(request, response);
		out.print("AFTER FORWARD");
	}

}
