package cn.csu.dianzi.LP;

import java.util.*;
import cn.csu.dianzi.bean.Edge;

/**
 * 	A -> B under C
 * 
 * */
public class TimeCN extends CN{
	private int[][] C;
	
	public TimeCN(ArrayList<Edge> a, ArrayList<Edge> b) {
		super(a, b);
	}
	
	public TimeCN(ArrayList<Edge> a, ArrayList<Edge> b, ArrayList<Edge> c){
		super(a,b);
		setMatrix(c,C);
	}
	
	public double run(int n, double factor){
		long start = System.currentTimeMillis();
		int[][] sim = square(A);
		C = square(C);
		thinkTime(sim, C, factor);
		double auc = auc(A, B, sim, n);
		time = (System.currentTimeMillis() - start)/1000;
		return auc;
	}

	private void thinkTime(int[][] sim, int[][] C, double factor) {
		if(!judge(sim, C)){
			System.out.println("thinking time is error");
			System.exit(0);
		}
		for(int i=0; i<sim.length; i++){
			for(int j=0; j<sim[i].length; j++){
				if(C[i][j] != 0){
					sim[i][j] += (int)(factor*C[i][j]); 
				}
			}
		}
	}
}
