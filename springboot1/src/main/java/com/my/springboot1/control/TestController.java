package com.my.springboot1.control;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	@GetMapping("/a")
	public String a() {
		return "a.jsp"; //viewname
	}
	
	@GetMapping("/b")
	@ResponseBody
	public String b() {
		return "WELCOME"; //응답내용
	}
	
	@GetMapping("/c")
	@ResponseBody
	public Map<String, Object> c(){
		Map<String, Object> map = new HashMap<>();
		map.put("status", 1);
		map.put("msg","JSON문자열");
		return map;
	}
	
	@GetMapping("/d")
	@ResponseBody
//	public void d(String id, int sal) {
//		if(id != null) {	
//			System.out.println(id+":"+sal);
//		}
//	}
	public void d(@RequestParam(name="id")Optional<String> optId, int sal) {
//		if(optId.isPresent()) {
//			String id = optId.get();
//			System.out.println(id.length()+":"+sal);
//		}
		optId.ifPresent(id->{
			System.out.println(id);
		});
	}
}
