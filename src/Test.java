import java.util.ArrayList;

import fx.leyu.people.DB.ConDB;
import fx.leyu.people.DB.Insert;
import fx.leyu.people.data.bean.*;
import fx.leyu.people.gather.Collection;
import fx.leyu.people.gather.Handle;


public class Test implements Runnable{
	
	int i;
	public Test(int i){
		this.i = i;
	}
	public static void main(String[] args) throws Exception {
		//129411
		int t = 126160;
		Test t1 = new Test(t+1);
		Test t2 = new Test(t+2);
		Test t3 = new Test(t+3);
		Test t4 = new Test(t+4);
		Test t5 = new Test(t+5);
		Test t6 = new Test(t+6);
		Test t7 = new Test(t+7);
		Test t8 = new Test(t+8);
		Test t9 = new Test(t+9);
		Test t10 = new Test(t+10);
		new Thread(t1).start();
		new Thread(t2).start();
		new Thread(t3).start();
		new Thread(t4).start();
		new Thread(t5).start();
		new Thread(t6).start();
		new Thread(t7).start();
		new Thread(t8).start();
		new Thread(t9).start();
		new Thread(t10).start();
	}

	@SuppressWarnings("static-access")
	public static void go(String s) throws Exception{
		String str= new Collection().getInfo(s);
		Handle h = new Handle();
		String[] t = h.cutString(str);
		
		People p = h.getPeople(t[0]);
		ArrayList<Publication> fx=h.getPublication(t[1]);
		for(Publication yu : fx){
			//System.out.println(yu.id+">>>>>>>>>>>>>ID");
			new Insert(new ConDB().getConnection()).publication(yu);
		}
		new Insert(new ConDB().getConnection()).people(p);
		//System.out.println(p.id+">>"+p.name+">>>>>>>>>>>>>ID");
	}

	public void run() {
		for(; i< 100000000; i = i + 10){
			String str = "http://arnetminer.org/services/person/"+i+"?u=oyster&o=ttf";
			try {
				go(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("success:: "+ i);
			System.gc();
		}
	}

}
