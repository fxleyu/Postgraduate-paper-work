package cn.csu.dianzi;

import java.util.*;

import cn.csu.dianzi.xls.*;
import fx.leyu.tools.UnionFind;

public class Main20131204Test {
	public static void main(String[] args){
		System.out.println("	抽取Data.xls数据，组合为边数据，一边Gephi使用");
		Xls data = new Xls("./data.xls");
		ArrayList<String> relationSet =data.getContent(1, 0);
		data.close();
		WXls input = new WXls("./edge.xls");
		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		for(String relation : relationSet){
			String[] nodes = relation.trim().split(" ");
			for(int i = 0; i < nodes.length-1; i++){
				for(int j = i+1; j < nodes.length; j++){
					updateMap(nodes[i], nodes[j], temp);
				}
			}
		}
		Set<String> keys = temp.keySet();
		ArrayList<String[]> result = new ArrayList<String[]>(keys.size());
		for(String key : keys ){
			String[] pair = key.trim().split(" ");
			String[] edge = new String[3];
			edge[0] = pair[0];
			edge[1] = pair[1];
			edge[2] = String.valueOf(temp.get(key));
			result.add(edge);
		}
		input.putContent(result);
		UnionFind f = new UnionFind();
		addFind(f, relationSet);
		System.out.println("*******************************************");
		System.out.println("data数据集中的连通分量数目：" + f.getSetRoot().size());
		System.out.println("********************************************");
		System.out.println("game over!!!!! \n 希望时间不是太久!!!");
	}

	private static void addFind(UnionFind f, ArrayList<String> list) {
		for(String temp : list){
			String[] nums = temp.trim().split(" ");
			addValue(f, nums);
		}
	}

	private static void addValue(UnionFind f, String[] strs) {
		int[] nums = getInt(strs);
		int min = getMin(nums);
		f.add(min, min);
		for(int n : nums){
			f.add(n, min);
		}
	}

	private static int getMin(int[] nums) {
		int min = Integer.MAX_VALUE;
		for(int x : nums){
			if(x<min){
				min =x;
			}
		}
		return min;
	}

	private static int[] getInt(String[] strs) {
		int[] result = new int [strs.length];
		for(int i=0; i<result.length; i++){
			result[i] = Integer.valueOf(strs[i]);
		}
		return result;
	}

	private static void updateMap(String A, String B,
			HashMap<String, Integer> temp) {
		addNodes(A, B, temp);
	}

	private static void addNodes(String a, String b,
			HashMap<String, Integer> temp) {
		String nodes =a + " " + b;
		Integer sum = temp.get(nodes);
		if(sum == null){
			temp.put(nodes, 1);
		}else{
			temp.put(nodes, sum+1);
		}
	}
}
