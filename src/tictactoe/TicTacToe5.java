package tictactoe;

import java.util.Scanner;

public class TicTacToe5 {
    public static void main(String[] args) {
        new TicTacToe5();
    }

    public TicTacToe5() {
        Scanner sc = new Scanner(System.in);
        int player = 1;
        int turn = 0;
        int d = 5;
        State s = new State();
        s.Print();
        while (true) {
            if (turn % 2 + 1 == player) {
                // User
                State child = null;
                while (child == null) {
                    System.out.println("Please input coordinate!!");
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    child = new Operator(x, y).Move(s);
                }
                s = child;
                if (Win5(s)) {
                    s.Print();
                    System.out.println("player won!");
                    break;
                }
            } else {
                // AI
                System.out.println("AI turn!!");
                int min = Integer.MAX_VALUE;
                State minchild = null;
                for (int i = 0; i < s.N; i++)
                    for (int j = 0; j < s.N; j++) {
                        State child = new Operator(i, j).Move(s);
                        if (child == null)
                            continue;
                        int tmp = MiniMax(child, 1, true);
                        System.out.println(i + "," + j + ":" + tmp);
                        if (min > tmp) {
                            min = tmp;
                            System.out.println("min: " + min);
                            minchild = child;
                        }
                    }
                s = minchild;
                if (Win5(s)) {
                    s.Print();
                    System.out.println("AI Won!");
                    break;
                }
            }
            s.Print();
            if (isEndNode5(s)) {
                System.out.println("Draw!");
                break;
            }
            turn++;
        }
    }

  /*  private boolean Win(State s) {
        for (int i = 0; i < s.N; i++) {
            if (s.a[i][0] != 0 && s.a[i][0] == s.a[i][1] && s.a[i][0] == s.a[i][2])
                return true;
            if (s.a[0][i] != 0 && s.a[0][i] == s.a[1][i] && s.a[0][i] == s.a[2][i])
                return true;
        }
        if (s.a[0][0] != 0 && s.a[0][0] == s.a[1][1] && s.a[0][0] == s.a[2][2])
            return true;
        if (s.a[0][2] != 0 && s.a[0][2] == s.a[1][1] && s.a[0][2] == s.a[2][0])
            return true;
        return false;
    }*/

    private int MiniMax(State s, int d, boolean MP) {
        System.out.println("minimax: " + AlphaBeta(s, d, Integer.MIN_VALUE, Integer.MAX_VALUE, MP));
        return AlphaBeta(s, d, Integer.MIN_VALUE, Integer.MAX_VALUE, MP);
    }

    private int AlphaBeta(State s, int d, int a, int b, boolean MP) {
        if (isEndNode5(s) || d == 0) {
            return Value(s);
        }
        lb:
        for (int i = 0; i < s.N; i++)
            for (int j = 0; j < s.N; j++) {
                State child = new Operator(i, j).Move(s);
                if (child == null)
                    continue;
                int tmp = AlphaBeta(child, d - 1, a, b, !MP);
                if (MP) {
                    if (a < tmp)
                        a = tmp;

                } else {
                    if (b > tmp)
                        b = tmp;

                }
                if (a >= b)
                    break lb;
            }
        if (MP)
            return a;
        else
            return b;
    }

    private int Value(State s) {
        if (Win5(s)) {
            if (MyTurn(s)) return 1;
            else return -1;
        }
        return 0;
    }

