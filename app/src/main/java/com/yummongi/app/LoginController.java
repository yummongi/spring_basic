package com.yummongi.app;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@PostMapping("/login")
	public String login(String id, String pwd, String rememberId, Model m) throws Exception {
		//1. id 와 pwd 확인
		
		//2-1.   일치하지 않으면, loginForm 으로 이동
		if(!loginCheck(id,pwd)) {
			String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");
			m.addAttribute("msg", msg);
			
			return "redirect:/login/login?msg=" + msg;
		}
		//2-2. id 와 pwd가 일치하면, 홈으로 이동
		return "redirect:/";
	}

	private boolean loginCheck(String id, String pwd) {
		
		return "asdf".equals(id) && "1234".equals(pwd);
	}
}
