package laudhoot.core.domain.rest;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import laudhoot.core.domain.GeoFence;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Post extends BaseRestDomain {
	
	@OneToOne
	private GeoFence geoFence;
	
	@Lob
	@Column(length = 500)
	private String message;
	
	@OneToMany
	private List<Vote> votes;
	
	private long laudCount;

	private long hootCount;
	
	public Post() {
		super();
	}
	
	public Post(GeoFence geoFence, String message) {
		super();
		this.geoFence = geoFence;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public GeoFence getGeoFence() {
		return geoFence;
	}

	public void setGeoFence(GeoFence geoFence) {
		this.geoFence = geoFence;
	}
	
	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public long getLaudCount() {
		return laudCount;
	}

	public long getHootCount() {
		return hootCount;
	}

	public void setLaudCount(long laudCount) {
		this.laudCount = laudCount;
	}

	public void setHootCount(long hootCount) {
		this.hootCount = hootCount;
	}
	
}
