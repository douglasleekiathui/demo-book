package com.example.demo.dto.validator;

import java.lang.reflect.Field;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AtLeastOneValidator implements ConstraintValidator<AtLeastOne, Object> {

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		try {
			for (Field field: value.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object fieldValue = field.get(value);
				field.setAccessible(false);
				if (fieldValue != null) {
					if (fieldValue instanceof String) {
						if (StringUtils.hasText((String) fieldValue)) {
							return true;
						}
					} else {
						return true;
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {}
		return false;
	}

}
