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
//������� �Է��ϸ� ������ �˷��ִ� ���α׷�
public class YoilTellerMVC {
	@RequestMapping("/getYoilMVC") // http://localhost/app/getYoilMVC?year=2022&month=10&day=5
	public ModelAndView main(int year, int month, int day) throws IOException {
		
		//1. ��ȿ�� �˻�
//		if (!isValid(year, month, day))
//			return "yoilError";
		
		ModelAndView model = new ModelAndView();
		
		//2. ���� ���
		char yoil = getYoil(year, month, day);
		
		//3. ����� ����� model�� ����
		model.addObject("year", year);
		model.addObject("month", month);
		model.addObject("day", day);
		model.addObject("yoil", yoil);
		
		//4. ����� ������ view�� ����
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
		return " �Ͽ�ȭ�������".charAt(dayOfWeek);
	}

}