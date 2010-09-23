package net.cockamamy.dataloader.delimitedfile;

import static java.lang.String.*;

import static net.cockamamy.dataloader.util.StringUtilities.*;

import java.util.*;

import net.cockamamy.dataloader.util.converter.*;

/**
 * 
 * Converts a string representing a {@link DelimitedString} into an object of
 * type <code>T</code>.
 * 
 * @author jburwell
 * 
 * @param <T>
 *            The type of object created by this converter
 * 
 * @since 1.0.0
 * 
 */
public final class DelimitedPropertyConverter<T> implements
		PropertyConverter<T> {

	private final char myDelimiter;
	private final ObjectBuilder<T> myBuilder;

	public DelimitedPropertyConverter(
			List<ColumnDefinition> theColumnDefinitions, char aDelimiter,
			ObjectFactory<T> aFactory) {

		super();

		assert theColumnDefinitions != null : format(
				"%1$s(List, char, ObjectFactory) requires a non-null list of column definitions.",
				this.getClass().getName());
		assert theColumnDefinitions.isEmpty() == false : format(
				"%1$s(List, char, ObjectFactory) requires a non-empty list of column definitions.",
				this.getClass().getName());

		this.myDelimiter = aDelimiter;
		this.myBuilder = new ObjectBuilder<T>(theColumnDefinitions, aFactory);

	}

	public T convertValue(String aValue) {

		T anObject = null;

		if (isNotBlank(aValue)) {

			DelimitedString aDelimitedString = new DelimitedString(aValue,
					this.myDelimiter);

			anObject = this.myBuilder.buildObject(aDelimitedString);

		}

		return anObject;

	}

}
