package control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController //각 메소드마다 responsebody를 계속 쓸 거라면 이렇게 해주자
public class TestRestController {
	@GetMapping("/n")
	//@ResponseBody
	public void n() {
		
	}
	
	@GetMapping("/o")
	//@ResponseBody
	public void o() {
		
	}
}
