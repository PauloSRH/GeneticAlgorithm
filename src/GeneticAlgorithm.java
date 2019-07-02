import java.util.Collections;
import java.util.Random;

/**
 * This class represents the data type which holds a Genetic Algorithm representation.
 */
public class GeneticAlgorithm {
	private Population population;
	private Random generator;
	private double crossoverProbability, replacementFraction, mutationProbability;
	private int generations,tournamentSize;

	/**
	 * Initializes the genetic algorithm with the corresponding fields.
	 * @param member The first individual of the population.
	 * @param generator The random number generator.
	 * @param popuSize The size of the population.
	 * @param mutProb The mutation probability.
	 * @param crossProb The crossover probability.
	 * @param replaceFraction The replacement fraction.
	 * @param generations The number of generations.
	 * @param tournamentSize The tournament size for the tournament selection.
	 */
	public GeneticAlgorithm(IIndividual member,Random generator, int popuSize,double mutProb, double crossProb, double replaceFraction, int generations, int tournamentSize){
		population = new Population(member,generator,popuSize);
		this.generator = generator;
		this.crossoverProbability=crossProb;
		this.replacementFraction=replaceFraction;
		this.mutationProbability= mutProb;
		this.generations=generations;
		this.tournamentSize = tournamentSize;
	}

	/**
	 * Initializes the genetic algorithm with the corresponding fields.
	 * @param member The first individual of the population.
	 * @param generator The random number generator.
	 * @param popuSize The size of the population.
	 * @param mutProb The mutation probability.
	 * @param crossProb The crossover probability.
	 * @param replaceFraction The replacement fraction.
	 * @param generations The number of generations.
	 */
	public GeneticAlgorithm(IIndividual member,Random generator, int popuSize,double mutProb, double crossProb, double replaceFraction, int generations){
		population = new Population(member,generator,popuSize);
		this.generator = generator;
		this.crossoverProbability=crossProb;
		this.replacementFraction=replaceFraction;
		this.mutationProbability= mutProb;
		this.generations=generations;
		this.tournamentSize = 0;
	}

	/**
	 * @return The population of the genetic algorithm.
	 */
	public Population getPopulation() { return population; }

	/**
	 * Change the current population of the genetic algorithm.
	 * @param population The population to be set.
	 */
	public void setPopulation(Population population) { this.population =
			population; }

	/**
	 * @return The random number generator used in the genetic algorithm.
	 */
	public Random getRandom() { return generator; }

	/**
	 * @return The probability value for which crossover will occur.
	 */
	public double getCrossProb() { return crossoverProbability; }

	/**
	 * @return The tournament size for the tournament selection.
	 */
	public double getTournamentSize() { return tournamentSize; }
	/**
	 * @return The probability value for which mutation will occur.
	 */
	public double getMutProb() { return mutationProbability; }

	/**
	 * @return The replacement value for which the worst members of the population are replaced by the best.
	 */
	public double getReplaceFraction() { return replacementFraction; }

	/**
	 * @return The number of generations for which the genetic algorithm will run.
	 */
	public int getGenerations() {return generations;}

	/**
	 * This method consists of sorting the population decreasingly by its fitness, then perform roulette wheel selection,
	 * crossover, mutation and replacing individuals of the population.
	 * @return The best individual found after running the algorithm for the selected generations.
	 */
	public IIndividual solveWithRoulette(){

		Collections.sort(population.getPopulation());

		Population parents,childs;

		for(int i=0;i<generations;i++) {

			parents = new Population();
			childs = new Population();

			parents.getPopulation().addAll(population.selectedPopulationRoulette(generator, population.getSize()));

			childs.offspring(parents,generator,crossoverProbability);

			childs.mutatePopulation(generator,mutationProbability);

			population.replaceWith(childs, replacementFraction);

			Collections.sort(population.getPopulation());

		}
		return population.getPopulation().get(0);

	}

	/**
	 * This method consists of sorting the population decreasingly by its fitness, then perform tournament selection,
	 * crossover, mutation and replacing individuals of the population.
	 * @return The best individual found after running the algorithm for the desired generations.
	 */
	public IIndividual solveWithTournament(){

		Collections.sort(population.getPopulation());

		Population parents,childs;
		for(int i=0;i<generations;i++) {

			parents = new Population();
			childs = new Population();

			parents.getPopulation().addAll(population.TournamentSelection(generator, tournamentSize));

			childs.offspring(parents,generator,crossoverProbability);

			childs.mutatePopulation(generator,mutationProbability);

			population.replaceWith(childs, replacementFraction);

			Collections.sort(population.getPopulation());
		}
		return population.getPopulation().get(0);
	}

}