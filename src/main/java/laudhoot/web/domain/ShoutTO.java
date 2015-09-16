package laudhoot.web.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import laudhoot.core.domain.rest.Reply;
import laudhoot.core.domain.rest.Shout;
import laudhoot.web.util.ServiceRequest;
import laudhoot.web.util.ServiceResponse;

public class ShoutTO extends BaseTO {

	@NotEmpty(groups = { ServiceRequest.CreateShout.class, ServiceRequest.class })
	@Length(max = 500, groups = { ServiceRequest.CreateShout.class,
			ServiceRequest.class, ServiceResponse.class })
	private String message;

	@NotNull(groups = { ServiceRequest.CreateShout.class, ServiceRequest.class })
	@Min(value = 0, groups = { ServiceRequest.CreateShout.class,
			ServiceRequest.class })
	private Long laudCount;

	@NotNull(groups = { ServiceRequest.CreateShout.class, ServiceRequest.class })
	@Min(value = 0, groups = { ServiceRequest.CreateShout.class,
			ServiceRequest.class })
	private Long hootCount;

	@NotNull(groups = { ServiceResponse.class })
	private String geoFenceCode;

	@Null(groups = { ServiceRequest.CreateShout.class })
	private List<ReplyTO> replies;

	public ShoutTO(String message) {
		super();
		this.message = message;
	}

	public ShoutTO(Shout shout) {
		super();
		this.id = shout.getId();
		this.message = shout.getMessage();
		this.laudCount = shout.getLaudCount();
		this.hootCount = shout.getHootCount();
		this.geoFenceCode = shout.getGeoFence().getCode();
		this.replies = new ArrayList<ReplyTO>();
		for (Reply reply : shout.getReplies()) {
			this.replies.add(new ReplyTO(reply));
		}
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

	public List<ReplyTO> getReplies() {
		return replies;
	}

	public void setReplies(List<ReplyTO> replies) {
		this.replies = replies;
	}

	public String getGeoFenceCode() {
		return geoFenceCode;
	}

	public void setGeoFenceCode(String geoFenceCode) {
		this.geoFenceCode = geoFenceCode;
	}

}
