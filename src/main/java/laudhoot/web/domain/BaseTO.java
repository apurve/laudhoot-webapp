package laudhoot.web.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import laudhoot.web.util.ServiceRequest;
import laudhoot.web.util.ServiceResponse;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(value = {"serialVersionUID", "validation", "ERROR_KEY"})
public abstract class BaseTO implements Serializable {
	
	private transient static final long serialVersionUID = 3076569036610518006L;

	@Null(groups={ServiceRequest.CreateGeoFence.class, ServiceRequest.CreateClient.class})
	@NotNull(groups={ServiceResponse.class, ServiceRequest.class})
	Long id;
	
	@NotNull(groups={ServiceResponse.class, ServiceRequest.class})
	String username;
	
	@NotNull(groups={ServiceResponse.class, ServiceRequest.class})
	String clientId;
	
	/**
	 * The result is passed from the controller to the service where validation is performed
	 * the service should never create a new BindingResult object rather perform operation
	 * of the passed objects so that validation results are available in the controller by using
	 * the same reference.
	 * 
	 * There should not be getter and setter for validation as JACKSON works on getters for serialization
	 * */
	private transient BindingResult validation;
	
	private boolean error;
	
	@JsonInclude(Include.NON_EMPTY)
	private Map<String, String> errorMessages;
	
	public transient final static String ERROR_KEY = "error"; 
	
	public BaseTO() {
		super();
	}

	public BaseTO(Long id) {
		super();
		this.id = id;
	}
	
	public BaseTO(Long id, String username, String clientId) {
		super();
		this.id = id;
		this.username = username;
		this.clientId = clientId;
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
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public BindingResult getValidation() {
		return validation;
	}

	public void setValidation(BindingResult validation) {
		this.validation = validation;
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

	public boolean hasError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	@JsonIgnore
	public String getErrorMessage() {
		return errorMessages.get(ERROR_KEY);
	}

	public void populateValidatonErrors() {
		if(validation.hasErrors()) {
			this.error = validation.hasErrors();
			if(errorMessages == null) {
				errorMessages = new HashMap<String, String>();
			}
			for(FieldError fieldError : validation.getFieldErrors()) {
				errorMessages.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
		}
	}
	
	public void populateError(String message) {
		this.error = true;
		if(errorMessages == null) {
			errorMessages = new HashMap<String, String>();
		}
		errorMessages.put(ERROR_KEY, message);
	}
	
}
