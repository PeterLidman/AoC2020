package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class L07b {
	private static List<String> bags;

	public static void run(String ext) {
		try {
			bags = Files.readAllLines(Paths.get("./src/AoC2020/L07input" + ext + ".txt")).stream().map(String::valueOf)
					.collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Knas med filimport: " + e);
		}

		System.out.println("Found: " + searchBag("shiny gold bag"));
	}

	private static int searchBag(String p) {
//		System.out.println(p);
		int a = 0, sum = 0;

		do {
			if (p.equals(trimSDot(getBag(bags.get(a))))) {
				List<String> bags2 = getBags(bags.get(a));
//				System.out.println(bags2);
				if (bags2.size() > 0) {
					for (String ba : bags2) {
						sum += getNr(ba) * searchBag(stripNumber(ba));
						sum += getNr(ba);
					}
					return sum;
				} else {
					return 0;
				}
			}
			a++;
		} while (a < bags.size());
		return 0;// unreachable
	}

	private static int getNr(String p) {
		return Integer.valueOf(p.split(" ")[0]);
	}

	private static String stripNumber(String p) {
//		System.out.println(p.substring(2));
		return p.substring(2);
	}

	private static String getBag(String p) {
		return p.split(" contain ")[0];
	}

	private static String trimSDot(String p) {
		if (p.endsWith("s.")) {
			return p.substring(0, p.length() - 2);
		}
		if (p.endsWith(".") || p.endsWith("s")) {
			return p.substring(0, p.length() - 1);
		}
		return p;
	}

	private static List<String> getBags(String p) {
		String tmp = p.split(" contain ")[1];
		List<String> ret = new ArrayList<>();

		if (tmp.equals("no other bags.")) {
			return Collections.emptyList();
		}

		for (String a : tmp.split(", ")) {
			ret.add(trimSDot(a));
		}
		return ret;
	}
}
