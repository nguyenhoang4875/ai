package xulyanh;

import java.util.Random;

public class Kmeans {

    public static void main(String[] args) {
        int[][] x = new int[][]{{3, 1}, {0, 3}, {1, 4}, {4, 1}};

        Kmeans km = new Kmeans(x, 2);
        for (int i = 0; i < x.length; i++) {
            System.out.println(km.id[i]);
        }
    }


    int[][] data;
    int k;
    int[] id;
    int[][] c;
    Random random = new Random();

    public Kmeans(int[][] data, int k) {
        this.data = data;
        this.k = k;
        this.id = new int[data.length];
        this.c = new int[k][data[0].length];

        //Step 1
        // Init data to group.
        for (int i = 0; i < data.length; i++) {
            id[i] = random.nextInt(k);
        }

        //Step 2-4


        while (true) {
            //Count is number elements of a group
            int[] count = new int[k];
            for (int i = 0; i < k; i++) {
                count[i] = 0;
                for (int j = 0; j < c[i].length; j++) {
                    c[i][j] = 0;
                }
            }
            // Sum coordinate of all group
            for (int i = 0; i < data.length; i++) {
                count[id[i]]++;
                // Sum coordinate of a group
                for (int j = 0; j < data[i].length; j++) {
                    c[id[i]][j] += data[i][j];

                }
            }
            for (int i = 0; i < k; i++) {
                if (count[i] != 0) {
                    for (int j = 0; j < c[i].length; j++) {
                        c[i][j] /= count[i];
                    }
                } else {
                    // Random one point for a group empty point
                    int x = random.nextInt(data.length);
                    for (int j = 0; j < c[i].length; j++) {
                        c[i][j] = data[x][j];

                    }
                }
            }
            // Step 3
            boolean change = false;
            for (int i = 0; i < data.length; i++) {
                int newid = Chia(data[i]);
                if (id[i] != newid) {
                    change = true;
                }
                id[i] = newid;
            }

            // Step 4
            if (!change) {
                break;
            }
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

    public int dis(int[] x, int[] y) {
        int dis = 0;
        for (int i = 0; i < x.length; i++) {
            dis += (x[i] - y[i]) * (x[i] - y[i]);
        }
        return dis;
    }
}
