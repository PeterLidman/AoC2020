package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class L04b {
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
		String value;

		value = extract(p, "byr:");
		if (value.equals("")) {
			return false;
		}
		if (value.length() != 4) {
			return false;
		}
		if (!value.matches("[0-9]*")) {
			return false;
		}
		if (Integer.valueOf(value) < 1920 || Integer.valueOf(value) > 2002) {
			return false;
		}

		value = extract(p, "iyr:");
		if (value.equals("")) {
			return false;
		}
		if (value.length() != 4) {
			return false;
		}
		if (!value.matches("[0-9]*")) {
			return false;
		}
		if (Integer.valueOf(value) < 2010 || Integer.valueOf(value) > 2020) {
			return false;
		}

		value = extract(p, "eyr:");
		if (value.equals("")) {
			return false;
		}
		if (value.length() != 4) {
			return false;
		}
		if (!value.matches("[0-9]*")) {
			return false;
		}
		if (Integer.valueOf(value) < 2020 || Integer.valueOf(value) > 2030) {
			return false;
		}

		value = extract(p, "hgt:");
		if (value.equals("")) {
			return false;
		}
		if (!value.contains("cm") && !value.contains("in")) {
			return false;
		}
		if (value.length() < 3) {
			return false;
		}
		if (value.contains("cm")) {
			value = value.substring(0, value.length() - 2);
			if (!value.matches("[0-9]*")) {
				return false;
			}
			if (Integer.valueOf(value) < 150 || Integer.valueOf(value) > 193) {
				return false;
			}
		} else { // in
			value = value.substring(0, value.length() - 2);
			if (!value.matches("[0-9]*")) {
				return false;
			}
			if (Integer.valueOf(value) < 59 || Integer.valueOf(value) > 76) {
				return false;
			}
		}

		value = extract(p, "hcl:");
		if (value.equals("")) {
			return false;
		}
		if (value.length() != 7) {
			return false;
		}
		if (!value.matches("^#[a-f0-9]*")) {
			return false;
		}

		value = extract(p, "ecl:");
		if (value.equals("")) {
			return false;
		}
		if (value.length() != 3) {
			return false;
		}
		if (!(value.equals("amb") || value.equals("blu") || value.equals("brn") || value.equals("gry")
				|| value.equals("grn") || value.equals("hzl") || value.equals("oth"))) {
			return false;
		}

		value = extract(p, "pid:");
		if (value.equals("")) {
			return false;
		}
		if (value.length() != 9) {
			return false;
		}
		if (!value.matches("[0-9]*")) {
			return false;
		}
		
		return true;
	}

	private static String extract(String p, String f) {
		if (!p.contains(f)) {
			return "";
		}
		return p.substring(p.indexOf(f)+4).split(" ")[0];
	}
}
