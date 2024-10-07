package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository repository;

	@Override
	public int register(ProductDTO dto) {

		Product entity = dtoToEntity(dto);

		repository.save(entity);

		int newNo = entity.getNo();

		return newNo;
	}

	/*
	 * @Override public Page<ProductDTO> getList(int pageNumber) {
	 * 
	 * int pageNum = (pageNumber == 0) ? 0 : pageNumber - 1;
	 * 
	 * 
	 * Sort sort = Sort.by("no").descending();
	 * 
	 * 
	 * Pageable pageable = PageRequest.of(pageNum, 10, sort);
	 * 
	 * Page<ProductDTO> page = repository.findAll(pageable);
	 * 
	 * Page<ProductDTO> dtoPage = page.map(entity -> entityToDto(entity));
	 * 
	 * return dtoPage; }
	 */

	@Override
	public List<ProductDTO> getList() {
	 
		List<Product> result = repository.findAll();
	 
		List<ProductDTO> list = new ArrayList<>();
	 
		list = result.stream() .map(entity ->
		entityToDto(entity)) .collect(Collectors.toList());
	 
	 return list;
	}

	@Override
	public ProductDTO read(int no) {
		// 게시물 번호로 글 조회
		Optional<Product> optional = repository.findById(no);

		// 값이 있는지 확인
		if (optional.isPresent()) {
			Product board = optional.get();
			ProductDTO dto = entityToDto(board);
			return dto;
		}

		return null;
	}

}

// 변환, 등록, 목록 조회, 상세 조회 기능을 가지고 있으며, 수정 및 삭제 기능은 없습니다.
