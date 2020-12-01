package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class L01b {
	private static List<Integer> expenses;

	public static void run(String ext) {
		try {
			expenses = Files.readAllLines(Paths.get("./src/AoC2020/L01input" + ext + ".txt")).stream()
					.map(Integer::valueOf).collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Knas med filimport: " + e);
		}

		int a, b, c;
		boolean working = true;

		for (a = 0; (a < expenses.size() - 3) && working; a++) {
			for (b = a + 1; (b < expenses.size() - 2) && working; b++) {
				for (c = b + 1; (c < expenses.size() - 1) && working; c++) {
//					System.out.println(a);
//					System.out.println(b);
//					System.out.println(c);
					if (expenses.get(a) + expenses.get(b) + expenses.get(c) == 2020) {
						working = false;
//						System.out.println(expenses.get(a));
//						System.out.println(expenses.get(b));
//						System.out.println(expenses.get(c));
						System.out.println("Sum " + (expenses.get(a) * expenses.get(b) * expenses.get(c)));
					}
				}
			}
		}
	}
}
