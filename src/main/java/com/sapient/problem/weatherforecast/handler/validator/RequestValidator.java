package com.sapient.problem.weatherforecast.handler.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;


/**
 * Generic Validator to validate request- response object. This is required as Spring webflux
 * doesn't support automatic validation as part of request model conversion
 * 
 * @author Manish Jain
 *
 */
@Component
@RequiredArgsConstructor
public class RequestValidator {

    private final Validator validator;

    public <T> Mono<T> validate(T obj) {

        if (obj == null) {
            return Mono.error(new IllegalArgumentException());
        }

        Set<ConstraintViolation<T>> violations = this.validator.validate(obj);
        if (violations == null || violations.isEmpty()) {
            return Mono.just(obj);
        }

        return Mono.error(new ConstraintViolationException(violations));
    }
}
