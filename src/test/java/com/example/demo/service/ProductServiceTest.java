package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.example.demo.dto.ProductDTO;

@SpringBootTest
public class ProductServiceTest {

	@Autowired
	ProductService service;
	
	@Test
	public void 상품등록() {
		ProductDTO dto = ProductDTO.builder()
			  					  .no(1)
								  .product_name("참치세트")
								  .price(10000)
								  .category("식품")
								  .build();
		int no = service.register(dto);
		
		System.out.println("상품번호: " + no);
	}
	
	@Test
	public void 상품목록조회() {
		int pageNumber = 1;
		Page<ProductDTO> page = service.getList(pageNumber);
		List<ProductDTO> list = page.getContent();
		
		list.forEach(product -> System.out.println(product));
	}
	
	@Test
	public void 상품정보단건조회() {
		int no = 1;
		ProductDTO product = service.read(no);
		
		if(product != null) {
			System.out.println(product);
		} else {
			System.out.println("상품이 없습니다..");
		}
	}
	
}
