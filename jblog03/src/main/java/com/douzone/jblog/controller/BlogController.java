package com.douzone.jblog.controller;

import java.util.List;
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

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;


@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	
	
	@RequestMapping({ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String index(
		@PathVariable("id") String id,
		@PathVariable("pathNo1") Optional<Long> pathNo1,
		@PathVariable("pathNo2") Optional<Long> pathNo2, Model model) {
		System.out.println(".......");
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		
		BlogVo vo =blogService.find(id);
		model.addAttribute("blogVo",vo);
		
		List<CategoryVo> list = categoryService.find(id);
		model.addAttribute("categoryList", list);
		
		
		if(pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
			categoryNo = list.get(0).getNo();
		}
		PostVo post = postService.findByNo(id, categoryNo, postNo);
		model.addAttribute("post", post);
		
		List<PostVo> postList = postService.findByNoAndNo(id, categoryNo);
		model.addAttribute("postList", postList);
		
		
		System.out.println("id:" + id);
		System.out.println("category:" + categoryNo);
		System.out.println("post:" + postNo);
		
		return "blog/main";
	}
	
    @Auth
	@RequestMapping(value="/admin/basic",method=RequestMethod.GET  )
	public String adminBasic(@PathVariable("id") String id ,Model model, BlogVo vo) {
		 vo=blogService.find(id);
         model.addAttribute("vo", vo);
		System.out.println("basic : " + vo);
		return "blog/admin/basic";
		
	}
    @Auth
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
    @Auth
	@RequestMapping(value="/admin/category", method=RequestMethod.GET)
	public String adminCategory(@PathVariable("id") String id, CategoryVo categoryVo, Model model) {
		List<CategoryVo> list = categoryService.find(id);
		model.addAttribute("list", list);
		BlogVo vo = blogService.find(id);
		model.addAttribute("blogVo", vo);

		return "/blog/admin/category";
	}
	@Auth
	@RequestMapping(value="/admin/category", method=RequestMethod.POST)
	public String adminCategory(@PathVariable("id") String id, CategoryVo categoryVo, BlogVo vo) {
		categoryVo.setBlodId(vo.getId());
		categoryService.insert2(categoryVo);

		return "redirect:/" + id + "/admin/category";
	
	}
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.GET)
	public String adminWrite(@PathVariable("id") String id, Long no, Model model) {
		List<CategoryVo> list = categoryService.find(id);
		model.addAttribute("list", list);
		BlogVo vo = blogService.find(id);
		model.addAttribute("blogVo",vo);

		
		return "/blog/admin/write";
	}
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String adminWrite(@PathVariable("id") String id, PostVo postVo, CategoryVo categoryVo) {
		postService.insert(postVo);
		
		return "redirect:/{id}";
		
	}
	@Auth
	@RequestMapping(value="/admin/category/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable("id") String id, @PathVariable("no") Long no, CategoryVo categoryVo) {
		categoryService.delete(categoryVo.getNo());
		return "redirect:/" + id + "/admin/category";
	}
	
}
