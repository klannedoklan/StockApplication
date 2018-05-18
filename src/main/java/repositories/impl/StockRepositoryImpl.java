package repositories.impl;

import java.util.Map;

import databases.StockDB;
import models.Stock;
import repositories.interfaces.StockRepository;

public final class StockRepositoryImpl implements StockRepository {

	private StockDB _stockDBInstance = StockDB.getInstance();

	private static StockRepositoryImpl instance;

	private StockRepositoryImpl() throws IllegalAccessException {
		if (instance != null) {
			throw new IllegalAccessException("This instance should be created only via getInstance() method");
		}
	};

	public static StockRepositoryImpl getInstance() throws IllegalAccessException {
		if (instance == null) {
			instance = new StockRepositoryImpl();
		}
		return instance;
	}

	@Override
	public Stock getStockBySymbol(String symbol) {
		return _stockDBInstance.getAllobjects().get(symbol);
	}

	@Override
	public Map<String, Stock> getAllStocks() {
		return _stockDBInstance.getAllobjects();
	}

	@Override
	public void save(Stock stock) {
		_stockDBInstance.addObject(stock);
	}

	@Override
	public void removeStock(String symbol) {
		_stockDBInstance.removeObject(symbol);
	}

}
