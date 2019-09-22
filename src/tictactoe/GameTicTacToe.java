package tictactoe;

import java.util.Scanner;

public class GameTicTacToe {
    public static void main(String[] args) {
        State s = new State(new int[3][3]);
        Scanner sc = new Scanner(System.in);
        while (true) {
            s.Print();
            do {
                System.out.println("Input position");
                int i = sc.nextInt();
                int j = sc.nextInt();
                if (i < 0 || i >= 3) {
                    continue;
                }
                if (s.d[i][j] != 0) {
                    continue;
                }
                s.d[i][j] = 1;
                break;
            }
            while (true);
            minimax(s, 4, true);
        }
    }

    private static MyValue minimax(State s, int d, boolean mP) {
        if (endNode(s) || d == 0) {
            return value(s);
        }
        if (mP) {
            MyValue m = new MyValue(-Integer.MIN_VALUE, null);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (s.d[i][j] == 0) {
                        State child = new State(s.d);
                        child.d[i][j] = 2;
                        MyValue r = minimax(child, d - 1, !mP);
                        if (r.score > m.score) {
                            m = r;
                        }
                    }
                }
            }
            return m;
        } else {

            MyValue m = new MyValue(Integer.MAX_VALUE, null);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (s.d[i][j] == 0) {
                        State child = new State(s.d);
                        child.d[i][j] = 1;
                        MyValue r = minimax(child, d - 1, !mP);
                        if (r.score > m.score) {
                            m = r;
                        }
                    }
                }
            }
            return m;
        }
    }

    private static MyValue value(State s) {
        int score =0;
        // Tinh score
        return new MyValue(score,s);
    }

    private static boolean endNode(State s) {

        for (int i = 1; i <=2 ; i++) {
            if (s.d[0][0]==i && s.d[0][1] ==i && s.d[0][2] ==i) return true;
            if (s.d[1][0]==i && s.d[1][1] ==i && s.d[1][2] ==i) return true;
            if (s.d[2][0]==i && s.d[2][1] ==i && s.d[2][2] ==i) return true;


            if (s.d[0][0]==i && s.d[1][0] ==i && s.d[2][0] ==i) return true;
            if (s.d[0][1]==i && s.d[1][1] ==i && s.d[2][1] ==i) return true;
            if (s.d[0][2]==i && s.d[1][2] ==i && s.d[2][2] ==i) return true;


            if (s.d[0][0]==i && s.d[1][1] ==i && s.d[2][2] ==i) return true;
            if (s.d[2][0]==i && s.d[1][1] ==i && s.d[0][2] ==i) return true;
        }
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                if (s.d[i][j]==0) return false;

            }

        }
        return false;
    }
}

class MyValue {
    int score = 0;
    State s;

    public MyValue(int score, State s) {
        this.score = score;
        this.s = s;
    }
}
