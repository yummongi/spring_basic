package com.yummongi.app;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.http.HttpResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
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
	public String login(String id, String pwd, boolean rememberId, HttpServletResponse response) throws Exception {
		//1. id �� pwd Ȯ��
		
		System.out.println(id);
		System.out.println(pwd);
		System.out.println(rememberId);
		//2-1.   ��ġ���� ������, loginForm ���� �̵�
		if(!loginCheck(id,pwd)) {
			String msg = URLEncoder.encode("id �Ǵ� pwd�� ��ġ���� �ʽ��ϴ�.", "utf-8");
			
			return "redirect:/login/login?msg=" + msg;
		}
		
		//2-2. id �� pwd�� ��ġ�ϸ�, 
		//2-3 üũ �ڽ��� true�� 
		if(rememberId) {
			//      1. ��Ű�� ����
			Cookie cookie = new Cookie("id", id);
			// 		2. ���信 ����
			response.addCookie(cookie);
			// 		3. Ȩ���� �̵�
		}else {
			//üũ �ڽ��� false ��Ű ����
			Cookie cookie = new Cookie("id",id);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

		return "redirect:/";
	}

	private boolean loginCheck(String id, String pwd) {
		
		return "asdf".equals(id) && "1234".equals(pwd);
		
	}
}
