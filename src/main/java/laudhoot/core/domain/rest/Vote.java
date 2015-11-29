package laudhoot.core.domain.rest;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * The domain for persisting a laud.
 * 
 * @author apurve
 */

@Entity
public class Vote extends BaseRestDomain {
	
	@ManyToOne
	private Post post;
	
	private Boolean isLaud;
	
	public Vote() {
		super();
	}

	public Vote(Post post, Boolean isLaud) {
		super();
		this.post = post;
		this.isLaud = isLaud;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Boolean getIsLaud() {
		return isLaud;
	}

	public void setIsLaud(Boolean isLaud) {
		this.isLaud = isLaud;
	}

}
