package com.cedricleumaleu.accessorassessmenttest.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cedricleumaleu.accessorassessmenttest.exceptions.CsvException;

import ch.qos.logback.core.util.FileUtil;

public class FileUtils {

	private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

	public static String readTxtFileToString(String fileName) {
		File file = new File(System.getProperty("user.home").replace("\\", "/") + "/Documents/songs/" + fileName);
		if (!file.exists()) {
			return "";
		}
		try {
			String properties = System.getProperty("user.home").replace("\\", "/") + "/Documents/csv/" + fileName;
			Path path = Paths.get(properties);
			return new String(Files.readAllBytes(path));
		} catch (IOException e) {
			LOG.error("Datei " + fileName + " konnte nicht gelesen werden");
			e.printStackTrace();
		}
		return null;
	}

	public static void printInputStream(InputStream is) {

		try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(streamReader)) {

			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean assumeThatCsvFile(String fileName) {
		return fileName.trim().endsWith(".csv");
	}

	public static void writeStringListToCsvFile(List<String> collect, String filename) {
		createDirectory(System.getProperty("user.home") + "/Documents/csv");
		String data = "";
		for (String line : collect) {
			data += line.toString() + "\n";
		}
		if (assumeThatCsvFile(filename)) {
			writeStringToFile(data, System.getProperty("user.home").replace("\\", "/") + "/Documents/csv/" + filename);
		} else {
			throw new CsvException("Wrong file", null);
		}

	}

	public static void writeStringToFile(String content, String filename) {
		File file = new File(filename);
		FileOutputStream faos = null;
		try {
			faos = new FileOutputStream(file);
			byte[] byteContent = (content != null) ? content.getBytes() : "".getBytes();
			faos.write(byteContent);

		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			if (faos != null) {
				try {
					faos.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static void createDirectory(String location) {
		Path path = Paths.get(location);
		try {
			Files.createDirectories(path);
		} catch (IOException e) {
			LOG.error("Directory konnte nicht angelegt werden");
		}
		LOG.info("Directory wurde erfolgreich angelegt");
	}

	public static void removeFile(String fileName) {
		try {
			File file = new File(System.getProperty("user.home").replace("\\", "/") + "/Documents/songs/" + fileName);
			if (file.exists() && file.delete()) {
				LOG.info(file.getName() + " is deleted!");
			} else {
				LOG.error("Delete operation is failed. File does not exist");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public static List<String[]> convertCsvStringIntoStringList(String csvString) {
		String[] lines = csvString.split("\n");
		List<String[]> result = new ArrayList<>();
		for (String line : lines) {
			result.add(line.split(","));
		}
		return result;
	}
}
