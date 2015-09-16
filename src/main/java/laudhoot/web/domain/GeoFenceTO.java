package laudhoot.web.domain;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import laudhoot.core.domain.GeoFence;
import laudhoot.web.util.ServiceRequest;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.joda.time.Hours;

public class GeoFenceTO extends BaseTO {
	
	private String name;
	
	@NotEmpty(groups={ServiceRequest.CreateGeoFence.class})
	private String code;
	
	private String description;
	
	@NotNull(groups={ServiceRequest.CreateGeoFence.class})
	@Valid
	private CoordinateTO center;
	
	@NotNull(groups={ServiceRequest.CreateGeoFence.class})
	@Min(value=1000, groups={ServiceRequest.CreateGeoFence.class})
	@Max(value=5000, groups={ServiceRequest.CreateGeoFence.class})
	private Integer radiusInMeters;
	
	@NotNull(groups={ServiceRequest.CreateGeoFence.class})
	@Min(value=1, groups={ServiceRequest.CreateGeoFence.class})
	@Max(value=72, groups={ServiceRequest.CreateGeoFence.class})
	private Integer expiresInHours;
	
	public GeoFenceTO() {
		super();
	}

	public GeoFenceTO(GeoFence geofence) {
		super();
		this.id = geofence.getId();
		this.name = geofence.getName();
		this.code = geofence.getCode();
		this.description = geofence.getDescription();
		this.center = new CoordinateTO(geofence.getCenter());
		this.radiusInMeters = geofence.getRadius();
		this.expiresInHours = Hours.hoursBetween(DateTime.now(), geofence.getExpiresOn()).getHours();
	}
	
	public GeoFenceTO(String code, CoordinateTO center, Integer radiusInMeters,
			Integer expiresInHours) {
		super();
		this.code = code;
		this.center = center;
		this.radiusInMeters = radiusInMeters;
		this.expiresInHours = expiresInHours;
	}

	public GeoFenceTO(String name, String code, String description,
			CoordinateTO center, Integer radiusInMeters, Integer expiresInHours) {
		super();
		this.name = name;
		this.code = code;
		this.description = description;
		this.center = center;
		this.radiusInMeters = radiusInMeters;
		this.expiresInHours = expiresInHours;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CoordinateTO getCenter() {
		return center;
	}

	public void setCenter(CoordinateTO center) {
		this.center = center;
	}

	public Integer getRadiusInMeters() {
		return radiusInMeters;
	}

	public void setRadiusInMeters(Integer radiusInMeters) {
		this.radiusInMeters = radiusInMeters;
	}

	public Integer getExpiresInHours() {
		return expiresInHours;
	}

	public void setExpiresInHours(Integer expiresInHours) {
		this.expiresInHours = expiresInHours;
	}

}
