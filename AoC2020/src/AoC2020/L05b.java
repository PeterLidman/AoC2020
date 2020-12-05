package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class L05b {
	private static List<String> bp;

	public static void run(String ext) {
		try {
			bp = Files.readAllLines(Paths.get("./src/AoC2020/L05input" + ext + ".txt")).stream().map(String::valueOf)
					.collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Knas med filimport: " + e);
		}

		int a = 0;
		Set<Integer> possibly = new HashSet<>(), taken = new HashSet<>();

		for (a = 0; a < bp.size(); a++) {
			int id = row(bp.get(a)) * 8 + col(bp.get(a));
			taken.add(id);
			possibly.add(id + 1);
			possibly.add(id - 1);
		}
		for (Integer occ : taken) {
			possibly.remove(occ);
		}
		System.out.println("pos: " + possibly);
	}

	private static int row(String s) {
		int row = 0;

		if (s.charAt(0) == 'B') {
			row += 64;
		}
		if (s.charAt(1) == 'B') {
			row += 32;
		}
		if (s.charAt(2) == 'B') {
			row += 16;
		}
		if (s.charAt(3) == 'B') {
			row += 8;
		}
		if (s.charAt(4) == 'B') {
			row += 4;
		}
		if (s.charAt(5) == 'B') {
			row += 2;
		}
		if (s.charAt(6) == 'B') {
			row += 1;
		}
		return row;
	}

	private static int col(String s) {
		int col = 0;

		if (s.charAt(7) == 'R') {
			col += 4;
		}
		if (s.charAt(8) == 'R') {
			col += 2;
		}
		if (s.charAt(9) == 'R') {
			col += 1;
		}
		return col;
	}
}
