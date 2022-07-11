package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 포스트 카운트
	public Integer postCount(int cateNo) {
		
		System.out.println("PostDao > postCount");
		Integer count = sqlSession.selectOne("post.postCount",cateNo);
		System.out.println("post dao:"+count);
		
		return count;
	}
	
	// 포스트 등록
	public int postInsert(PostVo postVo) {
		
		System.out.println("PostDao > postInsert");
		return sqlSession.insert("post.postInsert",postVo);
	}
}
