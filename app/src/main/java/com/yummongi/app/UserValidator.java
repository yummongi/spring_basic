package com.yummongi.app;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		//clazz가 User 또는 그 자손인지 확인
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		/**	원래는 instanceof 를 사용해야하지만, supports() 메소드에서
		 이미 확인했기 때문에 하지 않아도 된다.**/
		
		System.out.println("User Validator.validate() is called.");
		User user = (User) target; 
		
		String id = user.getId();
		
//		if(id==null||"".equals(id.trim())) {
//			errors.rejectValue("id", "required");
//		}
		//위와 동일 (비었거나 탭 등)
		//error 객체에 field 이름을 id로하고 에러이름을 required로 하자.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
		
		//id의 길이가 5글자보다 작거나, 12글자보다 크면
		if(id==null || id.length() < 5 || id.length() > 12) {
			errors.rejectValue("id", "invalidLength");
		}
		
	}

}
