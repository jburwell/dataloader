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

import net.cockamamy.dataloader.util.converter.*;

/**
 * 
 * A delimited file column definition mapping a domain object property name to a
 * {@link PropertyConverter}.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class ColumnDefinition {

	private final String myPropertyName;

	private final PropertyConverter<?> myConverter;

	/**
	 * 
	 * @param aPropertyName
	 *            The property name of the domain object to which this column is
	 *            mapped.
	 * @param aConverter
	 *            Converts the raw column value to property type on the domain
	 *            object
	 * 
	 * @since 1.0.0
	 * 
	 */
	public ColumnDefinition(String aPropertyName,
			PropertyConverter<?> aConverter) {

		super();

		this.myPropertyName = aPropertyName;
		this.myConverter = aConverter;

	}

	/**
	 * 
	 * @return The domain object property of this mapping
	 * 
	 * @since 1.0.0
	 * 
	 */
	public String getPropertyName() {

		return this.myPropertyName;

	}

	/**
	 * 
	 * @return The property converter that converts a string value to the
	 *         property type of the domain object property
	 * 
	 * @since 1.0.0
	 * 
	 */
	public PropertyConverter<?> getConverter() {

		return this.myConverter;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object thatObject) {

		boolean aReturnValue = false;

		if (thatObject != null && this.getClass().equals(thatObject.getClass())) {

			ColumnDefinition thatColumnDefinition = (ColumnDefinition) thatObject;

			if (this.myPropertyName.equals(thatColumnDefinition
					.getPropertyName())
					&& this.myConverter.equals(thatColumnDefinition
							.getConverter())) {

				aReturnValue = true;

			}

		}

		return aReturnValue;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		int aHashCode = 37;

		aHashCode = (17 * aHashCode) + this.myPropertyName.hashCode();
		aHashCode = (17 * aHashCode) + this.myConverter.hashCode();

		return aHashCode;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return format(
				"CSV Column Definition [propertyName: %1$s, converter: %2$s]",
				this.myPropertyName, this.myConverter);

	}

}
