package cn.csu.dianzi.xls;

import java.io.*;
import java.util.*;

import jxl.*;

public class Xls {
	Sheet sheet;
	private InputStream input;
	
	public Xls(String path){
		Workbook book = null;
		try{
			input = new FileInputStream(path);
			book = Workbook.getWorkbook(input);
		}catch(Exception e){
			System.out.println(" 初始化文件异常！");
		}
		sheet = book.getSheet(0);
	}
	
	public ArrayList<String> getContent(int column, int start){
		int[] columns = new int[1];
		columns[0] = column;
		ArrayList<String[]> results = getContent(columns, start);
		ArrayList<String> result = new ArrayList<String>();
		for(String[] r : results){
			result.add(r[0]);
		}
		return result;
	}
	
	public ArrayList<String> getContent(int column){
		return getContent(column, 1);
	}
	
	public ArrayList<String[]> getContent(int[] columns, int start){
		ArrayList<String[]> results = new ArrayList<String[]>();
		for(int row =start; row < sheet.getRows(); row++){
			String[] temps = new String[columns.length];
			for(int i = 0; i < columns.length; i++){
				temps[i] = sheet.getCell(columns[i], row).getContents().trim();
			}
			results.add(temps);
		}
		return results;
	}
	
	public void close(){
		try {
			input.close();
		} catch (IOException e) {
			System.out.println("Excel的流关闭异常");
		}
	}
	
	public void test(){
		ArrayList<String> result = new ArrayList<String>();
		int column = 2;
		for(int row =1; row < sheet.getRows(); row++){
			String content = sheet.getCell(column, row).getContents().trim();
			if(content == ""){
				System.out.println("空");
			}
			if(!result.contains(content)){
				if(content.length() > 1)
				result.add(content);
			}
		}
		int index = 1;
		for(String r : result){
			System.out.println(" test: " + index + " :: " + r);
			index++;
		}
		System.out.println(" time's length is " + result.size());
	}
	
	public static void main(String[] args){
		new Xls("./项目申请书.xls").test();
	}
}
