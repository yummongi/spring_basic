package com.yummongi.app;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RequestMapping("/register")
@Controller
public class RegisterController {

	//커스텀 변환기
	@InitBinder
	public void toDate(WebDataBinder binder) {
		ConversionService conversionService = binder.getConversionService();
//		System.out.println("conversionService= " + conversionService);
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
		binder.registerCustomEditor(String[].class,"hobby", new StringArrayPropertyEditor("#"));
//		binder.setValidator(new UserValidator()); //UserValidator를 WebDataBinder의 로컬 validator 로 등록
//		binder.addValidators(new UserValidator());
		//현재 GlobalValidator 를 등록된 상태
		List<Validator> validatorList = binder.getValidators();
		System.out.println("validatorList =" + validatorList);
	}
	
//	@RequestMapping(value = "/register/add", method = {RequestMethod.GET, RequestMethod.POST}) //아래와 동일
//	@RequestMapping("/register/add") //신규 회원 가입 화면
	//GET 방식으로만 가능
//	@GetMapping("/register/add")
	@GetMapping("/add")
	public String register() {
		
	
		return "registerForm"; //WEB-INF/views/registerForm.jsp
	}
	
//	@RequestMapping(value = "register/save", method = RequestMethod.POST) //아래와 동일
	//POST 방식으로만 가능
	@PostMapping("/add") //Spring 버전 4.3 부터
	public String add(@Valid User user, BindingResult result, Model m) throws Exception {
		
//		//수동 검증
//		UserValidator userValidator = new UserValidator();
//		userValidator.validate(user, result); //BindingResult는 Errors의 자손
//		
		
		//User 객체를 검증한 결과 에러가 있으면, registerForm를 이용해서 에러를 보여줘야함
		if(result.hasErrors()) {
			return "registerForm";
		}
//		//1. 유효성 검사
//		if(!isValid(user)) {
//			String msg = URLEncoder.encode("id를 잘못입력하셨습니다!", "utf-8");
//			//return "redirect:/register/add?msg="+msg; //URL 재작성 (rewriting)
//			m.addAttribute("msg", msg);
//			return "forward:/register/add";
//		}
		//2. DB에 신규 회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}
