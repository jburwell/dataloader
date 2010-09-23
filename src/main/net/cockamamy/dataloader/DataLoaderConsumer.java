package net.cockamamy.dataloader;

public interface DataLoaderConsumer<T> {

	void consume(T anObject);
	
}
