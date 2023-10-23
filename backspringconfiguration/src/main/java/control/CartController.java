package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.exception.FindException;
import com.my.product.dto.Product;
import com.my.product.service.ProductService;

@RestController
public class CartController {
	@Autowired
	private ProductService service;
	
	@GetMapping("/addcart")
	public void addcart(String prodNo,int quantity, HttpSession session) {
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
	
	@GetMapping("/cartlist")
	public List<Map> cartlist(HttpSession session){
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
				e.printStackTrace();
			}
		}
		return str;
	}
}
