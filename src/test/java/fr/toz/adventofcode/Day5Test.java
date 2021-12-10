package fr.toz.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import lombok.Getter;
import lombok.Setter;

public class Day5Test {
	@Getter
	@Setter
	class Line implements Iterator<Pair<Integer, Integer>> {
		private int x1;
		private int y1;
		private int x2;
		private int y2;
		private int curPosX;
		private int curPosY;
		private boolean first = true;
		
		Line(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.curPosX = x1;
			this.curPosY = y1;
		}
		
		public boolean isHorizontalLine() {
			return isXHorizontale() || isYHorizontale();
		}
		
		public boolean isXHorizontale() {
			return x1 == x2;
		}
		
		public boolean isYHorizontale() {
			return y1 == y2;
		}
		
		public int getMinX() {
			return Math.min(x2, x1);
		}
		
		public int getMaxX() {
			return Math.max(x2, x1);
		}
		
		public int getMaxY() {
			return Math.max(y1, y2);
		}
		
		public int getMinY() {
			return Math.min(y1, y2);
		}

		@Override
		public boolean hasNext() {
			return x2 != curPosX || y2 != curPosY;
		}

		@Override
		public Pair<Integer, Integer> next() {
			if (first) {
				first = false;
				return Pair.of(curPosX, curPosY);
			}
			if (curPosX < x2) {
				curPosX++;
			}
			else if (curPosX > x2) {
				curPosX--;
			}
			if (curPosY < y2) {
				curPosY++;
			}
			else if (curPosY > y2) {
				curPosY--;
			}
			return Pair.of(curPosX, curPosY);
		}
	}
	
	@Test
	public void displayArray() {
		int[][] a = {{ 1, 2, 3 }, { 4,5,6 }, { 7,8,9 }};
		Arrays.asList(a).forEach(e -> System.out.println(Arrays.toString(e)));
	}
	
	@Test
	public void testInput() {
		Path inputFile = Paths.get("src","test","resources", "inputDay5.txt");
		List<Line> positions = new ArrayList<Line>();
		try {
			Files.lines(inputFile).forEach((line) -> {
				String[] lineInput = line.split(" -> ");
				Line p = new Line(
					Integer.parseInt(lineInput[0].split(",")[0]),
					Integer.parseInt(lineInput[0].split(",")[1]),
					Integer.parseInt(lineInput[1].split(",")[0]),
					Integer.parseInt(lineInput[1].split(",")[1])
				);
				if (p.isHorizontalLine()) {
					positions.add(p);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		computeMax(positions);
	}

	private void computeMax(List<Line> positions) {
		Integer[][] matrice = initMatrice(positions);
		
		// COUNT :5306 HORIZONTAL
		// COUNT :17787 ALL_LINES
		positions.forEach(pos -> pos.forEachRemaining(p -> matrice[p.getKey()][p.getValue()]++));		
		
		Arrays.asList(matrice).forEach(e -> System.out.println(Arrays.toString(e)));
		long count = Stream.of(matrice).flatMap(Stream::of).filter(e -> e >= 2).count();
		System.out.println("COUNT:" + count);
	}

	private Integer[][] initMatrice(List<Line> positions) {
		Integer maxX = positions.stream().map(Line::getMaxX).max(Comparator.naturalOrder()).get();
		Integer minX = positions.stream().map(Line::getMinX).min(Comparator.naturalOrder()).get();
		Integer maxY = positions.stream().map(Line::getMaxY).max(Comparator.naturalOrder()).get();
		Integer minY = positions.stream().map(Line::getMinY).min(Comparator.naturalOrder()).get();
		
		System.out.println(String.format("maxX:%d minX:%d maxY:%d minY:%d", maxX, minX, maxY, minY));

		// 
		Integer[][] matrice = new Integer[maxX+1][maxY+1];
		// Init 0
		for (int i = 0; i < matrice.length; i++) {
			Arrays.fill(matrice[i], 0);
		}
		return matrice;
	}
}