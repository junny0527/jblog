package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 카테고리 메인 리스트
	public List<CategoryVo> cateSelect(String id) {
		
		System.out.println("CategoryDao > cateSelect");
		List<CategoryVo> cateList = sqlSession.selectList("category.cateSelect",id);
		System.out.println(cateList);
		
		//포스트 카운트
		
		return cateList;
	}
	
	
	// 카테고리 등록
	public int cateInsert(CategoryVo categoryVo) {
		
		System.out.println("CategoryDao > cateInsert");
		System.out.println(categoryVo);
		return sqlSession.insert("category.cateInsert",categoryVo);
	}	
	
	// 카테고리 Vo
	public CategoryVo cateVo(int cateNo) {
		
		System.out.println("CategoryDao > cateVo");
		return sqlSession.selectOne("category.cateVo",cateNo);
	}
	
	// 카테고리 삭제
	public int cateDelete(int cateNo) {
		
		System.out.println("CategoryDao > cateDelete");
		System.out.println(cateNo);
		//삭제
		int count = sqlSession.delete("category.cateDelete", cateNo);
		System.out.println("dao:"+ count);
		
		return count;
			
	}
	public int getTopCateNo(String id) {
		return sqlSession.selectOne("category.topCateNo", id);
	}

}
