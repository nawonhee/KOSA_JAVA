package control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.product.service.ProductService;

public abstract class ProductController implements Controller {
	protected ProductService service;
	public ProductController() {
		service = ProductService.getInstance();
	}
}
