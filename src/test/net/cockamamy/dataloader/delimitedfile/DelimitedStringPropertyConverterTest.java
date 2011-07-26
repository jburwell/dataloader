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

import static net.cockamamy.dataloader.delimitedfile.DelimitedFileDataLoaderBuilder.*;

import java.util.*;

import net.cockamamy.dataloader.delimitedfile.DelimitedStringPropertyConverter.ObjectFactory;
import net.cockamamy.dataloader.util.converter.*;

import org.testng.annotations.*;

import com.google.common.base.*;
import com.google.common.collect.*;

@Test
public final class DelimitedStringPropertyConverterTest extends
		AbstractPropertyConverterTest<DelimitedStringPropertyConverterTest.MockBean> {

	private static final String DESCRIPTION_PROP_NAME = "description";

	private static final String NAME_PROP_NAME = "name";

	private static final String NAME_VALUE = "john doe";

	private static final String DESCRIPTION_VALUE = "a very common name";

	private static final ImmutableList<ColumnDefinition> COLUMNS = ImmutableList
			.of(new ColumnDefinition(NAME_PROP_NAME,
					new StringPropertyConverter()), new ColumnDefinition(
					DESCRIPTION_PROP_NAME, new StringPropertyConverter()));

	private static final ObjectFactory<MockBean> FACTORY = new DelimitedStringPropertyConverter.ObjectFactory<MockBean>() {

		public MockBean createObject(Map<String, Object> thePropertyValues) {

			return new MockBean((String) thePropertyValues.get(NAME_PROP_NAME),
					(String) thePropertyValues.get(DESCRIPTION_PROP_NAME));

		}

	};

	@Override
	protected ImmutableList<TestScenario> buildConvertValueSuccessData() {

		final ImmutableList.Builder<TestScenario> aBuilder = ImmutableList
				.builder();
		final DelimitedStringPropertyConverter<MockBean> aPropertyConverter = new DelimitedStringPropertyConverter<MockBean>(
				COLUMNS, DEFAULT_DELIMITER, FACTORY);
		final MockBean aMockBean = new MockBean(NAME_VALUE, DESCRIPTION_VALUE);

		aBuilder.add(new TestScenario(Joiner.on(
				aPropertyConverter.getDelimiter()).join(aMockBean.getName(),
				aMockBean.getDescription()), aPropertyConverter, aMockBean));

		return aBuilder.build();

	}

	public static final class MockBean {

		private final String myName;

		private final String myDescription;

		public MockBean(final String aName, final String aDescription) {

			super();

			this.myName = aName;
			this.myDescription = aDescription;

		}

		@Override
		public boolean equals(final Object thatObject) {

			if (thatObject != null
					&& this.getClass().equals(thatObject.getClass())) {

				MockBean thatMock = (MockBean) thatObject;

				if (this.myName.equals(thatMock.getName())
						&& this.myDescription.equals(thatMock.getDescription())) {

					return true;

				}

			}

			return false;

		}

		@Override
		public int hashCode() {

			int aHashCode = 37;

			aHashCode += 17 * this.myDescription.hashCode();
			aHashCode += 17 * this.myName.hashCode();

			return aHashCode;

		}

		public final String getName() {

			return this.myName;

		}

		public final String getDescription() {

			return this.myDescription;

		}

	}

}