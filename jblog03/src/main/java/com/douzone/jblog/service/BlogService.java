package com.douzone.jblog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;

@Service
public class BlogService {
 
	@Autowired
	private BlogRepository blogRepository;
	
	
	
    public BlogVo find(String id) {
		return blogRepository.find(id);
	}
	public void updateblog(BlogVo vo) {
		blogRepository.update(vo);
		
	}

	public void insert(String id) {
		 blogRepository.insert(id);
	}
	
	
	
}