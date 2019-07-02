/**
 * This class represents the data structure which holds a Two Dimensional Point representation.
 * This class implements the interface ILocation
 */
public class PointTwoDimensions implements ILocation{
	private double x,y;
	/**
	 * @param x The x coordinate of the Point
	 * @param y The y coordinate of the Point
	 */
	public PointTwoDimensions(double x , double y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * @return The x coordinate
	 */
	public double getX() {
		return x;
	}
	/**
	 * @return The y coordinate
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * @return True If the two Points are equal, i.e, if the x and y coordinates
	 * have the same value, else False.
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(getClass() != o.getClass()) return false;
		PointTwoDimensions p = (PointTwoDimensions) o;
		return (this.x == p.getX() && this.y == p.getY());
	}

	/**
	 * @return The euclidean distance between two points with two coordinates
	 */
	@Override
	public double distance(ILocation I) {
		PointTwoDimensions other = (PointTwoDimensions) I; 
		return Math.sqrt(Math.pow(other.getX() - this.getX(), 2) + Math.pow(other.getY() - this.getY(), 2));
	}
	
	/**
	 * @return A string representation of an object of type PointTwoDimensions.
	 */
	@Override
	public String toString() {
		return this.getX() + " " + this.getY();
	}
}