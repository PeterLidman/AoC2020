package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class L14b {
	private static List<String> dock;
	private static HashMap<Long, Long> mem;

	public static void run(String ext) throws IOException {
		dock = Files.readAllLines(Paths.get("./src/AoC2020/L14input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		int i = -1;
		String mask = "", in[];
		long pos, value, pow[] = new long[36];
		mem = new HashMap<>();

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

				String newMask = "";
				for (int j = 0; j < mask.length(); j++) {
					if ((pos & pow[j]) > 0) {
						String a = mask.charAt(j) == 'X' ? "X" : "1";
						newMask = newMask + a;
					} else {
						newMask = newMask + mask.charAt(j);
					}
				}
//				System.out.println(newMask);
				recursiveAdd(newMask, value);
			}
		}
		System.out.println("sum = " + mem.values().stream().reduce((long) 0, Long::sum));
	}

	private static void recursiveAdd(String newMask, long value) {
		boolean found = false;
		for (int j = 0; j < newMask.length(); j++) {
			if (newMask.charAt(j) == 'X') {
				found = true;
				recursiveAdd(newMask.substring(0, j) + "1" + newMask.substring(j + 1), value);
				recursiveAdd(newMask.substring(0, j) + "0" + newMask.substring(j + 1), value);
				break;
			}
		}
		if (!found) {
			mem.put(Long.parseLong(newMask, 2), value);
		}
	}
}