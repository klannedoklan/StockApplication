import enums.StockType;
import exceptions.StockServiceException;
import services.impl.StockServiceImpl;
import services.interfaces.StockService;

public class StockApplication {

	public static void main(String[] args) throws StockServiceException, IllegalAccessException {

		StockService stockService = new StockServiceImpl();

		stockService.readAll().forEach((k, v) -> {
			System.out.println(String.format("Key: %s, Value: %s", k, v));
		});

		System.out.println(stockService.create("LKXJ", StockType.PREFERRED));
		System.out.println(stockService.readOne("B"));
		System.out.println(stockService.deleteOne("LKXJ"));
	}

}
