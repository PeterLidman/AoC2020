package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class L01b {
	private static final Logger LOGGER = Logger.getLogger(L01b.class.getName());
//test
	private static List<Integer> moduler;

	public static void run(String ext) {
		try {
			moduler = Files.readAllLines(Paths.get("./src/AoC2020/L01input" + ext + ".txt")).stream()
					.map(Integer::valueOf).collect(Collectors.toList());
		} catch (IOException e) {
			LOGGER.info("Knas med filimport: " + e);
		}

		LOGGER.info(() -> String.format("Fuel consumption: %s",
				moduler.stream().mapToInt(L01b::recursiveConsumption).sum()));
	}

	private static int recursiveConsumption(int unit) {
		int consumption = (int) (unit / 3.0) - 2;
		return consumption < 0 ? 0 : consumption + recursiveConsumption(consumption);
	}

}
