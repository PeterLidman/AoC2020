package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.Collectors;

public class L22a {

	public static void run(String ext) throws IOException {
		List<String> input = Files.readAllLines(Paths.get("./src/AoC2020/L22input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		ArrayDeque<Integer> a = new ArrayDeque<>();
		ArrayDeque<Integer> b = new ArrayDeque<>();

		int row = 0;
		while (input.get(++row).length() > 0) {
			a.add(Integer.valueOf(input.get(row)).intValue());
		}
		row++;
		while (input.size() > ++row) {
			b.add(Integer.valueOf(input.get(row)).intValue());
		}
//		System.out.println(a);
//		System.out.println(b);

		while (a.size() > 0 && b.size() > 0)
			if (a.peekFirst() > b.peekFirst()) {
				a.add(a.removeFirst());
				a.add(b.removeFirst());
			} else {
				b.add(b.removeFirst());
				b.add(a.removeFirst());
			}
		if (a.size() > 0) {
			System.out.println("score: " + score(a));
		} else {
			System.out.println("score: " + score(b));
		}
	}

	private static long score(ArrayDeque<Integer> a) {
		int m = 1;
		long ret = 0;
		while (a.size() > 0) {
			ret += a.removeLast() * m++;
		}
		return ret;
	}
}