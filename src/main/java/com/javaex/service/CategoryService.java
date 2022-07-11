package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;
	
	// 카테고리 메인 리스트
	public List<CategoryVo> cateSelect(String id) {

		System.out.println("CategoryService > cateSelect");
		return categoryDao.cateSelect(id);
	}

	// 카테고리 등록+리턴
	public CategoryVo cateInsert(CategoryVo categoryVo) {

		System.out.println("CategoryService > cateInsert");
		int count = categoryDao.cateInsert(categoryVo);
		System.out.println(count);
		// -------------
		int cateNo = categoryVo.getCateNo();
		CategoryVo cateVo = categoryDao.cateVo(cateNo);
		System.out.println("카테고리 등록+리턴:" + cateVo);

		return cateVo;
	}
	
	// 포스트 카운트
	public Integer postCount(int cateNo) {
		
		System.out.println("CategoryService > postCount");
		Integer postCount = postDao.postCount(cateNo);
		System.out.println(postCount);
		
		return postCount;
	}

	// 카테고리 삭제
	public int cateDelete(int cateNo) {

		System.out.println("CategoryService > cateDelete");

		int count = categoryDao.cateDelete(cateNo);
		
		return count;

	}

}
