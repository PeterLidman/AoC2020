package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class L12b {
	private static List<String> action;

	public static void run(String ext) throws IOException {
		action = Files.readAllLines(Paths.get("./src/AoC2020/L12input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		int i = -1, wNPos = 1, wEPos = 10, heading = 90, sNPos = 0, sEPos = 0, tmp;
		while (++i < action.size()) {
			String a = action.get(i);
			int v = Integer.valueOf(a.substring(1)).intValue();
			switch (a.charAt(0)) {
			case 'N':
				wNPos += v;
				break;
			case 'S':
				wNPos -= v;
				break;
			case 'E':
				wEPos += v;
				break;
			case 'W':
				wEPos -= v;
				break;
			case 'L':
				// gör om till R och fall-through
				if (v == 90) {
					v = 270;
				} else if (v == 270) {
					v = 90;
				}
			case 'R':
				switch (v) {
				case 0:
					break;
				case 90:
					tmp = wNPos;
					wNPos = 0 - wEPos;
					wEPos = tmp;
					break;
				case 180:
					wNPos = 0 - wNPos;
					wEPos = 0 - wEPos;
					break;
				case 270:
					tmp = wNPos;
					wNPos = wEPos;
					wEPos = 0 - tmp;
					break;
				default:
					System.out.println("implementera kod för heading= " + heading);
					System.exit(0);
				}
				break;
			case 'F':
				sNPos += v * wNPos;
				sEPos += v * wEPos;
				break;
			default:
				System.out.println("okänt kommando");
			}
//			System.out.println(a + " -> n" + sNPos + " e" + sEPos);
		}
		System.out.println("Manhattand distance: " + (Math.abs(sNPos) + Math.abs(sEPos)));
	}
}
