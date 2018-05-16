package io.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import io.interfaces.FileIO;

public class FileIOImpl implements FileIO {
	@Override
	public List<String> read(String file) throws IOException {
		String path = System.getProperty("user.dir") + "/src/main/resources" + file;
		List<String> stockObjects = new ArrayList<>();
		try (InputStream inputStream = getClass().getResourceAsStream(path);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line = "";

			while ((line = bufferedReader.readLine()) != null) {
				stockObjects.add(line);
			}
		}
		return stockObjects;
	}

	@Override
	public void write(String fileContent, String file) throws IOException {
		String path = System.getProperty("user.dir") + "/src/main/resources" + file;
		try (OutputStream outputStream = new FileOutputStream(path);
				BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))) {
			bufferedWriter.write(fileContent);
		}
	}
}
