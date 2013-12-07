package fx.leyu.datacleaning;

import java.sql.*;
import java.util.*;

import fx.leyu.people.DB.*;
import fx.leyu.tools.UnionFind;

public class DataClean {	
	
	public DataClean(){
	}
	
	public static void main(String[] args){
		UnionFind unionP = new UnionFind();
		getValue(unionP);
		ArrayList<Integer> roots = unionP.getSetRoot();
		int max = 0;
		int maxS = 0;
		int mQian = 0;
		int mid = 0;
 		for(int i : roots){
 			int temp = unionP.getSetNum(i);
			if (temp >= 500){
				mQian++;
			}
 			if(max < temp){
				max = temp;
				maxS = i;
			}
 			if(temp >= 500 && temp <= 800){
 				mid++;
 			}
		}
 		System.out.println(" ***********************************************");
 		System.out.println("   网络内的连通分量的个数为 " + roots.size());
 		System.out.println("   最大的团，其内的节点数位   " + max);
 		System.out.println("   最大的团，其root为   " + maxS);
 		System.out.println("   节点不小于500的连通分量个数为  " + mQian);
 		System.out.println("   节点数在500与800之间的连通分量个数为  " + mid);
 		System.out.println(" ***********************************************");
	}

	private static void getValue(UnionFind f) {
		Connection con = ConDB.getConnection();
		Select s = new Select(con);
		for(int i=1960; i<1981; i++){
			System.out.println("处理第"+i+"年..........");
			s.getTempYear(f, i);
		}
	}
}
