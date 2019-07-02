import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This class tests the equals and distance method of a PointTwoDimensions.
 */
class PointTwoDimensionsTest {

	@Test
	void testEquals1() {
		PointTwoDimensions a = new PointTwoDimensions(1,2);
		PointTwoDimensions b = new PointTwoDimensions(1,2);
		assertTrue(a.equals(b));
	}

	@Test
	void testEquals2() {
		PointTwoDimensions a = new PointTwoDimensions(1,2);
		PointTwoDimensions b = new PointTwoDimensions(1,4);
		assertFalse(a.equals(b));
	}

	@Test
	void testEquals3() {
		PointTwoDimensions a = new PointTwoDimensions(1,2);
		assertFalse(a.equals(null));
	}

	@Test
	void testDistance1() {
		PointTwoDimensions a = new PointTwoDimensions(1,2);
		PointTwoDimensions b = new PointTwoDimensions(1,4);
		assertEquals(a.distance(b),2.0);
	}

	@Test
	void testDistance2() {
		PointTwoDimensions a = new PointTwoDimensions(-20,-20);
		PointTwoDimensions b = new PointTwoDimensions(-391,-41);
		assertEquals(a.distance(b), 371.59386431963594);
	}

	@Test
	void testDistance3() {
		PointTwoDimensions a = new PointTwoDimensions(1,1);
		PointTwoDimensions b = new PointTwoDimensions(-1,-1);
		assertEquals(a.distance(b),2.8284271247461903);
	}
}