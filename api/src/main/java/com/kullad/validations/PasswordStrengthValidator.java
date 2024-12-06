package com.kullad.validations;

import com.kullad.annotation.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {
    List<String> weekPasswords;

    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        weekPasswords = Arrays.asList("12345", "qwerty", "password", "54321");
    }

    @Override
    public boolean isValid(String passwordFields, ConstraintValidatorContext constraintValidatorContext) {
        return passwordFields != null && (!weekPasswords.contains(passwordFields));
    }
}
