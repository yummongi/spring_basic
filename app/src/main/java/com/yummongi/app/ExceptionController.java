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
	
	//����ó�� : GlobalCatcher.java
	@RequestMapping("/ex")
	public String main(Model m) throws Exception{
		m.addAttribute("msg", "message from ExceptionController.main()");
		throw new Exception("���ܰ� �߻��߽��ϴ�.");
	}

	@RequestMapping("/ex2")
	public String main2() throws Exception{
		throw new Exception("���ܰ� �߻��߽��ϴ�.");
	}

	@RequestMapping("/ex3")
	public String main3() throws NullPointerException{
		throw new NullPointerException("���ܰ� �߻��߽��ϴ�.");
	}
	
	@RequestMapping("/ex4")
	public String main4() throws FileNotFoundException{
		throw new NullPointerException("���ܰ� �߻��߽��ϴ�.");
	}
}
