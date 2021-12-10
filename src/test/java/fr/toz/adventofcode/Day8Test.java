package fr.toz.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class Day8Test {
	public static List<String> readFile() {
		// Result: 1400
		Path inputFile = Paths.get("src", "test", "resources", "inputDay8.txt");
		try {
			return Files.lines(inputFile).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	@Test
	public void testDay8() {
		final List<String> lines = readFile();
		int count = 0;
		for (String line : lines) {
			final String[] l = line.split(" \\| ");
			for (String n : l[1].split(" ")) {
				System.out.println(n);
				if (n.length() == 2 ||
					n.length() == 4 || 
					n.length() == 3 ||
					n.length() == 7) {
					++count;
				}
			}
		}
		System.out.println(count);
	}
	
	@Test
	public void testDay8Star2() {
		final List<String> lines = readFile();
		int count = 0;
		for (String line : lines) {
			final String[] l = line.split(" \\| ");
			for (String n : l[1].split(" ")) {
				System.out.println(n);
				if (n.length() == 2 ||
					n.length() == 4 || 
					n.length() == 3 ||
					n.length() == 7) {
					++count;
				}
			}
		}
		System.out.println(count);
	}
}
