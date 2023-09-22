package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.product.dto.Product;
import com.my.product.service.ProductService;

public class CartListController implements Controller{
	ProductService service = new ProductService();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.12:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String,Integer> cart = (Map)session.getAttribute("cart");
		
		if(cart==null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}
		
		Map<String, Integer> map = new HashMap<>();
		map = (Map)session.getAttribute("cart");
		
		List<Map> str = new ArrayList<>();
		
		for(String key : map.keySet()) {
			try {
				Product p = service.findByProdNo(key);
				Map carts = new HashMap<>();
				carts.put("product", p);
				carts.put("quantity", map.get(key));
				str.add(carts);
			} catch (FindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String jsonStr = mapper.writeValueAsString(str);
		out.print(jsonStr);
		return null;
	}

}
