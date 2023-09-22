package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/res")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//응답형식설정 : MIME "application/json", "text/plain"
		response.setContentType("text/html;charset=utf-8"); //mime형식 
		//응답출력스트림 얻기
		PrintWriter out = response.getWriter();
		//ServletOutputStream sos = response.getOutputStream();
		out.print("<h1>WELCOME</h1> <div>오문정's 서블릿입니다</div>");
		out.print("<table>");
		out.print("<tr>");
		for(int i=1;i<=5;i++) {
			out.print("<td>");
			out.print(i);
			out.print("</td>");
		}
		out.print("</tr>");
		out.print("</table>");
	}

}
