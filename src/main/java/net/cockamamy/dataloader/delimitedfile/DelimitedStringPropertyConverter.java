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
public final class DelimitedStringPropertyConverter<T> implements
		PropertyConverter<T> {

	private final char myDelimiter;
	private final RecordParser myBuilder;
	private final ObjectFactory<T> myFactory;

	/**
	 * 
	 * @param theColumnDefinitions
	 *            The definition of each column a delimited string provided in
	 *            the order they in the file.
	 * @param aDelimiter
	 *            The character that delimits the fields
	 * @param aFactory
	 *            The factory that will create object instances
	 * 
	 * @since 1.0.0
	 * 
	 */
	public DelimitedStringPropertyConverter(
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
		this.myBuilder = new RecordParser(theColumnDefinitions);
		this.myFactory = aFactory;

	}

	public char getDelimiter() {
		
		return this.myDelimiter;
		
	}

	public RecordParser getBuilder() {
		
		return this.myBuilder;
		
	}

	public ObjectFactory<T> getFactory() {
		
		return this.myFactory;
		
	}

	@SuppressWarnings("unchecked")
	public DelimitedStringPropertyConverter(
			List<ColumnDefinition> theColumnDefinitions, char aDelimiter) {

		this(theColumnDefinitions, aDelimiter,
				(ObjectFactory<T>) new NullObjectFactory());
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.dataloader.util.converter.PropertyConverter#convertValue
	 * (java.lang.String)
	 */
	public T convertValue(String aValue) {

		T anObject = null;

		if (isNotBlank(aValue)) {

			DelimitedString aDelimitedString = new DelimitedString(aValue,
					this.myDelimiter);

			anObject = this.myFactory.createObject(this.myBuilder
					.parse(aDelimitedString));

		}

		return anObject;

	}

	/**
	 * 
	 * Abstract factory that creates an object instance of type <code>T</code>
	 * from a {@link Map} of property values.
	 * 
	 * @param <T>
	 *            The type of object created by this factory
	 * 
	 * @author jburwell
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static interface ObjectFactory<T> {

		/**
		 * 
		 * Creates an object of type <code>T</code> from the passed {@link Map}
		 * of property values, <code>thePropertyValues</code>.
		 * 
		 * @param thePropertyValues
		 *            The property values from which to create an object of type
		 *            <code>T</code>
		 * 
		 * @return An object of type <code>T</code> whose property values are to
		 *         set to <code>thePropertyValues</code>.
		 * 
		 * @since 1.0.0
		 * 
		 */
		T createObject(Map<String, Object> thePropertyValues);

	}

	/**
	 * 
	 * A null implementation of the {@link ObjectFactory} interface that returns
	 * the input map as the created object.
	 * 
	 * @author jburwell
	 * 
	 * @since 1.0.2
	 * 
	 */
	public static class NullObjectFactory implements
			ObjectFactory<Map<String, Object>> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * net.cockamamy.dataloader.delimitedfile.DelimitedStringPropertyConverter
		 * .ObjectFactory#createObject(java.util.Map)
		 */
		public Map<String, Object> createObject(
				Map<String, Object> thePropertyValues) {

			return thePropertyValues;

		}

	}

}
