package AoC2020;

public class L25a {
	public static void run() {
		long pKeyA = 13233401;
		long pKeyB = 6552760;
//		long pKeyA = 5764801L;
//		long pKeyB = 17807724L;
		System.out.println(transform(pKeyA, calcLoopSize(pKeyB)));
		System.out.println(transform(pKeyB, calcLoopSize(pKeyA)));
	}

	private static long transform(long subject, long loopSize) {
		long value = 1L;
		for (int i = 0; i < loopSize; i++) {
			value = value * subject;
			value = value % 20201227L;
		}
		return value;
	}

	private static long calcLoopSize(long pKeyB) {
		long ls = 0, value = 1;
		do {
			ls++;
			value = value * 7L;
			value = value % 20201227L;
		} while (value != pKeyB);
		return ls;
	}
}