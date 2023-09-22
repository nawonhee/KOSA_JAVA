package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String envFileName = "control.properties";
	private Properties env;
 
    public DispatcherServlet() {
    	super();
    }

    @Override
    public void init() throws ServletException{
    	super.init();
    	env = new Properties();
    	ServletContext sc = this.getServletContext();
    	String realPath = sc.getRealPath("WEB-INF\\classes\\com\\my\\env\\"+envFileName);
    	System.out.println("in DispatcherServlet의 init(): realPath="+realPath);
    	try{
    		env.load(new FileInputStream(realPath));
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("request.getServletPath()="+request.getServletPath());
//		if(request.getServletPath().equals("/productjson")) {
//			ProductJsonController control = new ProductJsonController();
//			control.execute(request, response);
//		}else {
//			ProductListJsonController control = new ProductListJsonController();
//			control.execute(request, response);
//		}
		String className = env.getProperty(request.getServletPath());
		try {
			Class clazz = Class.forName(className); //클래스 이름에 해당하는 .class파일 찾아서 JVM으로 로드
		
			Controller controller = (Controller) clazz.getDeclaredConstructor().newInstance();
			//clazz.newInstance(); //객체생성
			controller.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

