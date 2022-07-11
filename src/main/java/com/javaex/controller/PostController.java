package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.BlogService;
import com.javaex.service.CategoryService;
import com.javaex.service.PostService;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Controller
@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
public class PostController {
	
	@Autowired
	private PostService postService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BlogService blogService;
	
	// 포스트 폼
	@RequestMapping(value="/admin/writeForm")
	public String writeForm(@PathVariable(value = "id") String id, Model model) {
		System.out.println("PostController > writeForm");
		Map<String, Object> blogMap = blogService.getBlogVo(id);
		model.addAttribute("BlogVo", blogMap);
		System.out.println("BlogMap:" + blogMap);
		return "blog/admin/blog-admin-write";
	}
	
	@ResponseBody
	@RequestMapping(value="/writeForm")
	public List<CategoryVo> writeForm1(@PathVariable String id) {
		System.out.println("PostController > writeForm1");
		return categoryService.cateSelect(id);
	}
	
	
	// 포스트 등록
	@RequestMapping(value="/admin/write")
	public String write(@ModelAttribute PostVo postVo) {
		
		System.out.println("PostController > write");
		int count = postService.postAdd(postVo);
		System.out.println("성공여부:"+count);
		
		return "redirect:/{id}/admin/writeForm";
		
	}
	@ResponseBody
	@RequestMapping(value="/admin/postList")
	public List<PostVo> postList(@PathVariable String id,Model model){
		System.out.println("PostController > postList");
		List<PostVo> postList = postService.postSelect(id);
		model.addAttribute("postList", postList);
		System.out.println(postList);

		return postList;
	}
	
	

}
