package laudhoot.core.domain;

import javax.persistence.Entity;

/**
 * A geographical location on the earth.
 * 
 * */

@Entity
public class GeoLocation extends BaseDomain {
	
	private Double latitude;
	
	private Double longitude;

	public GeoLocation() {
		super();
	}

	public GeoLocation(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
}
