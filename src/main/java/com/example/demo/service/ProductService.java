package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;

public interface ProductService {

	// 등록
	int register(ProductDTO dto);
	
	// 목록조회
	Page<ProductDTO> getList(int pageNumber);
	
	// 상세조회
	ProductDTO read(int no);
	
	// DTO -> ENTITY
	default Product dtoToEntity(ProductDTO dto) {
		
		Product entity = Product.builder()
								.no(dto.getNo())
								.product_name(dto.getProduct_name())
								.price(dto.getPrice())
								.category(dto.getCategory())
								.build();
		return entity;
	}
	
	// ENTITY -> DTO
	default ProductDTO entityToDto(Product entity) {
		
		ProductDTO dto = ProductDTO.builder()
								   .no(entity.getNo())
								   .product_name(entity.getProduct_name())
								   .price(entity.getPrice())
								   .category(entity.getCategory())
								   .regDate(entity.getRegDate())
								   .modDate(entity.getModDate())
								   .build();
		return dto;
	}
}
// 변환,등록,목록조회,상세조회  수정삭제x