package com.example.product.dashboard;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.product.bean.Product;

@Component
public class ProductClientFallback implements ProductServiceClient {
	@Override
	public List<Product> getProducts() {
		return List.of(new Product(001, "", 0.0, 0, "service down"));
	}

	@Override
	public Product findById(Integer id) {
		
		return null;
	}
}