import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class represents the data structure which holds a Population representation.
 */
public class Population {
	private List<IIndividual> population ;

	/**
	 * @param population The population of individuals.
	 */
	public Population() {
		population= new ArrayList<IIndividual>();
	}

	/**
	 * @param population A population of individuals.
	 */
	public Population(List<IIndividual> population) {
		this.population = population;
	}

	/**
	 * Initialize a population and update each individuals fitness value.
	 * @param member An individual that belongs to the population.
	 * @param populationSize The size of the population.
	 */
	public Population(IIndividual member,int populationSize) {
		population = new ArrayList<IIndividual>();
		for(int i =0;i<populationSize;i++) {
			population.add((IIndividual)member.clone());
		}
		updateFitness();
	}

	/**
	 * Initialize a population with the permutation of the first member and
	 * update each individuals fitness value.
	 * @param member An individual that belong to the population.
	 * @param generator An instance of a random number generator.
	 * @param populationSize The size of the population.
	 */
	public Population(IIndividual member, Random generator,int populationSize) {
		population = new ArrayList<IIndividual>();
		population.add(member);
		for(int i =0;i<populationSize-1;i++) {
			population.add((IIndividual)member.permutation(generator));
		}
		updateFitness();
	}

	/**
	 * @return The size of the population.
	 */
	public int getSize() {
		return population.size();
	}

	/**
	 * Update the fitness value of each individual of the population.
	 */
	public void updateFitness() {
		for(IIndividual e : population) {
			e.calculateFitness();
		}
	}

	/**
	 * @return The list of the individuals in the population.
	 */
	public List<IIndividual> getPopulation(){
		return this.population;
	}

	/**
	 * Change the list of individuals in the population.
	 * @param population The list of individuals to be set.
	 */
	public void setPopulation(List<IIndividual> population) {
		this.population = population;
	}

	/**
	 * @return A new instance of the type Population and initializes all its fields
	 * with exactly the same contents of the corresponding fields of the POpulation object which called the method.
	 */
	@Override
	public Object clone() {
		List<IIndividual> newMembers = new ArrayList<IIndividual>();
		for(IIndividual individual : population) {
			newMembers.add((IIndividual)individual.clone());
		}
		Population newPopulation = new Population(newMembers);
		return  (Population) newPopulation;
	}

	/**
	 * Returns a new instance of Population where its members list has been permuted.
	 * @return A new instance of type Population
	 */
	public Population permutation(Random generator){
		int i,randomNumber;
		IIndividual temp;
		ArrayList<IIndividual> newIndividuals = new ArrayList<IIndividual>();
		for(i = 0; i < getSize(); i++) {
			newIndividuals.add((IIndividual)population.get(i).clone());
		}
		for(i = 0 ; i < newIndividuals.size()-1; i++) {
			randomNumber =  i+ (int)Math.round(generator.nextDouble() * (newIndividuals.size()-1-i) ) ;
			temp = newIndividuals.get(i);
			newIndividuals.set(i, newIndividuals.get(randomNumber));
			newIndividuals.set(randomNumber, temp);
		}
		return new Population(newIndividuals);
	}

	/**
	 * Performs tournament selection on the population, and selects an individual from it,
	 * according to a generated random number and the fitness values of the population.
	 * @param ran An instance of a Random number generator.
	 * @param tournamentSize The number of tournaments.
	 * @return The selected individual.
	 */
	public List<IIndividual> TournamentSelection(Random ran,int tournamentSize) {
		int i,j,k,ts = population.size()/tournamentSize;
		double winnerFitness;
		IIndividual winner = null,opponent;
		Population clones;
		List<IIndividual> winners = new ArrayList<IIndividual>();
		for(i=0;i<tournamentSize;i++) {
			clones = permutation(ran);
			for(j = 0; j < ts; j++) {
				winner = clones.population.get(j*tournamentSize);
				winnerFitness = winner.getFitness();
				for(k = 1; k < tournamentSize; k++) {
					opponent = clones.population.get(j*tournamentSize+k);
					if(opponent.getFitness() > winnerFitness) {
						winner = opponent;
						winnerFitness = opponent.getFitness();
					}
				}
				winners.add(winner);
			}
		}
		return winners;
	}

