package laudhoot.web.domain;

import javax.validation.constraints.Max;

import laudhoot.core.domain.rest.Shout;
import laudhoot.web.util.ServiceRequest;

public class ShoutTO extends PostTO {

	@Max(value=0, groups = { ServiceRequest.CreateShout.class })
	private int repliesCount;
	
	public ShoutTO() {
		super();
	}

	public ShoutTO(Shout shout) {
		super(shout);
		if(shout.getReplies() != null)
			this.repliesCount = shout.getReplies().size();
	}

	public int getRepliesCount() {
		return repliesCount;
	}

	public void setRepliesCount(int repliesCount) {
		this.repliesCount = repliesCount;
	}

}
