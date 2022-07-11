package com.javaex.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;

@Controller
@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
public class BlogController {

	@Autowired
	private BlogService blogService;

	@RequestMapping(value="")
	public String getBlog(@PathVariable(value = "id") String id,@RequestParam(value = "cateNo",required = false ,defaultValue = "0") int cateNo, Model model) {
		System.out.println("BlogController > getBlog");
		System.out.println(id);
		Map<String, Object> blogMap = blogService.getBlog(id,cateNo);
		model.addAttribute("blogMap", blogMap);
		model.addAttribute("BlogVo", blogMap.get("BlogVo"));
		System.out.println("BlogMap:" + blogMap);

		return "blog/blog-main";
	}

	// 블로그 로그인
	@RequestMapping(value="/blogLogin")
	public String blogLogin() {

		System.out.println("BlogController > blogLogin");
		return "redirect:/{id}";
	}

	// 블로그 로그아웃
	@RequestMapping(value="/blogLogout")
	public String blogLogout(HttpSession httpSession) {

		System.out.println("BlogController > blogLogout");

		httpSession.removeAttribute("authUser");
		httpSession.invalidate();

		return "redirect:/{id}";
	}

	
	// 내블로그 관리
	@RequestMapping(value="/admin/basic")
	public String adminBasic(@PathVariable(value = "id") String id, Model model) {
		Map<String, Object> blogMap = blogService.getBlogVo(id);
		model.addAttribute("BlogVo", blogMap);
		System.out.println("BlogController > adminBasic");
		return "blog/admin/blog-admin-basic";
	}

	// 블로그 기본정보 수정
	@RequestMapping(value="/basicModify")
	public String basicModify(@RequestParam MultipartFile file, 
							  @RequestParam("blogTitle") String blogTitle,
							  @RequestParam("id") String id) {

		System.out.println("BlogController > basicModify");
		System.out.println(file.getOriginalFilename());
		String logoFilePath = blogService.basicUpdate(blogTitle, file, id);
		
		System.out.println("controller:" + logoFilePath);
		
		return "redirect:/{id}";
	}

}
