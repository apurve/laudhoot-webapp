package laudhoot.core.util;

import laudhoot.core.domain.Coordinate;

public class CoordinateManager {

	/**
	 * The minimum allowed latitude
	 */
	public static final Double MIN_LATITUDE = Double.valueOf("-90.0000");

	/**
	 * The maximum allowed latitude
	 */
	public static final Double MAX_LATITUDE = Double.valueOf("90.0000");

	/**
	 * The minimum allowed longitude
	 */
	public static final Double MIN_LONGITUDE = Double.valueOf("-180.0000");

	/**
	 * The maximum allowed longitude
	 */
	public static final Double MAX_LONGITUDE = Double.valueOf("180.0000");

	/**
	 * The diameter of the Earth used in calculations in kilometers
	 */
	public static final Double EARTH_DIAMETER = Double.valueOf("12756.274");

	/**
	 * A method to validate a latitude value
	 *
	 * @param latitude
	 *            the latitude to check is valid
	 *
	 * @return true if, and only if, the latitude is within the MIN and MAX
	 *         latitude
	 */
	public static boolean isValidLatitude(Double latitude) {
		if (latitude >= MIN_LATITUDE && latitude <= MAX_LATITUDE) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * A method to validate a longitude value
	 *
	 * @param longitude
	 *            the longitude to check is valid
	 *
	 * @return true if, and only if, the longitude is between the MIN and MAX
	 *         longitude
	 */
	public static boolean isValidLongitude(Double longitude) {
		if (longitude >= MIN_LONGITUDE && longitude <= MAX_LONGITUDE) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * A private method to calculate the latitude constant
	 *
	 * @return a double representing the latitude constant
	 */
	private static double latitudeConstant() {
		return EARTH_DIAMETER * (Math.PI / Double.valueOf("360"));
		// return EARTH_DIAMETER * (Double.valueOf("3.14") /
		// Double.valueOf("360"));
	}

	/**
	 * A private method to caluclate the longitude constant
	 *
	 * @param latitude
	 *            a latitude coordinate in decimal notation
	 *
	 * @return a double representing the longitude constant
	 */
	private static double longitudeConstant(Double latitude) {

		// return Math.abs( Math.cos(Math.abs(latitude)));
		return EARTH_DIAMETER * Math.PI
				* Math.abs(Math.cos(Math.abs(latitude))) / Double.valueOf("360");

	}

	/**
	 * A method to add distance in a northerly direction to a coordinate
	 *
	 * @param latitude
	 *            a latitude coordinate in decimal notation
	 * @param longitude
	 *            a longitude coordinate in decimal notation
	 * @param distance
	 *            the distance to add in metres
	 *
	 * @return the new coordinate
	 */
	public static Coordinate addDistanceNorth(Double latitude, Double longitude,
			int distance) {

		// check on the parameters
		if (isValidLatitude(latitude) == false
				|| isValidLongitude(longitude) == false || distance <= 0) {
			throw new IllegalArgumentException(
					"All parameters are required and must be valid");
		}

		// convert the distance from metres to kilometers
		Double kilometers = distance / new Double(1000);

		// calculate the new latitude
		double newLat = latitude + (kilometers / latitudeConstant());

		return new Coordinate(new Double(newLat).doubleValue(), longitude);

	}

	/**
	 * A method to add distance in a southerly direction to a coordinate
	 *
	 * @param latitude
	 *            a latitude coordinate in decimal notation
	 * @param longitude
	 *            a longitude coordinate in decimal notation
	 * @param distance
	 *            the distance to add in metres
	 *
	 * @return the new coordinate
	 */
	public static Coordinate addDistanceSouth(Double latitude, Double longitude,
			int distance) {

		// check on the parameters
		if (isValidLatitude(latitude) == false
				|| isValidLongitude(longitude) == false || distance <= 0) {
			throw new IllegalArgumentException(
					"All parameters are required and must be valid");
		}

		// convert the distance from metres to kilometers
		Double kilometers = distance / new Double(1000);

		// calculate the new latitude
		double newLat = latitude - (kilometers / latitudeConstant());

		return new Coordinate(new Double(newLat).doubleValue(), longitude);

	}

	/**
	 * A method to add distance in an easterly direction to a coordinate
	 *
	 * @param latitude
	 *            a latitude coordinate in decimal notation
	 * @param longitude
	 *            a longitude coordinate in decimal notation
	 * @param distance
	 *            the distance to add in metres
	 *
	 * @return the new coordinate
	 */
	public static Coordinate addDistanceEast(Double latitude, Double longitude,
			int distance) {

		// check on the parameters
		if (isValidLatitude(latitude) == false
				|| isValidLongitude(longitude) == false || distance <= 0) {
			throw new IllegalArgumentException(
					"All parameters are required and must be valid");
		}

		// calculate the new longitude
		double newLng = longitude + (distance / longitudeConstant(latitude));

		return new Coordinate(latitude, new Double(newLng).doubleValue());
	}

	/**
	 * A method to add distance in an westerly direction to a coordinate
	 *
	 * @param latitude
	 *            a latitude coordinate in decimal notation
	 * @param longitude
	 *            a longitude coordinate in decimal notation
	 * @param distance
	 *            the distance to add in metres
	 *
	 * @return the new coordinate
	 */
	public static Coordinate addDistanceWest(Double latitude, Double longitude,
			int distance) {

		// check on the parameters
		if (isValidLatitude(latitude) == false
				|| isValidLongitude(longitude) == false || distance <= 0) {
			throw new IllegalArgumentException(
					"All parameters are required and must be valid");
		}

		// calculate the new longitude
		double newLng = longitude - (distance / longitudeConstant(latitude));

		return new Coordinate(latitude, new Double(newLng).doubleValue());
	}
	
	/**
	 * A method to find distance between two coordinates
	 *
	 * @param location1
	 *            coordinates of location one
	 * @param location2
	 *            coordinates of location two
	 *
	 * @return the distance in meters
	 */
	public static Double distanceBetween(Coordinate coordinate1, Coordinate coordinate2) {

		// check on the parameters
		if (!isValidLatitude(coordinate1.getLatitude()) || !isValidLatitude(coordinate2.getLatitude())
				|| !isValidLongitude(coordinate1.getLongitude()) || !isValidLongitude(coordinate2.getLongitude())) {
			throw new IllegalArgumentException(
					"All parameters are required and must be valid");
		}

		final double lat1 = Math.toRadians(coordinate1.getLatitude());
		final double lon1 = Math.toRadians(coordinate1.getLongitude());
		final double lat2 = Math.toRadians(coordinate2.getLatitude());
		final double lon2 = Math.toRadians(coordinate2.getLongitude());
		
		return Math.acos(
					Math.cos(lat1)*Math.cos(lon1)*Math.cos(lat2)*Math.cos(lon2)
					+Math.cos(lat1)*Math.sin(lon1)*Math.cos(lat2)*Math.sin(lon2)
					+Math.sin(lat1)*Math.sin(lat2)
				) * EARTH_DIAMETER / 2 * 1000;
	}

	/**
	 * A method to build four coordinates representing a bounding box given a
	 * start coordinate and a distance
	 *
	 * @param latitude
	 *            a latitude coordinate in decimal notation
	 * @param longitude
	 *            a longitude coordinate in decimal notation
	 * @param distance
	 *            the distance to add in metres
	 *
	 * @return a hashMap representing the bounding box (NE,SE,SW,NW)
	 */
	public static java.util.HashMap<String, Coordinate> getBoundingBox(
			Double latitude, Double longitude, int distance) {

		// check on the parameters
		if (isValidLatitude(latitude) == false
				|| isValidLongitude(longitude) == false || distance <= 0) {
			throw new IllegalArgumentException(
					"All parameters are required and must be valid");
		}

		// declare helper variables
		java.util.HashMap<String, Coordinate> boundingBox = new java.util.HashMap<String, Coordinate>();

		// calculate the coordinates
		Coordinate north = addDistanceNorth(latitude, longitude, distance);
		Coordinate south = addDistanceSouth(latitude, longitude, distance);
		Coordinate east = addDistanceEast(latitude, longitude, distance);
		Coordinate west = addDistanceWest(latitude, longitude, distance);

		// build the bounding box object
		boundingBox.put("NE",
				new Coordinate(north.getLatitude(), east.getLongitude()));
		boundingBox.put("SE",
				new Coordinate(south.getLatitude(), east.getLongitude()));
		boundingBox.put("SW",
				new Coordinate(south.getLatitude(), west.getLongitude()));
		boundingBox.put("NW",
				new Coordinate(north.getLatitude(), west.getLongitude()));

		// return the bounding box object
		return boundingBox;
	}
}
