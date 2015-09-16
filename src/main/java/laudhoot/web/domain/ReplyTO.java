package laudhoot.web.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.NotEmpty;

import laudhoot.core.domain.rest.Reply;
import laudhoot.web.util.ServiceRequest;
import laudhoot.web.util.ServiceResponse;

public class ReplyTO extends BaseTO {

	@NotNull(groups = { ServiceRequest.CreateReply.class, ServiceResponse.class })
	private Long shoutId;
	
	@Null(groups = { ServiceRequest.CreateShout.class })
	@NotEmpty(groups = { ServiceRequest.CreateReply.class, ServiceResponse.class })
	private String message;

	@Null(groups = { ServiceRequest.CreateReply.class, ServiceRequest.CreateShout.class })
	@Min(value = 0, groups = { ServiceResponse.class })
	private Long laudCount;

	@Null(groups = { ServiceRequest.CreateReply.class, ServiceRequest.CreateShout.class })
	@Min(value = 0, groups = { ServiceResponse.class })
	private Long hootCount;

	public ReplyTO(String message, Long laudCount, Long hootCount) {
		super();
		this.message = message;
		this.laudCount = laudCount;
		this.hootCount = hootCount;
	}

	public ReplyTO(Reply reply) {
		super();
		this.message = reply.getMessage();
		this.laudCount = reply.getLaudCount();
		this.hootCount = reply.getHootCount();
	}
	
	public ReplyTO(Reply reply, Long shoutId) {
		super();
		this.shoutId = shoutId;
		this.message = reply.getMessage();
		this.laudCount = reply.getLaudCount();
		this.hootCount = reply.getHootCount();
	}
	
	public Long getShoutId() {
		return shoutId;
	}

	public void setShoutId(Long shoutId) {
		this.shoutId = shoutId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getLaudCount() {
		return laudCount;
	}

	public void setLaudCount(Long laudCount) {
		this.laudCount = laudCount;
	}

	public Long getHootCount() {
		return hootCount;
	}

	public void setHootCount(Long hootCount) {
		this.hootCount = hootCount;
	}

}
