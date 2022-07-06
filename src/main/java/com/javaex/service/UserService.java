package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	// 회원가입 등록
	public int joinInsert(UserVo userVo) {

		System.out.println("UserService > joinInsert");
		return userDao.joinInsert(userVo);

	}

	// 로그인
	public UserVo getUser(UserVo userVo) {

		System.out.println("UserService > getUser");
		UserVo getUser = userDao.getUser(userVo);
		System.out.println(getUser);

		return getUser;

	}

	// 아이디 중복 체크
	public int idCheck(String id) {

		System.out.println("UserService > idCheck");
		return userDao.idCheck(id);
	}

}
