package utils;

//package com.example.stockapp.util;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FileIO {

	private FileIO() {
	};

	public static List<String> read(String file) throws IOException {
		String path = StockDBConstants.RESOURCE_DIR + file;
		try (Stream<String> stream = Files.lines(Paths.get(path))) {
			return stream.map(s -> s.toString()).collect(Collectors.toList());
		}
	}

	public static void write(String fileContent, String file) throws IOException {
		String path = StockDBConstants.RESOURCE_DIR + file;
		try (OutputStream outputStream = new FileOutputStream(path);
				BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))) {
			bufferedWriter.write(fileContent);
		}
	}
}
