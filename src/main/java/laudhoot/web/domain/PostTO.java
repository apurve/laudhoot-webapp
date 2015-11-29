package laudhoot.web.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import laudhoot.core.domain.rest.Post;
import laudhoot.core.domain.rest.Vote;
import laudhoot.web.util.ServiceRequest;
import laudhoot.web.util.ServiceResponse;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

public abstract class PostTO extends BaseTO {

	@NotEmpty(groups = { ServiceRequest.CreateShout.class, ServiceRequest.class })
	@Length(max = 500, groups = { ServiceRequest.CreateShout.class,
			ServiceRequest.class, ServiceResponse.class })
	private String message;
	
	@NotNull(groups = { ServiceResponse.class })
	private String geoFenceCode;

	@NotNull(groups = { ServiceResponse.class, ServiceRequest.class })
	@Min(value = 0, groups = { ServiceRequest.CreateShout.class,
			ServiceRequest.class })
	private Long laudCount;

	@NotNull(groups = { ServiceResponse.class, ServiceRequest.class })
	@Min(value = 0, groups = { ServiceRequest.CreateShout.class,
			ServiceRequest.class })
	private Long hootCount;
	
	private boolean voted;

	@Null(groups = { ServiceRequest.CreateShout.class, ServiceRequest.class })
	@NotNull(groups = { ServiceResponse.class })
    private Boolean isLaudVote;
	
	public PostTO() {
		super();
	}

	public PostTO(String geoFenceCode, String message) {
		super();
		this.geoFenceCode = geoFenceCode;
		this.message = message;
	}
	
	public PostTO(String geoFenceCode, String message, Long laudCount, Long hootCount) {
		super();
		this.geoFenceCode = geoFenceCode;
		this.message = message;
		this.laudCount = laudCount;
		this.hootCount = hootCount;
	}
	
	public PostTO(Post post) {
		super(post.getId(), post.getCreatedBy(), post.getClientId());
		this.geoFenceCode = post.getGeoFence().getCode();
		this.message = post.getMessage();
		this.laudCount = post.getLaudCount();
		this.hootCount = post.getHootCount();
		if (post.getVotes() != null)
			for (Vote vote : post.getVotes()) {
				if (SecurityContextHolder.getContext().getAuthentication() instanceof OAuth2Authentication) {
					if (vote.getClientId().equals(
							((OAuth2Authentication) SecurityContextHolder
									.getContext().getAuthentication())
									.getOAuth2Request().getClientId())) {
						this.voted = true;
						this.isLaudVote = vote.getIsLaud();
					}
				}
			}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getGeoFenceCode() {
		return geoFenceCode;
	}

	public void setGeoFenceCode(String geoFenceCode) {
		this.geoFenceCode = geoFenceCode;
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

	public boolean isVoted() {
		return voted;
	}

	public void setVoted(boolean voted) {
		this.voted = voted;
	}

	public Boolean getIsLaudVote() {
		return isLaudVote;
	}

	public void setIsLaudVote(Boolean isLaudVote) {
		this.isLaudVote = isLaudVote;
	}
	
}
