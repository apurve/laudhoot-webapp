package laudhoot.web.domain;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

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
	
	boolean error;
	
	Map<String, String> errorMessages;

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

	public boolean hasError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public Map<String, String> getErrorMessage() {
		return errorMessages;
	}

	public void prepareMobileResponce(MessageSource messageSource){
		this.error = validationResult.hasErrors();
		errorMessages = new HashMap<String, String>();
		for(FieldError fieldError : validationResult.getFieldErrors()) {
			errorMessages.put(fieldError.getField(), messageSource.getMessage(fieldError, null));
		}
	}
	
}
