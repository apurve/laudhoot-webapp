package laudhoot.core.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@Embeddable
public class GenericParameter extends BaseDomain {

	@Column(updatable = false, unique = true)
	String code;
	
	String name;
	
	@Lob
	@Column(length=512)
	String description;
	
	public GenericParameter() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}