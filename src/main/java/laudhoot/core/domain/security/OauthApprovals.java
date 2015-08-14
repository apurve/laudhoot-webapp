package laudhoot.core.domain.security;

import javax.persistence.Column;
import javax.persistence.Entity;

import laudhoot.core.domain.BaseDomain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
public class OauthApprovals extends BaseDomain {

	private String userid;
	private String clientid;
	private String scope;
	private String status;
	
	@Column(columnDefinition = "TIMESTAMP", insertable = true, updatable = true)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime expiresat;
	
	@Column(columnDefinition = "TIMESTAMP", insertable = true, updatable = true)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime lastmodifiedat;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DateTime getExpiresat() {
		return expiresat;
	}

	public void setExpiresat(DateTime expiresat) {
		this.expiresat = expiresat;
	}

	public DateTime getLastmodifiedat() {
		return lastmodifiedat;
	}

	public void setLastmodifiedat(DateTime lastmodifiedat) {
		this.lastmodifiedat = lastmodifiedat;
	}
	
}
