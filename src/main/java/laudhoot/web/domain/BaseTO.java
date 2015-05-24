package laudhoot.web.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.validation.BindingResult;

public class BaseTO {
	@Null(groups={ServiceRequest.CreateGeoFence.class})
	@NotNull(groups={ServiceResponse.class, ServiceRequest.class})
	Long id;
	
	/**
	 * The result is passed from the controller to the service where validation is performed
	 * the service should never create a new BindingResult object rather perform operation
	 * of the passed objects so that validation results are available in the controller by using
	 * the same reference.
	 * */
	BindingResult validationResult;

	public BaseTO() {
		super();
	}

	public BaseTO(Long id, BindingResult validationResult) {
		super();
		this.id = id;
		this.validationResult = validationResult;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BindingResult getValidationResult() {
		return validationResult;
	}

	public void setValidationResult(BindingResult validationResult) {
		this.validationResult = validationResult;
	}
	
}