	/**
	 * Performs roulette wheel selection on the population, and selects an individual from it,
	 * according to a generated random number and the fitness values of the population.
	 * @param generator An instance of a Random number generator.
	 * @return The selected individual.
	 */
	public IIndividual rouletteWheelSelection(Random generator, double total) {
		double current=0;
		double d=generator.nextDouble();
		for(int i=0;i<population.size();i++) {
			if(d>=current && d<current+(population.get(i).getFitness()/total)) {
				return (IIndividual)population.get(i).clone();
			}
			current+=(population.get(i).getFitness()/total);
		}
		return null;
	}


	/**
	 * Performs roulette wheel selection until populationSize individuals are selected.
	 * @param generator An instance of a Random number generator, which is used to perform roulette wheel selection.
	 * @param populationSize The size of the population.
	 * @return The list of individuals that belong to the population.
	 */
	public List<IIndividual> selectedPopulationRoulette(Random generator, int populationSize){
		List<IIndividual> result = new ArrayList<IIndividual>();
		double total=getTotalFitness();
		for(int i=0;i<populationSize;i++) {
			result.add(rouletteWheelSelection(generator,total));
		}
		return result;
	}

	/**
	 * @return The sum of each individuals fitness in the population. 
	 */
	public double getTotalFitness() {
		double sum=0;
		for(IIndividual e : population) {
			sum+=e.getFitness();
		}
		return sum;
	}

	/**
	 * Replaces the worst individuals from the population, with the best individuals of the
	 * obtained population, according to a double value.
	 * @param newGeneration The population obtained after selection, crossover and mutation.
	 * @param replaceFraction A double which indicates the fraction of worst elements to be
	 * replaced with the best elements.
	 */
	public void replaceWith(Population newGeneration, double replaceFraction) {
		Collections.sort(population);
		Collections.sort(newGeneration.population);
		int numberOfReplacements = (int) (population.size()*replaceFraction);
		for(int i=0;i<numberOfReplacements;i++) {
			population.set(population.size()-1-i, newGeneration.population.get(i));
		}
	}

	/**
	 * Add all the individuals obtained from the crossover, until the current population is full. 
	 * @param parents The population which will be used to perform crossover.
	 * @param ran An instance of a Random number generator, which is used to apply crossover.
	 * @param crossOverProbability The probability for which crossover occurs.
	 */
	public void offspring(Population parents,Random ran, double crossOverProbability){
		int i = 0;
		while(i < parents.getSize() && getSize() < parents.getSize()) {
			population.addAll(parents.population.get(i).crossOver(ran,crossOverProbability,parents.population.get(i+1)));
			i += 2;
		}
	}

	/**
	 * Mutate each individual of the given population, with a given probability.
	 * In this case, two bit swap mutation is used.
	 * After applying the mutation operator in the population, the population fitness is updated.
	 * @param ran An instance of a Random Number generator, which is used to apply mutation.
	 * @param mutationProbability The probability for which mutation will occur.
	 */
	public void mutatePopulation(Random ran, double mutationProbability) {
		int i;
		for(i = 0; i < getSize(); i++) {
			population.set(i, population.get(i).twoBitSwapMutation(ran, mutationProbability));
		}
		updateFitness();
	}

	/**
	 * @return A string representation of an object of type Population.
	 */
	@Override
	public String toString() {
		String a ="";
		for(int i=0;i<population.size();i++) {
			if(i<population.size()-1) {
				a+= population.get(i).toString() + "\n";
			}
			else a+=population.get(i).toString();
		}
		return a;
	}
}