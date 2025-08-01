package com.example.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.bean.Product;
import com.example.product.dashboard.ProductServiceClient;
import com.example.product.exception.ProductNotFoundException;


@RestController
@RequestMapping("/dashboard/products")
public class DashboardController {

	@Autowired
	private ProductServiceClient productServiceClient;

	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		return ResponseEntity.ok(productServiceClient.getProducts());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable int id) throws ProductNotFoundException {
		try {
			return ResponseEntity.ok(productServiceClient.findById(id));
		} catch (Exception e) {
			throw new ProductNotFoundException("Product with ID " + id + " not found");
		}

	}

}
