package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class L03a {
	private static List<String> slope;

	public static void run(String ext) {
		try {
			slope = Files.readAllLines(Paths.get("./src/AoC2020/L03input" + ext + ".txt")).stream().map(String::valueOf)
					.collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Knas med filimport: " + e);
		}

		int a, b = 0;

		for (a = 0; (a < slope.size()); a++) {
//			System.out.println(encounter(slope.get(a), a*3));
			if (encounter(slope.get(a),a*3)) {
				b++;
			}
		}
		System.out.println("encounters: " + b);
	}

	private static boolean encounter(String trees, int pos) {
		int modPos = pos, l = trees.length();
		
		if(pos >= l) {
			modPos = pos % l;
		}
//		System.out.println(modPos);
		if (trees.charAt(modPos) == '#') {
			return true;
		}
		return false;
	}
}
