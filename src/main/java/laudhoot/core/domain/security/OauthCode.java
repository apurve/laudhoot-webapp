package laudhoot.core.domain.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import laudhoot.core.domain.BaseDomain;

@Entity
public class OauthCode extends BaseDomain {

	private String code;
	@Lob @Column(length=100000)
	private byte[] authentication;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public byte[] getAuthentication() {
		return authentication;
	}
	public void setAuthentication(byte[] authentication) {
		this.authentication = authentication;
	}
	
	
}
