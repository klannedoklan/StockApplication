package databases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import enums.StockType;
import io.impl.FileIOImpl;
import io.interfaces.FileIO;
import models.Stock;

public class StockDB extends Database<Stock> {

	private Map<String, Stock> stocksData;

	public StockDB() {
		super();
		this.stocksData = loadStockData();
	}

	@Override
	public Map<String, Stock> getAllobjects() {
		return stocksData;
	}

	@Override
	public void removeObject(String symbol) {
		stocksData.remove(symbol);
	}

	@Override
	public void addObject(Stock stock) {
		stocksData.put(stock.getSymbol(), stock);
	}

	private Map<String, Stock> loadStockData() {
		FileIO fileIO = new FileIOImpl();
		List<String> lines = new ArrayList<>();
		ConcurrentHashMap<String, Stock> data = new ConcurrentHashMap<>();
		try {
			lines = fileIO.read("stocks.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String line : lines) {
			String[] stockObjects = line.split(",");
			data.put(stockObjects[0], new Stock(stockObjects[0], StockType.valueOf(stockObjects[1])));
		}
		return data;
	}
}