    private boolean MyTurn(State s) {
        int count = 0;

        for (int i = 0; i < s.N; i++) {
            for (int j = 0; j < s.N; j++) {
                if (s.a[i][j] == 0) count++;
            }
        }
        if (count % 2 == 0) return true;
        return false;
    }
/*
    private boolean isEndNode(State s) {
        for (int i = 0; i < s.N; i++) {
            if (s.a[i][0] != 0 && s.a[i][0] == s.a[i][1] && s.a[i][0] == s.a[i][2])
                return true;
            if (s.a[0][i] != 0 && s.a[0][i] == s.a[1][i] && s.a[0][i] == s.a[2][i])
                return true;
        }
        if (s.a[0][0] != 0 && s.a[0][0] == s.a[1][1] && s.a[0][0] == s.a[2][2])
            return true;
        if (s.a[0][2] != 0 && s.a[0][2] == s.a[1][1] && s.a[0][2] == s.a[2][0])
            return true;

        for (int i = 0; i < s.N; i++)
            for (int j = 0; j < s.N; j++)
                if (s.a[i][j] == 0)
                    return false;
        return true;
    }*/

    private boolean Win5(State s) {
        for (int i = 0; i < s.N; i++) {
            for (int j = 0; j < s.N; j++) {
                if (s.a[i][j] != 0) {
                    if (j + 4 < s.N) {
                        if (s.a[i][j] == s.a[i][j + 1] && s.a[i][j] == s.a[i][j + 2] &&
                                s.a[i][j] == s.a[i][j + 3] && s.a[i][j] == s.a[i][j + 4]) {
                            return true;
                        }
                    }
                    if (i + 4 < s.N) {
                        if (s.a[i][j] == s.a[i + 1][j] && s.a[i][j] == s.a[i + 2][j] &&
                                s.a[i][j] == s.a[i + 3][j] && s.a[i][j] == s.a[i + 4][j]) {
                            return true;
                        }
                    }
                    if (i + 4 < s.N && j + 4 < s.N) {
                        if (s.a[i][j] == s.a[i + 1][j + 1] && s.a[i][j] == s.a[i + 2][j + 2] &&
                                s.a[i][j] == s.a[i + 3][j + 3] && s.a[i][j] == s.a[i + 4][j + 4]) {
                            return true;
                        }
                    }
                    if (i >= 4 && j >= 4) {
                        if (s.a[i][j] == s.a[i - 1][j - 1] && s.a[i][j] == s.a[i - 2][j - 2] &&
                                s.a[i][j] == s.a[i - 3][j - 3] && s.a[i][j] == s.a[i - 4][j - 4]) {
                            return true;
                        }
                    }

                }
            }
        }
        return false;
    }

    private boolean isEndNode5(State s) {
        for (int i = 0; i < s.N; i++) {
            for (int j = 0; j < s.N; j++) {
                if (s.a[i][j] != 0) {
                    if (j + 5 < s.N) {
                        if (s.a[i][j] == s.a[i][j + 1] && s.a[i][j] == s.a[i][j + 2] &&
                                s.a[i][j] == s.a[i][j + 3] && s.a[i][j] == s.a[i][j + 4]) {
                            return true;
                        }
                    }
                    if (i + 5 < s.N) {
                        if (s.a[i][j] == s.a[i + 1][j] && s.a[i][j] == s.a[i + 2][j] &&
                                s.a[i][j] == s.a[i + 3][j] && s.a[i][j] == s.a[i + 4][j]) {
                            return true;
                        }
                    }
                    if (i + 5 < s.N && j + 5 < s.N) {
                        if (s.a[i][j] == s.a[i + 1][j + 1] && s.a[i][j] == s.a[i + 2][j + 2] &&
                                s.a[i][j] == s.a[i + 3][j + 3] && s.a[i][j] == s.a[i + 4][j + 4]) {
                            return true;
                        }
                    }
                    if (i >= 5 && j >= 5) {
                        if (s.a[i][j] == s.a[i - 1][j - 1] && s.a[i][j] == s.a[i - 2][j - 2] &&
                                s.a[i][j] == s.a[i - 3][j - 3] && s.a[i][j] == s.a[i - 4][j - 4]) {
                            return true;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < s.N; i++)
            for (int j = 0; j < s.N; j++)
                if (s.a[i][j] == 0)
                    return false;
        return false;
    }
}
