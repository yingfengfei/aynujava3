package com.bookStore.client.products.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookStore.client.products.service.IProductService;
import com.bookStore.commons.beans.Notice;
import com.bookStore.commons.beans.Product;
import com.bookStore.utils.PageModel;

@Controller
@RequestMapping("/client/products")
public class ProductHandler {

	@Autowired
	private IProductService productService;
	
	@RequestMapping("/findProductByCategory.do")
	public String findProductByCategory(@RequestParam(defaultValue="1")Integer pageIndex,
			String category,Model model){
		System.out.println(category);
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		int count = productService.findProductByCategoryCount(category);
		pageModel.setRecordCount(count);
		
		List<Product> products = productService.findProductByCategory(category,pageModel);
		System.out.println(products);
		model.addAttribute("category", category);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("products", products);
		return "/client/product_list.jsp";
	}
	
	@RequestMapping("/findProductById.do")
	public String findProductById(String id,Model model){
		Product product = productService.findProductById(id);
		System.out.println(product);
		model.addAttribute("p", product);
		return "/client/info.jsp";
	}
	
	@RequestMapping("/findProductByName.do")
	public String findProductByName(@RequestParam(defaultValue="1")Integer pageIndex,
			String name,Model model){
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		
		int count = productService.findProductByNameCount(name);
		pageModel.setRecordCount(count);
		
		List<Product> products = productService.findProductByName(name,pageModel);
		System.out.println(products);
		model.addAttribute("name", name);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("products", products);
		return "/client/product_search_list.jsp";
		
		
	}
	
	@RequestMapping("/showIndex.do")
	public String showIndex(Model model){
		Notice notice = productService.findRecentNotice();
		model.addAttribute("notice", notice);
		
		List<Product> products = productService.findWeekHotProduct();
		model.addAttribute("products", products);
		
		return "/client/index.jsp";
	}
	
	
	
}
