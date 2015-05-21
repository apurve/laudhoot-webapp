package laudhoot.core.util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public interface LaudhootDomainValidator extends Validator {

	public void validate(Object target, Errors errors, Class<?>... groups);
}
