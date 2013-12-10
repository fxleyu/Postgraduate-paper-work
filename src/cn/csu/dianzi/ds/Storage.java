package cn.csu.dianzi.ds;

import java.util.ArrayList;

import cn.csu.dianzi.xls.*;
import fx.leyu.people.DB.*;

public class Storage {
	
	public static void main(String[] args){
		Storage storage = new Storage();
		//storage.insertAuthorTable();
		//storage.insertContentTable();
		storage.insertDatasTable();
		System.out.println("game over");
	}
	
	private void insertDatasTable() {
		int[] columns = {0,1,2};
		ArrayList<String[]> data = new Xls("./data.xls").getContent(columns, 0);
		new InsertDianzi(ConDB.getConnection()).datas(data);
	}

	public void insertContentTable(){
		int[] columns = {0, 1, 2};
		ArrayList<String[]> data = new Xls("./contents.xls").getContent(columns, 0);
		new InsertDianzi(ConDB.getConnection()).contents(data);
	}
	public void insertDataTable(){
		int[] columns ={1,2};
		ArrayList<String[]> data = new Xls("./data.xls").getContent(columns, 0);
		new InsertDianzi(ConDB.getConnection()).dataStructrue(data);	
	}
	
	public void insertAuthorTable(){
		int[] columns ={0,1};
		ArrayList<String[]> data = new Xls("./people.xls").getContent(columns, 0);
		new InsertDianzi(ConDB.getConnection()).author(data);
	}
}