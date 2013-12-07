package fx.leyu.handle;

import fx.leyu.people.DB.ConDB;
import fx.leyu.people.DB.TempPublic;

public class PublicationHandle {
	
	public static void main(String[] args){
		System.out.println("start >>>>");
		long start =System.currentTimeMillis();
		int min = 0;
		int s = 0;
		for(int year = 1960; year < 1980; year++){
			System.out.println("handle   "+ year +"  year  ...........");
			new TempPublic(ConDB.getConnection()).publication(year);
			long temp =System.currentTimeMillis();
			min = (int)(temp - start)/(1000*60) - min;
			s = ((int)(temp - start)%(1000*60))/1000 - s ;
			System.out.println("handle "+year+" year  :  "+min+" min : "+ s +"s");
		}
		long end =System.currentTimeMillis();
		min = (int)(end - start)/(1000*60);
		s = ((int)(end - start)%(1000*60))/1000;
		System.out.println("handle all things  :  "+min+" min : "+ s +"s");
	}
}
