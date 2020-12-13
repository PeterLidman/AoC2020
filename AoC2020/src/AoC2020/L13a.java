package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class L13a {
	private static List<String> notes;

	public static void run(String ext) throws IOException {
		notes = Files.readAllLines(Paths.get("./src/AoC2020/L13input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		long earliest = Long.valueOf(notes.get(0)).longValue();
//		System.out.println("earliest " + earliest);
		List<String> interval = Stream.of(notes.get(1).split(",")).filter(c -> !c.equals("x"))
				.collect(Collectors.toList());
//		System.out.println("collect " + interval);

		long best = 9999999, bestBus = 0;
		for (String dep : interval) {
			long i = Long.valueOf(dep).longValue();
			long b = (i - (earliest % i));
			if (best > b) {
				best = b;
				bestBus = i;
			}
//			System.out.println("dep " + dep + " modulodist " + b);
		}
//		System.out.println("b " + best + " i " + bestBus + " *" + best * bestBus);
		System.out.println("ID*wait= " + (best * bestBus));
	}
}