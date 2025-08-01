package com.example.product.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.entity.Product;
import com.example.product.exception.ProductNotFoundException;
import com.example.product.repo.ProductRepository;
import com.example.product.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repo;

	public Product save(Product product) {
		return repo.save(product);
	}

	public List<Product> findAll() {
		return repo.findAll();
	}

	public Product findById(Integer id) throws ProductNotFoundException{

		return repo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product ID not found: " + id));

		
	}

}