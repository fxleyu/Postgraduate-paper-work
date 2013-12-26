package cn.csu.dianzi.LP.run;

import java.util.ArrayList;

import cn.csu.dianzi.LP.CN;
import cn.csu.dianzi.bean.E;

public class TestCN {
	
	// 运行
	public void run(){
		ArrayList<E> A = select(-1);
		for(int i=0; i<14; i++){
			ArrayList<E> nowList = select(i);
			CN cn = new CN(A, nowList);
			System.out.println("第"+i+"次预测auc值为：" + cn.run(100000));
			System.out.println("时间为(s)："+ cn.getTime());
			System.out.println();
			System.out.println("*******************************");
			add(nowList, A);
		}
	}

	protected void add(ArrayList<E> nowList, ArrayList<E> a) {
		for(E temp : nowList){
			if(!a.contains(temp)){
				a.add(temp);
			}
		}
	}

	protected static ArrayList<E> select(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
