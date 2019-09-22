package tamso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Search {

    public static void main(String[] args) {
        new Search();
    }

    Random rand = new Random();

    public Search() {
        List<State> Open = new ArrayList<>();
        Map<String, State> Closed = new HashMap<>();
        State O = null;
        State Goal = new State();
        for (int y = 0; y < 3; y++)
            for (int x = 0; x < 3; x++)
                Goal.a[y][x] = (x + y * 3 + 1) % 9;

        State Start = Goal.Clone();
        for (int i = 0; i < 1000000; i++) {
            State tmp = (new Operator(rand.nextInt(4))).move(Start);
            if (tmp != null)
                Start = tmp;
        }
        boolean OK = false;
        Goal.Print();
        Start.Print();

        int count = 0;
        int mem = 0;

        //1.
        Open.add(Start);
        Closed.put(Start.key(), Start);
        //2.6.
        while (Open.size() != 0) {
            count++;
            //3.
            O = Open.remove(0);
            //4.
            if (Equal(O, Goal)) {
                OK = true;
                break;
            }

            //5.
            for (int i = 0; i < 4; i++) {
                Operator op = new Operator(i);
                State Child = op.move(O);
                if (Child == null) continue;
                if (In(Child, Closed)) continue;
                Open.add(Child);
                if (mem < Open.size()) mem = Open.size();
                Closed.put(Child.key(), Child);
                Child.Cha = O;
                Child.Me = op;
            }
        }

        System.out.println(count);
        System.out.println(mem);
        //

        Print(O);
        System.out.println(count);
    }

    private void Print(State o) {
        if (o.Cha != null) {
            Print(o.Cha);
            System.out.println(o.Me.i);
        }
        o.Print();

    }

    private boolean In(State c, Map<String, State> list) {
        State s = list.get(c.key());
        if (s == null) return false;
        return true;
    }

    private boolean Equal(State o, State goal) {
        for (int y = 0; y < 3; y++)
            for (int x = 0; x < 3; x++)
                if (o.a[y][x] != goal.a[y][x]) return false;
        return true;
    }
}












