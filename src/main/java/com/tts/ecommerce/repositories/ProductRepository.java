package com.tts.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tts.ecommerce.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	List<Product> findAll();
	Product findById(long id);
	List<Product> findByBrand(String brand);
	List<Product> findByCategory(String category);
	List<Product> findByBrandAndCategory(String brand, String category);
	
	
	@Query("SELECT DISTINCT p.brand FROM Product p")
	List<String> findDistinctBrands();
	
	@Query("SELECT DISTINCT p.category FROM Product p")
	List<String> findDistinctCategories();
	
	

}
