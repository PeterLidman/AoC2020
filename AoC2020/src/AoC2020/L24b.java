package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class L24b {
	private static Map<Map.Entry<Integer, Integer>, Boolean> floor;
	private static List<Map.Entry<Integer, Integer>> flipToBlack;
	private static List<Map.Entry<Integer, Integer>> flipToWhite;

	public static void run(String ext) throws IOException {
		List<String> input = Files.readAllLines(Paths.get("./src/AoC2020/L24input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		floor = new HashMap<>();
		for (String a : input) {
			Map.Entry<Integer, Integer> curPos = Map.entry(0, 0);
			String s = a;
			Boolean bow;
			while (s.length() > 0) {
				if (s.charAt(0) == 'w') {
					curPos = getXY(curPos, s.substring(0, 1));
					s = s.substring(1);
					continue;
				}
				if (s.charAt(0) == 'e') {
					curPos = getXY(curPos, s.substring(0, 1));
					s = s.substring(1);
					continue;
				}
				if (s.charAt(0) == 'n' && s.charAt(1) == 'w') {
					curPos = getXY(curPos, s.substring(0, 2));
					s = s.substring(2);
					continue;
				}
				if (s.charAt(0) == 'n' && s.charAt(1) == 'e') {
					curPos = getXY(curPos, s.substring(0, 2));
					s = s.substring(2);
					continue;
				}
				if (s.charAt(0) == 's' && s.charAt(1) == 'w') {
					curPos = getXY(curPos, s.substring(0, 2));
					s = s.substring(2);
					continue;
				}
				if (s.charAt(0) == 's' && s.charAt(1) == 'e') {
					curPos = getXY(curPos, s.substring(0, 2));
					s = s.substring(2);
					continue;
				}
			}
//			System.out.println(curPos);
			bow = floor.get(curPos);
			if (bow == null) {// white -> black
				floor.put(curPos, true);
			} else { // whatever -> !whatever
				floor.put(curPos, !bow);
			}
		}
//		System.out.println(floor);
//		System.out.println("Black tiles: " + floor.entrySet().stream().filter(t -> t.getValue() == true).count());
		for (int i = 0; i < 100; i++) {
			flipToWhite = new ArrayList<>();
			for (Entry<Entry<Integer, Integer>, Boolean> a : floor.entrySet()) {
				encloseWithWhite(a.getKey());
			}
			for (Entry<Integer, Integer> a : flipToWhite) {
				floor.put(a, false);
			}
			flipToBlack = new ArrayList<>();
			flipToWhite = new ArrayList<>();
			int cadj;
			for (Entry<Entry<Integer, Integer>, Boolean> a : floor.entrySet()) {
//				System.out.println(countAdjacent(a.getKey()));
				cadj = countAdjacent(a.getKey());
				if (a.getValue()) { // black->white 0 or >2
					if (cadj == 0 || cadj > 2) {
						flipToWhite.add(a.getKey());
					}
				} else { // 2 turn black
					if (cadj == 2) {
						flipToBlack.add(a.getKey());
					}
				}
			}
			for (Entry<Integer, Integer> a : flipToWhite) {
				floor.put(a, false);
			}
			for (Entry<Integer, Integer> a : flipToBlack) {
				floor.put(a, true);
			}
//			System.out.println(flipToBlack);
//			System.out.println(flipToWhite);
		}
		System.out.println("Black tiles day: " + floor.entrySet().stream().filter(t -> t.getValue() == true).count());
	}

	private static void encloseWithWhite(Entry<Integer, Integer> pos) {
		Boolean bp = false;

		bp = floor.get(getXY(pos, "e"));
		if (bp == null) {
			flipToWhite.add(getXY(pos, "e"));
		}
		bp = floor.get(getXY(pos, "se"));
		if (bp == null) {
			flipToWhite.add(getXY(pos, "se"));
		}
		bp = floor.get(getXY(pos, "sw"));
		if (bp == null) {
			flipToWhite.add(getXY(pos, "sw"));
		}
		bp = floor.get(getXY(pos, "w"));
		if (bp == null) {
			flipToWhite.add(getXY(pos, "w"));
		}
		bp = floor.get(getXY(pos, "nw"));
		if (bp == null) {
			flipToWhite.add(getXY(pos, "nw"));
		}
		bp = floor.get(getXY(pos, "ne"));
		if (bp == null) {
			flipToWhite.add(getXY(pos, "ne"));
		}
	}

	private static int countAdjacent(Map.Entry<Integer, Integer> pos) {
		int adj = 0;
		Boolean bp = false;

		bp = floor.get(getXY(pos, "e"));
		if (bp != null) {
			adj += bp ? 1 : 0;
		}
		bp = floor.get(getXY(pos, "se"));
		if (bp != null) {
			adj += bp ? 1 : 0;
		}
		bp = floor.get(getXY(pos, "sw"));
		if (bp != null) {
			adj += bp ? 1 : 0;
		}
		bp = floor.get(getXY(pos, "w"));
		if (bp != null) {
			adj += bp ? 1 : 0;
		}
		bp = floor.get(getXY(pos, "nw"));
		if (bp != null) {
			adj += bp ? 1 : 0;
		}
		bp = floor.get(getXY(pos, "ne"));
		if (bp != null) {
			adj += bp ? 1 : 0;
		}
		return adj;
	}

	private static Map.Entry<Integer, Integer> getXY(Map.Entry<Integer, Integer> pos, String dir) {
		Map.Entry<Integer, Integer> newPos = null;
		int odd = pos.getValue() % 2 == 0 ? 0 : 1;
		switch (dir) {
		case "w":
			newPos = Map.entry(pos.getKey() - 1, pos.getValue());
			break;
		case "e":
			newPos = Map.entry(pos.getKey() + 1, pos.getValue());
			break;
		case "se":
			newPos = Map.entry(pos.getKey() + odd, pos.getValue() + 1);
			break;
		case "sw":
			newPos = Map.entry(pos.getKey() - 1 + odd, pos.getValue() + 1);
			break;
		case "nw":
			newPos = Map.entry(pos.getKey() - 1 + odd, pos.getValue() - 1);
			break;
		case "ne":
			newPos = Map.entry(pos.getKey() + odd, pos.getValue() - 1);
			break;
		default:
			System.out.println("knepig dir");
		}
		return newPos;
	}
}
