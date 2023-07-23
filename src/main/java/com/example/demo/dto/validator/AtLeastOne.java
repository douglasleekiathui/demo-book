package com.example.demo.dto.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * At least one field must not be null/empty
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=AtLeastOneValidator.class)
public @interface AtLeastOne {
	String message() default "Validation error";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
