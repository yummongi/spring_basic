package com.yummongi.app;

import java.io.FileNotFoundException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

//모든 컨트롤러에서 발생하는 예외를 처리
//@ControllerAdvice("com.yummongi.app") //해당 패키지에 적용
@ControllerAdvice //모든 패키지에 적용
public class GlobalCatcher {
	
	@ExceptionHandler({NullPointerException.class,FileNotFoundException.class})
	public String catcher2(Exception ex, Model m) {
		m.addAttribute("ex",ex);
		return "error";
	}
	
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex, Model m) {
		m.addAttribute("ex",ex);
		return "error";
	}
}