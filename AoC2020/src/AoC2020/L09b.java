package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class L09b {
	private static List<Long> code;
	private static int PREAMBLE;
	private static long weakness, weakNr;

	public static void run(String ext) throws IOException {
		PREAMBLE = 25 - 20 * Integer.valueOf(ext);
		code = Files.readAllLines(Paths.get("./src/AoC2020/L09input" + ext + ".txt")).stream().map(Long::valueOf)
				.collect(Collectors.toList());

		int adress = PREAMBLE;
		System.out.println("PREAMBLE=" + PREAMBLE);
		while (adress < code.size()) {
			if (!check(adress)) {
				break;
			}
			adress++;
		}
		weakNr = code.get(adress);
		System.out.println("First invalid nr: " + weakNr);

		// part 2
		adress = 0;
		while (adress < code.size()) {
			weakness = getWeakness(adress);
			if (weakness > 0L) {
				break;
			}
			adress++;
		}
		System.out.println("The weakness nr: " + weakness);
	}

	private static long getWeakness(int at) {
		long ret = code.get(at).longValue();
		long max = ret, min = ret, v;
		int pos = at;

		while (ret < weakNr) {
			pos++;
			v = code.get(pos).longValue();
			ret += v;
			max = Math.max(max, v);
			min = Math.min(min, v);
			if (ret == weakNr) {
				return max + min;
			}
		}
		return 0L;
	}

	private static boolean check(int at) {
		for (int i = at - PREAMBLE; i < at - 1; i++) {
			Long lookFor = code.get(at) - code.get(i);
			if (lookFor > 0) {// finns inga negativa tal
				for (int j = i + 1; j < at; j++) {
					if (lookFor.longValue() == code.get(j).longValue()) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
