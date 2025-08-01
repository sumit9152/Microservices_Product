package com.example.product.services;

import java.util.List;

import com.example.product.entity.Product;
import com.example.product.exception.ProductNotFoundException;

import jakarta.validation.Valid;

public interface ProductService {
    
	Product save(Product product);
    List<Product> findAll();
	Product findById(Integer id) throws ProductNotFoundException;
	
}