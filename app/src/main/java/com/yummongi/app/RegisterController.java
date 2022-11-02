package com.yummongi.app;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
	
//	@RequestMapping(value = "/register/add", method = {RequestMethod.GET, RequestMethod.POST}) //아래와 동일
//	@RequestMapping("/register/add") //신규 회원 가입 화면
	//GET 방식으로만 가능
//	@GetMapping("/register/add")
	@RequestMapping("/register/add")
	public String register() {
		return "registerForm"; //WEB-INF/views/registerForm.jsp
	}
	
//	@RequestMapping(value = "register/save", method = RequestMethod.POST) //아래와 동일
	//POST 방식으로만 가능
	@PostMapping("/register/save") //Spring 버전 4.3 부터
	public String save(User user, Model m) throws Exception {
		//1. 유효성 검사
		if(!isValid(user)) {
			String msg = URLEncoder.encode("id를 잘못입력하셨습니다!", "utf-8");
			//return "redirect:/register/add?msg="+msg; //URL 재작성 (rewriting)
			m.addAttribute("msg", msg);
			return "forward:/register/add";
		}
		//2. DB에 신규 회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return false;
	}
}
