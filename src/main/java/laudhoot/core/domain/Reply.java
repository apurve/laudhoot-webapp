package laudhoot.core.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import laudhoot.web.domain.ReplyTO;

/**
 * A domain for reply to a shout in a geofence
 * 
 * @author apurve
 */

@Entity
public class Reply extends Post {
	
	public Reply(String message) {
		super(message);
	}
	
	public Reply(ReplyTO replyTO) {
		super(replyTO.getMessage(), replyTO.getLaudCount(), replyTO.getHootCount());
		setId(replyTO.getId());
	}
	
}
