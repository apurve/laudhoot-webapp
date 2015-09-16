package laudhoot.core.domain.rest;

import javax.persistence.Entity;

import laudhoot.web.domain.ReplyTO;

/**
 * A domain for reply to a shout in a geofence
 * 
 * @author apurve
 */

@Entity
public class Reply extends Post {
	
	public Reply() {
		super();
	}

	public Reply(String message) {
		super(message);
	}
	
	public Reply(ReplyTO replyTO) {
		super(replyTO.getMessage(), replyTO.getLaudCount(), replyTO.getHootCount());
		setId(replyTO.getId());
	}
	
}
