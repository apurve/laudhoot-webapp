package laudhoot.core.util.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = LongitudeValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Longitude {

	String message() default "{laudhoot.core.util.validation.Longitude.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
}
