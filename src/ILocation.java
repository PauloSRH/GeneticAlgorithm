/**
 * The ILocation interface provides methods distance and toString.
 * The distance method should output the double value of the distance between
 * two types that implement ILocation.
 * The ILocation interface also provides a method for the implementer to specify its String representation.
 */
public interface ILocation {
	
	/**
	 * @param I A given Location.
	 * @return The distance between two given locations.
	 */
	double distance(ILocation I);
	
	/**
	 * @return A string representation of an object of a Class that implements ILocation.
	 */
	String toString();
}