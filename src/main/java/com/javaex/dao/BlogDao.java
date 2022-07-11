package com.javaex.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 블로그 기본 정보(타이틀,로고)
	public Map<String, Object> getBlogVo(String id) {
		System.out.println("BlogDao > getBlogVo");
		Map<String, Object> getBlogVo = sqlSession.selectOne("blog.getBlogVo",id);
		System.out.println(getBlogVo);
		return getBlogVo;
	}
	
	// 기본 정보 수정
	public int basicUpdate(Map<String,String> basicMap) {
		
		System.out.println("BlogDao > basicUpdate");
		return sqlSession.update("blog.basicUpdate",basicMap);
	}
	//회원가입시 블로그생성
	public int insert(BlogVo blogVo) {
		System.out.println("BlogDao > insert");
		return sqlSession.insert("blog.insert",blogVo);
		
	}
	
}
