package tamso;

public class Operator {
	/*
	 * 0:Up
	 * 1:Down
	 * 2:Left
	 * 3:Right
	 */
	int i;
	
	public Operator(int i){
		this.i = i;
	}
	
	public State move(State s){
		switch(i){
		case 0: return Up(s);
		case 1: return Down(s);
		case 2: return Left(s);
		case 3: return Right(s);
		}
		return null;
	}

	private State Up(State s) {
		int x0=0,y0=0;
		for (int y = 0; y<3;y++)
			for (int x=0;x<3;x++)
				if (s.a[y][x]==0){
					x0 = x;
					y0 = y;
				}
		if (y0==2) return null;
		State sn = s.Clone();
		sn.a[y0][x0] = s.a[y0+1][x0];
		sn.a[y0+1][x0] = 0;
		
		return sn;
	}
	
	private State Down(State s) {
		int x0=0,y0=0;
		for (int y = 0; y<3;y++)
			for (int x=0;x<3;x++)
				if (s.a[y][x]==0){
					x0 = x;
					y0 = y;
				}
		if (y0==0) return null;
		State sn = s.Clone();
		sn.a[y0][x0] = s.a[y0-1][x0];
		sn.a[y0-1][x0] = 0;
		
		return sn;
	}
	
	private State Left(State s) {
		int x0=0,y0=0;
		for (int y = 0; y<3;y++)
			for (int x=0;x<3;x++)
				if (s.a[y][x]==0){
					x0 = x;
					y0 = y;
				}
		if (x0==2) return null;
		State sn = s.Clone();
		sn.a[y0][x0] = s.a[y0][x0+1];
		sn.a[y0][x0+1] = 0;
		
		return sn;
	}
	
	private State Right(State s) {
		int x0=0,y0=0;
		for (int y = 0; y<3;y++)
			for (int x=0;x<3;x++)
				if (s.a[y][x]==0){
					x0 = x;
					y0 = y;
				}
		if (x0==0) return null;
		State sn = s.Clone();
		sn.a[y0][x0] = s.a[y0][x0-1];
		sn.a[y0][x0-1] = 0;
		
		return sn;
	}
}











