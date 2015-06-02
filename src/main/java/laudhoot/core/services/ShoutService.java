package laudhoot.core.services;

import java.util.List;

import laudhoot.web.domain.CoordinateTO;
import laudhoot.web.domain.ReplyTO;
import laudhoot.web.domain.ShoutTO;

public interface ShoutService {

	/**
	 * Creates a new shout.
	 * */
	public ShoutTO createShout(ShoutTO shoutTO);

	/**
	 * Create a reply to a shout.
	 * */
	public ReplyTO createReply(ReplyTO replyTO);

	/**
	 * Get all shouts from a geoFence in which the passed coordinates lies.
	 * */
	public List<ShoutTO> getShoutsFromCoordinate(CoordinateTO coordinateTO);

	/**
	 * Get all shouts from a geoFence with passed code.
	 * */
	public List<ShoutTO> getShoutsFromGeoFence(String geoFenceCode);

	/**
	 * Record a laud to a shout.
	 * 
	 * @return laudCount
	 * */
	public Long laudShout(Long shoutId);

	/**
	 * Record a laud to a Reply.
	 * 
	 * @return laudCount
	 * */
	public Long laudReply(Long replyId);

	/**
	 * Record a hoot to a shout.
	 * 
	 * @return hootCount
	 * */
	public Long hootShout(Long shoutId);

	/**
	 * Record a hoot to a Reply.
	 * 
	 * @return hootCount
	 * */
	public Long hootReply(Long replyId);

}
