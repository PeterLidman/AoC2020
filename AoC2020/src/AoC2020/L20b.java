package AoC2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class L20b {
	private static int SIZE = 144;
	private static Tile[] tis;
	private static Integer[][] orders;
	private static boolean lock[][];

	public static void run(String ext) throws IOException {
		List<String> input = Files.readAllLines(Paths.get("./src/AoC2020/L20input" + ext + ".txt")).stream()
				.collect(Collectors.toList());

		orders = new Integer[13][13];
		orders[0][0] = 4;
		orders[1][0] = 54;
		orders[2][0] = 82;
		orders[3][0] = 25;
		orders[4][0] = 132;
		orders[5][0] = 51;
		orders[6][0] = 34;
		orders[7][0] = 104;
		orders[8][0] = 57;
		orders[9][0] = 59;
		orders[10][0] = 74;
		orders[11][0] = 47;// översta kant

		orders[0][1] = 143;
		orders[1][1] = 50;
		orders[2][1] = 134;
		orders[3][1] = 112;
		orders[4][1] = 135;
		orders[5][1] = 75;
		orders[6][1] = 85;
		orders[7][1] = 127;
		orders[8][1] = 10;
		orders[9][1] = 97;
		orders[10][1] = 89;
		orders[11][1] = 40;

		orders[0][2] = 56;
		orders[1][2] = 52;
		orders[2][2] = 14;
		orders[3][2] = 9;
		orders[4][2] = 107;
		orders[5][2] = 58;
		orders[6][2] = 131;
		orders[7][2] = 29;
		orders[8][2] = 64;
		orders[9][2] = 114;
		orders[10][2] = 36;
		orders[11][2] = 118;

		orders[0][3] = 72;
		orders[1][3] = 30;
		orders[2][3] = 111;
		orders[3][3] = 6;
		orders[4][3] = 115;
		orders[5][3] = 123;
		orders[6][3] = 103;
		orders[7][3] = 46;
		orders[8][3] = 31;
		orders[9][3] = 92;
		orders[10][3] = 117;
		orders[11][3] = 120;

		orders[0][4] = 38;
		orders[1][4] = 140;
		orders[2][4] = 71;
		orders[3][4] = 60;
		orders[4][4] = 53;
		orders[5][4] = 96;
		orders[6][4] = 108;
		orders[7][4] = 26;
		orders[8][4] = 69;
		orders[9][4] = 133;
		orders[10][4] = 8;
		orders[11][4] = 80;

		orders[0][5] = 70;
		orders[1][5] = 102;
		orders[2][5] = 39;
		orders[3][5] = 121;
		orders[4][5] = 35;
		orders[5][5] = 130;
		orders[6][5] = 28;
		orders[7][5] = 137;
		orders[8][5] = 106;
		orders[9][5] = 126;
		orders[10][5] = 128;
		orders[11][5] = 17;

		orders[0][6] = 76;
		orders[1][6] = 19;
		orders[2][6] = 95;
		orders[3][6] = 99;
		orders[4][6] = 13;
		orders[5][6] = 109;
		orders[6][6] = 37;
		orders[7][6] = 138;
		orders[8][6] = 81;
		orders[9][6] = 68;
		orders[10][6] = 62;
		orders[11][6] = 125;

		orders[0][7] = 84;
		orders[1][7] = 3;
		orders[2][7] = 43;
		orders[3][7] = 24;
		orders[4][7] = 79;
		orders[5][7] = 98;
		orders[6][7] = 91;
		orders[7][7] = 5;
		orders[8][7] = 136;
		orders[9][7] = 11;
		orders[10][7] = 129;
		orders[11][7] = 44;

		orders[0][8] = 66;
		orders[1][8] = 116;
		orders[2][8] = 45;
		orders[3][8] = 100;
		orders[4][8] = 27;
		orders[5][8] = 49;
		orders[6][8] = 48;
		orders[7][8] = 88;
		orders[8][8] = 21;
		orders[9][8] = 139;
		orders[10][8] = 105;
		orders[11][8] = 63;

		orders[0][9] = 12;
		orders[1][9] = 7;
		orders[2][9] = 0;
		orders[3][9] = 41;
		orders[4][9] = 124;
		orders[5][9] = 32;
		orders[6][9] = 83;
		orders[7][9] = 78;
		orders[8][9] = 18;
		orders[9][9] = 65;
		orders[10][9] = 93;
		orders[11][9] = 23;

		orders[0][10] = 141;
		orders[1][10] = 110;
		orders[2][10] = 101;
		orders[3][10] = 61;
		orders[4][10] = 94;
		orders[5][10] = 86;
		orders[6][10] = 122;
		orders[7][10] = 142;
		orders[8][10] = 20;
		orders[9][10] = 33;
		orders[10][10] = 1;
		orders[11][10] = 15;

		orders[0][11] = 113;
		orders[1][11] = 119;
		orders[2][11] = 67;
		orders[3][11] = 77;
		orders[4][11] = 22;
		orders[5][11] = 2;
		orders[6][11] = 73;
		orders[7][11] = 55;
		orders[8][11] = 87;
		orders[9][11] = 90;
		orders[10][11] = 42;
		orders[11][11] = 16;// nedersta kant

		int row = -1, count = 0;
		tis = new Tile[SIZE];
		while (++row < input.size()) {
//			System.out.println("r= " + input.get(row));
			if (input.get(row).contains("Tile")) {
				int a = Integer.valueOf(input.get(row).split(" ")[1].split(":")[0]).intValue();
//				System.out.println("tilecount: " + count + " s " + a);
				tis[count] = new Tile(a);
				for (int j = 0; j < 10; j++) {
					tis[count].data[j] = input.get(++row);
				}
				count++;
			}
		}

		List<List<String>> matrix = new ArrayList<>();
		for (int i = 0; i < SIZE; i++) {
			matrix.add(tis[i].getBorder());
		}
//		System.out.println(matrix);
		List<Set<Integer>> neigbours = new ArrayList<>();
		List<Set<String>> edges = new ArrayList<>();
		for (int yt = 0; yt < matrix.size(); yt++) {
			Set<Integer> s = new HashSet<>();
			Set<String> ed = new HashSet<>();
			List<String> a = matrix.get(yt);
			for (String b : a) {
				for (int it = 0; it < matrix.size(); it++) {
					List<String> c = matrix.get(it);
					if (c.contains(b)) {
//						System.out.println("hit " + b + "yt= " + yt + "it= " +it);
						if (yt != it) {
							s.add(it);
							ed.add(b);
						}
					}
				}
			}
			neigbours.add(s);
			edges.add(ed);
		}
		int a2[] = new int[6];
		for (Set<Integer> q : neigbours) {
			a2[q.size()]++;
		}
//		System.out.println(a2[2]);
//		System.out.println(a2[3]);
//		System.out.println(a2[4]);
//		System.out.println(neigbours.size());
//		System.out.println(neigbours.get(50));
//		System.out.println(edges.get(50));
//		ti[50].print();
//		ti[50].rotate();
//		ti[50].print();
//		System.out.println(ti[50].getBorder());

//		System.out.println(neigbours.get(4));
//		System.out.println(neigbours.get(54));
//		System.out.println(neigbours.get(143));

//		int plats = 0;
//		for (Set<Integer> c : neigbours) {
//			if (c.contains(33)) {
//				System.out.println("hittade på: " + plats);
//			}
//			plats++;
//		}
//		System.out.println();
//		plats = 0;
//		for (Set<Integer> c : neigbours) {
//			if (c.contains(1)) {
//				System.out.println("hittade på: " + plats);
//			}
//			plats++;
//		}
//		System.out.println();
//		plats = 0;
//		for (Set<Integer> c : neigbours) {
//			if (c.contains(15)) {
//				System.out.println("hittade på: " + plats);
//			}
//			plats++;
//		}
//		System.exit(0);// TODO

//		for (int y = 0; y < 12; y++) {
//			for (int x = 0; x < 12; x++) {
//				System.out.print(String.format("%03d", orders[x][y]) + ",");
//			}
//			System.out.println();
//		}

		// rotera och flippa rätt alla

//		tis[4].print();// 4 är rättvänd
//		tis[54].vflip();		
//		tis[54].print();
//		tis[143].rotate();
//		tis[143].rotate();
//		tis[143].rotate();
//		tis[143].print();

		for (int i = 1; i < 12; i++) {
			int j = 0;
			if (tis[orders[i][j]].getBorder().get(3).equals(tis[orders[i - 1][j]].getBorder().get(1))) {
				continue;
			}
			tis[orders[i][j]].rotate();
			if (tis[orders[i][j]].getBorder().get(3).equals(tis[orders[i - 1][j]].getBorder().get(1))) {
				continue;
			}
			tis[orders[i][j]].rotate();
			if (tis[orders[i][j]].getBorder().get(3).equals(tis[orders[i - 1][j]].getBorder().get(1))) {
				continue;
			}
			tis[orders[i][j]].rotate();
			if (tis[orders[i][j]].getBorder().get(3).equals(tis[orders[i - 1][j]].getBorder().get(1))) {
				continue;
			}
			tis[orders[i][j]].hflip();
			if (tis[orders[i][j]].getBorder().get(3).equals(tis[orders[i - 1][j]].getBorder().get(1))) {
				continue;
			}
			tis[orders[i][j]].hflip();
			tis[orders[i][j]].vflip();
			if (tis[orders[i][j]].getBorder().get(3).equals(tis[orders[i - 1][j]].getBorder().get(1))) {
				continue;
			}
			tis[orders[i][j]].vflip();
			tis[orders[i][j]].rotate();
			tis[orders[i][j]].hflip();
			if (tis[orders[i][j]].getBorder().get(3).equals(tis[orders[i - 1][j]].getBorder().get(1))) {
				continue;
			}
			tis[orders[i][j]].hflip();
			tis[orders[i][j]].vflip();
			if (tis[orders[i][j]].getBorder().get(3).equals(tis[orders[i - 1][j]].getBorder().get(1))) {
				continue;
			}
		}

		for (int i = 1; i < 12; i++) {
			int j = 0;
			if (tis[orders[j][i]].getBorder().get(0).equals(tis[orders[j][i - 1]].getBorder().get(2))) {
				continue;
			}
			tis[orders[j][i]].rotate();
			if (tis[orders[j][i]].getBorder().get(0).equals(tis[orders[j][i - 1]].getBorder().get(2))) {
				continue;
			}
			tis[orders[j][i]].rotate();
			if (tis[orders[j][i]].getBorder().get(0).equals(tis[orders[j][i - 1]].getBorder().get(2))) {
				continue;
			}
			tis[orders[j][i]].rotate();
			if (tis[orders[j][i]].getBorder().get(0).equals(tis[orders[j][i - 1]].getBorder().get(2))) {
				continue;
			}
			tis[orders[j][i]].hflip();
			if (tis[orders[j][i]].getBorder().get(0).equals(tis[orders[j][i - 1]].getBorder().get(2))) {
				continue;
			}
			tis[orders[j][i]].hflip();
			tis[orders[j][i]].vflip();
			if (tis[orders[j][i]].getBorder().get(0).equals(tis[orders[j][i - 1]].getBorder().get(2))) {
				continue;
			}
			tis[orders[j][i]].vflip();
			tis[orders[j][i]].rotate();
			tis[orders[j][i]].hflip();
			if (tis[orders[j][i]].getBorder().get(0).equals(tis[orders[j][i - 1]].getBorder().get(2))) {
				continue;
			}
			tis[orders[j][i]].hflip();
			tis[orders[j][i]].vflip();
			if (tis[orders[j][i]].getBorder().get(0).equals(tis[orders[j][i - 1]].getBorder().get(2))) {
				continue;
			}
		}

		for (int j = 1; j < 12; j++) {
			for (int i = 1; i < 12; i++) {
				if (tis[orders[j][i]].getBorder().get(0).equals(tis[orders[j][i - 1]].getBorder().get(2))) {
					continue;
				}
				tis[orders[j][i]].rotate();
				if (tis[orders[j][i]].getBorder().get(0).equals(tis[orders[j][i - 1]].getBorder().get(2))) {
					continue;
				}
				tis[orders[j][i]].rotate();
				if (tis[orders[j][i]].getBorder().get(0).equals(tis[orders[j][i - 1]].getBorder().get(2))) {
					continue;
				}
				tis[orders[j][i]].rotate();
				if (tis[orders[j][i]].getBorder().get(0).equals(tis[orders[j][i - 1]].getBorder().get(2))) {
					continue;
				}
				tis[orders[j][i]].hflip();
				if (tis[orders[j][i]].getBorder().get(0).equals(tis[orders[j][i - 1]].getBorder().get(2))) {
					continue;
				}
				tis[orders[j][i]].hflip();
				tis[orders[j][i]].vflip();
				if (tis[orders[j][i]].getBorder().get(0).equals(tis[orders[j][i - 1]].getBorder().get(2))) {
					continue;
				}
				tis[orders[j][i]].vflip();
				tis[orders[j][i]].rotate();
				tis[orders[j][i]].hflip();
				if (tis[orders[j][i]].getBorder().get(0).equals(tis[orders[j][i - 1]].getBorder().get(2))) {
					continue;
				}
				tis[orders[j][i]].hflip();
				tis[orders[j][i]].vflip();
				if (tis[orders[j][i]].getBorder().get(0).equals(tis[orders[j][i - 1]].getBorder().get(2))) {
					continue;
				}
			}
		}

//		System.out.println(edges.get(orders[0][0]));
//		System.out.println(edges.get(orders[1][0]));
//		System.out.println(neigbours.get(orders[0][0]));
//		tis[orders[0][0]].print();

		lock = new boolean[12][12];
		lock[0][0] = true;

//		for (int y = 0; y < 12; y++) {
//			for (int x = 0; x < 12; x++) {
//				if(!lock[x][y]) {
//					tis[orders[x][y]].rotate();
//				}
//			}
//		}

//		for (int y = 0; y < 12; y++) {
//			for (int x = 0; x < 12; x++) {
//				System.out.print(lock[x][y] + ",");
//			}
//			System.out.println();
//		}
//		for (int y = 0; y < 12; y++) {
//			for (int x = 0; x < 12; x++) {
//				System.out.print(alignedValue(x, y) + ",");
//			}
//			System.out.println();
//		}

		String[] rough = new String[96];
		for (int i = 0; i < 96; i++) {
			rough[i] = "";
		}
		for (int y = 0; y < 12; y++) {
			for (int x = 0; x < 12; x++) {
				for (int i = 1; i < 9; i++) {
					rough[y * 8 + (i - 1)] += tis[orders[x][y]].data[i].substring(1, 9);
				}
			}
		}

		int ant = 0;
		for (int i = 0; i < 96; i++) {
//			System.out.println(rough[i]);
			ant += Stream.of(rough[i].split("")).filter(s -> s.equals("#")).count();
		}
//		System.out.println("antal rought sea: " + ant); // - x * 15

		String[] nydata = new String[96];
		for (int i = 0; i < 96; i++) {
			nydata[i] = "";
		}
		for (int j = 0; j < 96; j++) {
			for (int i = 0; i < 96; i++) {
				nydata[i] += rough[95 - j].charAt(i);
			}
		}
		for (int i = 0; i < 96; i++) {
			rough[i] = nydata[i];
		}

		for (int i = 0; i < 96; i++) {
			nydata[i] = "";
		}
		for (int j = 0; j < 96; j++) {
			for (int i = 0; i < 96; i++) {
				nydata[i] += rough[95 - j].charAt(i);
			}
		}
		for (int i = 0; i < 96; i++) {
			rough[i] = nydata[i];
		}

		for (int i = 0; i < 96; i++) {
			nydata[i] = "";
		}
		for (int j = 0; j < 96; j++) {
			for (int i = 0; i < 96; i++) {
				nydata[i] += rough[95 - j].charAt(i);
			}
		}
		for (int i = 0; i < 96; i++) {
			rough[i] = nydata[i];
		}

		for (int i = 0; i < 96; i++) {
			rough[i] = reverse(rough[i]);
		}

//		String tmp;
//		for (int i = 0; i < 48; i++) {
//			tmp = rough[i];
//			rough[i] = rough[96 - 1 - i];
//			rough[96 - 1 - i] = tmp;
//		}

		int antalMonster = 0;
		int dx = 0, dy = 0, match = 0;
		for (int y = 2; y < 96; y++) {
			for (int x = 0; x < 76; x++) {
				// kolla efter monster i rough sea
				match = 0;

				dx = 18;
				dy = -2;
				if (rough[y + dy].charAt(x + dx) == '#') {
					match++;
				}

				dx = 0;
				dy = -1;
				if (rough[y + dy].charAt(x + dx) == '#') {
					match++;
				}
				dx = 5;
				if (rough[y + dy].charAt(x + dx) == '#') {
					match++;
				}
				dx = 6;
				if (rough[y + dy].charAt(x + dx) == '#') {
					match++;
				}
				dx = 11;
				if (rough[y + dy].charAt(x + dx) == '#') {
					match++;
				}
				dx = 12;
				if (rough[y + dy].charAt(x + dx) == '#') {
					match++;
				}
				dx = 17;
				if (rough[y + dy].charAt(x + dx) == '#') {
					match++;
				}
				dx = 18;
				if (rough[y + dy].charAt(x + dx) == '#') {
					match++;
				}
				dx = 19;
				if (rough[y + dy].charAt(x + dx) == '#') {
					match++;
				}

				dx = 1;
				dy = 0;
				if (rough[y + dy].charAt(x + dx) == '#') {
					match++;
				}
				dx = 4;
				if (rough[y + dy].charAt(x + dx) == '#') {
					match++;
				}
				dx = 7;
				if (rough[y + dy].charAt(x + dx) == '#') {
					match++;
				}
				dx = 10;
				if (rough[y + dy].charAt(x + dx) == '#') {
					match++;
				}
				dx = 13;
				if (rough[y + dy].charAt(x + dx) == '#') {
					match++;
				}
				dx = 16;
				if (rough[y + dy].charAt(x + dx) == '#') {
					match++;
				}

				if (match == 15) {
					antalMonster++;
					System.out.println("Tack snälle Satan");
				}
			}
		}

		System.out.println("antal rought sea: " + (ant - antalMonster * 15));

	}

	private static String reverse(String in) {
		String ut = "";
		for (int i = 0; i < in.length(); i++) {
			ut += in.charAt(in.length() - 1 - i);
		}
		return ut;
	}

	static int alignedValue(int x, int y) {
		int ret = 0, maxhits = 0;

		if (x > 0) {
			maxhits++;
			if (tis[orders[x][y]].getBorder().get(3).equals(tis[orders[x - 1][y]].getBorder().get(1))) {
				ret++;
			}
		}
		if (x < 11) {
			maxhits++;
			if (tis[orders[x][y]].getBorder().get(1).equals(tis[orders[x + 1][y]].getBorder().get(3))) {
				ret++;
			}
		}
		if (y > 0) {
			maxhits++;
			if (tis[orders[x][y]].getBorder().get(0).equals(tis[orders[x][y - 1]].getBorder().get(2))) {
				ret++;
			}
		}
		if (y < 11) {
			maxhits++;
			if (tis[orders[x][y]].getBorder().get(2).equals(tis[orders[x][y + 1]].getBorder().get(0))) {
				ret++;
			}
		}
		if (maxhits == ret) { // full pott lås denna
			lock[x][y] = true;
//			System.out.println("locked!");
		}
		return ret;
	}

	public static int stringValue(String in) {
		int ut = 0, vp = 512;
		for (int i = 0; i < 10; i++) {
			if (in.charAt(i) == '#') {
				ut += vp;
			}
			vp /= 2;
		}
		return ut;
	}

}