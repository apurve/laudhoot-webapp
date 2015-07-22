package laudhoot.core.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import laudhoot.web.domain.ReplyTO;
import laudhoot.web.domain.ShoutTO;

/**
 * A domain for shouts in a geofence.
 * 
 * @author apurve
 */

@Entity
public class Shout extends Post {

	@OneToOne
	private GeoFence geoFence;

	@OneToMany
	List<Reply> replies;

	public Shout(String message, GeoFence geoFence) {
		super(message);
		this.geoFence = geoFence;
	}

	public Shout(ShoutTO shoutTO, GeoFence geoFence) {
		super(shoutTO.getMessage(), shoutTO.getLaudCount(),  shoutTO.getHootCount());
		super.setId(shoutTO.getId());
		this.geoFence = geoFence;
		List<Reply> replies = new ArrayList<Reply>();
		for (ReplyTO replyTO : shoutTO.getReplies()) {
			replies.add(new Reply(replyTO));
		}
		this.replies = replies;
	}

	public GeoFence getGeoFence() {
		return geoFence;
	}

	public void setGeoFence(GeoFence geoFence) {
		this.geoFence = geoFence;
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

}
