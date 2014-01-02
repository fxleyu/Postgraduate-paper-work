package fx.leyu.tools;

import java.util.*;



/**
 * 		使用说明：：
 * 			增加节点对时，一定要确保 root， root 节点对在UnionFind中
 * 		
 * */

public class UnionFind {
	TreeMap<Integer, Integer> data;
	int capacity;
	int length;
	public UnionFind(){
		data = new TreeMap<Integer, Integer>();
	}
	
	public void add(int[] nodes){
		int min = getMin(nodes);
		add(min , min);
		for(int node : nodes){
			add(node, min);
		}
	}
	
	private int getMin(int[] nodes) {
		int min = Integer.MAX_VALUE;
		for(int node : nodes){
			if(node < min){
				min = node;
			}
		}
		return min;
	}

	public void add(int value, int root){
		// Value == data[i][0]
		// Value == data[i][1]
		// Root == data[i][0]	
		// Root == data[i][1]	不考虑
		if(data.containsKey(root)){
			root = data.get(root);
		}
		if(data.containsKey(value)){
			int valueRoot = data.get(value);
			if(valueRoot > root){
				updateRoot(valueRoot, root);
				return;
			}else if(valueRoot < root){
				updateRoot(root, valueRoot);
				return;
			}
		}
		data.put(value, root);
	}
	
	public ArrayList<Integer> getSetRoot(){
		ArrayList<Integer> results = new ArrayList<Integer>();
		Set<Integer> temp = data.keySet();
		for(int t : temp){
			if(!results.contains(data.get(t))){
				results.add(data.get(t));
			}
		}
		return results;
	}
	
	public int getSetNum(int root){
		return getSetMember(root).size();
	}
	
	public ArrayList<Integer> getSetMember(int root){
		ArrayList<Integer> members = new ArrayList<Integer>();
		Set<Integer> keys = data.keySet();
		for(int key :keys){
			if(data.get(key).equals(root)){
				if(!members.contains(key)){
					members.add(key);
				}
			}
		}
		return members;
	}

	private void updateRoot(int big, int small) {
		Set<Integer> keys = data.keySet();
		for(int key : keys){
			if(data.get(key).equals(big)){
				data.put(key, small);
			}
		}
	}
}