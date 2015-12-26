package laudhoot.core.domain;

import javax.persistence.Entity;

import laudhoot.web.domain.CoordinateTO;

/**
 * A geographical location on the earth.
 * 
 * */

@Entity
public class Coordinate extends BaseDomain {

	private Double latitude;

	private Double longitude;

	public Coordinate() {
		super();
	}

	public Coordinate(CoordinateTO geoLocationTO) {
		super();
		this.latitude = geoLocationTO.getLatitude();
		this.longitude = geoLocationTO.getLongitude();
	}

	public Coordinate(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coordinate) {
			Coordinate coordinate = (Coordinate) obj;
			return this.latitude == coordinate.getLatitude()
					&& this.longitude == coordinate.getLongitude();
		}
		return super.equals(obj);
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
