package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class L08b {

	public static void run(String ext) throws IOException {
		List<String> code;
		code = Files.readAllLines(Paths.get("./src/AoC2020/L08input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		for (int i = 0;; i++) {
			int adress = 0, acc = 0, jump = 0, ci = 0;
			Set<Integer> visitedInstruction = new HashSet<>();
			while (visitedInstruction.add(adress) && adress < code.size()) {
				jump++;
				String[] split = code.get(adress).split(" ");
				switch (split[0]) {
				case "acc":
					acc += Integer.valueOf(split[1]);
					break;
				case "jmp":
					if (i != ci) {// reverse corruption
						adress += Integer.valueOf(split[1]) - 1;
					}
					ci++;
					break;
				case "nop":
					if (i == ci) {// reverse corruption
						adress += Integer.valueOf(split[1]) - 1;
					}
					ci++;
					break;
				default:
					System.out.println("okänd instruktion: " + code.get(adress));
				}
				adress++;
			}
			System.out.println("Acc at found loop: " + acc);
			System.out.println("Jumps: " + jump);

			if (adress >= code.size()) {
				System.out.println("Terminated correct:");
				break;
			}
		}
	}
}
