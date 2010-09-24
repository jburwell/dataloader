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

import static org.testng.Assert.*;

import static com.google.common.collect.Iterables.*;

import static net.cockamamy.dataloader.util.StringUtilities.*;

import java.util.*;

import org.testng.annotations.*;

import com.google.common.base.*;
import com.google.common.collect.*;

@Test
public final class DelimitedStringTest {

	private static final char DEFAULT_DELIMITER = ',';
	private static final String PARSE_DATA_PROVIDER = "parse_data_provider";

	@Test(dataProvider = PARSE_DATA_PROVIDER)
	public void testStringParse(List<Object> theFields) {

		assertNotNull(theFields);

		DelimitedString aDelimitedString = new DelimitedString(Joiner.on(
				DEFAULT_DELIMITER).join(theFields), DEFAULT_DELIMITER);

		assertTrue(
				elementsEqual(aDelimitedString, theFields),
				format(
						"The input fields, [%1$s], did not match the parsed fields, [%2$s].",
						theFields == null ? BLANK_STRING : theFields,
						aDelimitedString));

	}

	@Test
	public void testNullParse() {

		DelimitedString aDelimitedString = new DelimitedString(null,
				DEFAULT_DELIMITER);

		assertTrue(elementsEqual(aDelimitedString, emptyList()));

	}

	@DataProvider(name = PARSE_DATA_PROVIDER)
	public Object[][] provideParseData() {

		return new Object[][] { { emptyList() }, { ImmutableList.of("foo") },
				{ ImmutableList.of("foo", "bar", "zoo") } };

	}

}
