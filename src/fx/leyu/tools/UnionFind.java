package fx.leyu.tools;

import java.util.ArrayList;



/**
 * 		使用说明：：
 * 			增加节点对时，一定要确保 root， root 节点对在UnionFind中
 * 		
 * */

public class UnionFind {
	int[][] data;
	int capacity;
	int length;
	public UnionFind(){
		data = new int[10000][2];
		capacity = 10000;
		length =0;
	}
	
	public void add(int value, int root){
		// Value == data[i][0]
		// Value == data[i][1]
		// Root == data[i][0]	
		// Root == data[i][1]	不考虑
		int i=0;
		while(i < length){
			if(value == data[i][0]){
				if(data[i][1] != root){
					if(root > data[i][1]){
						updateRoot(root, data[i][1]);
					}else{
						updateRoot(data[i][1], root);
					}
				}
				break;
			}
			if(value == data[i][1]){
				if(root != value){
					updateRoot(data[i][1], root);
				}
				break;
			}
			if(root == data[i][0]){
				if(data[i][1] != root){
					root = data[i][1];
				}
			}
			i++;
		}
		if(i == length){
			if(capacity == length){
				capacity += 10000;
				int[][] temp = new int [capacity][2];
				copy(data, temp);
				data = temp;
			}
			data[i][0] = value;
			data[i][1] = root;
			length++;
		}
	}
	
	public ArrayList<Integer> getSetRoot(){
		ArrayList<Integer> results = new ArrayList<Integer>();
		for(int i=0; i<length; i++){
			int temp = data[i][1];
			if( !results.contains(temp)){
				results.add(temp);
			}
		}
		return results;
	}
	
	public int getSetNum(int root){
		int num = 0;
		for(int i=0; i<length; i++){
			if(data[i][1] == root){
				num++;
			}
		}
		return num;
	}
	
	public ArrayList<Integer> getSetMember(int root){
		ArrayList<Integer> members = new ArrayList<Integer>();
		for(int i=0; i<length; i++){
			if(data[i][1] == root && !members.contains(data[i][0])){
				members.add(data[i][0]);
			}
		}
		return members;
	}
	
	private void copy(int[][] as, int[][] bs) {
		for(int i=0; i<as.length; i++){
			bs[i][0] = as[i][0];
			bs[i][1] = as[i][1];
		}
	}

	private void updateRoot(int big, int small) {
		for(int i=0; i< length; i++){
			if(data[i][1] == big){
				data[i][1] = small;
			}
		}
	}
}