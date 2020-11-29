package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class L01a {
	private static final Logger LOGGER = Logger.getLogger(L01a.class.getName());

	private static List<Integer> moduler;

	public static void run(String ext) {
		try {
			moduler = Files.readAllLines(Paths.get("./src/AoC2020/L01input" + ext + ".txt")).stream()
					.map(Integer::valueOf).collect(Collectors.toList());
		} catch (IOException e) {
			LOGGER.info("Knas med filimport: " + e);
		}
		LOGGER.info(
				() -> String.format("Fuel consumption: %s", moduler.stream().mapToInt(i -> (int) (i / 3.0) - 2).sum()));
	}

}
