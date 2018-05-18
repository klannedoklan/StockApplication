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
	public String create(String symbol, StockType stockType) throws StockServiceException {

		if (symbol == null || stockType == null) {
			throw new StockServiceException("symbol and stockType cannot be null!");
		}
		if (_stockRepositoryInstance.getStockBySymbol(symbol) != null) {
			throw new StockServiceException("Stock with symbol: " + symbol + " already exist!");
		}
		Stock stock = new Stock(symbol, stockType);
		try {
			this._stockRepositoryInstance.save(stock);
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		}
		return "Stock: " + stock + " successfully created!";
	}

	@Override
	public Map<String, Stock> readAll() throws StockServiceException {
		// Map<String, Stock> allRecords;
		// try {
		// allRecords = this._stockRepositoryInstance.getAllStocks();
		// } catch (Exception ignored) {
		// // refactor to throw early as possible an catch here
		// throw new StockServiceException("Unable to read all records!");
		// }
		return this._stockRepositoryInstance.getAllStocks();
	}

	@Override
	public Stock readOne(String symbol) throws StockServiceException {
		if (symbol == null) {
			throw new StockServiceException("symbol cannot be null");
		}

		Stock oneRecord = this._stockRepositoryInstance.getStockBySymbol(symbol);
		return oneRecord;
	}

	@Override
	public String deleteOne(String symbol) throws StockServiceException {
		if (symbol == null) {
			throw new StockServiceException("Symbol cannot be null");
		}
		StringBuilder sb = new StringBuilder();
		try {
			this._stockRepositoryInstance.removeStock(symbol);
			sb.append("Record: " + symbol + " successfully deleted!");
		} catch (IllegalArgumentException iae) {
			sb.append(iae.getMessage());
		}
		return sb.toString();
	}

}
