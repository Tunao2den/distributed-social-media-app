package com.tuna.userservice.validation;

import com.tuna.userservice.context.ContextProvider;
import com.tuna.userservice.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class UniqueValidator implements ConstraintValidator<Unique, String> {
    private UserRepository userRepository;

    public UniqueValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.userRepository = (UserRepository) ContextProvider.getBean(UserRepository.class);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !userRepository.existsByUserName(value);
    }
}