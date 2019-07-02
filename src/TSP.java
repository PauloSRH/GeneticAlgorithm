import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * This class represents the data structure which holds a TSP representation.
 * This class implements the interface IIndividual
 */
public class TSP implements IIndividual {
	private List<City> Representation;
	private double fitness;
	
	/**
	 * @param Representation The list of cities.
	 * @param fitness The fitness value.
	 */
	public TSP() {
		Representation= new ArrayList<City>();
		fitness=0;
	}
	
	/**
	 * @param a A list of cities.
	 * @param fitness The fitness value.
	 */
	public TSP(List<City> a, double fitness) {
		this.Representation=a;
		this.fitness=fitness;
	}
	
	/**
	 * @param a A list of cities.
	 */
	public TSP(List<City> a) {
		this.Representation=a;
		calculateFitness();
	}
	
	/**
	 * Calculates the fitness value and sets it.
	 */
	public void calculateFitness(){
		double fitness=0;
		for(int i=0;i<Representation.size()-1;i++) {
			fitness += Representation.get(i).getDistance(Representation.get(i+1));
		}
		fitness += Representation.get(Representation.size()-1).getDistance(Representation.get(0));
		if(fitness == 0)
			throw new IllegalArgumentException("Fitness can not be 0!");
		this.fitness=1/fitness;}
	
	/**
	 * @return The list of cities that represent the path of the TSP.
	 */
	public List<City> getRepresentation() {
		return Representation;
	}
	
	/**
	 * @return The fitness value of the TSP.
	 */
	public double getFitness() {
		return fitness;
	}
	
	/**
	 * Sets the fitness value of the TSP accordingly.
	 * @param fitness The fitness value to be set.
	 */
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	/**
	 * Changes the list of cities of the TSP accordingly.
	 * @param cities The list of the cities to be set. 
	 */
	public void setRepresentation(List<City> cities) {
		this.Representation=cities;
	}

	/**
	 * @return An integer which defines the comparison between objects of type TSP.
	 */
	@Override
	public int compareTo(IIndividual o) {
		TSP a = (TSP) o;
		return (int) Math.signum(a.fitness-fitness);
	}
	
	/**
	 * Returns a new instance of TSP where its representation has been permuted.
	 * @return A new instance of type TSP
	 */
	@Override
	public TSP permutation(Random generator){
		int i,randomNumber;
		City temp;
		ArrayList<City> newIndividuals = new ArrayList<City>();
		for(i = 0; i < Representation.size(); i++) {
			newIndividuals.add(Representation.get(i));
		}
		for(i = 0 ; i < newIndividuals.size()-1; i++) {
			randomNumber =  i+ (int)Math.round(generator.nextDouble() * (newIndividuals.size()-1-i) ) ;
			temp = newIndividuals.get(i);
			newIndividuals.set(i, newIndividuals.get(randomNumber));
			newIndividuals.set(randomNumber, temp);
		}
		return new TSP(newIndividuals,fitness);
	}
	
	/**
	 * @return A new instance of the type TSP and initializes all its fields
	 * with exactly the same contents of the corresponding fields of the TSP object which called the method.
	 */
	@Override
	public Object clone(){
		TSP newTSP = new TSP();
		List<City> cities = new ArrayList<City>();
		for(City city : Representation) {
			cities.add(city);
		}
		newTSP.setRepresentation(cities);
		newTSP.setFitness(fitness);
		return newTSP;
	}
	
	/**
	 * @return A new instance of type TSP. The representation of TSP may be mutated, by swapping
	 * consecutive two cities of the TSP's representation, according to a given probability.
	 */
	@Override
	public TSP twoBitSwapMutationFull(Random ran,double mutationProbability) {
		int i;
		City temp;
		double d;
		for(i = 0; i < Representation.size()-1; i++) {
			d = ran.nextDouble();
			if(d < mutationProbability) {
				temp = Representation.get(i);
				Representation.set(i, Representation.get(i+1));
				Representation.set(i+1, temp);
			}
		}
		return new TSP(Representation);
	}
	
	/**
	 * @return A new instance of the implementing type, which may be mutated, by swapping
	 * two cities of the TSP's representation.
	 */
	@Override
	public TSP twoBitSwapMutation(Random ran,double mutationProbability) {
		if(ran.nextDouble() < mutationProbability) {
			int i = (int) Math.round(ran.nextDouble()*(getRepresentation().size()-1));
			int j = (int) Math.round(ran.nextDouble()*(getRepresentation().size()-1));
			City gene = Representation.get(i);
			Representation.set(i, Representation.get(j));
			Representation.set(j, gene);
		}
		return new TSP(Representation);
	}
	
	/**
	 * The elements between start and end are copied to the childs representation.
	 * Afterwards the sequence is filled up with the elements 
	 * @param start The starting point of the sequence 
	 * @param end The end point of the sequence.
	 * @param ran The Random number generator.
	 * @param child The resulting child.
	 * @param father2 The second father envolved in the crossover.
	 * @return An instance of type TSP, resulting from the order crossover.
	 */
	public TSP orderOneCrossOver(int start, int end,Random ran,TSP child, TSP father2) {
		int i;
		HashSet<City> sequence = new HashSet<City>();
		for(i = 0; i < Representation.size(); i++) {
			if(i>=start && i<=end) {
				sequence.add(father2.Representation.get(i));
			}
			child.Representation.add(father2.Representation.get(i));
		}
		int sequenceSize = sequence.size();
		i =  (end+1) % Representation.size();
		int j = i;
		while(sequenceSize < Representation.size()) {
			if(!sequence.contains(Representation.get(i))){
				child.Representation.set(j, Representation.get(i));
				j = (j+1) % Representation.size();
				sequenceSize++;
			}
			i = (i +1) % Representation.size();
		}
		return child;
	}
	
	/**
	 * If the two fathers are not selected for crossover, then the resulting offspring contains a copy of the fathers.
	 * Else, orderCrossOver will occur.
	 * @return A list of the resulting offspring between two instances of TSP, given a probability.
	 */
	@Override
	public List<IIndividual> crossOver(Random ran,double crossOverProbability,IIndividual father2) {
		List<IIndividual> childs = new ArrayList<IIndividual>();
		TSP secondFather = (TSP) father2;
		if(ran.nextDouble() < crossOverProbability) {
			TSP child1 = new TSP();
			TSP child2 = new TSP();
			int firstPoint = (int) Math.round( ran.nextDouble()*(Representation.size()-1) );
			int secondPoint = (int) Math.round( ran.nextDouble()*(Representation.size()-1) );
			int start = Math.min(firstPoint, secondPoint);
			int end = Math.max(firstPoint, secondPoint);
			childs.add(orderOneCrossOver(start,end,ran,child1,secondFather));
			childs.add(secondFather.orderOneCrossOver(start,end,ran,child2,this));
		}
		else {
			childs.add(new TSP(Representation));
			childs.add(new TSP(secondFather.Representation));
		}
		return childs;
	}
	
	/**
	 * @return True If the two TSP's representations are equal, i.e, if the list of cities are the same, and if
	 * TSP's fitness values are equal, else False.
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(getClass() != o.getClass()) return false;
		TSP a = (TSP) o;
		return (Representation.equals(a.Representation) && fitness == a.fitness);
	}
	
	/**
	 * @return A string representation of an object of type TSP.
	 */
	public String toString() {
		String a="";
		for(int i=0;i<Representation.size();i++) {
			a+= Representation.get(i) +"->";
		}
		a+= Representation.get(0) + "\n"+ "Path length: " + 1/fitness;
		return a;
	}
		
}