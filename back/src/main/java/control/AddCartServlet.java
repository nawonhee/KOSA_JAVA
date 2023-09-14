package control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.product.service.ProductService;

/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/addcart")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prodNo = request.getParameter("prodno");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		System.out.println(prodNo+":"+quantity);
		
		HttpSession session = request.getSession();
		
		//session객체의 어트리뷰트값 얻기(이름:cart)
		Map<String,Integer> cart = (Map)session.getAttribute("cart");
		
		//어트리뷰트가 없으면 객체 생성 후 어트리뷰트로 추가
		if(cart==null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}
		
		//요청전달데이터 상품번호가 key, 수량이 value로 어트리뷰트의 요소로 추가한다
		if(cart.containsKey(prodNo)){
			int oldquantity = cart.get(prodNo);
			cart.replace(prodNo, oldquantity+quantity);
		}else {
			cart.put(prodNo, quantity);
		}
		
		System.out.println(cart);
	}

}
