[![Build Status](https://travis-ci.org/jburwell/dataloader.svg?branch=master)](https://travis-ci.org/jburwell/dataloader)

# Introduction

dataloader is a simple producer-consumer framework to load data from a source into a target repository.  DataLoaders read data from a source into a record (represented as a `Map<String,Object>`) and pass each record to a `DataConsumer` for processing (e.g. transformation and load into a database).  The `DelimitedFileDataLoader` loads and parses data from a delimited file.

# Delimited Files

The `DelimitedFileDataLoader` loads data from a file containing data delimited by a single character (e.g. comma, pipe, tab, etc).  It assumes that each row in the file represents a single object or entity.  Each column in the file is described using a `ColumnDefinition` that specifies the target property name and a `PropertyConverter` to transform the input string into its object representation.

## Configuring DelimitedFileDataLoader

The `DelimitedFileDataLoaderBuilder` configures the following `DelimitedFileDataLoader` options:

   * `addColumnDefinition`: Adds a column definition to the data loader with the passed property name and property converter.  Column definitions must be added to the builder in the order that they appear in the input file.
   * `setDelimiter`: Specifies the character that delimits fields in the file.  By default, this value is a comma.
   * `setInputFile`: A reference to the input file.

The `buildLoader` method will construct a new `DelimitedFileDataLoader` instance using the specified configuration.  Each invocation of the `buildLoader` method creates a new instance.  Therefore, a single builder instance can be used to create multiple `DelimitedFileDataLoader`s with the same configuration.

## PropertyConverters

The `PropertyConverter` interface provides the mechanism to convert a `String` value read from a delimited file into a rich object value.  The following property converters are provided with the framework:

   * `DatePropertyConverter`: Parses a string value into a date.   By default, this implementation uses the MM/dd/yyyy format.  This default behavior can be overridden by passing in a DateFormat object representing the appropriate date parse format.  Throws a PropertyConversionException if a non-blank value is specified and it can not be parsed using the specified DateFormat.
   * `DelimitedStringPropertyConverter`: Treats a String value as a delimited string and creates an object representing the delimited values.  The format of the field is specified by specifying a list of ColumnDefinitions and providing an `ObjectFactory` implementation to translate a Map of property name/value pairs into an object.
   * `EnumPropertyConverter`: Converts a String value into the specified enumerated value.  Throws a PropertyConverstionException if a non-blank value is specified value and it can not be found on the specified enumeration.
   * `StringPropertyConverter`: A pass-through implementation that returns the String value parsed from the file.

Framework users can add new converters by implementing the `PropertyConverter` interface to perform transforms.  The `AbstractPropertyConverterTest` class provides a generalized test mechanism for `PropertyConverter` implementations.

## DataConsumers

`DataConsumer`s process loaded data on a per record basis.  All `DataConsumer` implementations must be re-entrant -- allowing them to be re-used by multiple threads.    
