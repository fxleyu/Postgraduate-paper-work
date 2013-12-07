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
 		System.out.println("   �����ڵ���ͨ�����ĸ���Ϊ " + roots.size());
 		System.out.println("   �����ţ����ڵĽڵ���λ   " + max);
 		System.out.println("   �����ţ���rootΪ   " + maxS);
 		System.out.println("   �ڵ㲻С��500����ͨ��������Ϊ  " + mQian);
 		System.out.println("   �ڵ�����500��800֮�����ͨ��������Ϊ  " + mid);
 		System.out.println(" ***********************************************");
	}

	private static void getValue(UnionFind f) {
		Connection con = ConDB.getConnection();
		Select s = new Select(con);
		for(int i=1960; i<1981; i++){
			System.out.println("�����"+i+"��..........");
			s.getTempYear(f, i);
		}
	}
}
