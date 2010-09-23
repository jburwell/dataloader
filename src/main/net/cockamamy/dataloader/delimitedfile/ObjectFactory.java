package net.cockamamy.dataloader.delimitedfile;

import java.util.*;

/**
 * 
 * Abstract factory that creates an object instance of type <code>T</code> from
 * a {@link Map} of property values.
 * 
 * @param <T>
 *            The type of object created by this factory
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public interface ObjectFactory<T> {

	/**
	 * 
	 * Creates an object of type <code>T</code> from the passed {@link Map} of
	 * property values, <code>thePropertyValues</code>.
	 * 
	 * @param thePropertyValues
	 *            The property values from which to create an object of type
	 *            <code>T</code>
	 * 
	 * @return An object of type <code>T</code> whose property values are to set
	 *         to <code>thePropertyValues</code>.
	 * 
	 * @since 1.0.0
	 * 
	 */
	T createObject(Map<String, Object> thePropertyValues);

}
