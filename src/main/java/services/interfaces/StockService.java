package services.interfaces;

import java.util.Map;

import enums.StockType;
import exceptions.StockServiceException;
import models.Stock;

public interface StockService {

	void create(String symbol, StockType stockType) throws StockServiceException;

	Map<String, Stock> readAll() throws StockServiceException;

	Stock readOne(String symbol) throws StockServiceException;

	// void update() throws StockServiceException;

	String deleteOne(String symbol) throws StockServiceException;

}
