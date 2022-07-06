package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;

	// 회원가입 등록
	public int joinInsert(UserVo userVo) {

		System.out.println("UserDao > joinInsert");
		int join = sqlSession.insert("user.joinInsert", userVo);

		return join;
	}

	// 로그인
	public UserVo getUser(UserVo userVo) {

		System.out.println("UserDao > getUser");
		return sqlSession.selectOne("user.getUser", userVo);
	}

	// 아이디 중복 체크 
	public int idCheck(String id) {

		System.out.println("UserDao > idCheck");
		return sqlSession.selectOne("user.idCheck", id);
	}

}
