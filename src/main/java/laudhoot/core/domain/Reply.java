package laudhoot.core.domain;

import javax.persistence.Entity;

import laudhoot.web.domain.ReplyTO;

/**
 * A domain for reply to a shout in a geofence
 * 
 * @author apurve
 */

@Entity
public class Reply extends BaseDomain {

	private String message;
	
	private Long laudCount;
	
	private Long hootCount;
	
	public Reply(String message) {
		super();
		this.message = message;
	}
	
	public Reply(ReplyTO replyTO) {
		super();
		setId(replyTO.getId());
		this.message = replyTO.getMessage();
		this.hootCount = replyTO.getHootCount();
		this.laudCount = replyTO.getLaudCount();
	}
	
	public void laud() {
		this.laudCount = this.laudCount + 1;
	}

	public void hoot() {
		this.hootCount = this.hootCount + 1;
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
