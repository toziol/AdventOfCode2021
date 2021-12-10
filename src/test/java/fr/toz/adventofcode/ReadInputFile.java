package fr.toz.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReadInputFile {
	public static List<String> readFile(final int day) {
		// Result: 1400
		Path inputFile = Paths.get("src", "test", "resources", "inputDay" + day +".txt");
		try {
			return Files.lines(inputFile).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public static List<String> readFileTest(final int day) {
		// Result: 1400
		Path inputFile = Paths.get("src", "test", "resources", "inputTestDay" + day +".txt");
		try {
			return Files.lines(inputFile).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
}
