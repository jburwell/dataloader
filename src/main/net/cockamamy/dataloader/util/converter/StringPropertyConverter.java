package net.cockamamy.dataloader.util.converter;

/**
 * 
 * Converts a string value to a string -- a no-op property converter.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public class StringPropertyConverter implements PropertyConverter<String> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.util.csv.PropertyConverter#convertValue(java.lang
	 * .String)
	 */
	public String convertValue(String aValue) {

		return aValue;

	}

	// Default equals and hashCode implementations are sufficent since this
	// class carries no state ...

	@Override
	public String toString() {

		return "String Property Converter";

	}

}
