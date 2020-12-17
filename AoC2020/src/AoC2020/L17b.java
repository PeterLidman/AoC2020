package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class L17b {
	private static List<String> init;
	static Set<IQuad> space;

	public static void run(String ext) throws IOException {
		init = Files.readAllLines(Paths.get("./src/AoC2020/L17input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		space = new HashSet<>();
		int i = -1;
		while (++i < init.size()) {
			for (int j = 0; j < init.get(i).length(); j++) {
				if (init.get(i).substring(j, j + 1).equals("#")) {
					space.add(new IQuad(j, i, 0, 0));
				}
			}
		}
//		printzw(0, 0);
		cycle();
//		printzw(-1, -1);
//		printzw(0, -1);
//		printzw(1, -1);
//		printzw(-1, 0);
//		printzw(0,0);
//		printzw(1,0);
//		printzw(-1,1);
//		printzw(0,1);
//		printzw(1,1);
		cycle();		
//		printzw(-2,0);
//		printzw(2,0);
//		printzw(0,2);
//		printzw(1,2);
//		printzw(2,2);
		cycle();
		cycle();
		cycle();
		cycle();
		System.out.println(space.size());
	}

	private static void cycle() {
		Set<IQuad> newSpace = new HashSet<>();
		Set<IQuad> observe = new HashSet<>();

		for (IQuad a : space) {
			for (int x = -1; x < 2; x++) {
				for (int y = -1; y < 2; y++) {
					for (int z = -1; z < 2; z++) {
						for (int w = -1; w < 2; w++) {
							observe.add(new IQuad(a.getFirst() + x, a.getSecond() + y, a.getThird() + z,
									a.getFourth() + w));
						}
					}
				}
			}
		}
//		System.out.println(observe);
		for (IQuad a : observe) {
			int neighbors = 0;
			for (int x = -1; x < 2; x++) {
				for (int y = -1; y < 2; y++) {
					for (int z = -1; z < 2; z++) {
						for (int w = -1; w < 2; w++) {
							if (x == 0 && y == 0 && z == 0 && w == 0) {
								continue;
							}
							if (space.contains(new IQuad(a.getFirst() + x, a.getSecond() + y, a.getThird() + z,
									a.getFourth() + w))) {
								neighbors++;
							}
						}
					}
				}
			}
			if (space.contains(new IQuad(a.getFirst(), a.getSecond(), a.getThird(), a.getFourth()))) {
				if (neighbors == 2 || neighbors == 3) {
					newSpace.add(new IQuad(a.getFirst(), a.getSecond(), a.getThird(), a.getFourth()));
				}
			} else {
				if (neighbors == 3) {
					newSpace.add(new IQuad(a.getFirst(), a.getSecond(), a.getThird(), a.getFourth()));
				}
			}
		}
		space = newSpace;
	}

	private static void printzw(int z, int w) {
		System.out.println("z=" + z + ", w=" + w);
		int maxx = -9, maxy = -9, minx = 9, miny = 9;

		for (IQuad a : space) {
			maxx = Math.max(maxx, a.getFirst());
			minx = Math.min(minx, a.getFirst());
			maxy = Math.max(maxy, a.getSecond());
			miny = Math.min(miny, a.getSecond());
		}

		for (int y = miny; y <= maxy; y++) {
			for (int x = minx; x <= maxx; x++) {
				if (space.contains(new IQuad(x, y, z, w))) {
					System.out.print('#');
				} else {
					System.out.print('.');
				}
			}
			System.out.println();
		}
	}
}