import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
/**
 * In the Main class we create an instance of the Travelling Salesman Problem. In this case, we are looking for the shortest path of
 * cities, without travelling to a city twice. Each city has a representation by it's name and two dimension coordinates.
 * The input start with an Integer representing the number of cities.
 * Afterwards the user must type a series of lines with the format string double double.
 * Then the population size, number of generations, mutation probability, crossover probability and replacement fraction must be given.
 * The population size and number of generations are Integers and mutation probability, crossover probability and replacement fraction are doubles.
 * @author André Pires
 * @author Paulo Henriques
 * @see City
 * @see CityTest
 * @see GeneticAlgorithm
 * @see GeneticAlgorithmTest
 * @see IIndividual
 * @see ILocation
 * @see PointTwoDimensions
 * @see PointTwoDimensionsTest
 * @see Population
 * @see PopulationTest
 * @see TSP
 * @see TSPTest
 */
public class Main {
		public static void main(String[] args) {
			Random generator = new Random();
			Scanner sc=new Scanner(System.in);
			sc.useLocale(Locale.ENGLISH);
			List<City> cities = new ArrayList<City>();
			System.out.print("How many cities: "); int numberOfCities= sc.nextInt();
			System.out.println("Insert the cities in following format (name x y): ");
			for(int i=0;i<numberOfCities;i++) {
				System.out.print("City number " + (i+1)+ ": ");
				cities.add(new City(sc.next(),new PointTwoDimensions(sc.nextDouble(),sc.nextDouble())));
			}
			TSP initial = new TSP(cities);
			
			System.out.print("Population Size: "); int popuSize=sc.nextInt();
			System.out.print("Number Of Generations: "); int generations=sc.nextInt();
			System.out.print("Mutation Probability(0-1): "); double mutationProbability= sc.nextDouble();
			System.out.print("CrossOver Probability(0-1): "); double crossoverProbability = sc.nextDouble();
			System.out.print("Replacement Fraction(0-1): "); double replacementFraction= sc.nextDouble(); 
			System.out.print("Tournament Size: "); int tournamentSize= sc.nextInt(); 
			
			GeneticAlgorithm a = new GeneticAlgorithm(initial,generator,popuSize,mutationProbability,crossoverProbability,replacementFraction,generations,tournamentSize);
			
			System.out.println(a.solveWithTournament());
			sc.close();
		}
			
}