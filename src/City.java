/**
 * This class represents the data structure which holds a City representation.
 */
public class City {
	private ILocation location;
	private String name;
	/**
	 * @param name The name of the city.
	 * @param location The location of the city.
	 */
	public City(String name,ILocation location) {
		this.name = name;
		this.location = location;
	}
	/**
	 * @return The name of the city.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return The location of the city.
	 */
	public ILocation getLocation() {
		return location;
	}
	/**
	 * @param b Another city.
	 * @return The distance between two cities.
	 */
	public double getDistance(City b) {
		return this.location.distance(b.location);
	}
	
	/**
	 * @return True If the city's names are equal and if their locations are equal
	 * else False.
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(getClass() != o.getClass()) return false;
		City a = (City) o;
		return name.equals(a.name) && getLocation().equals(a.getLocation());
	}
	
	/**
	 * @return A string representation of an object of type City.
	 */
	public String toString() {
		return name;
	}
}