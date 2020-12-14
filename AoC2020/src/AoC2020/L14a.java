package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class L14a {
	private static List<String> dock;

	public static void run(String ext) throws IOException {
		dock = Files.readAllLines(Paths.get("./src/AoC2020/L14input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		int i = -1;
		String mask = "", in[];
		long pos, value, pow[] = new long[36];
		HashMap<Long, Long> mem = new HashMap<>();

		long p = 34_359_738_368L;
		for (int j = 0; j < 36; j++) {
			pow[j] = p;
			p /= 2L;
		}

		while (++i < dock.size()) {
			in = dock.get(i).split(" = ");
			if (in[0].equals("mask")) {
				mask = in[1];
			} else { // mem
				pos = Long.valueOf(in[0].substring(4, in[0].length() - 1));
				value = Long.valueOf(in[1]);

				for (int j = 0; j < mask.length(); j++) {
					switch (mask.charAt(j)) {
					case '1':
						value = value | pow[j];
						break;
					case '0':
						value = value | pow[j];
						value = value ^ pow[j];
						break;
					default: // x ignore
					}
				}
				mem.put(pos, value);
			}
		}
		System.out.println("sum = " + mem.values().stream().reduce((long) 0, Long::sum));
	}
}