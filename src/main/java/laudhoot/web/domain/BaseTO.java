package laudhoot.web.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.validation.BindingResult;

public class BaseTO {
	@Null(groups={ServiceRequest.CreateGeoFence.class})
	@NotNull(groups={ServiceResponse.class, ServiceRequest.class})
	Long id;
	
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
