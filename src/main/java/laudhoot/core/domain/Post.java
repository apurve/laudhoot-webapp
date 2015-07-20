package laudhoot.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Post extends BaseDomain {
	
	@Lob
	@Column(length = 500)
	private String message;

	private Long laudCount;

	private Long hootCount;
	
	public Post(String message) {
		super();
		this.message = message;
	}
	
	public Post(String message, Long laudCount, Long hootCount) {
		super();
		this.message = message;
		this.laudCount = laudCount;
		this.hootCount = hootCount;
	}
	
	public void laud() {
		this.laudCount = this.laudCount + 1;
	}

	public void hoot() {
		this.hootCount = this.hootCount + 1;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getLaudCount() {
		return laudCount;
	}

	public void setLaudCount(Long laudCount) {
		this.laudCount = laudCount;
	}

	public Long getHootCount() {
		return hootCount;
	}

	public void setHootCount(Long hootCount) {
		this.hootCount = hootCount;
	}
	
}
