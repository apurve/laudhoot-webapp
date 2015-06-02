package laudhoot.core.services;

import java.util.ArrayList;
import java.util.List;

import laudhoot.core.domain.GeoFence;
import laudhoot.core.domain.Reply;
import laudhoot.core.domain.Shout;
import laudhoot.core.repository.GeoFenceRepository;
import laudhoot.core.repository.ReplyRepository;
import laudhoot.core.repository.ShoutRepository;
import laudhoot.core.util.validation.LaudhootExceptionUtils;
import laudhoot.core.util.validation.LaudhootValidator;
import laudhoot.web.domain.CoordinateTO;
import laudhoot.web.domain.ReplyTO;
import laudhoot.web.domain.ServiceRequest;
import laudhoot.web.domain.ShoutTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ShoutServiceImpl implements ShoutService {

	@Autowired
	ShoutRepository shoutRepository;

	@Autowired
	ReplyRepository replyRepository;

	@Autowired
	GeoFenceRepository geoFenceRepository;

	@Autowired
	LaudhootValidator validator;

	@Override
	public ShoutTO createShout(ShoutTO shoutTO) {
		LaudhootExceptionUtils.isNotNull(shoutTO, "Shout cannot be null.");
		validator.validate(shoutTO, shoutTO.getValidationResult(),
				ServiceRequest.CreateShout.class);
		if (shoutTO.getValidationResult().hasErrors()) {
			return shoutTO;
		}
		Shout shout = new Shout(shoutTO, geoFenceRepository.findByCode(shoutTO
				.getGeoFenceCode()));
		shout = shoutRepository.save(shout);
		return new ShoutTO(shout);
	}

	@Override
	public ReplyTO createReply(ReplyTO replyTO) {
		LaudhootExceptionUtils.isNotNull(replyTO, "Reply cannot be null.");
		validator.validate(replyTO, replyTO.getValidationResult(),
				ServiceRequest.CreateReply.class);
		if (replyTO.getValidationResult().hasErrors()) {
			return replyTO;
		}
		Shout shout = shoutRepository.findOne(replyTO.getShoutId());
		LaudhootExceptionUtils.isNotNull(shout, "Shout not found.");
		Reply reply = new Reply(replyTO);
		reply = replyRepository.save(reply);

		List<Reply> replies = shout.getReplies();
		if (CollectionUtils.isEmpty(replies)) {
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
	public Long laudShout(Long shoutId) {
		LaudhootExceptionUtils.isNotNull(shoutId, "Shout id cannot be null.");
		Shout shout = shoutRepository.findOne(shoutId);
		if (shout != null) {
			shout.laud();
			shoutRepository.save(shout);
			return shout.getLaudCount();
		}
		return null;
	}

	@Override
	public Long laudReply(Long replyId) {
		LaudhootExceptionUtils.isNotNull(replyId, "Reply id cannot be null.");
		Reply reply = replyRepository.findOne(replyId);
		if (reply != null) {
			reply.laud();
			replyRepository.save(reply);
			return reply.getLaudCount();
		}
		return null;
	}

	@Override
	public Long hootShout(Long shoutId) {
		LaudhootExceptionUtils.isNotNull(shoutId, "Shout id cannot be null.");
		Shout shout = shoutRepository.findOne(shoutId);
		if (shout != null) {
			shout.hoot();
			shoutRepository.save(shout);
			return shout.getHootCount();
		}
		return null;
	}

	@Override
	public Long hootReply(Long replyId) {
		LaudhootExceptionUtils.isNotNull(replyId, "Reply id cannot be null.");
		Reply reply = replyRepository.findOne(replyId);
		if (reply != null) {
			reply.hoot();
			replyRepository.save(reply);
			return reply.getHootCount();
		}
		return null;
	}

}
