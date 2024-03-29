package advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice //restcontroller 전용 advice라는 의미
public class MyControllerAdvice {
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseBody
	public ResponseEntity<?> maxUploadSize(MaxUploadSizeExceededException e){
		Map<String, Object> map = new HashMap<>();
		map.put("status", 0);
		map.put("msg", e.getMaxUploadSize()+"파일크기가 너무 큽니다");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "http://192.168.1.12:5500"); //myMVCContext의 cors는 controller에만 적용
		headers.add("Access-Control-Allow-Credentials", "true");
		return new ResponseEntity<> (map,headers,HttpStatus.OK);
		//return map;
	}
	
//	@ExceptionHandler(Exception.class)
//	@ResponseBody
//	public Map<String, Object> exceptionHandler(Exception e){
//		Map<String, Object> map = new HashMap<>();
//		map.put("status", 0);
//		map.put("msg", e.getMessage());
//		return map;
//	}
}
