package laudhoot.core.domain.rest;

import javax.persistence.Entity;

import laudhoot.core.domain.GeoFence;
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

	public Reply(GeoFence geoFence, String message) {
		super(geoFence, message);
	}
	
	public Reply(GeoFence geoFence, ReplyTO replyTO) {
		super(geoFence, replyTO.getMessage(), replyTO.getLaudCount(), replyTO.getHootCount());
	}
	
}
