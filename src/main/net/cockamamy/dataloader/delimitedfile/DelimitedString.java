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

import static net.cockamamy.dataloader.util.ObjectUtilities.*;
import static net.cockamamy.dataloader.util.StringUtilities.*;

import java.util.*;

import net.cockamamy.dataloader.util.*;

/**
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class DelimitedString implements Iterable<String> {

	private static final char DEFAULT_DELIMITER = ',';

	private final String myLine;

	private final char myDelimter;

	private final Iterator<String> myIterator;

	public DelimitedString(String aLine, char aDelimiter) {

		super();

		this.myLine = aLine;
		this.myDelimter = aDelimiter;
		this.myIterator = isNotBlank(this.myLine) ? new DelimitedLineIterator(
				this.myLine, this.myDelimter) : new NullIterator<String>();

	}

	public DelimitedString(String aLine) {

		this(aLine, DEFAULT_DELIMITER);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<String> iterator() {

		return this.myIterator;

	}

	public String getLine() {

		return this.myLine;

	}

	// BEGIN: Object implementation
	@Override
	public boolean equals(Object thatObject) {

		if (thatObject != null && this.getClass().equals(thatObject.getClass())) {

			DelimitedString thatLine = (DelimitedString) thatObject;

			if (isEqualTo(this.myLine, thatLine.getLine())
					&& this.myDelimter == thatLine.getDelimter()) {

				return true;

			}

		}

		return false;

	}

	@Override
	public int hashCode() {

		int aHashCode = 37;

		aHashCode = (aHashCode * 17) + this.myDelimter;
		aHashCode = (aHashCode * 17) + this.myLine != null ? this.myLine
				.hashCode() : 0;

		return aHashCode;

	}

	@Override
	public String toString() {

		return format("Delimited Line (delimiter: %1$c, line: %2$s)",
				this.myDelimter, this.myLine);

	}

	// END: Object implementation

	public char getDelimter() {

		return this.myDelimter;

	}

	private static final class DelimitedLineIterator implements
			Iterator<String> {

		private final StringTokenizer myTokenizer;

		public DelimitedLineIterator(String aLine, char aDelimiter) {

			this.myTokenizer = new StringTokenizer(aLine, String
					.valueOf(aDelimiter));

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {

			return this.myTokenizer.hasMoreTokens();

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next()
		 */
		public String next() {

			return this.myTokenizer.nextToken();

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {

			throw new UnsupportedOperationException(
					"DelimitedLineIterator is read-only and does not support item removal.");

		}

	}

}
