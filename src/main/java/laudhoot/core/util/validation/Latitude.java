package laudhoot.core.util.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = LatitudeValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Latitude {

	String message() default "{laudhoot.core.util.validation.Latitude.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
}
