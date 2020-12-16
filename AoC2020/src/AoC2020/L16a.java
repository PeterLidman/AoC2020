package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class L16a {
	private static List<String> rules;
	private static List<List<Integer>> tickets;

	public static void run(String ext) throws IOException {
		List<String> in = Files.readAllLines(Paths.get("./src/AoC2020/L16input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		rules = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			rules.add(in.get(i));
		}
		tickets = new ArrayList<>(new ArrayList<>());
		tickets.add(Stream.of(in.get(22).split(",")).map(Integer::valueOf).collect(Collectors.toList()));
		for (int i = 25; i < 268; i++) {
			tickets.add(Stream.of(in.get(i).split(",")).map(Integer::valueOf).collect(Collectors.toList()));
		}

		List<Integer> allValues = new ArrayList<>();
		List<Integer> invalidValues = new ArrayList<>();
		for (int i = 1; i < tickets.size(); i++) {// alla värden utom egna
			for (int value : tickets.get(i)) {
				allValues.add(value);
			}
		}

		Pattern p = Pattern.compile("(.*): (\\d+)-(\\d+) or (\\d+)-(\\d+)");
		for (int v : allValues) {
			boolean ok = false;

			for (String rule : rules) {
				Matcher m = p.matcher(rule);
				if (!m.find()) {
					System.out.println("NO MATCH");
					break;
				}
//				System.out.println(m.group(1) + " (" + m.group(2) + "-" + m.group(3) + " or (" + m.group(4) + "-" + m.group(5) + ")");
				int c1 = Integer.valueOf(m.group(2)).intValue();
				int c2 = Integer.valueOf(m.group(3)).intValue();
				int c3 = Integer.valueOf(m.group(4)).intValue();
				int c4 = Integer.valueOf(m.group(5)).intValue();

				if ((c1 <= v && c2 >= v) || (c3 <= v && c4 >= v)) {
					ok = true;
					break;
				}
			}
			if (!ok) {
				invalidValues.add(v);
			}
		}
//		System.out.println(invalidValues);
		System.out.println("Invalid sum: " + invalidValues.stream().collect(Collectors.summingInt(Integer::intValue)));
	}
}