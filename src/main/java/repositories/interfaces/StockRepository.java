package repositories.interfaces;

import java.util.Map;

import models.Stock;

public interface StockRepository {

	Stock getStockBySymbol(String symbol);

	Map<String, Stock> getAllStocks();

	void save(Stock stock);

	void removeStock(String symbol);
}
