package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class L04a {
	private static List<String> passport;

	public static void run(String ext) {
		try {
			passport = Files.readAllLines(Paths.get("./src/AoC2020/L04input" + ext + ".txt")).stream()
					.map(String::valueOf).collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Knas med filimport: " + e);
		}

		int a = 0, b = 0;
		String tmp = "";

		do {
			tmp = tmp + " " + passport.get(a);
			
			if (passport.get(a).equals("") || a == passport.size() - 1) {
//				System.out.println(tmp);
//				System.out.println(check(tmp));
				if (check(tmp)) {
					b++;
				}
				tmp = "";
			}
			a++;
		} while (a < passport.size());

		System.out.println("Correct: " + b);
	}

	private static boolean check(String p) {
		if (!p.contains("byr:")) {
			return false;
		}
		if (!p.contains("iyr:")) {
			return false;
		}
		if (!p.contains("eyr:")) {
			return false;
		}
		if (!p.contains("hgt:")) {
			return false;
		}
		if (!p.contains("hcl:")) {
			return false;
		}
		if (!p.contains("ecl:")) {
			return false;
		}
		if (!p.contains("pid:")) {
			return false;
		}
//		if (!p.contains("cid:")) {
//			return false;
//		}
		return true;
	}
}
