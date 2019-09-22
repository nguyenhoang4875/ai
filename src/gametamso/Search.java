package gametamso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Search {

	public static void main(String[] args) {
		Random rand = new Random();
		State Start, Goal, O = null;
		List<State> Open = new ArrayList<>();
		Map<String,State> Closed = new HashMap<>();

		Goal = new State(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } });

		Start = new State(Goal.d);
		for (int i = 0; i < 1000; i++) {
			Operator op = new Operator(rand.nextInt(4));
			State child = op.move(Start);
			if (child != null)
				Start = child;
		}
		Start.Print();
		// 1.
		Open.add(Start);
		Closed.put(Start.Key(), Start);
		int count=0;
		// 2.6
		while (Open.size() > 0) {
			count++;
			// 3.
			O = Open.remove(0);
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
				Open.add(child);
				Closed.put(child.Key(),child);
			}
		}
		Print(O);
		System.out.println("So vong lap la:"+count);
	}

	private static void Print(State o) {
		if (o.Cha!=null){
			Print(o.Cha);
			System.out.println(o.Me.id);
		}
		o.Print();
		
	}

	private static boolean include(Map<String,State> l, State a) {
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
