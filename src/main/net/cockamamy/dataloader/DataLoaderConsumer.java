package net.cockamamy.dataloader;

import java.util.*;

public interface DataLoaderConsumer {

	void consume(Map<String, Object> theData);
	
}
