package cn.csu.dianzi.bean;

public class Edge {
	public int a;
	public int b;
	
	public Edge(int a, int b){
		this.a = a;
		this.b = b;
	}
	
	public boolean equals(Object o){
		if(o instanceof Edge){
			Edge temp = (Edge)o;
			if(temp.a == this.a && temp.b == this.b ){
				return true;
			}else if(temp.a == this.b && temp.b == this.a){
				return true;
			}
		}
		return false;
	}
}
