package cn.csu.dianzi.LP;

import java.util.*;

import cn.csu.dianzi.bean.*;

/**
 * 	A -> B
 * 	已知状态A，增加状态B
 * 	CN算法计算出气AUC
 * */
public class CN {
	
	protected int[][] A;
	protected int[][] B;
	protected HashMap<Integer, Integer> transition;
	protected long time;

	public CN(ArrayList<Edge> a, ArrayList<Edge> b){
		setMap(a, b);
		setMatrix(a, A);
		setMatrix(b, B, A);
	}
	
	public double run(int n){
		long start =System.currentTimeMillis();
		int[][] sim = square(A);
		double auc = auc(A, B, sim, n);
		time = (System.currentTimeMillis() - start)/1000;
		return auc;
	}
	
	public long getTime(){
		return time;
	}

	protected double auc(int[][] A, int[][] B, int[][] sim, int n) {
		ArrayList<Integer> ns = getNone(A, B, sim);
		ArrayList<Integer> bs = getBs(B,sim);
		double sum = 0;
		for(int i=0; i<n; i++){
			int nIndex = (int)(ns.size()*Math.random());
			int bIndex = (int)(bs.size()*Math.random());
			if(ns.get(nIndex) == bs.get(bIndex)){
				sum += 0.5;
			}else if(ns.get(nIndex) < bs.get(bIndex)){
				sum += 1;
			}
		}
		return sum/n;
	}

	private ArrayList<Integer> getBs(int[][] B, int[][] sim) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i=0; i < B.length; i++){
			for(int j=0; j<i; j++){
				if(B[i][j] != 0){
					result.add(sim[i][j]);
				}
			}
		}
		return result;
	}

	private ArrayList<Integer> getNone(int[][] A, int[][] B, int[][] sim) {
		if(!judge(A, B)){
			System.out.println("getNone");
			System.exit(0);
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i=0; i<A.length; i++){
			for(int j=0; j<i; j++){
				if(A[i][j] == 0 && B[i][j]==0){
					result.add(sim[i][j]);
				}
			}
		}
		return null;
	}

	protected int[][] square(int[][] X) {
		int[][] result = new int[X.length][X.length];
		for(int i=0; i < result.length; i++){
			for(int j=0; j<result[i].length; j++){
				int temp = 0;
				for(int k=0; k < result.length; k++){
					temp += X[i][k]*X[k][j];
				}
				result[i][j] = temp;
			}
		}
		return result;
	}

	protected void setMatrix(ArrayList<Edge> x, int[][] X) {
		for(Edge temp : x){
			int i = transition.get(temp.a);
			int j = transition.get(temp.b);
			if(X[i][j] == 0){
				X[i][j] = 1;
			}
			if(X[j][i] == 0){
				X[j][i] = 1;
			}
		}
	}
	
	protected void setMatrix(ArrayList<Edge> x, int[][] X, int[][] T) {
		setMatrix(x, X);
		remove(T, X);
	}

	private void remove(int[][] T, int[][] X) {
		if(!judge(T, X)){
			System.out.println("remove");
			System.exit(0);
		}
		for(int i=0; i<T.length; i++){
			for(int j=0; j<T[i].length; j++){
				if(T[i][j]!=0 && X[i][j] != 0){
					X[i][j] = 0;
				}
			}
		}
	}

	protected boolean judge(int[][] A, int[][] B) {
		if(A.length != B.length && A[0].length != B[0].length && A.length != A[0].length){
			System.out.println(" 出现错误！建议写入日志");
			return false;
		}
		return true;
	}

	private void setMap(ArrayList<Edge> a, ArrayList<Edge> b) {
		setMap(a);
		setMap(b);
	}

	private void setMap(ArrayList<Edge> x) {
		int index = transition.size();
		for(Edge temp : x){
			if(!transition.containsKey(temp.a)){
				transition.put(temp.a, index++);
			}
			if(!transition.containsKey(temp.b)){
				transition.put(temp.b, index++);
			}
		}
	}
}
