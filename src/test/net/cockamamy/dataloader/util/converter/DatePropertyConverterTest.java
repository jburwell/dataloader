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

import static java.util.Calendar.*;

import static net.cockamamy.dataloader.util.StringUtilities.*;

import java.text.*;
import java.util.*;

import org.testng.annotations.*;

import com.google.common.collect.*;

/**
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
@Test
public final class DatePropertyConverterTest extends
		AbstractPropertyConverterTest<Date> {

	private Date myReferenceDate;

	@BeforeClass
	public void setup() {

		final Calendar aCalendar = Calendar.getInstance();

		aCalendar.set(2008, 0, 1, 0, 0);
		aCalendar.set(SECOND, 0);
		aCalendar.set(MILLISECOND, 0);

		this.myReferenceDate = aCalendar.getTime();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.util.csv.AbstractPropertyConverterTest#
	 * provideConvertValueData()
	 */
	@Override
	protected ImmutableList<TestScenario> buildConvertValueSuccessData() {

		final ImmutableList.Builder<TestScenario> aBuilder = ImmutableList
				.builder();
		final DateFormat aDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		final DatePropertyConverter aPropertyConverter = new DatePropertyConverter(
				aDateFormat);

		aBuilder.add(new TestScenario(aDateFormat.format(this.myReferenceDate),
				aPropertyConverter, this.myReferenceDate));
		aBuilder.add(new TestScenario(null, aPropertyConverter, null));
		aBuilder.add(new TestScenario(BLANK_STRING, aPropertyConverter, null));

		return aBuilder.build();

	}

	@Override
	protected ImmutableList<TestScenario> buildConvertValueFailureData() {

		final ImmutableList.Builder<TestScenario> aBuilder = ImmutableList
				.builder();
		final DatePropertyConverter aPropertyConverter = new DatePropertyConverter();

		aBuilder.add(new TestScenario("aaa", aPropertyConverter,
				this.myReferenceDate));

		return aBuilder.build();

	}

}
