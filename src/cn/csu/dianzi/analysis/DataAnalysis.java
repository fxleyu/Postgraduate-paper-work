package cn.csu.dianzi.analysis;

import java.util.*;

import cn.csu.dianzi.bean.*;
import fx.leyu.people.DB.ConDB;
import fx.leyu.people.DB.SelectDianzi;
import fx.leyu.tools.*;

public class DataAnalysis {

	public static void main(String[] args) {
		new DataAnalysis().entirety();
	}
	
	public void entirety(){
		UnionFind find = new UnionFind();
		ArrayList<Integer> nodes =new ArrayList<Integer>();
		for(int classes = -1; classes <=13; classes++){
			ArrayList<Pair> data = getPairData(classes);
			putValueTo(data, find, nodes);
			printResult(find, nodes, classes);
		}
	}

	private void putValueTo(ArrayList<Pair> data, UnionFind find,
			ArrayList<Integer> nodes) {
		for(Pair p : data){
			int a = p.nodeA;
			int b = p.nodeB;
			if( a < b ){
				int temp = a;
				a = b;
				b = temp;
			}
			if(!nodes.contains(b)){
				find.add(b, b);
				nodes.add(b);
			}
			if(!nodes.contains(a)){
				nodes.add(a);
			}
			find.add(a, b);
		}
	}

	private void printResult(UnionFind find, ArrayList<Integer> nodes, int classes) {
		System.out.println("***************************************");
		System.out.println(" 数据分析片段编号： " + classes);
		System.out.println(" 具有学者数量： " + nodes.size());
		System.out.println(" 连通分量数量：" + find.getSetRoot().size());
		System.out.println("****************************************");
	}

	private ArrayList<Pair> getPairData(int classes) {
		ArrayList<Data> data = new SelectDianzi(ConDB.getConnection()).data(classes);
		ArrayList<Pair> result = new ArrayList<Pair>();
		for(Data d : data){
			String[] nodes = d.authors.trim().split("  ");
			for(int i = 0; i < nodes.length-1; i++){
				for(int j = i+1; j < nodes.length; j++){
					Pair p = new Pair();
					p.nodeA = Integer.valueOf(nodes[i]);
					p.nodeB = Integer.valueOf(nodes[j]);
					if(!result.contains(p)){
						result.add(p);
					}
				}
			}
		}
		return result;
	}

}
