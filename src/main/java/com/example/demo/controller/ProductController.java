package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.ProductDTO;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService service;
	
	@GetMapping("/list")
	public void list(@RequestParam(defaultValue = "0", name = "page") int page, Model model) {
		
		Page<ProductDTO> list = service.getList(page);
		
		model.addAttribute("list", list);
		
		System.out.println("전체 페이지 수: " + list.getTotalPages());
		System.out.println("전체 상품 수: " + list.getTotalElements());
		System.out.println("현재 페이지 번호: " + (list.getNumber() + 1));
		System.out.println("페이지에 표시할 상품 수: " + list.getNumberOfElements());
	}
	
    @GetMapping("/register")
    public void register() {
    }
    
    @PostMapping("/register")
    
    public String registerPost(ProductDTO dto, RedirectAttributes redirectAttributes) {
    	
    	int no = service.register(dto);
    	System.out.println("no:" + no);
    	
    	redirectAttributes.addFlashAttribute("no", no);
    	
    	return "redirect:/board/list";
    }
    
    @GetMapping("/read")
    public void read(@RequestParam(name = "no") int no, @RequestParam(defaultValue = "0", name = "page") int page, Model model) {
    	
    	ProductDTO dto = service.read(no);
    	
    	model.addAttribute("dto", dto);
    	
    	model.addAttribute("page", page);
    }
}
