package cn.csu.dianzi.bean;

public class Edge {
	
	public int source;
	public int target;
	public int weight;
	public int classes;
	
	public boolean equals(Object o){
		if(o instanceof Edge){
			Edge d = (Edge) o;
			if(d.source == source && d.target == target && d.classes == classes){
				return true;
			}else if(d.source == target && d.target == source && d.classes == classes){}
		}
		return false;	
	}
	
}
