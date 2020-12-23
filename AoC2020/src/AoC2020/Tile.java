package AoC2020;

import java.util.ArrayList;
import java.util.List;

class Tile {
	public String[] data = new String[10];
	private int tileNr = 0;

	Tile(int tile) {
		tileNr = tile;
	}

	private String reverse(String in) {
		String ut = "";
		for (int i = 0; i < 10; i++) {
			ut += in.charAt(9 - i);
		}
		return ut;
	}

	public void rotate() {
		String[] nydata = new String[10];
		for (int i = 0; i < 10; i++) {
			nydata[i]="";
		}
		for (int j = 0; j < 10; j++) {
			for (int i = 0; i < 10; i++) {
				nydata[i] += data[9 - j].charAt(i);
			}
		}
		for (int i = 0; i < 10; i++) {
			data[i] = nydata[i];
		}
	}

	public void hflip() {
		for (int i = 0; i < 10; i++) {
			data[i] = reverse(data[i]);
		}
	}

	public void vflip() {
		String tmp;
		for (int i = 0; i < 5; i++) {
			tmp = data[i];
			data[i] = data[9 - i];
			data[9 - i] = tmp;
		}
	}

	List<String> getBorder() {
		List<String> ret = new ArrayList<>();
		ret.add(data[0]);
		String tret1 = "";
		String tret3 = "";
		for (int i = 0; i < 10; i++) {
			tret3 += data[i].charAt(0);
			tret1 += data[i].charAt(9);
		}
		ret.add(tret1);
		ret.add(data[9]);
		ret.add(tret3);
		ret.add(reverse(ret.get(0)));
		ret.add(reverse(ret.get(1)));
		ret.add(reverse(ret.get(2)));
		ret.add(reverse(ret.get(3)));
		return ret;
	}

	int getTile() {
		return tileNr;
	}

	void print() {
		System.out.println("Tile " + tileNr + ":");
		for (int a = 0; a < 10; a++) {
			System.out.println(data[a]);
		}
	}
}