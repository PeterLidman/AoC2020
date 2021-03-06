package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class L03b {
	private static List<String> slope;

	public static void run(String ext) {
		try {
			slope = Files.readAllLines(Paths.get("./src/AoC2020/L03input" + ext + ".txt")).stream().map(String::valueOf)
					.collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Knas med filimport: " + e);
		}
//		System.out.println(pitch(1, 1));
//		System.out.println(pitch(3, 1));
//		System.out.println(pitch(5, 1));
//		System.out.println(pitch(7, 1));
//		System.out.println(pitch(1, 2));
		System.out.println("encounters: " + 1L * pitch(1, 1) * pitch(3, 1) * pitch(5, 1) * pitch(7, 1) * pitch(1, 2));
	}

	private static int pitch(int right, int down) {
		int a, b = 0;

		for (a = 0; (a < slope.size()); a += down) {
//			System.out.println(encounter(slope.get(a), a*3));
			if (encounter(slope.get(a), a * right / down)) {
				b++;
			}
		}
		return b;
	}

	private static boolean encounter(String trees, int pos) {
		int modPos = pos, l = trees.length();

		if (pos >= l) {
			modPos = pos % l;
		}
		if (trees.charAt(modPos) == '#') {
			return true;
		}
		return false;
	}
}
