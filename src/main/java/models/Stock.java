package models;

import enums.StockType;

public class Stock {

	private String symbol;
	private StockType stockType;

	public Stock(String symbol, StockType stockType) {
		this.symbol = symbol;
		this.stockType = stockType;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public StockType getStockType() {
		return stockType;
	}

	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}

}
