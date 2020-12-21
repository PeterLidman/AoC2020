package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class L21a {

	public static void run(String ext) throws IOException {
		List<String> ingr = Files.readAllLines(Paths.get("./src/AoC2020/L21input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		Map<String, List<Set<String>>> aif = new HashMap<>();
		List<Set<String>> ingred = new ArrayList<>();

		int i = -1;
		while (++i < ingr.size()) {
//			System.out.println("ingr " + ingr.get(i));
			String s[] = ingr.get(i).split(" \\(contains ");
			Set<String> a = new HashSet<>();
			for (String b : s[0].split(" ")) {
				a.add(b);
			}

			ingred.add(new HashSet<String>(a));

			s[1] = s[1].substring(0, s[1].length() - 1);
			for (String c : s[1].split(", ")) {
				List<Set<String>> b = aif.get(c);
				if (b == null) {
					List<Set<String>> d = new ArrayList<>();
					d.add(a);
					aif.put(c, d);
				} else {
					b.add(a);
					aif.put(c, b);
				}
			}
		}
//		System.out.println(aif);
		Map<String, Set<String>> nyaif = new HashMap<>();
		for (String e : aif.keySet()) {
			List<Set<String>> list = aif.get(e); // för varje allergen, leta upp samband
			Set<String> gem = new HashSet<>(); // ett set med bara gemensamma
			if (list.size() > 1) {
				gem.addAll(list.get(0));
				for (int g = 1; g < list.size(); g++) {
					gem.retainAll(list.get(g));
				}
			} else {
				gem.addAll(list.get(0));
			}
//			System.out.println("rensad:" + e + "innehåll " + gem);
			nyaif.put(e, gem);
		}
//		System.out.println(aif);
//		System.out.println(nyaif);
		Map<String, String> perfMatch = new HashMap<>();
		while (nyaif.size() > 0) {
			for (String e : nyaif.keySet()) {
				if (nyaif.get(e).size() == 1) {
					perfMatch.put(e, nyaif.get(e).stream().findFirst().get());
				}
			}
//			System.out.println(perfMatch);
			for (String e : nyaif.keySet()) {
				if (perfMatch.containsKey(e)) {
					nyaif.remove(e);
					break;
				}
			}
//			System.out.println(nyaif);
			for (String e : nyaif.keySet()) {
				Set<String> gem = new HashSet<>();
				for (String h : nyaif.get(e)) {
					if (!perfMatch.containsValue(h)) {
						gem.add(h);
					}
				}
				nyaif.put(e, gem);
			}
		}
//		System.out.println(aif);
//		System.out.println(nyaif);
//		System.out.println(perfMatch);		

		System.out.println(perfMatch);
//		System.out.println(ingred);

		int count = 0;
		for (Set<String> q : ingred) {
//			System.out.println("eeeee" + q);
			for (String p : q) {
				if (!perfMatch.containsValue(p)) {
//					System.out.println("2222:" + p);
					count++;
				}
			}
		}
		System.out.println("count=" + count);
	}
}