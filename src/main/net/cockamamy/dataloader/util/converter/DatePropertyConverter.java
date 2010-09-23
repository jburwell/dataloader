package net.cockamamy.dataloader.util.converter;

import static java.lang.String.*;

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

	private final DateFormat myDateFormatter;

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
	public DatePropertyConverter(DateFormat aDateFormatter) {

		super();

		assert aDateFormatter != null : format(
				"%1$s(DateFormat) requires a non-null DateFormat", this
						.getClass().getName());

		this.myDateFormatter = aDateFormatter;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.util.csv.PropertyConverter#convertValue(java.lang
	 * .String)
	 */
	public Date convertValue(String aValue) {

		Date aConvertedValue = null;

		try {

			if (isNotBlank(aValue) == true) {

				aConvertedValue = this.myDateFormatter.parse(aValue);

			}

		} catch (ParseException e) {

			throw new PropertyConversionException(aValue, Date.class, e);

		}

		return aConvertedValue;

	}

}
