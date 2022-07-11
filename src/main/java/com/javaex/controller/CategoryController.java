package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;

@Controller
@RequestMapping(value="/{id}", method = { RequestMethod.GET, RequestMethod.POST })
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	// 카테고리 메인 리스트
	@RequestMapping(value="/admin/category")
	public String categoryMain() {
		
		System.out.println("CategoryController > categoryMain");
		return "blog/admin/blog-admin-cate";
	}
	
	// 카테고리 리스트 요청
	@ResponseBody
	@RequestMapping(value="/admin/cateList")
	public List<CategoryVo> cateList(@PathVariable String id){
		//cateNo 받기 + 포스트 수 가져오기 + 리스트 뿌리기
		System.out.println("CategoryController > cateList");
		List<CategoryVo> cateList = categoryService.cateSelect(id);
		System.out.println(cateList);

		return cateList;
	}
	
	// 카테고리 등록+리턴
	@ResponseBody
	@RequestMapping(value="/admin/cateAdd")
	public CategoryVo cateAdd(@ModelAttribute CategoryVo categoryVo) {
		
		System.out.println("CategoryController > cateAdd");
		System.out.println(categoryVo);
		CategoryVo cateVo = categoryService.cateInsert(categoryVo);
		
		return cateVo;
	}
	
	// 포스트 카운트
	
	@ResponseBody
	@RequestMapping(value="/postCount" )
	public Integer postCount(@RequestBody int cateNo) {
		
		System.out.println("CategoryController > postCount");
		System.out.println(cateNo);
		
		Integer postCount = categoryService.postCount(cateNo);
		
		return postCount;
	}
	
	// 카테고리 삭제
	@ResponseBody
	@RequestMapping(value="/cateDelete")
	public int cateDelete(@RequestParam("cateNo") int cateNo) {
		
		System.out.println("CategoryController > cateDelete");
		System.out.println(cateNo);
		
		int count = categoryService.cateDelete(cateNo);
		System.out.println("최종카운트:"+count);
		
		return count;
	}
}
