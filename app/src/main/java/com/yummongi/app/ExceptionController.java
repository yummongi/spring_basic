package com.yummongi.app;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex, Model m) {
		System.out.println("msg=" + m);
		m.addAttribute("ex",ex);
		return "error";
	}
	
	//예외처리 : GlobalCatcher.java
	@RequestMapping("/ex")
	public String main(Model m) throws Exception{
		m.addAttribute("msg", "message from ExceptionController.main()");
		throw new Exception("예외가 발생했습니다.");
	}

	@RequestMapping("/ex2")
	public String main2() throws Exception{
		throw new Exception("예외가 발생했습니다.");
	}

	@RequestMapping("/ex3")
	public String main3() throws NullPointerException{
		throw new NullPointerException("예외가 발생했습니다.");
	}
	
	@RequestMapping("/ex4")
	public String main4() throws FileNotFoundException{
		throw new NullPointerException("예외가 발생했습니다.");
	}
}