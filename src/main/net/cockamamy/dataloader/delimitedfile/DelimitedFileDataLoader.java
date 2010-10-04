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

import static net.cockamamy.dataloader.util.FileUtilities.*;

import java.io.*;
import java.util.*;

import net.cockamamy.dataloader.*;

/**
 * 
 * Loads data from a delimited file based on a set of column definitions and
 * converts it to rich objects. It then delegates processing of the processed
 * data to passed consumer.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
final class DelimitedFileDataLoader implements DataLoader {

	private final File myInputFile;

	private final char myDelimiter;

	private final RecordParser myParser;

	/**
	 * 
	 * @param anInputFile
	 *            The input file from which to load data
	 * @param theColumnDefinitions
	 *            The definition of each column a delimited file provided in the
	 *            order they in the file.
	 * @param aDelimiter
	 *            The character that delimits the fields
	 * 
	 * @since 1.0.0
	 * 
	 */
	public DelimitedFileDataLoader(File anInputFile,
			List<ColumnDefinition> theColumnDefinitions, char aDelimiter) {

		super();

		assert anInputFile != null : format(
				"%1$s(File, String, List) requires a non-null file.", this
						.getClass().getName());
		assert theColumnDefinitions != null
				&& theColumnDefinitions.isEmpty() == false : format(
				"%1$s(File, String, List) requires a non-empty column definition list.",
				this.getClass().getName());

		this.myInputFile = anInputFile;
		this.myDelimiter = aDelimiter;
		this.myParser = new RecordParser(theColumnDefinitions);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.DataLoader#loadData(java.io.File)
	 */
	public final void loadData(DataConsumer aConsumer) {

		BufferedReader aReader = null;

		try {

			aReader = new BufferedReader(new FileReader(this.myInputFile));

			while (aReader.ready()) {

				aConsumer.consume(this.myParser.parse(new DelimitedString(
						aReader.readLine(), this.myDelimiter)));

			}

		} catch (IOException e) {

			throw new IllegalStateException(format(
					"Failed to read delimited file %1$s.",
					this.myInputFile.getPath()), e);

		} finally {

			close(aReader);

		}

	}

}
