package net.cockamamy.dataloader;

public interface DataLoader<T> {

	void loadData(DataLoaderConsumer<T> aConsumer);

}
