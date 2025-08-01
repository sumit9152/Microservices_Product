package com.example.product.dashboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.product.bean.Product;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name = "product-service", url = "http://localhost:1010", fallback = ProductClientFallback.class)
public interface ProductServiceClient {

	@GetMapping("/v1/products")
	@CircuitBreaker(name = "productCircuitBreaker", fallbackMethod = "productServiceMethod")
	List<Product> getProducts();

	public default List<Product> productServiceMethod(Exception ex) {
		List<Product> product = new ArrayList<>();
		Product pd = new Product(1001, "Dummy", 1000, 0, "service is down");

		product.add(pd);

		return product;
	}

	@GetMapping("/v1/products/{id}")
	Product findById(@PathVariable("id") Integer id);
}
