package fr.toz.adventofcode;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import lombok.Data;

class Day2Test {
	public static final String FORWARD = "forward";
	public static final String DOWN = "down";
	public static final String UP = "up";

	@Data
	class Pos {
		private int position; // FORWARD
		private int depth; // FORWARD
		private int aim; // FORWARD UP DOWN
		
		Pos() {
			position = 0; 
			aim = 0;
			depth = 0;
		}
		
		void forward(int x) {
			this.position += x;
			this.depth += this.aim * x; //
		}
		
		void up(int y) {
			this.aim -= y;
		}
		
		void down(int y) {
			this.aim += y; 
		}
		
		int calculate() {
			return position * depth;
		}
		
		void move(String direction, int value) {
			switch (direction) {
			case FORWARD: forward(value); break;
			case UP: up(value); break;
			case DOWN: down(value); break;
			default: new IllegalArgumentException();
			}
		}
	}
	
	int compute() {
		Pos p = new Pos();
		List<String> moves = ReadInputFile.readFile(2);
		for(String move : moves) {
			String[] t = move.split(" ");
			String direction = t[0];
			int value = Integer.parseInt(t[1]);
			p.move(direction, value);
		}
		System.out.println("VALUE:" + p);
		System.out.println("VALUE:" + p.calculate());
		return p.calculate();
	}
	
	@Test
	void secondStart() {
		Assertions.assertEquals(1463827010, compute());
	}

}
