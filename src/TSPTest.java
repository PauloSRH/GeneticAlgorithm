import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * This class tests the twoBitSwapMutation, calculateFitness, crossOver,
 * permutation and clone method of a TSP.
 */
class TSPTest {

	@Test
	void testMutation1() {
		Random generator = new Random(0);
		List<City> a = new ArrayList<City>();
		City a1 = new City("Andre",new PointTwoDimensions(1,2));
		City a2 = new City("Paulo",new PointTwoDimensions(3,4));
		City a3 = new City("Pires",new PointTwoDimensions(5,6));
        City a4 = new City("Henri",new PointTwoDimensions(7,8));
		a.add(a1);a.add(a2);a.add(a3);a.add(a4);
		TSP test = new TSP(a);
		test.twoBitSwapMutation(generator, 0.74);
		List<City> result = new ArrayList<City>();
		result.add(a1);result.add(a3);result.add(a2);result.add(a4);
		assertTrue(test.getRepresentation().equals(result));
	}

	@Test
	void testMutation2() {
		Random generator = new Random(0);
		List<City> a = new ArrayList<City>();
		City a1 = new City("Andre",new PointTwoDimensions(1,2));
		City a2 = new City("Paulo",new PointTwoDimensions(3,4));
		City a3 = new City("Pires",new PointTwoDimensions(5,6));
        City a4 = new City("Henri",new PointTwoDimensions(7,8));
		a.add(a1);a.add(a2);a.add(a3);a.add(a4);
		TSP test = new TSP(a);
		test.twoBitSwapMutation(generator, 0.005);
		List<City> result = new ArrayList<City>();
		result.add(a1);result.add(a2);result.add(a3);result.add(a4);
		assertTrue(test.getRepresentation().equals(result));
	}

	@Test
	void testMutation3() {
		Random generator = new Random(0);
		List<City> a = new ArrayList<City>();
		City a1 = new City("Andre",new PointTwoDimensions(1,2));
		City a2 = new City("Paulo",new PointTwoDimensions(3,4));
		City a3 = new City("Pires",new PointTwoDimensions(5,6));
        City a4 = new City("Henri",new PointTwoDimensions(7,8));
		a.add(a1);a.add(a2);a.add(a3);a.add(a4);
		TSP test = new TSP(a);
		test.twoBitSwapMutation(generator, 0.999);
		List<City> result = new ArrayList<City>();
		result.add(a1);result.add(a3);result.add(a2);result.add(a4);
		assertTrue(test.getRepresentation().equals(result));
	}

	@Test
	void testCalculateFitness1() {
		List<City> a = new ArrayList<City>();
		City a1 = new City("Andre",new PointTwoDimensions(1,2));
		City a2 = new City("Paulo",new PointTwoDimensions(3,4));
		City a3 = new City("Pires",new PointTwoDimensions(5,6));
        City a4 = new City("Henri",new PointTwoDimensions(7,8));
        City a5 = new City("AnPa",new PointTwoDimensions(9,10));
        a.add(a1);a.add(a2);a.add(a3);a.add(a4);a.add(a5);
		TSP test = new TSP(a);
		test.calculateFitness();
		assertEquals(test.getFitness(),0.044194173824159216);
	}

	@Test
	void testCalculateFitness2() {
		List<City> a = new ArrayList<City>();
		City a1 = new City("Andre",new PointTwoDimensions(-1,2));
		City a2 = new City("Paulo",new PointTwoDimensions(-3,42));
		City a3 = new City("Pires",new PointTwoDimensions(-5,60));
        City a4 = new City("Henri",new PointTwoDimensions(7,81));
        City a5 = new City("AnPa",new PointTwoDimensions(9,103));
        a.add(a1);a.add(a2);a.add(a3);a.add(a4);a.add(a5);
		TSP test = new TSP(a);
		test.calculateFitness();
		assertEquals(test.getFitness(),0.004855970069780639);
	}

	@Test
	void testCalculateFitness3() {
		List<City> a = new ArrayList<City>();
		City a1 = new City("Andre",new PointTwoDimensions(-10.5,2));
		City a2 = new City("Paulo",new PointTwoDimensions(-3.7,4.2));
		City a3 = new City("Pires",new PointTwoDimensions(-5.2,6.0));
        City a4 = new City("Henri",new PointTwoDimensions(7.0,8.1));
        City a5 = new City("AnPa",new PointTwoDimensions(13.9,10.3));
        a.add(a1);a.add(a2);a.add(a3);a.add(a4);a.add(a5);
		TSP test = new TSP(a);
		test.calculateFitness();
		assertEquals(test.getFitness(),0.018219978827278736);
	}

