package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Product;

@SpringBootTest
public class ProductRepositoryTest {
	
	@Autowired
	ProductRepository repository;
	
	@Test
	void 상품등록() {
		Product product = Product.builder()
								 .no(1)
								 .product_name("참치세트")
								 .price(10000)
								 .category("식품")
								 .build();
		repository.save(product);
		
		Product product2 = Product.builder()
								 .no(2)
								 .product_name("샴푸세트")
								 .price(20000)
								 .category("생활용품")
								 .build();
		repository.save(product2);
		
		Product product3 = Product.builder()
								  .no(3)
								  .product_name("세차용품")
								  .price(30000)
								  .category("생활용품")
								  .build();
		repository.save(product3);
	}
	
	@Test
	void 상품조회() {
		List<Product> list = repository.findAll();
				for(Product product : list) {
					System.out.println(product);
				}
	}
	
	@Test
	void 상품수정() {
		Optional<Product> result = repository.findById(1);
		Product product = result.get();
		product.setCategory("생활용품");
		repository.save(product);
	}
	
	@Test
	void 상품삭제() {
		repository.deleteById(1);
	}
	
	
}
