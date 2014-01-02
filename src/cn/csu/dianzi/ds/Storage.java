package cn.csu.dianzi.ds;

import java.util.ArrayList;

import cn.csu.dianzi.bean.Data;
import cn.csu.dianzi.bean.Edge;
import cn.csu.dianzi.xls.*;
import fx.leyu.people.DB.*;

public class Storage {
	
	public static void main(String[] args){
		Storage storage = new Storage();
		//storage.insertAuthorTable();
		//storage.insertContentTable();
		//storage.insertDatasTable();
		storage.insertEdgeTable();
		System.out.println("game over");
	}
	
	public void insertEdgeTable(){
		for(int classes = -1; classes <=13; classes++){
			ArrayList<Data> data = new SelectDianzi(ConDB.getConnection()).data(classes);
			ArrayList<Edge> edge = transferEdgeFormat(data, classes);
			new InsertDianzi(ConDB.getConnection()).edge(edge);
		}
	}
	
	private ArrayList<Edge> transferEdgeFormat(ArrayList<Data> data, int classes) {
		ArrayList<Edge> result = new ArrayList<Edge>();
		for(Data d : data){
			String[] authors = d.authors.trim().split("  ");
			for(int i = 0; i<authors.length-1; i++){
				for(int j = i+1; j < authors.length; j++){
					Edge e = new Edge();
					e.classes = classes;
					e.source = Integer.valueOf(authors[i]);
					e.target = Integer.valueOf(authors[j]);
					if(result.contains(e)){
						int index = result.indexOf(e);
						Edge edge = result.remove(index);
						edge.weight++;
						result.add(edge);
					}else{
						e.weight = 1;
						result.add(e);
					}
				}
			}
		}
		return result;
	}

	public void insertDatasTable() {
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