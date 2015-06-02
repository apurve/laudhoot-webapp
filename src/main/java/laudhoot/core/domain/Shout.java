package laudhoot.core.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import laudhoot.web.domain.ReplyTO;
import laudhoot.web.domain.ShoutTO;

/**
 * A domain for shouts in a geofence
 * 
 * @author apurve
 */

@Entity
public class Shout extends BaseDomain {

	@Lob
	@Column(length = 500)
	private String message;

	private Long laudCount;

	private Long hootCount;

	@OneToOne
	private GeoFence geoFence;

	@OneToMany
	List<Reply> replies;

	public Shout(String message, GeoFence geoFence) {
		super();
		this.message = message;
		this.geoFence = geoFence;
	}

	public Shout(ShoutTO shoutTO, GeoFence geoFence) {
		super();
		super.setId(shoutTO.getId());
		this.message = shoutTO.getMessage();
		this.laudCount = shoutTO.getLaudCount();
		this.hootCount = shoutTO.getHootCount();
		this.geoFence = geoFence;
		List<Reply> replies = new ArrayList<Reply>();
		for (ReplyTO replyTO : shoutTO.getReplies()) {
			replies.add(new Reply(replyTO));
		}
		this.replies = replies;
	}

	public void laud() {
		this.laudCount = this.laudCount + 1;
	}

	public void hoot() {
		this.hootCount = this.hootCount + 1;
	}

	public GeoFence getGeoFence() {
		return geoFence;
	}

	public void setGeoFence(GeoFence geoFence) {
		this.geoFence = geoFence;
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

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

}
