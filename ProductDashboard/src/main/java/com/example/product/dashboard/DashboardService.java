package com.example.product.dashboard;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.product.bean.Product;

@Service
public class DashboardService {

	private final ProductServiceClient productServiceClient;

	public DashboardService(ProductServiceClient productServiceClient) {
		this.productServiceClient = productServiceClient;
	}

	public List<Product> getDashboardProducts() {
		return productServiceClient.getProducts();
	}

	public Product getProductById(int id) {

		return productServiceClient.findById(id);
	}

}
