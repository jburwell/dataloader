package net.cockamamy.dataloader.util.converter;

/**
 * 
 * Converts a string value to an object of type <code>T</code>.
 * 
 * @param <T>
 *            The type of object created by this converter
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public interface PropertyConverter<T> {

	/**
	 * 
	 * Converts to the passed string value, <code>aValue</code>, to an object of
	 * type <code>T</code>.
	 * 
	 * @param aValue
	 *            The value to be converted to an object of type <code>T</code>.
	 * 
	 * @return An object of type <code>T</code> converted from the passed string
	 *         value, <code>aValue</code>.
	 * 
	 * @since 1.0.0
	 * 
	 */
	public T convertValue(String aValue);

}
