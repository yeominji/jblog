package com.douzone.jblog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;


@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	
	
	@RequestMapping({ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String index(
		@PathVariable("id") String id,
		@PathVariable("pathNo1") Optional<Long> pathNo1,
		@PathVariable("pathNo2") Optional<Long> pathNo2) {
		System.out.println(".......");
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if(pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
		
		System.out.println("id:" + id);
		System.out.println("category:" + categoryNo);
		System.out.println("post:" + postNo);
		
		return "blog/index";
	}
	
   
	@RequestMapping(value="/admin/basic",method=RequestMethod.GET  )
	public String adminBasic(@PathVariable("id") String id ,Model model, BlogVo vo) {
		 vo=blogService.find(id);
         model.addAttribute("vo", vo);
		System.out.println("basic : " + vo);
		return "blog/admin/basic";
		
	}
	@RequestMapping(value="/admin/basic", method=RequestMethod.POST)
	public String updateMain(@RequestParam (value ="logo-file")@ModelAttribute MultipartFile file, @RequestParam String title,@PathVariable("id") String id){
		String logo = fileUploadService.restore(file);
		System.out.println(id +" : " +title);
		BlogVo vo= new BlogVo();
		vo.setTitle(title);
		vo.setLogo(logo);
		vo.setId(id);
		System.out.println(vo.toString());
		
		blogService.updateblog(vo);
		return "redirect:/" + id;
	}
	

}
