package AoC2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class L23b {
	static int moves = 10_000_000;
	static int maxNo = 1_000_000;

	public static void run(String ext) throws IOException {
		List<Integer> in = new ArrayList<>();
		List<Integer> three = new ArrayList<>();

//		String label = "389125467";
		String label = "389547612";
		for (int i = 0; i < label.length(); i++) {
			in.add(Integer.valueOf(label.charAt(i) - 48).intValue());
		}
		for (int i = 10; i <= 1_000_000; i++) {
			in.add(i);
		}

		Cup[] cups = new Cup[maxNo + 1];
		Cup prev = null, first = null, last = null;

		for (Integer t : in) {
			Cup tt = new Cup(t);
			cups[t] = tt;
			if (prev != null) {
				prev.setNext(tt);
				tt.setPrev(prev);
			} else {
				first = tt;
			}
			prev = tt;
			last = tt;
		}
		last.setNext(first);
		first.setPrev(last);

		Cup cur = first;
		for (var i = 0; i < moves; i++) {
			Cup a = cur.next(); // plocka ut tre
			Cup b = a.next();
			Cup c = b.next();

			a.prev().setNext(c.next()); // Stäng hålet efter de tre bortplockade
			c.next().setPrev(a.prev());

			Cup d = cups[getDestination(cur, maxNo, a, b, c)];// leta upp destination

			d.next().setPrev(c);// lägg in de tre utplockade
			c.setNext(d.next());
			d.setNext(a);
			a.setPrev(d);

			cur = cur.next();
		}

		Cup b = cups[1];
		System.out.println(b.next().value() * b.next().next().value());
	}

	private static int getDestination(Cup cur, int maxNo, Cup a, Cup b, Cup c) {
		int nextN = (int) (cur.value() - 1);
		while (true) {
			if (nextN <= 0) {
				nextN = maxNo;
			}
			if (nextN != a.value() && nextN != b.value() && nextN != c.value()) {
				return nextN;
			}
			nextN--;
		}
	}

	static class Cup {
		private Cup next;
		private Cup prev;
		private final long value;

		public Cup(long value) {
			this.value = value;
		}

		public Cup next() {
			return next;
		}

		public void setNext(Cup next) {
			this.next = next;
		}

		public Cup prev() {
			return prev;
		}

		public void setPrev(Cup prev) {
			this.prev = prev;
		}

		public long value() {
			return value;
		}
	}
}
