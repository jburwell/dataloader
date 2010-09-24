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

import static java.util.Collections.*;

import static org.testng.Assert.*;

import static net.cockamamy.dataloader.util.StringUtilities.*;
import static net.cockamamy.dataloader.delimitedfile.DelimitedFileDataLoaderBuilder.*;

import static com.google.common.collect.Lists.*;

import java.util.*;

import net.cockamamy.dataloader.util.converter.*;

import org.testng.annotations.*;

import com.google.common.base.*;
import com.google.common.collect.*;

@Test
public final class RecordParserTest {

	private static final String PARSE_DATA_PROVIDER = "parse_data_provider";

	@Test(dataProvider = PARSE_DATA_PROVIDER)
	public void testParse(RecordParser aRecordParser,
			DelimitedString aDelimitedString,
			Map<String, Object> anExpectedRecord) {

		assertEquals(aRecordParser.parse(aDelimitedString), anExpectedRecord);

	}

	@DataProvider(name = PARSE_DATA_PROVIDER)
	public Object[][] provideParseData() {

		Map<String, Object> anExpectedRecord = ImmutableMap.of("foo",
				(Object) "oof", "bar", (Object) "rab", "zoo", (Object) "ooz");

		List<ColumnDefinition> theColumnDefinitions = newArrayList();
		for (String aColumnName : anExpectedRecord.keySet()) {

			theColumnDefinitions.add(new ColumnDefinition(aColumnName,
					new StringPropertyConverter()));

		}

		return new Object[][] {
				{ new RecordParser(new ArrayList<ColumnDefinition>()),
						new DelimitedString(BLANK_STRING, DEFAULT_DELIMITER),
						emptyMap() },
				{
						new RecordParser(theColumnDefinitions),
						new DelimitedString(Joiner.on(DEFAULT_DELIMITER).join(
								anExpectedRecord.values()), DEFAULT_DELIMITER),
						anExpectedRecord } };

	}

}
