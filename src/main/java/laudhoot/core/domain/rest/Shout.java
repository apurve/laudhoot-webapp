package laudhoot.core.domain.rest;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import laudhoot.core.domain.GeoFence;
import laudhoot.web.domain.ShoutTO;

/**
 * A domain for shouts in a geofence.
 * 
 * @author apurve
 */

@Entity
public class Shout extends Post {

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	List<Reply> replies;
	
	public Shout() {
		super();
	}

	public Shout(GeoFence geoFence, String message) {
		super(geoFence, message);
	}

	public Shout(GeoFence geoFence, ShoutTO shoutTO) {
		super(geoFence, shoutTO.getMessage());
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

}
