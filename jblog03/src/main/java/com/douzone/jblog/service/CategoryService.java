package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<CategoryVo> findAll(){
		return categoryRepository.findAll();
	}
	
	

	
	public void delete(Long no) {
		categoryRepository.delete(no);
	}

	public void insert2(CategoryVo categoryVo) {
		categoryRepository.insert2(categoryVo);
		
	}

	public List<CategoryVo> find(String id) {
		return categoryRepository.find(id);
	}
}
