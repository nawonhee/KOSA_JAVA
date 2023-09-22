package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.product.dto.Product;
import com.my.util.PageGroup;

public class ProductListJsonController extends ProductController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 응답형식
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.12:5500");
		// 응답 출력 스트림 얻기
		PrintWriter out = response.getWriter();

		// 요청전달데이터 currentPage 얻기
		String currentPage = request.getParameter("currentPage");
		int cp = 1;
		if (currentPage != null && !currentPage.equals("")) {
			cp = Integer.parseInt(currentPage);
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			PageGroup<Product> pg = service.findAll(cp);
			// out.print("{ \"나이\" : 10, \"주소\" : \"서울특별시 양천구 목동\" }");
			String jsonStr = mapper.writeValueAsString(pg);
			out.print(jsonStr);
		} catch (FindException e) {
			e.printStackTrace();
		}
		//return "productlistresult.jsp";
		return null;
	}

}
