package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Servlet implementation class SessionTrackingServlet
 */
@WebServlet("/sessiontracking")
public class SessionTrackingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		boolean flag = session.isNew();
		long time = session.getLastAccessedTime();
		System.out.println("세션ID:"+sessionId
						+", 세션생성여부"+flag
						+", 세션 최종사용시간:"+new Date(time));
	}

}
