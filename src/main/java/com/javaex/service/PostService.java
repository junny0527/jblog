package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	// 포스트 등록
	public int postAdd(PostVo postVo) {
		
		System.out.println("PostService > postAdd");
		return postDao.postInsert(postVo);
	}
}
