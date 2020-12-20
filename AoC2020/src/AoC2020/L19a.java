package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class L19a {
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

		List<String> all = generate(0);
//		System.out.println(all.size());
		int count = 0;
		for (String l : candidates) {
			if (all.contains(l)) {
				count++;
			}
		}
		System.out.println("Matches: " + count);
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
//				System.out.println(children);
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
