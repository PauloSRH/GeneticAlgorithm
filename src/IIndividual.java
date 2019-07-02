import java.util.List;
import java.util.Random;

/**
 * The IIndividual interface provides methods getFitness, calculateFitness, clone, permutation,
 * twoBitSwapMutation, twoBitSwapMutationFull and orderCrossOver.
 * The getFitness method should return the fitness value of the implementing type.
 * The calculateFitness method should calculate and set the fitness value of the implementing type.
 * The clone method returns a new instance of the implementing type and initializes all its fields
 * with exactly the same contents of the corresponding fields.
 * The permutation method returns a new instance of the implementing type where its representation has been permuted.
 * The twoBitSwapMutation returns a new instance of the implementing type where two bits of its representation were swapped, according to a given probability.
 * The twoBitSwapMutationFull returns a new instance of the implementing type where each two consecutive bits are swapped, according to a given probability.
 * The crossOver method returns a List of new instances of the implementing type, which represent the offspring, according to a given probability.
 * The ILocation interface also provides a method for the implementer to specify its String representation.
 */
public interface IIndividual extends Comparable<IIndividual>,Cloneable {
	/**
	 * @return The fitness value of the implementing type.
	 */
	double getFitness();
	
	/**
	 * Calculate the fitness value of the implementing type.
	 */
	void calculateFitness();
	
	/**
	 * @return A clone of the implementing type.
	 */
	Object clone();
	
	/**
	 * @param a An object of type Random.
	 * @return A permutation of the implementing type.
	 */
	IIndividual permutation(Random a);

	/**
	 * @param ran An object of type Random.
	 * @param mutationProbability The probability for which mutation will occur.
	 * @return A new instance of the implementing type, which may have be mutated, by swapping
	 * two elements of the implementers representation.
	 */
	IIndividual twoBitSwapMutation(Random ran,double mutationProbability);

	/**
	 * @param ran An object of type Random.
	 * @param mutationProbability The probability for which mutation will occur.
	 * @return A new instance of the implementing type, which may have been mutated, by swapping
	 * consecutive two elements of the implementers representation.
	 */
	IIndividual twoBitSwapMutationFull(Random ran, double mutationProbability);
	
	/**
	 * 
	 * @param ran An object of type Random.
	 * @param crossOverProbability The probability for which crossOver will occur.
	 * @param father2 The second Individual which will be used as father
	 * @return A list of the resulting offspring between two implementing types, given a probability.
	 */
	List<IIndividual> crossOver(Random ran,double crossOverProbability,IIndividual father2);
}