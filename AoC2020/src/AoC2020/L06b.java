package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class L06b {
	private static List<String> answer;

	public static void run(String ext) {
		try {
			answer = Files.readAllLines(Paths.get("./src/AoC2020/L06input" + ext + ".txt")).stream()
					.map(String::valueOf).collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Knas med filimport: " + e);
		}

		int a = 0, b = 0;
		String tmp = "", tm = "";

		do {
			tm = answer.get(a);

			if (tm.length() > 0) {
				tmp = tmp + ":" + tm;
			}

			if (tm.equals("") || a == answer.size() - 1) {
				b += countSame(tmp.substring(1));
				tmp = "";
			}
			a++;
		} while (a < answer.size());
		System.out.println("Sum: " + b);
	}

	private static String intersectionStrings(String a, String b) {
		List<String> sList = new ArrayList<>();
		List<String> aList = Arrays.asList(a.split(""));
		List<String> bList = Arrays.asList(b.split(""));
		sList.addAll(aList);
		sList.addAll(bList);
		sList.retainAll(aList);
		sList.retainAll(bList);

		return sList.stream().distinct().collect(Collectors.joining());
	}

	private static int countSame(String p) {
		String a[] = p.split(":"), tmp = "";
		boolean first = true;

		for (String b : a) {
			if (first) {
				tmp = b;
				first = false;
			} else {
//				System.out.println("merge: " + tmp + " med " + b);
				tmp = intersectionStrings(tmp, b);
			}
		}
//		System.out.println("merge: " + tmp );
		return tmp.length();
	}
}
