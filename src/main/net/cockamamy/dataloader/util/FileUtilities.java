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

import static java.lang.String.*;

import static net.cockamamy.dataloader.util.StringUtilities.*;

import java.io.*;

/**
 * 
 * Utilities for creating and manipulating {@link File}s.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class FileUtilities {

	private FileUtilities() {

		super();

		// Enforce the utility class pattern ..

	}

	/**
	 * 
	 * Loads a {@link File} instance for the passed filename,
	 * <code>aFileName</code>, in the passed parent directory,
	 * <code>aParentDirectory</code>.
	 * 
	 * @param aParentDirectory
	 *            The directory containing <code>aFileName</code>
	 * @param aFileName
	 *            The name of the file to load
	 * 
	 * @return A {@link File} instance for the passed filename,
	 *         <code>aFileName</code>, in the passed parent directory,
	 *         <code>aParentDirectory</code>.
	 * 
	 * @throws IllegalStateException
	 *             The passed filename, <code>aFileName</code>, does not exist
	 *             in the passed parent directory, <code>aParentDirectory</code>
	 *             .
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static File openFile(File aParentDirectory, String aFileName)
			throws FileNotFoundException {

		assert aParentDirectory != null : format(
				"%1$s.createFile requires a non-null parent directory.",
				FileUtilities.class.getName());
		assert isNotBlank(aFileName) == true : format(
				"%1$s.createFile requires a non-blank file name.",
				FileUtilities.class.getName());

		File aFile = new File(aParentDirectory, aFileName);
		if (aFile.exists() == false) {

			throw new FileNotFoundException(format(
					"%1$s does not contain %2$s", aParentDirectory.getName(),
					aFileName));

		}

		return aFile;

	}

	public static void close(Reader aReader) {

		try {

			if (aReader != null) {

				aReader.close();

			}

		} catch (IOException e) {

			System.err
					.println(format(
							"Failed to close the buffered reader due to exception %1$s",
							e));

		}

	}

	public static File getCurrentDirectory() {

		return new File(System.getProperty("user.dir"));
		
	}
}
