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
package net.cockamamy.dataloader.util;


/**
 * 
 * Utilities for managing {@link String}s.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class StringUtilities {

	public static final String BLANK_STRING = "";

	private StringUtilities() {

		super();

	}

	/**
	 * 
	 * Determines whether or not the passed string, <code>aString</code>, is
	 * <strong>not</strong> blank.
	 * 
	 * @param aString
	 *            The string to determine blankness
	 * 
	 * @return <strong>true</strong>: The string is <strong>not</strong> blank.
	 *         <strong>false</strong>: The string is blank.
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static boolean isNotBlank(String aString) {

		if (aString != null && aString.trim().length() > 0) {

			return true;

		}

		return false;

	}

	/**
	 * 
	 * Determines whether or not the passed string, <code>aString</code>, is
	 * blank.
	 * 
	 * @param aString
	 *            The string to determine blankness
	 * 
	 * @return <strong>true</strong>: The string is blank.
	 *         <strong>false</strong>: The string is <strong>not</strong> blank.
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static boolean isBlank(String aString) {

		return !isNotBlank(aString);

	}

	/**
	 * 
	 * Thunks a null or space-only {@link String} value to
	 * {@link net.cockamamy.dataloader.util.StringUtilities#BLANK_STRING}.
	 * 
	 * @param aString
	 *            The value to thunk
	 * 
	 * @return The thunked value
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static String toBlank(String aString) {

		if (isBlank(aString) == true) {

			return BLANK_STRING;
		}

		return aString;

	}

}
