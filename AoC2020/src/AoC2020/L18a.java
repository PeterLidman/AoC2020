package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class L18a {
	private static List<String> expr;

	public static void run(String ext) throws IOException {
		expr = Files.readAllLines(Paths.get("./src/AoC2020/L18input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		int i = -1;
		long totSum = 0;
		while (++i < expr.size()) {
//			System.out.println("sum " + pEval(expr.get(i)));
			totSum += pEval(expr.get(i));
		}
		System.out.println("Total sum= " + totSum);
	}

	private static long pEval(String in) {
		String reduced = in;
		int start = 0, stop = 0;

		while (reduced.indexOf(')') > 0) {
			for (int i = 0; i < reduced.length(); i++) {
				if (reduced.charAt(i) == '(') {
					start = i;
				}
				if (reduced.charAt(i) == ')') {
					stop = i;
					break;
				}
			}
			reduced = reduced.substring(0, start) + eval(reduced.substring(start + 1, stop))
					+ reduced.substring(stop + 1);
		}
		return eval(reduced);
	}

	private static long eval(String in) {
		String t[] = in.split(" ");
		long sum = Long.valueOf(t[0]).longValue();
		int p = 0;

		while (++p < t.length) {
			if (t[p].charAt(0) == '*') {
				sum *= Long.valueOf(t[++p]).longValue();
			} else {// +
				sum += Long.valueOf(t[++p]).longValue();
			}
		}
		return sum;
	}
}