package laudhoot.core.util.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import laudhoot.core.domain.BaseDomain;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class LaudhootValidatorImpl implements InitializingBean,
		LaudhootValidator {

	private Validator validator;

	@Override
	public void afterPropertiesSet() throws Exception {
		ValidatorFactory validatorFactory = Validation
				.buildDefaultValidatorFactory();
		validator = validatorFactory.usingContext().getValidator();
	}

	@Override
	public boolean supports(Class<?> clazz) {
		while (!clazz.equals(Object.class)) {
			if (clazz.getSuperclass().equals(BaseDomain.class)) {
				return true;
			}
			clazz = clazz.getSuperclass();
		}
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Set<ConstraintViolation<Object>> constraintViolations = validator
				.validate(target);
		for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
			String propertyPath = constraintViolation.getPropertyPath()
					.toString();
			String message = constraintViolation.getMessage();
			errors.rejectValue(propertyPath, "", message);
		}
	}

	@Override
	public void validate(Object target, Errors errors, Class<?>... groups) {
		Set<ConstraintViolation<Object>> constraintViolations = validator
				.validate(target, groups);
		for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
			String propertyPath = constraintViolation.getPropertyPath()
					.toString();
			String message = constraintViolation.getMessage();
			errors.rejectValue(propertyPath, "", message);
		}
	}
	
}
