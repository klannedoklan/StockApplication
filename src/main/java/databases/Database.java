package databases;

import java.util.Map;

import models.Stock;

public abstract class Database<T> {

	abstract Map<String, Stock> getAllobjects();

	abstract void addObject(T object);

	abstract void removeObject(String symbol);
}
