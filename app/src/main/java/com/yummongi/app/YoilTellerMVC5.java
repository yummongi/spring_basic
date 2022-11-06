package com.yummongi.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
//년월일을 입력하면 요일을 알려주는 프로그램
public class YoilTellerMVC5 {
	
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		//ex.printStackTrace();
		return "yoilError";
	}
	@RequestMapping("/getYoilMVC5") // http://localhost/app/getYoilMVC?year=2022&month=10&day=5
//	public String main(@ModelAttribute("myDate") Mydate date, Model model) throws IOException {	//아래와 동일	
	public String main(@ModelAttribute Mydate date, Model model) throws IOException {
		//1. 유효성 검사
		if (!isValid(date))
			return "yoilError";
		
/*
@ModelAttribute 로 인해 넣지 않아도 됨
		2. 요일 계산
		char yoil = getYoil(date); 
		
		3. 계산한 결과를 model에 저장
		model.addAttribute("myDate", date);
		model.addAttribute("yoil", yoil);
*/
		return "yoil";
	}

	private boolean isValid(Mydate date) {
		// TODO Auto-generated method stub
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}
	
	private boolean isValid(int year, int month, int day) {
    	if(year==-1 || month==-1 || day==-1) 
    		return false;
    	
    	return (1<=month && month<=12) && (1<=day && day<=31); // 간단히 체크 
	}
	
	private @ModelAttribute("yoil") char getYoil(Mydate date) {
		// TODO Auto-generated method stub
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return " 일월화수목금토".charAt(dayOfWeek);
	}

}