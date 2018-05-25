package databases;

import java.util.Map;

import models.Stock;

public interface DatabaseExtend<T> {
	T findOneById(String id);

	abstract Map<String, Stock> getAllobjects();

	abstract void addObject(T object);

	abstract void removeObject(String symbol);
}
