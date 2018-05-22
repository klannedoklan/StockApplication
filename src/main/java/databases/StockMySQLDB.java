package databases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import enums.StockType;
import models.Stock;

public final class StockMySQLDB extends Database<Stock> implements DatabaseExtend<Stock> {

	private static final String SELECT_ALL_STOCKS_QUERY = "SELECT symbol, stock_type FROM stocks";
	private static final String INSERT_STOCK_QUERY = "INSERT INTO stocks (symbol, stock_type) VALUES (?,?)";
	private static final String DELETE_STOCK_QUERY = "DELETE FROM stocks WHERE symbol = ?";
	private static final String SELECT_STOCK_BY_ID_QUERY = "SELECT symbol, stock_type FROM stocks WHERE symbol = ?";

	private static StockMySQLDB instance;

	private StockMySQLDB() throws IllegalAccessException {
		if (instance != null) {
			throw new IllegalAccessException("This instance should be created only via getInstance() method");
		}
	}

	public static StockMySQLDB getInstance() throws IllegalAccessException {
		if (instance == null) {
			instance = new StockMySQLDB();
		}
		return instance;
	}

	@Override
	public Map<String, Stock> getAllobjects() {

		Map<String, Stock> result = new HashMap<>();
		try (Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement();
				ResultSet allStocksResultSet = statement.executeQuery(StockMySQLDB.SELECT_ALL_STOCKS_QUERY)) {

			while (allStocksResultSet.next()) {
				String key = allStocksResultSet.getString(1);
				Stock value = new Stock(key, StockType.valueOf(allStocksResultSet.getString(2)));
				result.put(key, value);
			}

			allStocksResultSet.close();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void addObject(Stock object) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(StockMySQLDB.INSERT_STOCK_QUERY)) {
			statement.setString(1, object.getSymbol());
			statement.setString(2, object.getStockType().name());
			statement.executeUpdate();

			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeObject(String symbol) {
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(StockMySQLDB.DELETE_STOCK_QUERY)) {
			statement.setString(1, symbol);
			statement.executeUpdate();

			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Stock findOneById(String symbol) {
		Stock result = null;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(StockMySQLDB.SELECT_STOCK_BY_ID_QUERY)) {
			statement.setString(1, symbol);
			ResultSet foundStockResultSet = statement.executeQuery();
			while (foundStockResultSet.next()) {
				result = new Stock(foundStockResultSet.getString(1),
						StockType.valueOf(foundStockResultSet.getString(2)));
				break;
			}
			foundStockResultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
