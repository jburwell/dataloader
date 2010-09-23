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
package net.cockamamy.dataloader.util.converter;

import static java.lang.String.*;

import static net.cockamamy.dataloader.util.ObjectUtilities.*;

/**
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class PropertyConversionException extends RuntimeException {

	private static final long serialVersionUID = 2152810331796105358L;

	private final String myMessage;
	private final Class<?> myTargetType;
	private final String myValue;

	public PropertyConversionException(String aValue, Class<?> aTargetType,
			Throwable aCause) {

		super();

		assert aTargetType != null : format(
				"%1$s(String, Class) requires a non-null target type",
				PropertyConversionException.class.getName());

		this.myMessage = format("Unable to convert %1$s to %2$s.", aValue,
				aTargetType.getName());
		this.myTargetType = aTargetType;
		this.myValue = aValue;

	}

	@Override
	public String getMessage() {

		return this.myMessage;

	}

	public String getValue() {

		return this.myValue;

	}

	public Class<?> getTargetType() {

		return this.myTargetType;

	}

	@Override
	public boolean equals(Object thatObject) {

		if (thatObject != null
				&& PropertyConversionException.class.equals(thatObject
						.getClass()) == true) {

			PropertyConversionException thatException = (PropertyConversionException) thatObject;

			if (isEqualTo(this.myTargetType, thatException.getTargetType()) == true
					&& isEqualTo(this.myValue, thatException.getValue()) == true
					&& isEqualTo(this.myMessage, thatException.getMessage()) == true) {

				return true;

			}

		}

		return false;

	}

	@Override
	public int hashCode() {

		int aValue = 37;

		aValue += (aValue * 17) + this.myTargetType.hashCode();
		aValue += (aValue * 17) + this.myMessage.hashCode();
		aValue += (aValue * 17) + this.myValue != null ? this.myValue
				.hashCode() : 0;

		return aValue;

	}

}
