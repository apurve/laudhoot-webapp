package laudhoot.core.util.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public interface LaudhootValidator extends Validator {

	public void validate(Object target, Errors errors, Class<?>... groups);
}
