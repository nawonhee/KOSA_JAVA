package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.exception.FindException;
import com.my.product.dto.PageGroup;
import com.my.product.dto.Product;
import com.my.product.service.ProductService;

/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/productlist")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService service;
	public ProductListServlet() {
		service = new ProductService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청전달데이터 currentPage 얻기
		String currentPage = request.getParameter("currentPage");
		int cp=1;
		if(currentPage != null && !currentPage.equals("")) {
			cp = Integer.parseInt(currentPage);
		}
		
		String path = "productlistresult.jsp";
		try {
			//List<Product> list = service.findAll(cp);
			//request.setAttribute("list", list);
			PageGroup<Product> pb = service.findAll(cp);
			request.setAttribute("pb", pb);
		} catch (FindException e) {
			e.printStackTrace();
			path = "fail.jsp"; //실패인 경우
			request.setAttribute("msg", e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
