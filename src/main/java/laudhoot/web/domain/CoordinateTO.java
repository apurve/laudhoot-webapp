package laudhoot.web.domain;


import laudhoot.core.domain.Coordinate;
import laudhoot.core.util.validation.Latitude;
import laudhoot.core.util.validation.Longitude;
import laudhoot.web.util.ServiceRequest;

public class CoordinateTO extends BaseTO {
	
	private static final long serialVersionUID = 5756361205943290081L;

	@Latitude(groups={ServiceRequest.CreateGeoFence.class, ServiceRequest.class})
	private Double latitude;
	
	@Longitude(groups={ServiceRequest.CreateGeoFence.class, ServiceRequest.class})
	private Double longitude;
	
	public CoordinateTO() {
		super();
	}

	public CoordinateTO(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public CoordinateTO(Coordinate geoLocation) {
		this.id = geoLocation.getId();
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
