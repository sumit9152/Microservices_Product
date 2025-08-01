package com.example.product.dashboard;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.product.bean.Product;

@Component
public class ProductServiceWithCircuitBreaker {
	private final ProductServiceClient productClient;
	private final CircuitBreaker circuitBreaker = new CircuitBreaker();

	public ProductServiceWithCircuitBreaker(ProductServiceClient productClient) {
		this.productClient = productClient;
	}

	public List<Product> getProductsSafe() {
		if (!circuitBreaker.allowRequest()) {
			return List.of(new Product(001, "", 0.0,0,"Circuit OPEN - service fallback triggered"));
		}

		try {
			List<Product> products = productClient.getProducts();
			circuitBreaker.recordSuccess();
			return products;
		} catch (Exception ex) {
			circuitBreaker.recordFailure();
			
			return List.of(new Product(001, "", 0.0, 0, "Custom CircuitBreaker: fallback response"));
			
			
		}
	}
}
