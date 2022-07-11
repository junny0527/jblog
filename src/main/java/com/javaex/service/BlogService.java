package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;
	String saveDir = "c:\\javaStudy\\jblogfile";
	
	// 블로그 정보 가져오기
	public Map<String, Object> getBlog(String id,int cateNo) {
	
		
		Map<String, Object> blogMap= new HashMap<String, Object>();
		blogMap.put("BlogVo", blogDao.getBlogVo(id));
		blogMap.put("cateList", categoryDao.cateSelect(id));
		blogMap.put("postList", postDao.getList(cateNo));
		System.out.println(cateNo);
		return blogMap;
	}
	// 블로그 기본 정보(타이틀,로고)
	public Map<String,Object> getBlogVo(String id) {
		System.out.println("BlogService > getBlogVo");
		Map<String,Object> blogMap  = blogDao.getBlogVo(id);

		return blogMap ;
	}
	
	
	// 내블로그 관리
	public String basicUpdate(String blogTitle, MultipartFile file, String id) {
		
		System.out.println("BlogService > basicUpdate");

		//운영 내용 추출
		//1) 원본파일명
		String orgFile = file.getOriginalFilename();
		//2) 확장자
		String exName = orgFile.substring(orgFile.lastIndexOf("."));
		//3) 저장파일명
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		//4) 파일 저장 위치
		String filePath = saveDir+"\\"+saveName;
		
		Map<String, String> basicMap = new HashMap<String, String>();
		basicMap.put("blogTitle",blogTitle);
		basicMap.put("saveName","upload/" + saveName);
		basicMap.put("id", id);
		
		//5) DB저장
		blogDao.basicUpdate(basicMap);
		
		//6) 파일 업로드
		try {
			byte[] fileDate = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			bout.write(fileDate);
			bout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return saveName;
	}
}
