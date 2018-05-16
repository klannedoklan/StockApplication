package repositories.impl;

import java.util.Map;

import databases.StockDB;
import models.Stock;
import repositories.interfaces.StockRepository;

public class StockRepositoryImpl implements StockRepository {

	private StockDB stockDB;

	public StockRepositoryImpl() {
		super();
		this.stockDB = new StockDB();
	}

	@Override
	public Stock getStockBySymbol(String symbol) {
		return stockDB.getAllobjects().get(symbol);
	}

	@Override
	public Map<String, Stock> getAllStocks() {
		return stockDB.getAllobjects();
	}

	@Override
	public void save(Stock stock) {
		stockDB.addObject(stock);
	}

	@Override
	public void removeStock(String symbol) {
		stockDB.removeObject(symbol);
	}

}
