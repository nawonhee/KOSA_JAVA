package control;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestServlet
 */
@WebServlet("/req")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tValue = request.getParameter("t"); //?c=c1인 경우 tValue는 null
												   //?t=&c=c1인 경우 tValue은 ""
		//String cValue = request.getParameter("c"); 
		//System.out.println(tValue+":"+cValue);
		String []cValues = request.getParameterValues("c");
		if(cValues != null) {
			for(int i=0;i<cValues.length;i++) {
				System.out.println(cValues[i]);
			}
		}
		System.out.println("request.getContextPath() : "+request.getContextPath());
		System.out.println("request.getRequestURL() : "+request.getRequestURL());
		System.out.println("request.getRequestURI() : "+request.getRequestURI());
		System.out.println("request.getServletPath() : "+request.getServletPath());
		
		Enumeration<String> em = request.getHeaderNames();
		while(em.hasMoreElements()) {
			String name = em.nextElement();
			String value = request.getHeader(name);
			System.out.println(name+":"+value);
		}
		request.setAttribute("msg", "요청속성1");
		request.setAttribute("cnt",1);  //AutoBoxing : (int -> Integer) -> upcasting : Object
		request.setAttribute("now", new Date());
		
		String msg = (String)request.getAttribute("msg");
		int cnt = (Integer)request.getAttribute("cnt");
		request.removeAttribute("now");
	}

}
