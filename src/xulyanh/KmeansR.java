package xulyanh;

import java.util.Random;

//Step 1
// Init data to group.
//Step 2-4
//Count is number elements of a group
// Sum coordinate of all group
// Random one point for a group empty point
// Step 3
// Step 4
// Chia
// Calculator distance.
public class KmeansR {
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

    public KmeansR(int[][] data, int k) {
        this.data = data;
        this.k = k;
        this.id = new int[data.length];
        this.c = new int[k][data[0].length];
        Random random = new Random();


        //Step 1
        // Init data to group.
        for (int i = 0; i < data.length; i++) {
            id[i] = random.nextInt(k);
        }


        while (true) {
            // step 2
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
                    int x = random.nextInt(k);
                    for (int j = 0; j < c[i].length; j++) {
                        c[i][j] = data[x][j];
                    }
                }
            }
            //Step 3
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
            dis += (x[i] - y[i]) *(x[i] - y[i]);

        }
        return dis;
    }
}
