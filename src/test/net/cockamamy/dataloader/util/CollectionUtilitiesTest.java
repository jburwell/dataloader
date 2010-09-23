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

import static java.util.Collections.*;

import static org.testng.Assert.*;

import static net.cockamamy.dataloader.util.CollectionUtilities.*;

import java.util.*;

import org.testng.annotations.*;

/**
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
@Test
public final class CollectionUtilitiesTest {

	private static final String BUILD_UNMODIFIABLE_LIST_PROVIDER = "build_unmodifiable_list";
	private static final String BUILD_UNMODIFIABLE_SET_SUCCESS_PROVIDER = "build_unmodifiable_set_success";
	private static final String BUILD_UNMODIFIABLE_SET_FAILURE_PROVIDER = "build_unmodifiable_set_failure";

	@Test(dataProvider = BUILD_UNMODIFIABLE_LIST_PROVIDER)
	public <T> void testBuildUnmodifiableList(T[] theElements,
			List<T> theExpectedResults) {

		assertEquals(buildUnmodifiableList(theElements), theExpectedResults);

	}

	@DataProvider(name = BUILD_UNMODIFIABLE_LIST_PROVIDER)
	public Object[][] provideBuildUnmodifiableTestData() {

		Object[][] theData = new Object[2][2];

		List<String> aList = new ArrayList<String>();

		aList.add("foo");
		aList.add("bar");
		aList.add("zoo");

		aList = unmodifiableList(aList);

		theData[0][0] = aList.toArray();
		theData[0][1] = aList;

		theData[1][0] = null;
		theData[1][1] = emptyList();

		return theData;

	}

	@Test(dataProvider = BUILD_UNMODIFIABLE_SET_SUCCESS_PROVIDER)
	public <T> void testBuildUnmodifableSet(T[] theElements,
			Set<T> anExpectedResult) {

		assertEquals(buildUnmodifiableSet(theElements), anExpectedResult);

	}

	@Test(dataProvider = BUILD_UNMODIFIABLE_SET_FAILURE_PROVIDER, expectedExceptions = IllegalStateException.class)
	public <T> void testBuildUnmodifiableSetFailure(T[] theElements) {

		buildUnmodifiableSet(theElements);

	}

	@DataProvider(name = BUILD_UNMODIFIABLE_SET_SUCCESS_PROVIDER)
	public Object[][] provideBuildUnmodifableSetSuccessData() {

		Object[][] theData = new Object[2][2];

		Set<String> aSet = new HashSet<String>();

		aSet.add("foo");
		aSet.add("bar");
		aSet.add("zoo");

		aSet = unmodifiableSet(aSet);

		theData[0][0] = aSet.toArray();
		theData[0][1] = aSet;

		theData[1][0] = null;
		theData[1][1] = emptySet();

		return theData;

	}

	@DataProvider(name = BUILD_UNMODIFIABLE_SET_FAILURE_PROVIDER)
	public Object[][] provideUnmodifiableSetFailureData() {

		return new Object[][] {

			{ new String[] { "foo", "foo" } }

		};

	}

}
