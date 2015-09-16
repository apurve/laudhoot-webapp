package laudhoot.core.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import laudhoot.core.util.LaudhootValidationException;
import laudhoot.core.util.validation.LaudhootExceptionUtils;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * A base class for all other classes which needs to be persisted in a database, 
 * it provides common properties while persisting.
 * 
 * @author Apurve Gupta
 */

@MappedSuperclass
public abstract class BaseDomain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(columnDefinition = "TIMESTAMP", insertable = true, updatable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createdOn;

	@Column(columnDefinition = "TIMESTAMP", insertable = true, updatable = true)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updatedOn;
	
	private String createdBy;
	
	private String updatedBy;

	Integer archiveStatus;

	public BaseDomain() {
		super();
	}

	public String getEntityURI() throws LaudhootValidationException {
		if(LaudhootExceptionUtils.isPersisted(this, "Entity is not persisted."))
			return this.getClass().getCanonicalName()+":"+String.valueOf(this.id);
		return null;
	}

	@PrePersist
	protected void onCreate() {
		this.setCreatedOn(DateTime.now());
		this.setUpdatedOn(null);
		String createdByUsername = getUserName();
		this.setCreatedBy(LaudhootExceptionUtils.isNotEmpty(createdByUsername) ? createdByUsername : "system-generated");
		this.setUpdatedBy(null);
	}

	@PreUpdate
	protected void onPersist() {
		this.setUpdatedOn(DateTime.now());
		String updatedByUsername = getUserName();
		this.setUpdatedBy(LaudhootExceptionUtils.isNotEmpty(updatedByUsername) ? updatedByUsername : "system-updated");
	}
	
	private String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof User) {
			User user = (User) authentication.getPrincipal();
			if(user != null) {
				return user.getUsername();
			}
		}
		return null;
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
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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
