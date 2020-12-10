package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class L10a {
	private static List<Integer> jolts;

	public static void run(String ext) throws IOException {
		jolts = Files.readAllLines(Paths.get("./src/AoC2020/L10input" + ext + ".txt")).stream().map(Integer::valueOf)
				.collect(Collectors.toList());

		int i = -1, prev = 0;
		int[] joltDiff = new int[3];
		Collections.sort(jolts);

		while (++i < jolts.size()) {
			int adapter = jolts.get(i).intValue();
			joltDiff[adapter - prev - 1]++;
			prev = adapter;
		}
		joltDiff[2]++;//sista hoppet
		System.out.println("Answer: " + joltDiff[2] * joltDiff[0]);
	}
}
