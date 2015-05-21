package laudhoot.web.domain;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import laudhoot.core.domain.GeoLocation;

public class GeoLocationTO extends BaseTO {
	
	@NotNull(groups={ServiceRequest.CreateGeoFence.class})
	private Double latitude;
	
	@NotNull(groups={ServiceRequest.CreateGeoFence.class})
	private Double longitude;
	
	public GeoLocationTO() {
		super();
	}

	public GeoLocationTO(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public GeoLocationTO(GeoLocation geoLocation) {
		super();
		this.latitude = geoLocation.getLatitude();
		this.longitude = geoLocation.getLongitude();
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
