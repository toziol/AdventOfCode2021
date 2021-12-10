package fr.toz.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import org.junit.Test;

public class Day10Test {
	public static List<String> readFile() {
		// Result: 1400
		Path inputFile = Paths.get("src", "test", "resources", "inputDay10.txt");
		try {
			return Files.lines(inputFile).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	private static final Map<Character, Integer> MATCHING_VALUE =  new HashMap<Character, Integer>();
	static {
		MATCHING_VALUE.put(')', 3);
		MATCHING_VALUE.put( ']', 57);
		MATCHING_VALUE.put( '}', 1197);
		MATCHING_VALUE.put( '>', 25137);
	};
	
	private static final Map<Character, Character> MATCHING_CLOSED = new HashMap<Character, Character>();
	static {
		MATCHING_CLOSED.put(')', '(');
		MATCHING_CLOSED.put(']', '[');
		MATCHING_CLOSED.put('}', '{');
		MATCHING_CLOSED.put('>', '<');
	}

	public int isCorrupted(final String line) {
		Vector<Character> t = new Vector<Character>();
		for (int i = 0; i < line.length(); ++i) {
			final char c = line.charAt(i);
			if (c == '(' || c == '[' || c == '{' || c == '<') {
				t.add(c);
			}
			if (c == ')' || c == ']' || c == '}' || c == '>') {
//				if (t.size() == 0) {
//					return MATCHING_VALUE.get(c);
//				}
				Character open = MATCHING_CLOSED.get(c);
				Character my = t.lastElement();
				t.remove(t.size() - 1);
				if (open != my) {
					// Corrupted Lines
					return MATCHING_VALUE.get(c);
				}
			}
		}
		// No need to be balanced
		return 0; //t.size() == 0;
	}
	
	
	public Vector<Character> incomplete(final String line) {
		Vector<Character> t = new Vector<Character>();
		for (int i = 0; i < line.length(); ++i) {
			final char c = line.charAt(i);
			if (c == '(' || c == '[' || c == '{' || c == '<') {
				t.add(c);
			}
			if (c == ')' || c == ']' || c == '}' || c == '>') {
				t.remove(t.size() - 1);
			}
		}
		// Remaining unclosed
		return t;
	}

	private static final Map<Character, Long> VALUES = new HashMap<Character, Long>();
	static {
		VALUES.put('(', 1L);
		VALUES.put('[', 2L);
		VALUES.put('{', 3L);
		VALUES.put('<', 4L);
	}
	
	@Test
	public void testDay10() {
		final List<String> lines = readFile();
		int sum = 0;
		List<Long> listIncomplete = new ArrayList<Long>();
		for (String line : lines) {
			final boolean isCorrupted = isCorrupted(line) != 0;
			sum += isCorrupted(line);
			System.out.println("line:" + line+ " isCorrupted:" + isCorrupted);
			if (! isCorrupted) {
				Vector<Character> missingClosingTag = incomplete(line);
				Collections.reverse(missingClosingTag);
				System.out.println(missingClosingTag);
				long incomplete = missingClosingTag.stream()
					.map(VALUES::get)
					.reduce(0L, (a, b) -> 5L * a + b);
				listIncomplete.add(incomplete);
				System.out.println("line:" + line + " incomplete:" + incomplete);
			}
		}
		Collections.sort(listIncomplete);
		System.out.println("sum:" + sum);
		System.out.println(listIncomplete);
		System.out.println("sumIncomplete:" + listIncomplete.get((listIncomplete.size()) / 2));
	}
}
