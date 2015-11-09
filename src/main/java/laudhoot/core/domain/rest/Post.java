package laudhoot.core.domain.rest;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import laudhoot.core.domain.GeoFence;

@MappedSuperclass
public abstract class Post extends BaseRestDomain {
	
	@OneToOne
	private GeoFence geoFence;
	
	@Lob
	@Column(length = 500)
	private String message;
	
	private Long laudCount;

	private Long hootCount;
	
	public Post() {
		super();
	}
	
	public Post(GeoFence geoFence, String message) {
		super();
		this.geoFence = geoFence;
		this.message = message;
	}
	
	public Post(GeoFence geoFence, String message, Long laudCount, Long hootCount) {
		super();
		this.geoFence = geoFence;
		this.message = message;
		this.laudCount = laudCount;
		this.hootCount = hootCount;
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
	
	public GeoFence getGeoFence() {
		return geoFence;
	}

	public void setGeoFence(GeoFence geoFence) {
		this.geoFence = geoFence;
	}

	public Long getLaudCount() {
		return laudCount;
	}

	public Long getHootCount() {
		return hootCount;
	}

}
