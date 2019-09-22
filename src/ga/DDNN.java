package ga;

import java.util.Arrays;
import java.util.Random;

public class DDNN {
	int M = 100;
	int dis[][] = new int[M][M];

	public static void main(String[] args) {
		new DDNN();
	}

	int N = 1000;
	int[][] nghiem = new int[N][M];
	Random rand = new Random();
	int[] f = new int[N];

	public DDNN() {
		for (int i = 0; i < M; i++)
			for (int j = i + 1; j < M; j++)
				dis[i][j] = dis[j][i] = rand.nextInt(100001);

		KhoiTao();

		for (int i = 1; i < 1000; i++) {
			DanhGia();
			Print();
			LuaChon();
			LaiGhep();
			DotBien();
		}
	}

	private void Print() {
		int[] cl = f.clone();
		Arrays.sort(cl);
		int best = cl[0];
		for (int i = 0; i < N; i++)
			if (f[i] == best) {
				//for (int j = 0; j < M; j++)
				//	System.out.print(nghiem[i][j] + ",");
				System.out.println(f[i]);
				break;
			}

	}

	private void DotBien() {
		int i = rand.nextInt(N);
		int x = rand.nextInt(M);
		int y = rand.nextInt(M);
		int tmp = nghiem[i][x];
		nghiem[i][x] = nghiem[i][y];
		nghiem[i][y] = tmp;
	}

	private void LaiGhep() {
		for (int i = 0; i < N * 30 / 100; i++) {
			int cha = rand.nextInt(N);
			int me = rand.nextInt(N);
			int id = rand.nextInt(M - 1) + 1;
			int[] con1 = Child(nghiem[cha], nghiem[me], id);
			int[] con2 = Child(nghiem[me], nghiem[cha], id);
			nghiem[cha] = con1;
			nghiem[me] = con2;
		}

	}

	private int[] Child(int[] a, int[] b, int id) {
		int[] checktrung = new int[a.length];
		int[] ans = new int[a.length];
		for (int i = 0; i < id; i++) {
			ans[i] = a[i];
			checktrung[a[i]] = 1;
		}

		for (int i = id; i < a.length; i++)
			if (checktrung[b[i]] == 0) {
				ans[i] = b[i];
				checktrung[b[i]] = 1;
			} else {
				ans[i] = -1;
			}
		int j = 0;
		for (int i = id; i < a.length; i++)
			if (ans[i] == -1) {
				while (checktrung[j] == 1)
					j++;
				ans[i] = j;
				j++;
			}

		return ans;
	}

	private void LuaChon() {
		int[] tmp = f.clone();
		Arrays.sort(tmp);
		int nguong = tmp[N * 80 / 100];
		for (int i = 0; i < N; i++)
			if (f[i] > nguong) {
				nghiem[i] = nghiem[rand.nextInt(N)].clone();
			}

	}

	private void DanhGia() {
		for (int i = 0; i < N; i++) {
			int d = 0;
			for (int j = 1; j < M; j++) {
				d += dis[nghiem[i][j - 1]][nghiem[i][j]];
			}
			f[i] = d;
		}
	}

	private void KhoiTao() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				nghiem[i][j] = j;
			for (int j = 0; j < M * 10; j++) {
				int x = rand.nextInt(M);
				int y = rand.nextInt(M);

				int temp = nghiem[i][x];
				nghiem[i][x] = nghiem[i][y];
				nghiem[i][y] = temp;
			}
		}

	}

}
