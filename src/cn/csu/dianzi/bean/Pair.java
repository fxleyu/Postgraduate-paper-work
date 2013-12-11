package cn.csu.dianzi.bean;

public class Pair {
	public int nodeA;
	public int nodeB;
	public boolean equals(Object o){
		if(o instanceof Pair){
			Pair p = (Pair) o;
			if(p.nodeA == nodeA && p.nodeB == nodeB){
				return true;
			}else if(p.nodeA == nodeB && p.nodeB == nodeA){
				return true;
			}
		}
		return false;
	}
}
