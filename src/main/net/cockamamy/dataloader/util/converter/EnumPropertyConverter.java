package net.cockamamy.dataloader.util.converter;

import static net.cockamamy.dataloader.util.StringUtilities.*;

/**
 * 
 * Converts a string value to a enumeration value of type <code>E</code>.
 * 
 * @param <E>
 *            The type of enumerated value created by this converter
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class EnumPropertyConverter<E extends Enum<E>> implements
		PropertyConverter<E> {

	private final Class<E> myEnumClass;

	/**
	 * 
	 * @param anEnumClass
	 *            The type of enumeration supported by this converter
	 * 
	 * @since 1.0.0
	 * 
	 */
	public EnumPropertyConverter(Class<E> anEnumClass) {

		super();

		this.myEnumClass = anEnumClass;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.util.csv.PropertyConverter#convertValue(java.lang
	 * .String)
	 */
	public E convertValue(String aValue) {

		E anEnumValue = null;

		if (isNotBlank(aValue) == true) {

			try {

				anEnumValue = Enum.valueOf(this.myEnumClass, aValue
						.toUpperCase());

			} catch (IllegalArgumentException e) {

				throw new PropertyConversionException(aValue, this.myEnumClass,
						e);

			}

		}

		return anEnumValue;

	}

}
