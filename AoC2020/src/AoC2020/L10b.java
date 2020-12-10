package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class L10b {
	private static List<Integer> jolts;

	public static void run(String ext) throws IOException {
		jolts = Files.readAllLines(Paths.get("./src/AoC2020/L10input" + ext + ".txt")).stream().map(Integer::valueOf)
				.collect(Collectors.toList());

		int i = -1, prev = 0, seq = 1;
		long poss = 1;
		Collections.sort(jolts);

		while (++i < jolts.size()) {
			int curr = jolts.get(i).intValue();
			if (curr - prev == 1) {
				seq++;
			} else {
				poss *= alt(seq);
				seq = 1;// reset
			}
			prev = curr;
		}
		poss *= alt(seq);// sista hoppet
		System.out.println("Possibities: " + poss);
	}

	private static int alt(int in) { // e.g {1,1,1,2,4,7,13,26,54, ...}
		int ret = 0;
		if (in < 3) {
			return 1;
		}
		for (int i = 1; i < (in - 3); i++) {
			ret += i;
		}
		if (in > 2) {
			ret = (int) (Math.pow(2, (in - 2)) - ret);
		}
		return ret;
	}
}
