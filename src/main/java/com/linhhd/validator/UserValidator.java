package com.linhhd.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.linhhd.model.UserDTO;

@Component
public class UserValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		
		return UserDTO.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		UserDTO user = (UserDTO) target;
		if (user.getName() == null || user.getName().length() == 0) {
			errors.rejectValue("name", "field.require");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "field.require");
		
//		if(user.getPassword().length() < 6 || user.getPassword().length() > 12) {
//			errors.rejectValue("password", "password.inValid");
//		}

	}

}
