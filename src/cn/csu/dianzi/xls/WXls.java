package cn.csu.dianzi.xls;

import java.io.*;
import java.util.*;

import jxl.*;
import jxl.write.*;

public class WXls {
	
	WritableSheet sheet;
	WritableWorkbook book;
	public WXls(String path){
		try{
			book = Workbook.createWorkbook(new File(path));
		}catch(Exception e){
			System.out.println("Warnning ≥ı ºªØ¥ÌŒÛ!!");
		}
		sheet = book.createSheet("author", 0);
	}
	
	public void putContent(TreeMap<String,String> content){
		Set<String> ids = content.keySet();
		int index = 0;
		for(String id : ids){
			Label lab00 = new Label(0, index, id);
			Label lab01 = new Label(1, index, content.get(id));
			index++;
			try {
				sheet.addCell(lab00);
				sheet.addCell(lab01);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		try {
			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void putContent(ArrayList<String[]> contents){
		int index = 0;
		for(String[] content : contents){
			for(int i = 0; i < content.length; i++){
				Label lab = new Label(i, index, content[i]);
				try {
					sheet.addCell(lab);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			index++;
			 
		}
		try {
			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
