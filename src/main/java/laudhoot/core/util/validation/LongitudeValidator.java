package laudhoot.core.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import laudhoot.core.util.CoordinateManager;

public class LongitudeValidator implements ConstraintValidator<Longitude, Double> {

	@Override
	public void initialize(Longitude arg0) {
		
	}

	@Override
	public boolean isValid(Double longitude, ConstraintValidatorContext arg1) {
		if (longitude == null)
			return false;
		if (CoordinateManager.isValidLongitude(longitude))
			return true;
		return false;
	}

}
