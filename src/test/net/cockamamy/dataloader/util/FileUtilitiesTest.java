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

import static java.io.File.*;

import static org.testng.Assert.*;

import static net.cockamamy.dataloader.util.FileUtilities.*;

import java.io.*;
import java.util.*;

import org.testng.annotations.*;

@Test
public final class FileUtilitiesTest {

	private File myTemporaryFile;

	@BeforeClass
	public void setup() {

		// Create a temporary file ...
		try {

			this.myTemporaryFile = createTempFile("unit-test", "txt");

		} catch (IOException e) {

			fail("Unable to create a temporary file", e);

		}

	}

	@AfterClass
	public void cleanup() {

		// Delete the temporary file ...
		this.myTemporaryFile.delete();

	}

	@Test
	public void testOpenFileSuccess() {

		try {

			File aFile = openFile(this.myTemporaryFile.getParentFile(),
					this.myTemporaryFile.getName());

			assertEquals(aFile, this.myTemporaryFile);

		} catch (FileNotFoundException e) {

			fail("Failed to open file " + this.myTemporaryFile.getParent()
					+ pathSeparator + this.myTemporaryFile.getName(), e);

		}

	}

	@Test(expectedExceptions = FileNotFoundException.class)
	public void testOpenFileNotFound() throws FileNotFoundException {

		openFile(this.myTemporaryFile.getParentFile(), UUID.randomUUID()
				.toString());

	}
}
