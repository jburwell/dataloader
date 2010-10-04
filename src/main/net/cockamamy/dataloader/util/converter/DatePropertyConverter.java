package net.cockamamy.dataloader.util.converter;

import static java.lang.String.*;
import static java.util.Arrays.*;
import static java.util.Collections.*;

import static net.cockamamy.dataloader.util.StringUtilities.*;

import java.text.*;
import java.util.*;

/**
 * 
 * Converts a string in the form of MM/dd/yyyy to a {@link Date} object
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class DatePropertyConverter implements PropertyConverter<Date> {

	public static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(
			"MM/dd/yyyy");

	private final List<DateFormat> myFormatters;

	/**
	 * 
	 * Configures this property converter to parse dates using
	 * {@link DatePropertyConverter#DEFAULT_DATE_FORMAT}.
	 * 
	 * @since 1.0.0
	 * 
	 */
	public DatePropertyConverter() {

		this(DEFAULT_DATE_FORMAT);

	}

	/**
	 * 
	 * Configures this property converter to parse dates using the passed date
	 * format, <code>aDateFormat</code>.
	 * 
	 * @param aDateFormatter
	 *            A date format string conforming to rules defined by
	 *            {@link DateFormat}
	 * 
	 * @since 1.0.0
	 * 
	 */
	public DatePropertyConverter(final DateFormat... aDateFormatter) {

		super();

		assert aDateFormatter != null : format(
				"%1$s(DateFormat) requires a non-null DateFormat", this
						.getClass().getName());

		this.myFormatters = unmodifiableList(asList(aDateFormatter));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.util.csv.PropertyConverter#convertValue(java.lang
	 * .String)
	 */
	public Date convertValue(String aValue) {

		if (isBlank(aValue)) {

			return null;

		}

		Date aConvertedValue = null;

		for (DateFormat aFormatters : this.myFormatters) {

			try {

				aConvertedValue = aFormatters.parse(aValue);
				break;

			} catch (ParseException e) {

				// Ignore exception and try with the next formatter. Unparseable
				// values are handled later ...

			}

		}

		if (aConvertedValue == null) {

			throw new DatePropertyConversionException(aValue);

		}

		return aConvertedValue;

	}

	public List<DateFormat> getFormatters() {

		return this.myFormatters;

	}

	// BEGIN: Object implementation
	@Override
	public int hashCode() {

		return (37 * 17) + this.myFormatters.hashCode();

	}

	@Override
	public boolean equals(Object thatObject) {

		if (thatObject != null && this.getClass().equals(thatObject.getClass())) {

			DatePropertyConverter thatConverter = (DatePropertyConverter) thatObject;

			if (this.myFormatters.equals(thatConverter.getFormatters())) {

				return true;

			}

		}

		return false;
	}

	@Override
	public String toString() {

		return format("Date Property Converter (formatter: %1$s)",
				this.myFormatters);

	}

	// END: Object implementation

	public final class DatePropertyConversionException extends
			PropertyConversionException {

		private static final long serialVersionUID = -3873243438588889164L;

		public DatePropertyConversionException(String aValue) {

			super(aValue, Date.class, format(
					"Failed to parse %1$s into a Date using formatters %2$s.",
					aValue, myFormatters));

		}

		public List<DateFormat> getFormatters() {

			return myFormatters;

		}

		@Override
		protected boolean determineEquality(
				PropertyConversionException thatObject) {

			assert thatObject != null;

			if (this.getClass().equals(thatObject.getClass())) {

				DatePropertyConversionException thatException = (DatePropertyConversionException) thatObject;

				if (myFormatters.equals(thatException.getFormatters())) {

					return true;

				}

			}

			return false;

		}

		@Override
		protected int calculateHashCode(final int aHashCode) {

			return (aHashCode * 17) + myFormatters.hashCode();

		}

	}

}
