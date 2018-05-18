import enums.StockType;
import exceptions.StockServiceException;
import services.impl.StockServiceImpl;
import services.interfaces.StockService;

public class StockApplication {

	public static void main(String[] args) throws StockServiceException, IllegalAccessException {

		StockService service = new StockServiceImpl();

		service.readAll().forEach((k, v) -> {
			System.out.println(String.format("Key: %s, Value: %s", k, v));
		});

		service.create("45", StockType.PREFERRED);
	}

}
