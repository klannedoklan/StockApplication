package services.impl;

import java.util.Map;

import enums.StockType;
import exceptions.StockServiceException;
import models.Stock;
import repositories.impl.StockRepositoryImpl;
import repositories.interfaces.StockRepository;
import services.interfaces.StockService;

public final class StockServiceImpl implements StockService {

	private StockRepository _stockRepositoryInstance;

	public StockServiceImpl() throws IllegalAccessException {
		this._stockRepositoryInstance = StockRepositoryImpl.getInstance();
	}

	@Override
	public void create(String symbol, StockType stockType) throws StockServiceException {

		if (symbol == null || stockType == null) {
			throw new StockServiceException("symbol and stockType cannot be null!");
		}
		if (_stockRepositoryInstance.getStockBySymbol(symbol) != null) {
			throw new StockServiceException("Stock " + symbol + " already exist!");
		}
		Stock stock = new Stock(symbol, stockType);
		try {
			this._stockRepositoryInstance.save(stock);
		} catch (Exception ignored) {
			throw new StockServiceException("Stock " + stock.getSymbol() + " already exist!");
		}
	}

	@Override
	public Map<String, Stock> readAll() throws StockServiceException {
		Map<String, Stock> allRecords;
		try {
			allRecords = this._stockRepositoryInstance.getAllStocks();
		} catch (Exception ignored) {
			throw new StockServiceException("Unable to read all records!");
		}
		return allRecords;
	}

	@Override
	public Stock readOne(String symbol) throws StockServiceException {
		if (symbol == null) {
			throw new StockServiceException("symbol cannot be null");
		}

		Stock oneRecord;
		try {
			oneRecord = this._stockRepositoryInstance.getStockBySymbol(symbol);
		} catch (Exception ignored) {
			throw new StockServiceException("Error finding: " + symbol);
		}
		return oneRecord;
	}

	@Override
	public String deleteOne(String symbol) throws StockServiceException {
		if (symbol == null) {
			throw new StockServiceException("symbol cannot be null");
		}
		StringBuilder sb = new StringBuilder();
		try {
			this._stockRepositoryInstance.removeStock(symbol);
			sb.append("Record: " + symbol + "successfully deleted!");
		} catch (Exception ignored) {
			sb.append("Record: " + symbol + "cannot be deleted!");
			throw new StockServiceException(sb.toString());
		}
		return sb.toString();
	}

}
