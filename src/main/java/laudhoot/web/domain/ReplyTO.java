package laudhoot.web.domain;

import javax.validation.constraints.NotNull;

import laudhoot.core.domain.rest.Reply;
import laudhoot.web.util.ServiceRequest;
import laudhoot.web.util.ServiceResponse;

public class ReplyTO extends PostTO {

	@NotNull(groups = { ServiceRequest.CreateReply.class, ServiceResponse.class })
	private Long shoutId;
	
	public ReplyTO() {
		super();
	}
	
	public ReplyTO(Reply reply, Long shoutId) {
		super(reply);
		this.shoutId = shoutId;
	}

	public Long getShoutId() {
		return shoutId;
	}

	public void setShoutId(Long shoutId) {
		this.shoutId = shoutId;
	}

}
