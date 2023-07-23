package com.example.demo.dto.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WhitelistedValueValidator implements ConstraintValidator<WhitelistedValue, String> {
	private List<String> whitelist = new ArrayList<>();
	
	public void initialize(WhitelistedValue constraintAnnotation) {
		if (constraintAnnotation.whitelist() != null && constraintAnnotation.whitelist().length > 0) {
			whitelist = Arrays.asList(constraintAnnotation.whitelist());	
		} else {
			for (Class<? extends Enum> e : constraintAnnotation.enumVal()) {
				whitelist.addAll(Arrays.asList(e.getEnumConstants()).stream().map(x -> x.name()).collect(Collectors.toList()));
			}
		}
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.hasText(value)) {
			return whitelist.contains(value);
		}
		return true;
	}

}
