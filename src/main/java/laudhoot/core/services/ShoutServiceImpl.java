package laudhoot.core.services;

import java.util.ArrayList;
import java.util.List;

import laudhoot.core.domain.GeoFence;
import laudhoot.core.domain.rest.Post;
import laudhoot.core.domain.rest.Reply;
import laudhoot.core.domain.rest.Shout;
import laudhoot.core.domain.rest.Vote;
import laudhoot.core.repository.GeoFenceRepository;
import laudhoot.core.repository.PostRepository;
import laudhoot.core.repository.ReplyRepository;
import laudhoot.core.repository.ShoutRepository;
import laudhoot.core.repository.VoteRepository;
import laudhoot.core.util.validation.LaudhootExceptionUtils;
import laudhoot.core.util.validation.LaudhootValidator;
import laudhoot.web.domain.CoordinateTO;
import laudhoot.web.domain.ReplyTO;
import laudhoot.web.domain.ShoutTO;
import laudhoot.web.domain.VoteTO;
import laudhoot.web.util.ServiceRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShoutServiceImpl implements ShoutService {

	@Autowired
	private ShoutRepository shoutRepository;

	@Autowired
	private ReplyRepository replyRepository;

	@Autowired
	private GeoFenceRepository geoFenceRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private LaudhootValidator validator;

	@Override
	public ShoutTO createShout(ShoutTO shoutTO) {
		LaudhootExceptionUtils.isNotNull(shoutTO, "Shout cannot be null.");
		validator.validate(shoutTO, shoutTO.getValidation(),
				ServiceRequest.CreateShout.class);
		if (shoutTO.getValidation().hasErrors()) {
			return shoutTO;
		}
		Shout shout = new Shout(geoFenceRepository.findByCode(shoutTO
				.getGeoFenceCode()), shoutTO);
		shout = shoutRepository.save(shout);
		return new ShoutTO(shout);
	}

	@Override
	public ReplyTO createReply(ReplyTO replyTO) {
		LaudhootExceptionUtils.isNotNull(replyTO, "Reply cannot be null.");
		validator.validate(replyTO, replyTO.getValidation(),
				ServiceRequest.CreateReply.class);
		if (replyTO.getValidation().hasErrors()) {
			return replyTO;
		}
		Shout shout = shoutRepository.findOne(replyTO.getShoutId());
		LaudhootExceptionUtils.isNotNull(shout, "Shout not found.");
		Reply reply = new Reply(shout.getGeoFence(), replyTO);
		reply = replyRepository.save(reply);

		List<Reply> replies = shout.getReplies();
		if (replies == null) {
			replies = new ArrayList<Reply>();
			replies.add(reply);
			shout.setReplies(replies);
		} else {
			replies.add(reply);
		}
		shoutRepository.save(shout);

		return new ReplyTO(reply, shout.getId());
	}

	@Override
	public List<ShoutTO> getShoutsFromCoordinate(CoordinateTO coordinateTO) {
		// TODO -- Not implementing this service as it may not be needed in business.
		return null;
	}

	@Override
	public List<ShoutTO> getShoutsFromGeoFence(String geoFenceCode) {
		LaudhootExceptionUtils.isNotEmpty(geoFenceCode, "GeoFence code cannot be empty.");
		GeoFence geoFence = geoFenceRepository.findByCode(geoFenceCode);
		List<ShoutTO> shouts = new ArrayList<ShoutTO>();
		for (Shout shout : shoutRepository.findByGeoFence(geoFence)) {
			shouts.add(new ShoutTO(shout));
		}
		return shouts;
	}
	
	@Override
	public List<ShoutTO> getShoutsFromGeoFence(String geoFenceCode, Integer shoutsAvailable) {
		List<ShoutTO> shouts = getShoutsFromGeoFence(geoFenceCode);
		if (shouts != null && shoutsAvailable < shouts.size()) {
			shouts = shouts.subList(shoutsAvailable, shouts.size());
			if (shouts.size() > 10) {
				return shouts.subList(shoutsAvailable, shoutsAvailable + 10);
			} else {
				return shouts;
			}
		}
		return null;
	}
	
	@Override
	public List<ReplyTO> getRepliesFromShout(Long shoutId) {
		LaudhootExceptionUtils.isNotNull(shoutId, "Shout id code cannot be null.");
		Shout shout = shoutRepository.findOne(shoutId);
		List<ReplyTO> replies = new ArrayList<ReplyTO>();
		for (Reply reply : shout.getReplies()) {
			replies.add(new ReplyTO(reply, shoutId));
		}
		return replies;
	}
	
	@Override
	public List<ReplyTO> getRepliesFromShout(Long shoutId, Integer repliesAvailable) {
		List<ReplyTO> replies = getRepliesFromShout(shoutId);
		if (replies != null && repliesAvailable < replies.size()) {
			replies = replies.subList(repliesAvailable, replies.size());
			if (replies.size() > 10) {
				return replies.subList(repliesAvailable, repliesAvailable + 10);
			} else {
				return replies;
			}
		}
		return null;
	}

	@Override
	public VoteTO vote(VoteTO voteTO) {
		LaudhootExceptionUtils.isNotNull(voteTO, "vote cannot be null.");
		validator.validate(voteTO, voteTO.getValidation(),
				ServiceRequest.LaudHoot.class);
		if (voteTO.getValidation().hasErrors()) {
			return voteTO;
		}
		Post post = postRepository.findOne(voteTO.getPostId());
		Vote vote = new Vote(post, voteTO.getIsLaud());
		voteRepository.save(vote);
		if(vote.getIsLaud()) {
			post.setLaudCount(post.getLaudCount() + 1);
		} else {
			post.setHootCount(post.getHootCount() + 1);
		}
		if (post.getVotes() != null) {
			post.setVotes(new ArrayList<Vote>());
		}
		post.getVotes().add(vote);
		return new VoteTO(vote);
	}

	@Override
	public List<ShoutTO> getShoutsOfClient(String clientId, Integer shoutsAvailable) {
		LaudhootExceptionUtils.isNotNull(clientId, "Client id cannot be empty.");
		List<ShoutTO> shouts = new ArrayList<ShoutTO>();
		for (Shout shout : shoutRepository.findByClientId(clientId)) {
			shouts.add(new ShoutTO(shout));
		}
		if (shouts != null && shoutsAvailable < shouts.size()) {
			shouts = shouts.subList(shoutsAvailable, shouts.size());
			if (shouts.size() > 10) {
				return shouts.subList(shoutsAvailable, shoutsAvailable + 10);
			} else {
				return shouts;
			}
		}
		return null;
	}

	@Override
	public ShoutTO getShout(Long id) {
		return new ShoutTO(shoutRepository.findOne(id));
	}

}
