package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class L20a {

	public static void run(String ext) throws IOException {
		int SIZE = ext.equals("1") ? 9 : 144;
		List<String> input = Files.readAllLines(Paths.get("./src/AoC2020/L20input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		int row = -1, count = 0;
		Tile[] ti = new Tile[SIZE];

		while (++row < input.size()) {
//			System.out.println("r= " + input.get(i));
			if (input.get(row).contains("Tile")) {
				int a = Integer.valueOf(input.get(row).split(" ")[1].split(":")[0]).intValue();
//				System.out.println("tilecount: " + count + " s " + a);
				ti[count] = new Tile(a);
				for (int j = 0; j < 10; j++) {
					ti[count].data[j] = input.get(++row);
				}
				count++;
			}
		}

//		System.out.println(ti[0].getBorder().get(0) + " v " + stringValue(ti[0].getBorder().get(0)));
//		System.out.println(ti[0].getBorder().get(1) + " v " + stringValue(ti[0].getBorder().get(1)));
//		System.out.println(ti[0].getBorder().get(2) + " v " + stringValue(ti[0].getBorder().get(2)));
//		System.out.println(ti[0].getBorder().get(3) + " v " + stringValue(ti[0].getBorder().get(3)));
//		System.out.println(ti[0].getBorder().get(4) + " v " + stringValue(ti[0].getBorder().get(4)));
//		System.out.println(ti[0].getBorder().get(5) + " v " + stringValue(ti[0].getBorder().get(5)));
//		System.out.println(ti[0].getBorder().get(6) + " v " + stringValue(ti[0].getBorder().get(6)));
//		System.out.println(ti[0].getBorder().get(7) + " v " + stringValue(ti[0].getBorder().get(7)));
//		System.out.println(ti[0].getBorder().get(0) + " v " + stringValue(ti[0].getBorder().get(0)));

		List<List<String>> matrix = new ArrayList<>();
		List<Set<Integer>> neigbours = new ArrayList<>();
		for (int i = 0; i < SIZE; i++) {
			matrix.add(ti[i].getBorder());
			neigbours.add(new HashSet<Integer>());
		}

//		ti[0].print();

//		System.out.println(matrix);

		int yt = 0, it = 0;
		for (List<String> a : matrix) {
			yt++;
			for (String b : a) {
				for (List<String> c : matrix) {
					it++;
					if (c.contains(b)) {
//						System.out.println("hit " + b + "yt= " + yt + "it= " +it);
						if (yt != it) {
							neigbours.get(yt - 1).add(it - 1);
						}
					}
				}
				it = 0;
			}
		}
//		System.out.println(neigbours);

		int ord = 0;
		long prod = 1L;
		for (Set<Integer> c : neigbours) {
			if (c.size() < 3) {
//				System.out.println(c);
				prod *= ti[ord].getTile();
			}
			ord++;
		}
		System.out.println("prod= " + prod);

	}

	public static int stringValue(String in) {
		int ut = 0, vp = 512;
		for (int i = 0; i < 10; i++) {
			if (in.charAt(i) == '#') {
				ut += vp;
			}
			vp /= 2;
		}
		return ut;
	}
}