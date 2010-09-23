package net.cockamamy.dataloader.delimitedfile;

import static java.lang.String.*;

import static net.cockamamy.dataloader.util.StringUtilities.*;

import java.io.*;
import java.util.*;

import net.cockamamy.dataloader.*;
import net.cockamamy.dataloader.util.converter.*;

public final class DelimitedFileDataLoaderBuilder<T> {

	private static final char DEFAULT_DELIMITER = ',';

	private File myFile;

	private List<ColumnDefinition> myColumnDefinitions;

	private char myDelimiter;

	private ObjectFactory<T> myFactory;

	public DelimitedFileDataLoaderBuilder() {

		super();

		this.myColumnDefinitions = new ArrayList<ColumnDefinition>();
		this.myDelimiter = DEFAULT_DELIMITER;

	}

	public <V> DelimitedFileDataLoaderBuilder<T> addColumnDefinition(
			String aPropertyName, PropertyConverter<V> aConverter) {

		assert isNotBlank(aPropertyName) : format(
				"%1$s#addColumnDefinition(String, PropertyConverter) requires a non-blank property name.",
				this.getClass().getName());
		assert aConverter != null : format(
				"%1$s#addColumnDefinition(String, PropertyConverter) requires a non-null converter.",
				this.getClass().getName());

		this.myColumnDefinitions.add(new ColumnDefinition(aPropertyName,
				aConverter));

		return this;

	}

	public DelimitedFileDataLoaderBuilder<T> setFileName(File aFile) {

		assert aFile != null : format(
				"%1$s#setFile(File) requires a non-blank file name.", this
						.getClass().getName());

		this.myFile = aFile;

		return this;

	}

	public DelimitedFileDataLoaderBuilder<T> setDelimiter(char aDelimiter) {

		this.myDelimiter = aDelimiter;

		return this;

	}

	public DelimitedFileDataLoaderBuilder<T> setFactory(
			ObjectFactory<T> aFactory) {

		this.myFactory = aFactory;

		return this;

	}

	public DataLoader<T> buildLoader() {

		return new DelimitedFileDataLoader<T>(this.myFile,
				this.myColumnDefinitions, this.myDelimiter, this.myFactory);

	}
}
