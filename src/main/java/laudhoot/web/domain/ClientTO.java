package laudhoot.web.domain;

import laudhoot.web.util.ServiceRequest;
import laudhoot.web.util.ServiceResponse;

import org.hibernate.validator.constraints.NotEmpty;

public class ClientTO extends BaseTO {

	private static final long serialVersionUID = 3927498939498847964L;

	@NotEmpty(groups={ServiceRequest.CreateClient.class})
	private String clientId;
	
	@NotEmpty(groups={ServiceResponse.CreateClient.class})
	private String clientSecret;
	
	public ClientTO() {
		super();
	}

	public ClientTO(String clientId) {
		super();
		this.clientId = clientId;
	}

	public ClientTO(String clientId, String clientSecret) {
		super();
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getclientSecret() {
		return clientSecret;
	}

	public void setclientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	
}
