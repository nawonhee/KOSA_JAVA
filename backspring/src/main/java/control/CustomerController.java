package control;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.my.customer.dto.Customer;
import com.my.customer.service.CustomerService;
import com.my.exception.FindException;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@GetMapping("/iddupchk")
	public Map<String, Object> iddupchk(String id){
		Map<String, Object> map = new HashMap<>();

		try{
			service.idDupChk(id);
			map.put("status", 0);
			return map;
		}catch(FindException e) {
			map.put("status", 1);
			return map;
		}
	}
	
	@PostMapping("/login")
	public Map<String, Object> login(String id, String pwd, HttpSession session){
		Map<String, Object> map = new HashMap<>();

		session.removeAttribute("loginedId");
		
		try {
			service.login(id, pwd);
			map.put("status", 1);
			map.put("msg", "로그인 성공");
			session.setAttribute("loginedId", id);
			return map;
		} catch (FindException e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "로그인 실패");
			return map;
		}
	}
	
	@GetMapping("/logout")
	public void logout(HttpSession session) {
		session.removeAttribute("loginedId");
		session.invalidate();
	}
	
	@PostMapping("/signup")
	public Map<String, Object> signup(String id, String pwd, String name,MultipartFile f1,MultipartFile f2){
		Map<String, Object> map = new HashMap<>();
		
		try {
			Customer c= new Customer(id, pwd, name);
			service.signup(c);
		
			try {
				String extension = StringUtils.getFilenameExtension(f1.getOriginalFilename());
				System.out.println(extension);
				File targetFile = new File("C:\\KOSA202307\\attaches", id+"_profile."+extension);
				FileCopyUtils.copy(f1.getBytes(), targetFile);
				
				String extension2 = StringUtils.getFilenameExtension(f2.getOriginalFilename());
				File targetFile2 = new File("C:\\KOSA202307\\attaches", id+"_intro."+extension2); 
				FileCopyUtils.copy(f2.getBytes(), targetFile2);
			} catch(Exception e) {
				
			}
		
			map.put("status", 1);
			map.put("msg", "가입성공");
			return map;
		} catch(Exception e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", e.getMessage());
			return map;
		}
	}
}