package laudhoot.core.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import laudhoot.core.util.CoordinateManager;

public class LatitudeValidator implements ConstraintValidator<Latitude, Double> {

	@Override
	public void initialize(Latitude arg0) {

	}

	@Override
	public boolean isValid(Double latitude, ConstraintValidatorContext arg1) {
		if (latitude == null)
			return false;
		if (CoordinateManager.isValidLatitude(latitude))
			return true;
		return false;
	}

}
