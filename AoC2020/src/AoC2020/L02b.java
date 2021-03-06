package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class L02b {
	private static List<String> pwd;

	public static void run(String ext) {
		try {
			pwd = Files.readAllLines(Paths.get("./src/AoC2020/L02input" + ext + ".txt")).stream().map(String::valueOf)
					.collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Knas med filimport: " + e);
		}

		int a, b = 0;

		for (a = 0; (a < pwd.size()); a++) {
//			System.out.println(checkPwd(pwd.get(a)));
			if (checkPwd(pwd.get(a))) {
				b++;
			}
		}
		System.out.println("ok pwd: " + b);
	}

	private static boolean checkPwd(String pwd) {
		String p, i1, i2, l, t[];

		t = pwd.split(":");
		p = t[1].trim();
		t = t[0].split(" ");
		l = t[1];
		t = t[0].split("-");
		i1 = t[0];
		i2 = t[1];
		if (p.charAt(Integer.valueOf(i1) - 1) == l.charAt(0) ^ p.charAt(Integer.valueOf(i2) - 1) == l.charAt(0)) {
			return true;
		}
		return false;
	}
}
