package xulyanh;

import java.util.Random;

public class k_means {

	public static void main(String[] args) {
		int[][] x = new int[][] { { 3, 1 }, { 0, 3 }, { 1, 4 }, { 4, 1 } };

		k_means km = new k_means(x, 2);
		for (int i = 0; i < x.length; i++) {
			System.out.println(km.id[i]);
		}

	}

	int[][] data;
	int k;
	int id[];
	int[][] c;
	Random rand = new Random();

	public k_means(int[][] data, int k) {
		this.data = data;
		this.k = k;
		this.id = new int[data.length];
		this.c = new int[k][data[0].length];

		// Buoc 1
		for (int i = 0; i < data.length; i++)
			id[i] = rand.nextInt(k);

		// Buoc 2-4
		while (true) {
			// Buoc 2
			int[] count = new int[k];
			for (int i = 0; i < k; i++) {
				count[i] = 0;
				for (int j = 0; j < c[i].length; j++)
					c[i][j] = 0;
			}

			for (int i = 0; i < data.length; i++) {
				count[id[i]]++;
				for (int j = 0; j < data[i].length; j++)
					c[id[i]][j] += data[i][j];
			}

			for (int i = 0; i < k; i++) {
				if (count[i] != 0) {
					for (int j = 0; j < c[i].length; j++)
						c[i][j] /= count[i];
				} else{
					int x = rand.nextInt(data.length);
					for (int j = 0; j < c[i].length; j++)
						c[i][j] = data[x][j];
				}
			}

			// Buoc 3
			boolean thaydoi = false;
			for (int i = 0; i < data.length; i++) {
				int newid = Chia(data[i]);
				if (id[i] != newid)
					thaydoi = true;
				id[i] = newid;
			}

			// Buoc 4
			if (!thaydoi)
				break;

		}

	}

	public int Chia(int[] x) {
		int id = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < k; i++) {
			int d = dis(x, c[i]);
			if (min > d) {
				min = d;
				id = i;
			}
		}

		return id;
	}

	private int dis(int[] x, int[] y) {
		int dis = 0;
		for (int i = 0; i < x.length; i++)
			dis += (x[i] - y[i]) * (x[i] - y[i]);
		return dis;
	}

}