	@Test
	void testCrossOver1() {
		Random generator = new Random(0);
		List<City> a = new ArrayList<City>();
		City a1 = new City("Andre",new PointTwoDimensions(1,2));
		City a2 = new City("Paulo",new PointTwoDimensions(3,4));
		City a3 = new City("Pires",new PointTwoDimensions(5,6));
        City a4 = new City("Henri",new PointTwoDimensions(7,8));
        City a5 = new City("AnPa",new PointTwoDimensions(9,10));
		a.add(a1);a.add(a2);a.add(a3);a.add(a4);a.add(a5);
		TSP test = new TSP(a);
		
		List<City> b = new ArrayList<City>();
		b.add(a1);b.add(a5);b.add(a2);b.add(a3);b.add(a4);
		TSP test2 = new TSP(b);
		
		List<IIndividual> result = test.crossOver(generator, 0.8, test2);
		
		List<City> result1 = new ArrayList<City>();
		result1.add(a4);result1.add(a5);result1.add(a2);result1.add(a3);result1.add(a1);
		
		List<City> result2 = new ArrayList<City>();
		result2.add(a5);result2.add(a2);result2.add(a3);result2.add(a4);result2.add(a1);
		
		TSP um =(TSP)result.get(0);
		TSP dois = (TSP)result.get(1);
		
		assertTrue(um.getRepresentation().equals(result1));
		assertTrue(dois.getRepresentation().equals(result2));
		
	}

	@Test
	void testPermutation() {
		Random generator = new Random(0);
		
		List<City> a = new ArrayList<City>();
		City a1 = new City("Andre",new PointTwoDimensions(1,2));
		City a2 = new City("Paulo",new PointTwoDimensions(3,4));
		City a3 = new City("Pires",new PointTwoDimensions(5,6));
        City a4 = new City("Henri",new PointTwoDimensions(7,8));
        City a5 = new City("AnPa",new PointTwoDimensions(9,10));
		a.add(a1);a.add(a2);a.add(a3);a.add(a4);a.add(a5);
		TSP test = new TSP(a);
		
		TSP output = test.permutation(generator);
		
		List<City> result = new ArrayList<City>();
		result.add(a4);result.add(a3);result.add(a1);result.add(a5);result.add(a2);

		assertTrue(output.getRepresentation().equals(result));
	}

	@Test
	void testClone1() {
		List<City> a = new ArrayList<City>();
		City a1 = new City("Andre",new PointTwoDimensions(1,2));
		City a2 = new City("Paulo",new PointTwoDimensions(3,4));
		City a3 = new City("Pires",new PointTwoDimensions(5,6));
        City a4 = new City("Henri",new PointTwoDimensions(7,8));
        City a5 = new City("AnPa",new PointTwoDimensions(9,10));
		a.add(a1);a.add(a2);a.add(a3);a.add(a4);a.add(a5);
		TSP test = new TSP(a);
		
		assertTrue(test.equals(test.clone()));
	}
	
	@Test
	void testClone2() {
		List<City> a = new ArrayList<City>();
		City a1 = new City("Andre",new PointTwoDimensions(-4,-3));
		City a2 = new City("Paulo",new PointTwoDimensions(23,-4.5));
		City a3 = new City("Pires",new PointTwoDimensions(45,60));
        City a4 = new City("Henri",new PointTwoDimensions(19,400));
        City a5 = new City("AnPa",new PointTwoDimensions(9.0,-310));
		a.add(a1);a.add(a2);a.add(a3);a.add(a4);a.add(a5);
		TSP test = new TSP(a);
		
		assertTrue(test.equals(test.clone()));
	}

	@Test
	void testClone3() {
		List<City> a = new ArrayList<City>();
		City a1 = new City("London",new PointTwoDimensions(-4,-3));
		City a2 = new City("Berlin",new PointTwoDimensions(-40,-30));
		a.add(a1); a.add(a2);
		TSP test = new TSP(a);
		
		assertTrue(test.equals(test.clone()));
	}
	
}