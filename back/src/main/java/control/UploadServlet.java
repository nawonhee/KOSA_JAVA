package control;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post방식의 요청인 경우 요청바디의 형식은 application/x-www-form-urlencoded입니다
//		String tValue=request.getParameter("t");
//		String f1Value=request.getParameter("f1");
		
		//post방식의 요청인 경우 요청바디의 형식은 multi-part/form-data인 경우
		ServletInputStream sis = request.getInputStream();
		Scanner sc = new Scanner(sis);
		while(sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}
	}

}
