package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.Collectors;

public class L23a {

	public static void run(String ext) throws IOException {
		ArrayDeque<Integer> a = new ArrayDeque<>();
		ArrayDeque<Integer> three = new ArrayDeque<>();

//		String label = "389125467";
		String label = "389547612";
		for (int i = 0; i < label.length(); i++) {
			a.add(Integer.valueOf(label.charAt(i) - 48).intValue());
		}
//		System.out.println(a);
		int cc = 0, dest;

		for (int c = 0; c < 100; c++) {
//			System.out.println(a);
			cc = a.peekFirst();
//			System.out.println("current: " + cc);
			a.addLast(a.removeFirst());
			three.add(a.removeFirst());
			three.add(a.removeFirst());
			three.add(a.removeFirst());
//			System.out.println(three);

			dest = cc - 1;
			while (!a.contains(dest)) {
				dest--;
				if (dest < 0) {
					dest = 9;
				}
			}

//			System.out.println("dest: " + dest);

			while (dest != a.peekFirst()) {
				a.add(a.removeFirst());
			}
			a.add(a.removeFirst());

//			System.out.println(a.peekFirst());

			a.addFirst(three.removeLast());
			a.addFirst(three.removeLast());
			a.addFirst(three.removeLast());

			// a.addFirst(a.removeLast());

			while (cc != a.peekFirst()) {
				a.add(a.removeFirst());
			}
			a.addLast(a.removeFirst());
		}
		while (1 != a.peekFirst()) {
			a.add(a.removeFirst());
		}
		System.out.println(a);
	}
}