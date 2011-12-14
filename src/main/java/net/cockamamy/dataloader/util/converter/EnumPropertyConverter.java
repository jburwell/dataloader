package net.cockamamy.dataloader.util.converter;

import static java.lang.String.*;

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

		assert anEnumClass != null : format(
				"%1$s(Class) requires a non-null enumeration class.", this
						.getClass().getName());

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

				anEnumValue = Enum.valueOf(this.myEnumClass,
						aValue.toUpperCase());

			} catch (IllegalArgumentException e) {

				throw new EnumPropertyConversionException(aValue,
						this.myEnumClass, e);

			}

		}

		return anEnumValue;

	}

	public final Class<E> getEnumClass() {

		return this.myEnumClass;

	}

	@Override
	public int hashCode() {

		return (37 * 17) + this.myEnumClass.hashCode();

	}

	@Override
	public boolean equals(Object thatObject) {

		if (thatObject != null && this.getClass().equals(thatObject.getClass())) {

			EnumPropertyConverter<?> thatConverter = (EnumPropertyConverter<?>) thatObject;

			if (this.myEnumClass.equals(thatConverter.getEnumClass())) {

				return true;

			}

		}

		return false;

	}

	@Override
	public String toString() {

		return format("Enum Property Converter for %1$s",
				this.myEnumClass.getName());

	}

	public static final class EnumPropertyConversionException extends
			PropertyConversionException {

		private static final long serialVersionUID = -6262915570458386553L;

		public EnumPropertyConversionException(String aValue,
				Class<?> aTargetType, IllegalArgumentException e) {

			super(aValue, aTargetType, format(
					"Failed to find an enumerated value on %1$s for %2$s.",
					aTargetType.getClass().getName(), aValue), e);

		}

	}

}
