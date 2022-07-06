package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value ="/user")
public class UserController {
	@Autowired
	private UserService userService;

	// 로그인 폼
	@RequestMapping(value = "/loginForm", method = { RequestMethod.POST, RequestMethod.GET })
	public String loginForm() {

		System.out.println("UserController > loginForm");
		return "user/loginForm";

	}

	// 로그인
	@RequestMapping(value ="/login" , method = { RequestMethod.POST, RequestMethod.GET })
	public String login(@ModelAttribute UserVo userVo, HttpSession httpSession) {

		System.out.println("UserController > login");
		UserVo authUser = userService.getUser(userVo);
		System.out.println(authUser);
		// 세션 저장
		if (authUser != null) {
			httpSession.setAttribute("authUser", authUser);
			return "redirect:/";
		} else {
			return "redirect:/user/loginForm";
		}

	}

	// 회원가입 폼
	@RequestMapping(value ="/joinForm" , method = { RequestMethod.POST, RequestMethod.GET })
	public String joinForm() {

		System.out.println("UserController > joinForm");
		return "user/joinForm";

	}

	// 회원가입 실행
	@RequestMapping(value ="/join" , method = { RequestMethod.POST, RequestMethod.GET })
	public String join(@ModelAttribute UserVo userVo) {

		System.out.println("UserController > join");
		userService.joinInsert(userVo);
		System.out.println(userVo);

		return "user/joinSuccess";

	}

	// 아이디 중복 체크
	@ResponseBody
	@RequestMapping(value ="/inCheck" , method = { RequestMethod.POST, RequestMethod.GET })
	public int inCheck(@RequestParam("id") String id) {

		System.out.println("UserController > inCheck");
		int count = userService.idCheck(id);

		return count;
	}

	// 로그아웃
	@RequestMapping(value ="/logout" , method = { RequestMethod.POST, RequestMethod.GET })
	public String logout(HttpSession httpSession) {

		System.out.println("UserController > logout");

		// 세션정보 삭제
		httpSession.removeAttribute("authUser");
		httpSession.invalidate();

		return "redirect:/";
	}
}
