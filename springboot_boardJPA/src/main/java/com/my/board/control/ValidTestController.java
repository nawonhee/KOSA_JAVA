package com.my.board.control;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.board.dto.Board;

@RestController
@Validated
public class ValidTestController {
	@GetMapping(value = "/a", produces="text/html;charset=utf-8") //정확히 하려면 직접 하드코딩을 해줘야 한다
	public String a(String no, int sal) {
		if(no==null) {
			return "no값을 반드시 입력하세요";
		}else if(no.length()>3) {
			return "no는 3자리 이상으로 입력하세요";
		}
		return "성공";
	}
	
	@GetMapping(value="/b", produces="text/html;charset=utf-8")
	public String b(@NotNull(message="no값을 반드시 입력하세요") @Size(min=3, message="no는 3자리 이상으로 입력하세요") String no, int sal) {
		return "성공";
	}
	
	@GetMapping("value=/e/{no}")
	public String e(@Size(min=3, message="no는 3자리 이상으로 입력하세요") @PathVariable String no) {
		return "성공";
	}
	
	//요청전달데이터
	@GetMapping(value="/c", produces="text/html;charset=utf-8")
	public String c(@Validated Board b) { //@RequestParam 쓴 것과 마찬가지
		return "성공";
	}
	
	//요청 JSON 문자열
	@GetMapping(value="/d", produces="text/html;charset=utf-8")
	public String d(@Validated @RequestBody Board b) { //@RequestParam 쓴 것과 마찬가지
		return "성공";
	}
}
