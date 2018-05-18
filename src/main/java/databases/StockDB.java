package databases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import enums.StockType;
import models.Stock;
import utils.FileIO;

public final class StockDB extends Database<Stock> {

	private Map<String, Stock> stocksData;

	private static StockDB instance;

	private StockDB() throws IllegalAccessException {
		if (instance != null) {
			throw new IllegalAccessException("This instance should be created only via getInstance() method");
		}
		this.stocksData = loadStockData();
	}

	public static StockDB getInstance() throws IllegalAccessException {
		if (instance == null) {
			instance = new StockDB();
		}
		return instance;
	}

	@Override
	public Map<String, Stock> getAllobjects() {
		return stocksData;
	}

	@Override
	public void removeObject(String symbol) {
		stocksData.remove(symbol);
		this.saveStockData();
	}

	@Override
	public void addObject(Stock stock) {
		stocksData.put(stock.getSymbol(), stock);
		this.saveStockData();
	}

	private Map<String, Stock> loadStockData() {
		List<String> lines = new ArrayList<>();
		ConcurrentHashMap<String, Stock> data = new ConcurrentHashMap<>();
		try {
			lines = FileIO.read("stocks.txt");
			if (Optional.of(lines).isPresent()) {
				lines.forEach(item -> {
					String[] stockObjects = item.split(",");

					data.putIfAbsent(stockObjects[0], new Stock(stockObjects[0], StockType.valueOf(stockObjects[1])));
				});
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	private void saveStockData() {
		StringBuilder fileContent = new StringBuilder();
		this.stocksData.forEach((k, v) -> {
			fileContent.append(String.format("%s,%s", k, v.getStockType().toString())).append(System.lineSeparator());
		});
		try {
			FileIO.write(fileContent.toString().trim(), "stocks.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
