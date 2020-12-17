package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class L17a {
	private static List<String> init;
	static Set<ITri> space;

	public static void run(String ext) throws IOException {
		init = Files.readAllLines(Paths.get("./src/AoC2020/L17input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		space = new HashSet<>();
		int i = -1;
		while (++i < init.size()) {
			for (int j = 0; j < init.get(i).length(); j++) {
				if (init.get(i).substring(j, j + 1).equals("#")) {
					space.add(new ITri(j, i, 0));
				}
			}
		}
//		printz(0);
		cycle();
//		printz(-1);
//		printz(0);
//		printz(1);
		cycle();
//		printz(-2);
//		printz(-1);
//		printz(0);
//		printz(1);
//		printz(2);
		cycle();
//		printz(-2);
//		printz(-1);
//		printz(0);
//		printz(1);
//		printz(2);
		cycle();
		cycle();
		cycle();
		System.out.println(space.size());
	}

	private static void cycle() {
		Set<ITri> newSpace = new HashSet<>();
		Set<ITri> observe = new HashSet<>();

		for (ITri a : space) {
			for (int x = -1; x < 2; x++) {
				for (int y = -1; y < 2; y++) {
					for (int z = -1; z < 2; z++) {
						observe.add(new ITri(a.getFirst() + x, a.getSecond() + y, a.getThird() + z));
					}
				}
			}
		}
//		System.out.println(observe);
		for (ITri a : observe) {
			int neighbors = 0;
			for (int x = -1; x < 2; x++) {
				for (int y = -1; y < 2; y++) {
					for (int z = -1; z < 2; z++) {
						if (x == 0 && y == 0 && z == 0) {
							continue;
						}
						if (space.contains(new ITri(a.getFirst() + x, a.getSecond() + y, a.getThird() + z))) {
							neighbors++;
						}
					}
				}
			}
			if (space.contains(new ITri(a.getFirst(), a.getSecond(), a.getThird()))) {
				if (neighbors == 2 || neighbors == 3) {
					newSpace.add(new ITri(a.getFirst(), a.getSecond(), a.getThird()));
				}
			} else {
				if (neighbors == 3) {
					newSpace.add(new ITri(a.getFirst(), a.getSecond(), a.getThird()));
				}
			}
		}
		space = newSpace;
	}

	private static void printz(int z) {
		int maxx = 0, maxy = 0, minx = 0, miny = 0;

		for (ITri a : space) {
			maxx = Math.max(maxx, a.getFirst());
			minx = Math.min(minx, a.getFirst());
			maxy = Math.max(maxy, a.getSecond());
			miny = Math.min(miny, a.getSecond());
		}

		for (int y = miny; y <= maxy; y++) {
			for (int x = minx; x <= maxx; x++) {
				if (space.contains(new ITri(x, y, z))) {
					System.out.print('#');
				} else {
					System.out.print('.');
				}
			}
			System.out.println();
		}
	}
}