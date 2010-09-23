package net.cockamamy.dataloader.util;

import static java.lang.String.*;
import static java.util.Collections.*;

import java.util.*;

/**
 * 
 * Utilities for creating and manipulating Collections.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class CollectionUtilities {

	private CollectionUtilities() {

		super();

	}

	/**
	 * 
	 * Builds an unmodifiable {@link List} from the passed elements,
	 * <code>theElements</code>.
	 * 
	 * @param <T>
	 *            The element type
	 * 
	 * @param theElements
	 *            The elements from which to create the unmodifiable list
	 * 
	 * @return An unmodifiable {@link List} containing the passed elements,
	 *         <code>theElements</code>.
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static <T> List<T> buildUnmodifiableList(T... theElements) {

		if (theElements == null) {

			return emptyList();

		}

		List<T> aList = new ArrayList<T>(theElements.length);

		for (T anElement : theElements) {

			aList.add(anElement);

		}

		return unmodifiableList(aList);

	}

	public static <T> Set<T> buildUnmodifiableSet(T... theElements) {

		if (theElements == null) {

			return emptySet();

		}

		Set<T> aSet = new HashSet<T>(theElements.length);

		for (T anElement : theElements) {

			boolean aResult = aSet.add(anElement);

			if (aResult != true) {

				throw new IllegalStateException(format(
						"Attempt to add a duplicate element, %1$s, to set.",
						anElement));

			}
			
		}

		return unmodifiableSet(aSet);
		
	}

}
