package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class L19b {
	private static List<String> candidates;
	private static HashMap<Integer, String> rules;

	public static void run(String ext) throws IOException {
		List<String> input = Files.readAllLines(Paths.get("./src/AoC2020/L19input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		rules = new HashMap<>();
		candidates = new ArrayList<>();

		int index = 0;
		String in = input.get(index++);
		while (in.length() > 0) {
			String s[] = in.split(": ");
			rules.put(Integer.valueOf(s[0]).intValue(), s[1]);
			in = input.get(index++);
		}
		while (index < input.size()) {
			in = input.get(index++);
			candidates.add(in);
		}
//		System.out.println(rules);
//		System.out.println(candidates);
		
		// 0 -> 8 11
		// som görs om till 42 42 ... 42 42 eller 42 42 ... 31 31 
		List<String> poss42 = generate(42);
//		System.out.println(poss42);
		List<String> poss31 = generate(31);
//		System.out.println(poss31);
		for (int j = 0; j < candidates.size(); j++) {
			String candi = candidates.get(j);
			String del;

			// börja med 42
			del = candi.substring(0, 8);
			if (!poss42.contains(del)) {
				candidates.remove(candi);
				j--;
			}
			// andra 42
			del = candi.substring(8, 16);
			if (!poss42.contains(del)) {
				candidates.remove(candi);
				j--;
			}

			// sluta med 31
			del = candi.substring(candi.length() - 8);
			if (!poss31.contains(del)) {
				candidates.remove(candi);
				j--;
			}

			// 42 42 ... 31, fler 42 än 31
			del = candi.substring(16, candi.length() - 8);
			int c0 = 0;
			int c1 = 0;
			while (del.length() > 0 && poss42.contains(del.substring(0, 8))) {
				del = del.substring(8);
				c0++;
			}
			while (del.length() > 0 && poss31.contains(del.substring(0, 8))) {
				del = del.substring(8);
				c1++;
			}
			if (del.length() > 0 || c1 > c0) {
				candidates.remove(candi);
				j--;
			}
		}
		System.out.println("Matches: " + candidates.size());
	}

	private static List<String> generate(int in) {
		List<String> result = new ArrayList<>();

		if (rules.get(in).charAt(0) == '"') {
			result.add("" + rules.get(in).charAt(1));
			return result;
		}
		String rule = rules.get(in);
		String[] r = rule.split(" \\| ");
		for (String s : r) {
			List<List<String>> children = new ArrayList<>();
			String[] u = s.split(" ");
			for (String t : u) {
				children.add(generate(Integer.parseInt(t)));
			}
			if (children.size() == 1) {
				result.addAll(children.get(0));
			} else if (children.size() == 2) {
				for (int i = 0; i < children.get(0).size(); i++) {
					for (int j = 0; j < children.get(1).size(); j++) {
						result.add(children.get(0).get(i) + children.get(1).get(j));
					}
				}
			} else if (children.size() > 2) {
				System.out.println(children);
				for (int i = 0; i < children.get(0).size(); i++) {
					for (int j = 0; j < children.get(1).size(); j++) {
						for (int k = 0; k < children.get(2).size(); k++) {
							result.add(children.get(0).get(i) + children.get(1).get(j) + children.get(2).get(k));
						}
					}
				}
			}
		}
		return result;
	}
}
