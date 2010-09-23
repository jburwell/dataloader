package net.cockamamy.dataloader.delimitedfile;

import static java.lang.String.*;

import static net.cockamamy.dataloader.util.FileUtilities.*;

import java.io.*;
import java.util.*;

import net.cockamamy.dataloader.*;

/**
 * 
 * Loads domain object from a delimited file.
 * 
 * @author jburwell
 * 
 * @param <T>
 *            The type of object that will be loaded from the delimited file
 *            data
 * 
 * @since 1.0.0
 * 
 */
final class DelimitedFileDataLoader<T> implements DataLoader<T> {

	private final File myFile;

	private final char myDelimiter;

	private final ObjectBuilder<T> myObjectBuilder;

	/**
	 * 
	 * @param theColumnDefinitions
	 *            The definition of each column a delimited file provided in the
	 *            order they in the file.
	 * 
	 * @since 1.0.0
	 * 
	 */
	public DelimitedFileDataLoader(File aFile,
			List<ColumnDefinition> theColumnDefinitions, char aDelimiter,
			ObjectFactory<T> aFactory) {

		super();

		assert theColumnDefinitions != null
				&& theColumnDefinitions.isEmpty() == false : format(
				"%1$s(File, String, List) requires a non-empty column definition list.",
				this.getClass().getName());

		this.myFile = aFile;
		this.myDelimiter = aDelimiter;
		this.myObjectBuilder = new ObjectBuilder<T>(theColumnDefinitions,
				aFactory);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.DataLoader#loadData(java.io.File)
	 */
	public final void loadData(DataLoaderConsumer<T> aConsumer) {

		BufferedReader aReader = null;

		try {

			aReader = new BufferedReader(new FileReader(this.myFile));

			while (aReader.ready()) {

				aConsumer.consume(this.myObjectBuilder
						.buildObject(new DelimitedString(aReader.readLine(),
								this.myDelimiter)));

			}

		} catch (IOException e) {

			throw new IllegalStateException(format(
					"Failed to read delimited file %1$s.", this.myFile
							.getPath()), e);

		} finally {

			close(aReader);

		}

	}

}
