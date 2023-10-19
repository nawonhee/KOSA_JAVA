package control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.product.dto.Product;

@Controller
public class TestJsonController {
	@GetMapping(value="/h",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String h() {
		return "응답내용입니다";
	}
	
	@GetMapping(value="/i", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String i() {
		String jsonStr="{";
		jsonStr += "\"status\": 0";
		jsonStr += "}";
		return jsonStr;
	}
	
	@GetMapping(value="/j", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map j() {
		Map<String, Object> map = new HashMap<>();
		map.put("status", 1);
		map.put("msg", "JSON");
		return map;
	}
	
	@GetMapping(value="/k")
	@ResponseBody
	public Product k() {
		Product p = new Product();
		p.setProdNo("C0001");
		p.setProdName("아메리카노");
		return p;
	}
}
