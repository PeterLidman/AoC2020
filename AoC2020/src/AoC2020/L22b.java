package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class L22b {

	public static void run(String ext) throws IOException {
		List<String> input = Files.readAllLines(Paths.get("./src/AoC2020/L22input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		ArrayDeque<Integer> a = new ArrayDeque<>();
		ArrayDeque<Integer> b = new ArrayDeque<>();
		int row = 0;
		while (input.get(++row).length() > 0) {
			a.add(Integer.valueOf(input.get(row)).intValue());
		}
		row++;
		while (input.size() > ++row) {
			b.add(Integer.valueOf(input.get(row)).intValue());
		}
//		System.out.println(a);
//		System.out.println(b);
//		aSet.add(a);
//		bSet.add(b);
//		System.out.println(aSet);
//		System.out.println(bSet);
//		System.out.println(bSet.contains(b));
//		System.out.println(bSet.contains(a));
		Integer drawA, drawB;
		Set<String> bSet = new HashSet<>();
		Set<String> aSet = new HashSet<>();
		while (a.size() > 0 && b.size() > 0) {
//			System.out.println(aSet);
//			System.out.println(bSet);
			if (!(aSet.add(a.toString()) && bSet.add(b.toString()))) {
//				System.out.println(a);
//				System.out.println(b);
				System.out.println("infinite");
				b.clear();
				break;
			}
			drawA = a.removeFirst();
			drawB = b.removeFirst();
//			System.out.println("draw" + drawA);
//			System.out.println(drawB);			
//			System.out.println(a.toString());
//			System.out.println(b.toString());
			if (a.size() >= drawA && b.size() >= drawB) {
				if (recursivePlay(a, drawA, b, drawB)) {
					a.add(drawA);
					a.add(drawB);
				} else {
					b.add(drawB);
					b.add(drawA);
				}
			} else {
				if (drawA > drawB) {
					a.add(drawA);
					a.add(drawB);
				} else {
					b.add(drawB);
					b.add(drawA);
				}
			}
//			System.out.println("slutet:" + a);
//			System.out.println("slutet:" + b);
		}
		if (a.size() > 0) {
			System.out.println("score: " + score(a));
		} else {
			System.out.println("score: " + score(b));
		}
	}

	private static boolean recursivePlay(ArrayDeque<Integer> ina, int indrawA, ArrayDeque<Integer> inb, int indrawB) {
//		System.out.println("orga" + ina);
//		System.out.println("orgb" + inb);
		ArrayDeque<Integer> a = new ArrayDeque<>(ina);
		ArrayDeque<Integer> b = new ArrayDeque<>(inb);
		for (int i = 0; i < ina.size() - indrawA; i++) {
			a.removeLast();
		}
		for (int i = 0; i < inb.size() - indrawB; i++) {
			b.removeLast();
		}
//		System.out.println("reca" + a);
//		System.out.println("recb" + b);
//		System.out.println("orga" + ina);
//		System.out.println("orgb" + inb);
		Integer drawA, drawB;
		Set<String> bSet = new HashSet<>();
		Set<String> aSet = new HashSet<>();
		while (a.size() > 0 && b.size() > 0) {
//			System.out.println(aSet);
//			System.out.println(bSet);
			if (!(aSet.add(a.toString()) && bSet.add(b.toString()))) {
//				System.out.println(a);
//				System.out.println(b);
//				System.out.println("infinite recursive");
				b.clear();
				break;
			}
//			System.out.println(a);
//			System.out.println(b);
			drawA = a.removeFirst();
			drawB = b.removeFirst();
//			System.out.println("draw " + drawA);
//			System.out.println("draw " + drawB);
			if (a.size() >= drawA && b.size() >= drawB) {
//				System.out.println("deeper rec");
				if (recursivePlay(a, drawA, b, drawB)) {
					a.add(drawA);
					a.add(drawB);
				} else {
					b.add(drawB);
					b.add(drawA);
				}
			} else {
//				System.out.println("cont.");
				if (drawA > drawB) {
//					System.out.println("a vann");
					a.add(drawA);
					a.add(drawB);
				} else {
//					System.out.println("b vann");
					b.add(drawB);
					b.add(drawA);
				}
			}
		}
//		System.out.println("over");
		return a.size() > 0;
	}

	private static long score(ArrayDeque<Integer> a) {
		int m = 1;
		long ret = 0;
		while (a.size() > 0) {
			ret += a.removeLast() * m++;
		}
		return ret;
	}
}