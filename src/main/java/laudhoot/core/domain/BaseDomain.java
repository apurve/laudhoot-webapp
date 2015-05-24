package laudhoot.core.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import laudhoot.core.util.LaudhootValidationException;
import laudhoot.core.util.validation.LaudhootValidationUtils;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * A base class for all other classes which needs to be persisted in a database, 
 * it provides common properties while persisting.
 * 
 * @author Apurve Gupta
 */

@MappedSuperclass
public abstract class BaseDomain {
	
	//TODO - createdBy and updatedBy

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(columnDefinition = "TIMESTAMP", insertable = true, updatable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createdOn;

	@Column(columnDefinition = "TIMESTAMP", insertable = true, updatable = true)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updatedOn;

	Integer archiveStatus;

	public BaseDomain() {
		super();
	}

	public String getEntityURI() throws LaudhootValidationException {
		if(LaudhootValidationUtils.isPersisted(this, "Entity is not persisted."))
			return this.getClass().getCanonicalName()+":"+String.valueOf(this.id);
		return null;
	}

	@PrePersist
	void onCreate() {
		this.setCreatedOn(DateTime.now());
		this.setUpdatedOn(null);
	}

	@PreUpdate
	void onPersist() {
		this.setUpdatedOn(DateTime.now());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public Integer getArchiveStatus() {
		return archiveStatus;
	}

	public void setArchiveStatus(Integer archiveStatus) {
		this.archiveStatus = archiveStatus;
	}

	public static class ArchiveStatus {
		public static final int NOT_ARCHIVED = 0;
		public static final int MARKED_FOR_ARCHIVE = 1;
		public static final int ARCHIVED = 2;
	}

}
