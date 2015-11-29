package laudhoot.web.domain;

import javax.validation.constraints.NotNull;

import laudhoot.core.domain.rest.Vote;
import laudhoot.web.util.ServiceRequest;
import laudhoot.web.util.ServiceResponse;

public class VoteTO extends BaseTO {

	@NotNull(groups = { ServiceRequest.LaudHoot.class, ServiceResponse.LaudHoot.class })
	private Long postId;
	
	@NotNull(groups = { ServiceRequest.LaudHoot.class, ServiceResponse.LaudHoot.class })
	private Boolean isLaud;
	
	public VoteTO() {
		super();
	}

	public VoteTO(Vote vote) {
		super(vote.getId(), vote.getCreatedBy(), vote.getClientId());
		this.postId = vote.getPost().getId();
		this.isLaud = vote.getIsLaud();
	}
	
	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Boolean getIsLaud() {
		return isLaud;
	}

	public void setIsLaud(Boolean isLaud) {
		this.isLaud = isLaud;
	}

}
