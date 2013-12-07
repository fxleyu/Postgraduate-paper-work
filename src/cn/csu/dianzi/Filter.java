package cn.csu.dianzi;

import java.io.*;
import java.util.*;

public class Filter {
	
	private static ArrayList<String> errorPaper;
	private static String SURNAME;
	private static ArrayList<String> errorName;
	
	static{
		errorPaper = getErrorPaper();
		SURNAME = getSurname();
		errorName = getErrorName();
	}

	private static String peopleFilter(String people){
		people = people.replaceAll("[^\u4E00-\u9FA5]", "");
		if(people.lastIndexOf("著") == people.length()-1){
			people = people.replaceAll("著", "");
		}
		if(people.lastIndexOf("主编") == people.length()-2){
			people = people.replaceAll("主编", "");
		}
		if(errorName.contains(people)){
			return "";
		}
		if(people.length() < 2 || people.length() > 4){
			return "";
		}
		String surname = people.substring(0, 1);
		if(SURNAME.contains(surname) ){
			return people;
		}
		return "";
	}
	
	public static ArrayList<String> peopleFilter(ArrayList<String> people){
		ArrayList<String> result = new ArrayList<String>();
		for(String p : people){
			p = peopleFilter(p);
			if(p.length() > 1 && !result.contains(p)){
				result.add(p);
			}
		}
		return result;
	}

	private static String paperFilter(String paper){
		paper = paper.replaceAll("[^ 0-9a-zA-Z\u4E00-\u9FA5]", "");
		if(errorPaper.contains(paper)){
			return "";
		}
		if(paper.length() < 4){
			return "";
		}
		return paper;
	}
	
	public static ArrayList<String> paperFilter(ArrayList<String> paper){
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> errorPaper = getErrorPaper();
		for(String p : paper){
			p = paperFilter(p);
			if(p.length() > 1 && !result.contains(p)){
				result.add(p);
			}
		}
		System.out.println("错误论文数：：" + errorPaper.size());
		return result;
	}
	
	public static ArrayList<String[]> contentFilter(ArrayList<String[]> contents){
		ArrayList<String[]> results = new ArrayList<String[]> ();
		for(String[] c : contents){
			String people = c[0];
			String paper = c[1];
			String year = c[2];
			people = peopleFilter(people);
			paper = paperFilter(paper);
			String[] temp = {people, paper, year};
			if(people.length() > 1 && paper.length() > 1 && year.length() > 1){
				results.add(temp);
			}
		}
		return results;
	}
	
	private static ArrayList<String> getErrorPaper() {
		return getItem("./data/PaperError.txt");
	}

	private static String getSurname() {
		String surname = "";
		File file = new File("./data/百家姓全文.txt");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line =br.readLine();
			while(line != null && line.length() > 2){
				line = line.replaceAll(" ", "").trim();
				surname += line;
				line = br.readLine();
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return surname.trim();
	}
	
	private static ArrayList<String> getErrorName() {
		return getItem("./data/NameError.txt");
	}
	
	private static ArrayList<String> getItem(String path){
		ArrayList<String> Name = new ArrayList<String> ();
		File file = new File(path);
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line =br.readLine();
			while(line != null){
				line = line.replaceAll(" ", "").trim();
				line = line.replaceAll("论文名：", "").trim();
				if(!Name.contains(line)){
					Name.add(line);
					//System.out.println(line);  //test
				}
				
				
				line = br.readLine();
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Name;
	} 
}
