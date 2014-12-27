package laudhoot.core.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * A base class for core models to provide common properties while persisting.
 * @author Apurve Gupta
 */

@MappedSuperclass
public abstract class BaseCoreDomain {
	
	//@Converter(name = "dateTimeConverter", converterClass = laudhoot.core.domain.util.JodaDateTimeConverter.class)

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(columnDefinition="TIMESTAMP", insertable=true, updatable=false)
	//@Convert("dateTimeConverter")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createdOn;
	
	@Column(columnDefinition="TIMESTAMP", insertable=true, updatable=true)
	//@Convert("dateTimeConverter")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updatedOn;
	
	// Timestamp in sql cannot be null or 0000-00-00 00:00:00 because such a date does not exists.
	@PrePersist
	void onCreate() {
		this.setCreatedOn(DateTime.now());
		this.setUpdatedOn(DateTime.now());
	}

	@PreUpdate
	void onPersist() {
		this.setUpdatedOn(DateTime.now());
	}

	public BaseCoreDomain() {	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(DateTime createdOn) {
		this.createdOn = createdOn;
	}

	public DateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(DateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
	
}
