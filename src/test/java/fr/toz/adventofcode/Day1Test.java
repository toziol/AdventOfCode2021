package fr.toz.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import org.junit.Test;

public class Day1Test {
	@Test
	public void testInput() {
		// Result: 1400
		Path inputFile = Paths.get("src","test","resources", "inputDay1.txt");
		try {
			List<Integer> t = Files.lines(inputFile)
					.map(line -> Integer.parseInt(line))
					.collect(Collectors.toList());
			numOfIncrement(t);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testStarTwo() {
		Path inputFile = Paths.get("src","test","resources", "inputDay1.txt");
		try {
			List<Integer> t = Files.lines(inputFile)
					.map(line -> Integer.parseInt(line))
					.collect(Collectors.toList());
			final Vector<Integer> frameOf3 = new Vector<Integer>();
			final List<Integer> sumByFrameOf3 = new ArrayList<Integer>();
			for (Integer i : t) {
				if (frameOf3.size() == 3) {
					sumByFrameOf3.add(frameOf3.stream().reduce(0, Integer::sum));
				}
				frameOf3.add(i);
				if (frameOf3.size() == 4) {
					frameOf3.remove(0);
				}
			}
			sumByFrameOf3.add(frameOf3.stream().reduce(0, Integer::sum));
			numOfIncrement(sumByFrameOf3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void numOfIncrement(List<Integer> t) {
		int previous = 1000000;
		int numIncrement = 0;
		for (Integer i : t) {
			if (i > previous) {
				numIncrement++;
			}
			previous = i;
		}
		System.out.print("numIncrement: " + numIncrement);
	}
}
