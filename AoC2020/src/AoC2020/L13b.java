package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class L13b {
	private static List<String> notes;

	public static void run(String ext) throws IOException {
		notes = Files.readAllLines(Paths.get("./src/AoC2020/L13input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

//		long earliest = Long.valueOf(notes.get(0)).longValue();
//		System.out.println("earliest " + earliest);
		List<String> interval = Stream.of(notes.get(1).split(",")).collect(Collectors.toList());
//		System.out.println("collect " + interval);

		int pos = 0;
		long min = 0, prod = 1;
		for (String dep : interval) {
			if (dep.equals("x")) {
				pos++;
				continue;
			}
			int b = Integer.valueOf(dep).intValue();

			for (; (min + pos) % b != 0; min += prod) {
			}
			prod *= b;
//			System.out.println("min " + min + " prod " + prod);
//			System.out.println("t=" + pos + " bus " + dep);
			pos++;
		}
		System.out.println("First time: " + min);
		
//		long min = 0;// körning som lyckades innan uppsnyggning av koden
//		long prod = 1;
//		
//		for (; (min + 23) % 431 != 0; min += prod) {
//		}
//		prod *= 431;
//		System.out.println("min " + min + " prod " + prod);
//		
//		for (; (min + 54) % 409 != 0; min += prod) {
//		}
//		prod *= 409;
//		System.out.println("min " + min + " prod " + prod);
//		
//		for (; (min + 64) % 41 != 0; min += prod) {
//		}
//		prod *= 41;
//		System.out.println("min " + min + " prod " + prod);
//
//		for (; (min + 17) % 37 != 0; min += prod) {
//		}
//		prod *= 37;
//		System.out.println("min " + min + " prod " + prod);
//		
//		for (; (min + 83) % 29 != 0; min += prod) {
//		}
//		prod *= 29;
//		System.out.println("min " + min + " prod " + prod);
//		
//		for (; (min + 0) % 23 != 0; min += prod) {
//		}
//		prod *= 23;
//		System.out.println("min " + min + " prod " + prod);
//		
//		for (; (min + 42) % 19 != 0; min += prod) {
//		}
//		prod *= 19;
//		System.out.println("min " + min + " prod " + prod);
//		
//		for (; (min + 37) % 17 != 0; min += prod) {
//		}
//		prod *= 17;
//		System.out.println("min " + min + " prod " + prod);
//		
//		for (; (min + 36) % 13 != 0; min += prod) {
//		}
//		prod *= 13;
//		System.out.println("min " + min + " prod " + prod);

//		long min = 0;//enkla testet
//		long prod = 1;
//		for (; (min + 0) % 17 != 0; min += prod) {
//		}
//		prod *= 17;
//		System.out.println("min " + min + " prod " + prod);
//		for (; (min + 2) % 13 != 0; min += prod) {
//		}
//		prod *= 13;
//		System.out.println("min " + min + " prod " + prod);
//		for (; (min + 3) % 19 != 0; min += prod) {
//		}
//		prod *= 19;
//		System.out.println("min " + min + " prod " + prod);
	}
}
