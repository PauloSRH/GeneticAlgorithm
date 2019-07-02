import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This class tests the getDistance method of a City.
 * The method calculates the distance between two cities.
 */
class CityTest {

	@Test
	void testDistance1() {
		City a = new City("Andre", new PointTwoDimensions(1,2));
		City b = new City("Paulo", new PointTwoDimensions(1,4));
	
		assertEquals(a.getDistance(b),2.0);
	}
	
	@Test
	void testDistance2() {
		City a = new City("London", new PointTwoDimensions(1337,-2000));
		City b = new City("Tokyo", new PointTwoDimensions(4545,3371));
	
		assertEquals(a.getDistance(b),6256.109414004842);
	}
	
	@Test
	void testDistance3() {
		City a = new City("London", new PointTwoDimensions(-137,-200));
		City b = new City("Tokyo", new PointTwoDimensions(-45,-71));
	
		assertEquals(a.getDistance(b), 158.4455742518547);
	}

}
