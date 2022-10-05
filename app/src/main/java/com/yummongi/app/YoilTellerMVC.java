package com.yummongi.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//년월일을 입력하면 요일을 알려주는 프로그램
public class YoilTellerMVC {
	@RequestMapping("/getYoilMVC") // http://localhost/app/getYoilMVC?year=2022&month=10&day=5
	public ModelAndView main(int year, int month, int day) throws IOException {
		
		//1. 유효성 검사
//		if (!isValid(year, month, day))
//			return "yoilError";
		
		ModelAndView model = new ModelAndView();
		
		//2. 요일 계산
		char yoil = getYoil(year, month, day);
		
		//3. 계산한 결과를 model에 저장
		model.addObject("year", year);
		model.addObject("month", month);
		model.addObject("day", day);
		model.addObject("yoil", yoil);
		
		//4. 결과를 보여줄 view를 지정
		model.setViewName("yoil");
		return model;
//		return "yoil";
	}

	private boolean isValid(int year, int month, int day) {

		return true;
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return " 일월화수목금토".charAt(dayOfWeek);
	}

}