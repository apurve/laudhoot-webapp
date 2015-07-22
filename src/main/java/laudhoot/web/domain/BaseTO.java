package laudhoot.web.domain;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class BaseTO {
	@Null(groups={ServiceRequest.CreateGeoFence.class})
	@NotNull(groups={ServiceResponse.class, ServiceRequest.class})
	Long id;
	
	@NotNull(groups={ServiceResponse.class, ServiceRequest.class})
	String username;
	
	/**
	 * The result is passed from the controller to the service where validation is performed
	 * the service should never create a new BindingResult object rather perform operation
	 * of the passed objects so that validation results are available in the controller by using
	 * the same reference.
	 * */
	BindingResult validation;
	
	boolean error;
	
	Map<String, String> errorMessages;
	
	public BaseTO() {
		super();
	}

	public BaseTO(Long id, BindingResult validation) {
		super();
		this.id = id;
		this.validation = validation;
	}
	
	public BaseTO(Long id, String username, BindingResult validation) {
		super();
		this.id = id;
		this.username = username;
		this.validation = validation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public boolean isError() {
		return error;
	}

	public BindingResult getValidation() {
		return validation;
	}

	public void setValidation(BindingResult validation) {
		this.validation = validation;
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
		this.error = validation.hasErrors();
		errorMessages = new HashMap<String, String>();
		for(FieldError fieldError : validation.getFieldErrors()) {
			errorMessages.put(fieldError.getField(), messageSource.getMessage(fieldError, null));
		}
	}
	
}
