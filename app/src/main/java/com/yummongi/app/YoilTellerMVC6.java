package com.yummongi.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
//������� �Է��ϸ� ������ �˷��ִ� ���α׷�
public class YoilTellerMVC6 {
	
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex, BindingResult result) {
		System.out.println("result= " + result);
		FieldError error = result.getFieldError();
		//ex.printStackTrace();
		
		System.out.println("code="+error.getCode());
		System.out.println("field="+error.getField());
		System.out.println("msg="+error.getDefaultMessage());
		return "yoilError";
	}
	@RequestMapping("/getYoilMVC6") // http://localhost/app/getYoilMVC?year=2022&month=10&day=5
//	public String main(@ModelAttribute("myDate") Mydate date, Model model) throws IOException {	//�Ʒ��� ����	
	public String main(Mydate date,BindingResult result) {
		
		System.out.println("result= " + result);
		//1. ��ȿ�� �˻�
		if (!isValid(date))
			return "yoilError";
		
/*
@ModelAttribute �� ���� ���� �ʾƵ� ��
		2. ���� ���
		char yoil = getYoil(date); 
		
		3. ����� ����� model�� ����
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
    	
    	return (1<=month && month<=12) && (1<=day && day<=31); // ������ üũ 
	}
	
	private @ModelAttribute("yoil") char getYoil(Mydate date) {
		// TODO Auto-generated method stub
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return " �Ͽ�ȭ�������".charAt(dayOfWeek);
	}

}