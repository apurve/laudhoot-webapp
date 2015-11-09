package laudhoot.core.domain.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import laudhoot.core.domain.GeoFence;
import laudhoot.web.domain.ReplyTO;
import laudhoot.web.domain.ShoutTO;

/**
 * A domain for shouts in a geofence.
 * 
 * @author apurve
 */

@Entity
public class Shout extends Post {

	@OneToMany(fetch=FetchType.EAGER)
	List<Reply> replies;
	
	public Shout() {
		super();
	}

	public Shout(GeoFence geoFence, String message) {
		super(geoFence, message);
	}

	public Shout(GeoFence geoFence, ShoutTO shoutTO) {
		super(geoFence, shoutTO.getMessage(), shoutTO.getLaudCount(),  shoutTO.getHootCount());
		super.setId(shoutTO.getId());
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

}
