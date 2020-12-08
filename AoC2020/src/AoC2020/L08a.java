package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class L08a {
	private static List<String> code;

	public static void run(String ext) throws IOException {
		code = Files.readAllLines(Paths.get("./src/AoC2020/L08input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		int adress = 0, acc = 0;

		Set<Integer> visitedInstruction = new HashSet<>();

		while (visitedInstruction.add(adress)) {
			String[] split = code.get(adress).split(" ");
			switch (split[0]) {
			case "acc":
				acc += Integer.valueOf(split[1]);
				break;
			case "jmp":
				adress += Integer.valueOf(split[1]) - 1;
				break;
			case "nop":
				break;
			default:
				System.out.println("okänd instruktion: " + code.get(adress));
			}
			adress++;
		}
		System.out.println("Acc at found loop: " + acc);
	}
}
