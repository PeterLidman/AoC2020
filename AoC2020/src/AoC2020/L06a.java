package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class L06a {
	private static List<String> answer;

	public static void run(String ext) {
		try {
			answer = Files.readAllLines(Paths.get("./src/AoC2020/L06input" + ext + ".txt")).stream()
					.map(String::valueOf).collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Knas med filimport: " + e);
		}

		int a = 0, b = 0;
		String tmp = "";

		do {
			tmp = tmp + answer.get(a);

			if (answer.get(a).equals("") || a == answer.size() - 1) {
//				System.out.println(tmp);
//				System.out.println(countUnique(tmp));
				b += countUnique(tmp);
				tmp = "";
			}
			a++;
		} while (a < answer.size());

		System.out.println("Sum: " + b);
	}

	private static int countUnique(String p) {
		return (int) Arrays.asList(p.split("")).stream().distinct().count();
	}
}
