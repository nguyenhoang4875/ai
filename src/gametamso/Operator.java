package gametamso;

public class Operator {
	// 0: Up
	// 1: Down
	// 2: Left
	// 3: Right
	int id;

	public Operator(int id) {
		this.id = id;
	}

	State move(State s) {
		switch (id) {
		case 0:
			return Up(s);
		case 1:
			return Down(s);
		case 2:
			return Left(s);
		case 3:
			return Right(s);
		}
		return null;
	}

	private State Right(State s) {
		MyPoint Zp = ZeroP(s);
		if (Zp.j > 0) {
			State ns = new State(s.d);
			ns.d[Zp.i][Zp.j] = ns.d[Zp.i][Zp.j-1];
			ns.d[Zp.i][Zp.j-1] = 0;
			return ns;
		}
		return null;
	}

	private State Left(State s) {
		MyPoint Zp = ZeroP(s);
		if (Zp.j < 2) {
			State ns = new State(s.d);
			ns.d[Zp.i][Zp.j] = ns.d[Zp.i][Zp.j+1];
			ns.d[Zp.i][Zp.j+1] = 0;
			return ns;
		}
		return null;
	}

	private State Down(State s) {
		MyPoint Zp = ZeroP(s);
		if (Zp.i > 0) {
			State ns = new State(s.d);
			ns.d[Zp.i][Zp.j] = ns.d[Zp.i-1][Zp.j];
			ns.d[Zp.i-1][Zp.j] = 0;
			return ns;
		}
		return null;
	}

	private State Up(State s) {
		MyPoint Zp = ZeroP(s);
		if (Zp.i < 2) {
			State ns = new State(s.d);
			ns.d[Zp.i][Zp.j] = ns.d[Zp.i+1][Zp.j];
			ns.d[Zp.i+1][Zp.j] = 0;
			return ns;
		}
		return null;
	}

	private MyPoint ZeroP(State s) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (s.d[i][j] == 0)
					return new MyPoint(i, j);
		return null;
	}
}

class MyPoint {
	int i, j;

	public MyPoint(int i, int j) {
		this.i = i;
		this.j = j;
	}
}
