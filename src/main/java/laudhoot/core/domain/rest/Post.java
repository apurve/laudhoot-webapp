package laudhoot.core.domain.rest;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Post extends BaseRestDomain {
	
	@Lob
	@Column(length = 500)
	private String message;

	private Long laudCount;

	private Long hootCount;
	
	public Post() {
		super();
	}
	
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

	public Long getHootCount() {
		return hootCount;
	}

}
