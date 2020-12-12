package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class L12a {
	private static List<String> action;

	public static void run(String ext) throws IOException {
		action = Files.readAllLines(Paths.get("./src/AoC2020/L12input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		int i = -1, nPos = 0, ePos = 0, heading = 90;
		while (++i < action.size()) {
			String a = action.get(i);
			int v = Integer.valueOf(a.substring(1)).intValue();
			switch (a.charAt(0)) {
			case 'N':
				nPos += v;
				break;
			case 'S':
				nPos -= v;
				break;
			case 'E':
				ePos += v;
				break;
			case 'W':
				ePos -= v;
				break;
			case 'L':
				heading -= v;
				heading = (heading + 360) % 360;
				break;
			case 'R':
				heading += v;
				heading = (heading + 360) % 360;
				break;
			case 'F':
				switch (heading) {
				case 0:
					nPos += v;
					break;
				case 90:
					ePos += v;
					break;
				case 180:
					nPos -= v;
					break;
				case 270:
					ePos -= v;
					break;
				default:
					System.out.println("implementera kod för heading= " + heading);
					System.exit(0);
				}
				break;
			default:
				System.out.println("okänt kommando");
			}
//			System.out.println(a + " -> n" + nPos + " e" + ePos);
		}
		System.out.println("Manhattan distance: " + (Math.abs(nPos) + Math.abs(ePos)));
	}
}