package gametamso;

public class State {
    int g = 0, h = 0;
    State Cha;
    Operator Me;
    int d[][] = new int[3][3];

    public State(int d[][]) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                this.d[i][j] = d[i][j];
    }

    public String Key() {
        String s = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                s += d[i][j];
        }
        return s;
    }

    public void Print() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(d[i][j] + " ");
            System.out.println();
        }
        System.out.println("--------------");
    }
}
