package com.bookStore.client.products.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.client.products.dao.IProductDao;
import com.bookStore.client.products.service.IProductService;
import com.bookStore.commons.beans.Notice;
import com.bookStore.commons.beans.Product;
import com.bookStore.utils.PageModel;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDao productDao;
	
	@Override
	public List<Product> findProductByCategory(String category, PageModel pageModel) {
		Map map = new HashMap<>();
		map.put("category", category);
		map.put("start", pageModel.getFirstLimitParam());
		map.put("pageSize", pageModel.getPageSize());
		
		return productDao.selectProductByCategory(map);
	}

	@Override
	public int findProductByCategoryCount(String category) {
		return productDao.selectProductByCategoryCount(category);
	}

	@Override
	public Product findProductById(String id) {
		return productDao.selectProductById(id);
	}

	@Override
	public int findProductByNameCount(String name) {
		return productDao.selectProductByNameCount(name);
	}

	@Override
	public List<Product> findProductByName(String name, PageModel pageModel) {
		Map map = new HashMap<>();
		map.put("name", name);
		map.put("start", pageModel.getFirstLimitParam());
		map.put("pageSize", pageModel.getPageSize());
		return productDao.selectProductByName(map);
	}

	@Override
	public Notice findRecentNotice() {
		return productDao.selectRecentNotice();
	}

	@Override
	public List<Product> findWeekHotProduct() {
		return productDao.selectWekkHotProduct();
	}

}
