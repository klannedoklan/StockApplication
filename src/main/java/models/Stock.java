package models;

import java.io.Serializable;

import enums.StockType;

public class Stock implements Serializable {

	private static final long serialVersionUID = 1434507726444L;
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

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + symbol.hashCode();
		result = 31 * result + stockType.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (obj == this) {
			result = true;
		}
		if (obj instanceof Stock) {
			Stock stock = (Stock) obj;
			result = stock.symbol.equals(symbol) && stock.stockType.equals(stockType);
		}

		return result;
	}

	@Override
	public String toString() {

		return String.format("[%s:%s]", this.symbol, this.stockType.name());
	}

}
