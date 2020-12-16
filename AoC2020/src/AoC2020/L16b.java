package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class L16b {
	public static void run(String ext) throws IOException {
		List<String> in = Files.readAllLines(Paths.get("./src/AoC2020/L16input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		List<String> rules = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			rules.add(in.get(i));
		}
		List<List<Integer>> tickets = new ArrayList<>(new ArrayList<>());
//		Stream.of(in.get(22).split(",")).forEach(s -> System.out.println(s));
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
//		System.out.println("Invalid sum: " + invalidValues.stream().collect(Collectors.summingInt(Integer::intValue)));

		List<List<Integer>> okTickets = new ArrayList<>(new ArrayList<>());
		for (List<Integer> tick : tickets) { // including our ticket
			boolean pass = true;
			for (int tickv : tick) {
				if (invalidValues.contains(tickv)) {
					pass = false;
					break;
				}
			}
			if (pass) {
				okTickets.add(tick);
			}
		}

//		System.out.println(okTickets);
//		System.out.println(okTickets.size());

		Set<Map.Entry<Integer, String>> combinations = new HashSet<>();
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
//			denna regel måste stämma på alla fält 
			for (int i = 0; i < 20; i++) { // kolla pos
				int v;
				boolean allIsOk = true;
				for (List<Integer> t : okTickets) {
					v = t.get(i);
					if (!((c1 <= v && c2 >= v) || (c3 <= v && c4 >= v))) {
						allIsOk = false;
						break;
					}
				}
				if (allIsOk) {
					combinations.add(Map.entry(i, m.group(1)));
//					System.out.println("Pos= " + i + " is: " + m.group(1));
				}
			}
		}

		long departureProduct=1L;
		int removeI = 0;
		String removeS = "";
		for (int i = 0; i < 20; i++) {// 20 olika fält
			removeI = 0;
			removeS = "";
			for (Entry<Integer, String> c : combinations) {
				String lonely = c.getValue();
				int counter = 0;
				for (Entry<Integer, String> c2 : combinations) {
					if (c2.getValue().equals(lonely)) {
						if (counter++ > 1) {
							break;
						}
					}
				}
				if (counter == 1) {
//					System.out.println(c.getValue());
//					System.out.println(c.getKey());
					removeS = c.getValue();
					removeI = c.getKey();
					if (removeS.substring(0, 3).equals("dep")) {
//						System.out.println(removeI);
						departureProduct *= tickets.get(0).get(removeI);
					}
				}
			}
			int bort = removeI;
			combinations.removeIf(e -> e.getKey() == bort);
		}
		System.out.println("Departure product: " + departureProduct);
	}
}