package laudhoot.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import laudhoot.web.domain.GeoFenceTO;
import laudhoot.web.domain.GeoLocationTO;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.joda.time.Hours;

/**
 * A geographical fence under which a network can be established.
 * 
 * @author apurve
 * */

@Entity
public class GeoFence extends BaseDomain {

	//commonly known name of the geofence
	private String name;
	
	//unique code of the geofence, cannot be changed once created
	@Column(updatable = false, unique = true)
	private String code;
	
	@Lob
	@Column(length=512)
	private String description;
	
	@OneToOne
	private GeoLocation center;
	
	//radius of geofence in meters
	private Integer radius;
	
	@Column(columnDefinition = "TIMESTAMP", insertable = true, updatable = true)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime expiresOn;
	
	public GeoFence() {

	}
	
	public GeoFence(GeoFenceTO geofenceTO) {
		super();
		this.name = geofenceTO.getName();
		this.code = geofenceTO.getCode();
		this.description = geofenceTO.getDescription();
		this.center = new GeoLocation(geofenceTO.getCenter());
		this.radius = geofenceTO.getRadiusInMeters();
		this.expiresOn = DateTime.now().plusHours(geofenceTO.getExpiresInHours());
	}
	
	public GeoFence(String code, GeoLocation center, Integer radius,
			DateTime expiresOn) {
		super();
		this.code = code;
		this.center = center;
		this.radius = radius;
		this.expiresOn = expiresOn;
	}

	public GeoFence(String name, String code, String description,
			GeoLocation center, Integer radius, DateTime expiresOn) {
		super();
		this.name = name;
		this.code = code;
		this.description = description;
		this.center = center;
		this.radius = radius;
		this.expiresOn = expiresOn;
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

	public GeoLocation getCenter() {
		return center;
	}

	public void setCenter(GeoLocation center) {
		this.center = center;
	}

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	public DateTime getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(DateTime expiresOn) {
		this.expiresOn = expiresOn;
	}
	
}
