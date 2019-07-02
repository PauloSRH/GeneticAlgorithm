import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * This class tests the rouletteWheelSelection, selectedPopulation, getTotalFitness
 * and replaceWith method of a Population.
 */
class PopulationTest {

	@Test
	void testRouletteWheel() {
		Random generator = new Random(0);
		
		List<City> a = new ArrayList<City>();
		City a1 = new City("Andre",new PointTwoDimensions(1,2));
		City a2 = new City("Paulo",new PointTwoDimensions(3,4));
		City a3 = new City("Pires",new PointTwoDimensions(5,6));
        City a4 = new City("Henri",new PointTwoDimensions(7,8));
        City a5 = new City("AnPa",new PointTwoDimensions(9,10));
		a.add(a1);a.add(a2);a.add(a3);a.add(a4);a.add(a5);
		TSP test = new TSP(a);
		
		Population inicial = new Population(test,4);
		
		assertEquals(inicial.rouletteWheelSelection(generator,inicial.getTotalFitness()),test);
	}

	@Test
	void testSelectedPopulation() {
		Random generator = new Random(0);
		int PopulationSize = 4;
		
		List<City> a = new ArrayList<City>();
		City a1 = new City("Andre",new PointTwoDimensions(1,2));
		City a2 = new City("Paulo",new PointTwoDimensions(3,4));
		City a3 = new City("Pires",new PointTwoDimensions(5,6));
        City a4 = new City("Henri",new PointTwoDimensions(7,8));
        City a5 = new City("AnPa",new PointTwoDimensions(9,10));
		a.add(a1);a.add(a2);a.add(a3);a.add(a4);a.add(a5);
		TSP test = new TSP(a);
		
		Population inicial = new Population(test,PopulationSize);
		
		assertEquals(inicial.getPopulation(),inicial.selectedPopulationRoulette(generator, PopulationSize));
		
	}

	@Test
	void testTotalFitness() {
		List<City> a = new ArrayList<City>();
		City a1 = new City("Andre",new PointTwoDimensions(1,2));
		City a2 = new City("Paulo",new PointTwoDimensions(1,4));
		City a3 = new City("Pires",new PointTwoDimensions(1,6));
        City a4 = new City("Henri",new PointTwoDimensions(1,8));
        City a5 = new City("AnPa",new PointTwoDimensions(1,10));
		a.add(a1);a.add(a2);a.add(a3);a.add(a4);a.add(a5);
		TSP test = new TSP(a);
		
		Population inicial = new Population(test,4);
		
		assertEquals(inicial.getTotalFitness(), 0.25);
	}

	@Test
	void testReplaceWith() {
		List<City> a = new ArrayList<City>();
		City a1 = new City("Andre",new PointTwoDimensions(1,2));
		City a2 = new City("Paulo",new PointTwoDimensions(1,4));
		City a3 = new City("Pires",new PointTwoDimensions(1,6));
        City a4 = new City("Henri",new PointTwoDimensions(1,8));
        City a5 = new City("AnPa",new PointTwoDimensions(1,10));
		a.add(a1);a.add(a5);a.add(a3);a.add(a4);a.add(a2);
		TSP first = new TSP(a);
		
		Population old = new Population(first,4);
		
		List<City> b = new ArrayList<City>();
		b.add(a1);b.add(a2);b.add(a3);b.add(a4);b.add(a5);
		TSP second = new TSP(b);
		
		Population novo = new Population(second,4);
		
		old.replaceWith(novo, 1);
		
		assertEquals(old.getPopulation(),novo.getPopulation());
	}

	@Test
	void testTournament() {
		Random generator = new Random(0);
		
		
		List<City> a = new ArrayList<City>();
		City a1 = new City("Andre",new PointTwoDimensions(1,2));
		City a2 = new City("Paulo",new PointTwoDimensions(3,4));
		City a3 = new City("Pires",new PointTwoDimensions(5,6));
        City a4 = new City("Henri",new PointTwoDimensions(7,8));
        City a5 = new City("AnPa",new PointTwoDimensions(9,10));
		a.add(a1);a.add(a2);a.add(a3);a.add(a4);a.add(a5);
		TSP test = new TSP(a);
		
		Population inicial = new Population(test,generator,4);
		
		Population result = new Population();
		result.getPopulation().add(inicial.getPopulation().get(0));
		result.getPopulation().add(inicial.getPopulation().get(2));
		result.getPopulation().add(inicial.getPopulation().get(3));
		result.getPopulation().add(inicial.getPopulation().get(0));
		
		assertEquals(inicial.TournamentSelection(generator, 2),result.getPopulation());
	}

}