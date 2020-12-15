package AoC2020;

import java.io.IOException;
import java.util.HashMap;

public class L15 {
	private static HashMap<Integer, Integer> game;
	private static HashMap<Integer, Integer> mem;

	public static void run() throws IOException {
		game = new HashMap<>();
		mem = new HashMap<>();
		Integer s;

//		game.put(0, 1); // <- part 1
//		game.put(3, 2);
//		game.put(6, 3);
//		int turn = 4, spoken = 6;

		game.put(9, 1); // <- part2
		game.put(19, 2);
		game.put(1, 3);
		game.put(6, 4);
		game.put(0, 5);
		game.put(5, 6);
		game.put(4, 7);
		int turn = 8, spoken = 7;

		while (turn <= 30000000) { // <- 2020 for part 1
			s = mem.get(spoken);
			if (s == null) {
				spoken = 0;
				mem.put(0, game.get(0));
			} else {
				spoken = game.get(spoken) - s;
				mem.put(spoken, game.get(spoken));
			}
			game.put(spoken, turn++);
		}
		System.out.println("Turn= " + (turn - 1) + " spoken= " + spoken);
	}
}