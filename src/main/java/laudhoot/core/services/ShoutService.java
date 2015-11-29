package laudhoot.core.services;

import java.util.List;

import laudhoot.web.domain.CoordinateTO;
import laudhoot.web.domain.ReplyTO;
import laudhoot.web.domain.ShoutTO;
import laudhoot.web.domain.VoteTO;

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
	 * Get paginated shouts from a geoFence with passed code.
	 * @param geoFenceCode, code for a geofence
	 * @param shoutsAvailable, shouts already available to the client
	 * 
	 * */
	public List<ShoutTO> getShoutsFromGeoFence(String geoFenceCode, Integer shoutsAvailable);
	
	/**
	 * Get all replies from a shout.
	 * */
	public List<ReplyTO> getRepliesFromShout(Long shoutId);
	
	/**
	 * Get paginated replies from a shout.
	 * @param shoutId, primary key for a shout
	 * @param repliesAvailable, replies already available to the client
	 * 
	 * */
	public List<ReplyTO> getRepliesFromShout(Long shoutId, Integer repliesAvailable);

	/**
	 * Record a vote.
	 * 
	 * */
	public VoteTO vote(VoteTO voteTO);

}
