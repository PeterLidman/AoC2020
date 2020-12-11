package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class L11a {
	private static List<String> seat;
	private static HashMap<Map.Entry<Integer, Integer>, Character> ferry;
	private static int maxx, maxy;

	public static void run(String ext) throws IOException {
		seat = Files.readAllLines(Paths.get("./src/AoC2020/L11input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		int i = -1;
		ferry = new HashMap<>();// OMFG!! Gör inte detta i fältdeklarationen!!
		maxx = seat.get(0).length() - 1;
		maxy = seat.size() - 1;
		while (++i <= maxy) {
			for (int j = 0; j <= maxx; j++) {
				if (seat.get(i).charAt(j) == 'L') {
					ferry.put(Map.entry(i, j), 'L');
				}
			}
		}

		boolean sit = true;
		while (jumpAround(sit)) {
//			print();
			sit = !sit;
		}
		print();
		System.out.println("Seats occupied: " + ferry.values().stream().filter(a -> a == '#').count());
	}

	private static boolean jumpAround(boolean take) {
		Character c;
		int adj = 0;
		boolean change = false;
		for (Entry<Entry<Integer, Integer>, Character> s : ferry.entrySet()) {
			Integer x = s.getKey().getKey();
			Integer y = s.getKey().getValue();
//			System.out.println("x " + x + "y " + y);
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
//					System.out.println("i " + i + " j " + j);
					c = ferry.get(Map.entry(x + i, y + j));
					if (c != null && (c == '#' || c == 'l')) {
						adj++;
//						System.out.println("adj");
					}
				}
			}
			if (adj == 0 && take) {
				s.setValue('o');
				change = true;
			}
			if (adj > 4 && !take && s.getValue() == '#') {
				s.setValue('l');
				change = true;
			}
			adj = 0;
		}
		for (Entry<Entry<Integer, Integer>, Character> s : ferry.entrySet()) {
			if (s.getValue() == 'l') {
				s.setValue('L');
			}
			if (s.getValue() == 'o') {
				s.setValue('#');
			}
		}
		return change;
	}

	private static void print() {
		String tmp = "";
		Character c;
		for (int i = 0; i <= maxy; i++) {
			for (int j = 0; j <= maxx; j++) {
				c = ferry.get(Map.entry(i, j));
				if (c != null) {
					tmp += c;
				} else {
					tmp += '.';
				}
			}
			System.out.println(tmp);
			tmp = "";
		}
	}
}
