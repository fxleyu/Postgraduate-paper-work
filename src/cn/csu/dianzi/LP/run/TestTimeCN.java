package cn.csu.dianzi.LP.run;

import java.util.ArrayList;

import cn.csu.dianzi.LP.TimeCN;
import cn.csu.dianzi.bean.Edge;

public class TestTimeCN extends TestCN{
	public void run(double k){
		ArrayList<Edge> A = select(-1);
		ArrayList<Edge> C = select(0);
		add(C, A);
		for(int i=1; i<14; i++){
			ArrayList<Edge> nowList = select(i);
			TimeCN cn = new TimeCN(A, nowList, C);
			System.out.println("第"+i+"次预测auc值为：" + cn.run(100000));
			System.out.println("时间为(s)："+ cn.getTime());
			System.out.println();
			System.out.println("*******************************");
			add(nowList, A);
			C = nowList;
		}
	}
}
