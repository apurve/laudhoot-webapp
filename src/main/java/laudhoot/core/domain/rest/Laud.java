package laudhoot.core.domain.rest;


/**
 * The domain for persisting a laud.
 * 
 * @author apurve
 */

public class Laud extends BaseRestDomain {
	
	Post post;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
}
