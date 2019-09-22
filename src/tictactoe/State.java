package tictactoe;

public class State {
    //0 chua danh
    //1 x
    //2 o
    int d[][] = new int[3][3];
    public State(int d[][]){
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {

                this.d[i][j] = d[i][j];
            }
        }
    }
    public void Print(){
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {

                if(d[i][j] == 0){
                    System.out.println("_");
                }
            }

        }
    }
}
