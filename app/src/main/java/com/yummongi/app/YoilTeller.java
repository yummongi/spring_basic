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

@Controller
//년월일을 입력하면 요일을 알려주는 프로그램
public class YoilTeller { //http://localhost/app/getYoilMVC?year=2022&month=10&day5
	@RequestMapping("/getYoilMVC")
//	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
	public String main(int year, int month, int day, HttpServletResponse response) throws IOException {
		//1. 유효성 검사
		if(!isValid(year, month,day)) return "yoilError";
		
      getYoil(year, month, day);
      
      // /WEB-INF/views/yoil.jsp
      return "yoil";

	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		  cal.set(year, month - 1, day);

		  int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		  return " 일월화수목금토".charAt(dayOfWeek);
	}

	private boolean isValid(int year, int month, int day) {
		
		return false;
	}

}