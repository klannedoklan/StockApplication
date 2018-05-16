package io.interfaces;

import java.io.IOException;
import java.util.List;

public interface FileIO {
	List<String> read(String file) throws IOException;

	void write(String fileContent, String file) throws IOException;
}
