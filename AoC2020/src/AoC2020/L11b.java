package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class L11b {
	private static List<String> seat;
	private static HashMap<Map.Entry<Integer, Integer>, Character> ferry;
	private static int maxx, maxy;

	public static void run(String ext) throws IOException {
		seat = Files.readAllLines(Paths.get("./src/AoC2020/L11input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		int i = -1;
		ferry = new HashMap<>();//OMFG!! Gör inte detta i fältdeklarationen!!
		maxx = seat.get(0).length() - 1;
		maxy = seat.size() - 1;
		while (++i <= maxy) {
			for (int j = 0; j <= maxx; j++) {
				if (seat.get(i).charAt(j) == 'L') {
					ferry.put(Map.entry(j, i), 'L');
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
					if (i == 0 && j == 0) {
						continue;
					}

					boolean look = true;
					int times = 1;
					while (look) {
//					System.out.println("i " + i + " j " + j + " times " + times);
						c = ferry.get(Map.entry(x + i * times, y + j * times));
						if (c != null) {
							if (c == 'L' || c == 'o') {
								look = false;
//								System.out.println("Lo @ " + (x + i * times) + " " + (y + j * times));
							}
							if (c == '#' || c == 'l') {
								adj++;
								look = false;
//								System.out.println("#l @ " + (x + i * times) + " " + (y + j * times));
							}
						}
						look = look && !(x + i * times >= maxx + 2 || x + i * times < -2 || y + j * times >= maxy + 2
								|| y + j * times < -2);
						times++;
					}
				}
			}
//			System.out.println("adj " + adj + " v at " + s.getKey().getKey() + " " + s.getKey().getValue());
			if (take) {
				if (adj == 0 && s.getValue() == 'L') {
					s.setValue('o');
					change = true;
				}
			} else {
				if (adj > 4 && s.getValue() == '#') {
					s.setValue('l');
					change = true;
				}
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
				c = ferry.get(Map.entry(j, i));
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
