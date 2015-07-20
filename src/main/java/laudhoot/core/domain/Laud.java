package laudhoot.core.domain;

/**
 * The domain for persisting a laud.
 * 
 * @author apurve
 */

public class Laud extends BaseDomain {
	
	Post post;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
}
