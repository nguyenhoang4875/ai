package gametamso;

import java.util.*;

public class SearchA {

    public static void main(String[] args) {
        Random rand = new Random();
        State Start, Goal, O = null;
        PriorityQueue<State> Open =
                new PriorityQueue<State>(100000000, new Comparator<State>() {
                    @Override
                    public int compare(State o1, State o2) {
                        return (o1.g + o1.h) - (o2.g + o2.h);
                    }
                });
        Map<String, State> Closed = new HashMap<>();

        Goal = new State(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});

        Start = new State(Goal.d);
        for (int i = 0; i < 1000; i++) {
            Operator op = new Operator(rand.nextInt(4));
            State child = op.move(Start);
            if (child != null)
                Start = child;
        }
        Start.Print();
        Start.g = 0;
        Start.h = Distance(Start);
        // 1.
        Open.add(Start);
        Closed.put(Start.Key(), Start);
        int count = 0;
        // 2.6
        while (Open.size() > 0) {
            count++;
            // 3.
            O = Open.remove();
            // 4.
            if (isEqual(O, Goal))
                break;
            // 5.
            for (int i = 0; i < 4; i++) {
                Operator op = new Operator(i);
                State child = op.move(O);
                if (child == null)
                    continue;

                if (include(Closed, child))
                    continue;
                child.Cha = O;
                child.Me = op;
                child.g = O.g + 1;
                child.g = Distance(child);
                Open.add(child);
                Closed.put(child.Key(), child);
            }
        }
        Print(O);
        System.out.println("So vong lap la:" + count);
    }

    private static int Distance(State a) {
        int count = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (a.d[i][j] != 0 && a.d[i][j] != i * 3 + j + 1) count++;
            }
        return count;
    }

    private static void Print(State o) {
        if (o.Cha != null) {
            Print(o.Cha);
            System.out.println(o.Me.id);
        }
        o.Print();

    }

    private static boolean include(Map<String, State> l, State a) {
        return l.containsKey(a.Key());
    }

    private static boolean isEqual(State a, State b) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (a.d[i][j] != b.d[i][j])
                    return false;
        return true;
    }

}
