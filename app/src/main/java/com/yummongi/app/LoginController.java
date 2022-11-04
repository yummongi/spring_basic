package com.yummongi.app;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.http.HttpResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.catalina.connector.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//세션 종료 
		session.invalidate();
		
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(String  id, String pwd, boolean rememberId, String toURL, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1. id 와 pwd 확인
		
		System.out.println(id);
		System.out.println(pwd);
		System.out.println(rememberId);
		//2-1.   일치하지 않으면, loginForm 으로 이동
		if(!loginCheck(id,pwd)) {
			String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");
			
			return "redirect:/login/login?msg=" + msg;
		}
		
		//2-2. id 와 pwd가 일치하면, 
		//세션 객체 얻어오기
		HttpSession session = request.getSession();
		
		
		if(session != null) session.setAttribute("id",id);
		//세션 객체에 id를 저장
		
		//2-3 체크 박스가 true면 
		if(rememberId) {
			//      1. 쿠키를 생성
			Cookie cookie = new Cookie("id", id);
			// 		2. 응답에 저장
			response.addCookie(cookie);
			// 		3. 홈으로 이동
		}else {
			//체크 박스가 false 쿠키 삭제
			Cookie cookie = new Cookie("id",id);
			cookie.setMaxAge(0);
			response.addCookie(cookie);

		}
		
		//default
		toURL = toURL==null || toURL.equals("") ? "/" : toURL;
		return "redirect:"+toURL;
	}

	private boolean loginCheck(String id, String pwd) {
		
		return "asdf".equals(id) && "1234".equals(pwd);
		
	}
}
