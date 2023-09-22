package control;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LifeCycleServlet
 */
@WebServlet("/lifecycle")
public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public LifeCycleServlet() {
        super();
        System.out.println("LifecycleServlet생성자가 호출됨");
//        ServletContext sc = this.getServletContext();
//		System.out.println(sc.getRealPath("a.jpg")); //배포된 현재 실제 경로의 a이미지에 대한 경로값을 얻어올 것이다.
    }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("LifecycleServlet init메서드 호출됨");
		super.init(config);
		ServletContext sc = this.getServletContext();
		System.out.println(sc.getRealPath("a.jpg")); //배포된 현재 실제 경로의 a이미지에 대한 경로값을 얻어올 것이다.
	}

	public void destroy() {
		System.out.println("LifecycleServlet destroy메서드 호출됨");
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifecycleServlet service메서드 호출됨");
		String idValue = request.getParameter("id");
		String pwdValue = request.getParameter("pwd");
		System.out.println("요청전달데이터 id="+idValue+", pwd="+pwdValue);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifecycleServlet doGet메서드 호출됨");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifecycleServlet doPost메서드 호출됨");
	}
}
