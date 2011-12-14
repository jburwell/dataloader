/*
 * Copyright (c) 2008-2010, John Burwell
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are 
 * permitted provided that the following conditions are met:
 * 
 *    * Redistributions of source code must retain the above copyright notice, this list of 
 *      conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above copyright notice, this list of 
 *      conditions and the following disclaimer in the documentation and/or other materials 
 *      provided with the distribution.
 *    * Neither the name of the John Burwell nor the names of its contributors may be used to 
 *      endorse or promote products derived from this software without specific prior written 
 *      permission.
 *    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR 
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY 
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY 
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package net.cockamamy.dataloader.delimitedfile;

import static java.lang.String.*;
import static java.util.Collections.*;

import java.util.*;

/**
 * 
 * Builds a set of objects from a {@link DelimitedString} assuming each a single
 * {@link DelimitedString} instance maps to a object <code>T</code> instance.
 * 
 * @param <T>
 *            The type of object built by this builder
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
final class RecordParser {

	private final List<ColumnDefinition> myColumnDefinitions;

	/**
	 * 
	 * @param theColumnDefinitions
	 *            The definition of each column a delimited string provided in
	 *            the order they in the file.
	 * @param aFactory
	 *            A factory capable of building objects of type <code>T</code>
	 *            from a {@link Map} of property values.
	 * 
	 * @since 1.0.0
	 * 
	 */
	public RecordParser(List<ColumnDefinition> theColumnDefinitions) {

		super();

		this.myColumnDefinitions = theColumnDefinitions;

	}

	/**
	 * 
	 * Parses the fields in the passed delimited string,
	 * <code>aDelimitedString</code>, into a record represented as a {@link Map}
	 * .
	 * 
	 * @param aDelimitedString
	 *            The delimited string to parse into a record.
	 * 
	 * @return A record representing the data of the passed delimited string,
	 *         <code>aDelimitedString</code>.
	 * 
	 * @since 1.0.0
	 * 
	 */
	public Map<String, Object> parse(final DelimitedString aDelimitedString) {

		final Map<String, Object> thePropertyValues = new HashMap<String, Object>();
		int aColumnNumber = 0;

		try {

			for (String aFieldValue : aDelimitedString) {

				ColumnDefinition aColumnDefinition = this.myColumnDefinitions
						.get(aColumnNumber);

				thePropertyValues.put(
						aColumnDefinition.getPropertyName(),
						aColumnDefinition.getConverter().convertValue(
								aFieldValue.trim()));

				aColumnNumber++;

			}

		} catch (IndexOutOfBoundsException e) {

			// TODO Consider defining a custom, unchecked exception ...
			throw new IllegalStateException(format(
					"Undefined column number %1$s. Columns defined: %2$s.",
					aColumnNumber, this.myColumnDefinitions));

		}

		// Build the object from the Map and add it to the results ...
		return unmodifiableMap(thePropertyValues);

	}

}
