package laudhoot.core.repository;


import java.util.Collection;

import laudhoot.core.domain.Shout;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * An interface for a repository that can store Test Shout
 * objects and allow them to be searched.
 * 
 * @author Apurve Gupta
 *
 */
// This @RepositoryRestResource annotation tells Spring Data Rest to
// expose the VideoRepository through a controller and map it to the 
// "/video" path. This automatically enables you to do the following:
//
// 1. List all videos by sending a GET request to /video 
// 2. Add a video by sending a POST request to /video with the JSON for a video
// 3. Get a specific video by sending a GET request to /video/{videoId}
//    (e.g., /video/1 would return the JSON for the video with id=1)
// 4. Send search requests to our findByXYZ methods to /video/search/findByXYZ
//    (e.g., /video/search/findByName?title=Foo)
//
@Repository
public interface TestRepository extends CrudRepository<Shout, Long>{

	// Find all videos with a matching name (e.g., Video.name)
	public Collection<Shout> findByTestText(String testText);
	
}
