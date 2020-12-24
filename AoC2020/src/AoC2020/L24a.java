package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class L24a {

	public static void run(String ext) throws IOException {
		List<String> input = Files.readAllLines(Paths.get("./src/AoC2020/L24input" + ext + ".txt")).stream()
				.collect(Collectors.toList());
//		One way to represent the data would be to think of it like this:
//
//			a-b-c-d-e-
//			-f-g-h-i-j
//			k-l-m-n-o-
//			-p-q-r-s-t
//			u-v-w-x-y-
//			The dashes are null locations -- they exist in the array, but do not represent any hexagon. Here, hexagon m is connected to hexagons c, g, h, q, r, w. Once you are ok with that representation, you can make it more compact by removing the null locations:
//
//			abcde
//			fghij
//			klmno
//			pqrst
//			uvwxy
//
//		       -1,-1     0,-1
//         -1,0      0,0      1,0	
//		        -1,1      0,1
//		
		Map<Map.Entry<Integer, Integer>, Boolean> floor = new HashMap<>();
//		System.out.println(getXY(Map.entry(0,1), "e"));
//		System.out.println(getXY(Map.entry(0,1), "w"));
//		System.out.println(getXY(Map.entry(0,1), "nw"));
//		System.out.println(getXY(Map.entry(0,1), "ne"));
//		System.out.println(getXY(Map.entry(0,1), "sw"));
//		System.out.println(getXY(Map.entry(0,1), "se"));

		// parse koord för
		// e, se, sw, w, nw, and ne
		int x = 0, y = 0;
		Map.Entry<Integer, Integer> StartPos = Map.entry(0, 0);

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
		System.out.println("Black tiles: " + floor.entrySet().stream().filter(t -> t.getValue() == true).count());
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
