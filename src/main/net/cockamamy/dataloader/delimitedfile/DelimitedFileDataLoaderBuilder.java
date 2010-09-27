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

import java.io.*;
import java.util.*;

import net.cockamamy.dataloader.*;
import net.cockamamy.dataloader.util.converter.*;

/**
 * 
 * Builds an instance of {@link DelimitedFileDataLoader}. All setter methods on
 * this class return a reference to the builder permitting a fluent programming
 * style.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class DelimitedFileDataLoaderBuilder {

	public static final char DEFAULT_DELIMITER = ',';

	private File myInputFile;

	private List<ColumnDefinition> myColumnDefinitions;

	private char myDelimiter;

	/**
	 * 
	 * Configures a builder with an empty list of column definitions and a
	 * delimier of ",".
	 * 
	 */
	public DelimitedFileDataLoaderBuilder() {

		super();

		this.myColumnDefinitions = new ArrayList<ColumnDefinition>();
		this.myDelimiter = DEFAULT_DELIMITER;

	}

	/**
	 * 
	 * <p>
	 * Adds a {@link ColumnDefinition} to this builder.
	 * </p>
	 * 
	 * <p>
	 * <b>N.B.</b> Columns must be added to the builder in the order that they
	 * appear in the input file.
	 * </p>
	 * 
	 * @param <V>
	 *            The type of value converted to by the passed property
	 *            converter, <code>aConverter</code>.
	 * 
	 * @param aPropertyName
	 *            The name of the property that corresponds to the column in the
	 *            input file. This value must be non-blank.
	 * @param aConverter
	 *            The converter for the property value. This object must be
	 *            non-null.
	 * 
	 * @return A reference to this builder.
	 * 
	 * @since 1.0.0
	 * 
	 */
	public <V> DelimitedFileDataLoaderBuilder addColumnDefinition(
			String aPropertyName, PropertyConverter<V> aConverter) {

		assert isNotBlank(aPropertyName) : format(
				"%1$s#addColumnDefinition(String, PropertyConverter) requires a non-blank property name.",
				this.getClass().getName());
		assert aConverter != null : format(
				"%1$s#addColumnDefinition(String, PropertyConverter) requires a non-null converter.",
				this.getClass().getName());

		this.myColumnDefinitions.add(new ColumnDefinition(aPropertyName,
				aConverter));

		return this;

	}

	/**
	 * 
	 * @param anInputFile
	 *            A reference to the input file. This object must be non-null.
	 * 
	 * @return A reference to this builder.
	 * 
	 * @since 1.0.0
	 * 
	 */
	public DelimitedFileDataLoaderBuilder setInputFile(File anInputFile) {

		assert anInputFile != null : format(
				"%1$s#setInputFile(File) requires a non-null input file.", this
						.getClass().getName());

		this.myInputFile = anInputFile;

		return this;

	}

	/**
	 * 
	 * @param aDelimiter
	 *            The character that delimits the fields
	 * 
	 * @return A reference to this builder.
	 * 
	 * @since 1.0.0
	 * 
	 */
	public DelimitedFileDataLoaderBuilder setDelimiter(char aDelimiter) {

		this.myDelimiter = aDelimiter;

		return this;

	}

	/**
	 * 
	 * Builds a {@link DelimitedFileDataLoader} instance based on the
	 * configuration of this builder.
	 * 
	 * @return A configured {@link DelimitedFileDataLoader} instance ready for
	 *         use
	 * 
	 * @since 1.0.0
	 * 
	 */
	public DataLoader buildLoader() {

		this.validate();

		return new DelimitedFileDataLoader(this.myInputFile,
				this.myColumnDefinitions, this.myDelimiter);

	}

	private void validate() {

		if (this.myInputFile == null) {

			throw new IllegalStateException(
					"An input file must be specified in order to build a "
							+ DelimitedFileDataLoader.class.getName()
							+ " instance.");

		}

	}

}