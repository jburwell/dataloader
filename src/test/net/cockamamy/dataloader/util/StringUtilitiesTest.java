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

import static org.testng.Assert.*;

import static net.cockamamy.dataloader.util.StringUtilities.*;

import org.testng.annotations.*;

@Test
public final class StringUtilitiesTest {

	private static final String IS_BLANK_PROVIDER = "is_blank_provider";
	private static final String TO_BLANK_PROVIDER = "to_blank_provider";

	@Test(dataProvider = IS_BLANK_PROVIDER)
	public void testIsBlank(String aValue, boolean aBlankFlag) {

		assertEquals(isBlank(aValue), aBlankFlag);

	}

	@Test(dataProvider = IS_BLANK_PROVIDER)
	public void testIsNotBlank(String aValue, boolean aBlankFlag) {

		assertEquals(isNotBlank(aValue), !aBlankFlag);

	}

	@Test(dataProvider = TO_BLANK_PROVIDER)
	public void testToBlank(String aValue, String anExpectedResult) {

		assertEquals(toBlank(aValue), anExpectedResult);

	}

	@DataProvider(name = IS_BLANK_PROVIDER)
	public Object[][] provideIsBlank() {

		return new Object[][] {
				
			{ "", true }, { " ", true },
			{ "        ", true }, { null, true }, { "foo", false }
			
		};

	}

	@DataProvider(name = TO_BLANK_PROVIDER)
	public Object[][] provideToBlank() {

		return new Object[][] {

			{ "", BLANK_STRING }, { "         ", BLANK_STRING },
			{ null, BLANK_STRING }, { "foo", "foo" }

		};

	}

}
