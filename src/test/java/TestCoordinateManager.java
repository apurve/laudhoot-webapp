import laudhoot.core.domain.Coordinate;
import laudhoot.core.util.CoordinateManager;

public class TestCoordinateManager {
	public static void main(String[] args) {
		Coordinate one = new Coordinate(-28.6827485, 77.357788); //home
		Coordinate two = new Coordinate(-28.6834772, 77.3638161); //rajendra club
		System.out.println(CoordinateManager.distanceBetween(one, two));
	}
}
