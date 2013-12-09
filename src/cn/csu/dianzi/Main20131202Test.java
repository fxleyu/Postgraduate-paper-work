package cn.csu.dianzi;

import java.util.*;

import cn.csu.dianzi.xls.*;
import fx.leyu.tools.UnionFind;

public class Main20131202Test {

	//**************************************
	//	���ڷ���������������
	//**************************************
	
	public static void main(String[] args) {
		int[] columns = new int[3];
		for(int i=0; i<columns.length; i++){
			columns[i]=i;
		}
		ArrayList<String[]> content = new Xls("./data.xls").getContent(columns, 0);
		ArrayList<Integer> years = getYear(content);
		ArrayList<Integer> people = getPeople(content);
		
		
		UnionFind f = new UnionFind();
		addUnionFindValue(f, content);
		System.out.println("��ͨ�����ĸ���Ϊ" + f.getSetRoot().size());
		
		//************************************
		//	�������ݷ���������
		//************************************
		System.out.println("*******************************************");
		System.out.println("	������������Ϊ: " + content.size());
		System.out.println("	��������ڵ���: " + people.size());
		System.out.println("	�������ķ�����������: " + years.size());
		System.out.println("	������������С�����Ϊ: " + getMin(years));
		System.out.println("	�����������������Ϊ: " + getMax(years));
		System.out.println("	�����������Ƿ��м�����: " + (years.size() != (getMax(years) - getMin(years) + 1)));		
		System.out.println("*******************************************");
		
		TreeMap<Integer, Integer> yearPaper = getYearPaper(years, content);
		years = sort(years);
		for(int year : years){
			System.out.println("��" + year + "��������ķ�������Ϊ: " +  yearPaper.get(year));
		}
	}

	private static void addUnionFindValue(UnionFind f,
			ArrayList<String[]> content) {
		for(String[] c : content){
			String relation = c[1]; 
			int[] nodes = getInt(relation);
			f.add(nodes);
		}
	}

	private static int[] getInt(String relation) {
		String[] temp = relation.split("  ");
		int[] result = new int[temp.length];
		for(int i=0; i< temp.length; i++){
			result[i] = Integer.valueOf(temp[i]);
		}
		return result;
	}

	private static ArrayList<Integer> sort(ArrayList<Integer> years) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		while(!years.isEmpty()){
			result.add(removeMax(years));
		}
		return result;
	}

	private static Integer removeMax(ArrayList<Integer> years) {
		int max = getMax(years);
		if(!years.remove(Integer.valueOf(max))){
			System.out.println("error : ���󣡣�����");
			System.exit(0);
		}
		return max;
	}

	private static TreeMap<Integer, Integer> getYearPaper(
			ArrayList<Integer> years, ArrayList<String[]> contents) {
		TreeMap<Integer, Integer> result = new TreeMap<Integer, Integer>();
		for(int year : years){
			result.put(year, 0);
		}
		for(String[] content : contents){
			int tempYear = Integer.valueOf(content[2]);
			result.put(tempYear, result.get(tempYear)+1);
		}
		return result;
	}

	private static ArrayList<Integer> getPeople(ArrayList<String[]> contents) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(String[] content : contents){
			String[] ids = content[1].trim().split("  ");
			for(String id : ids){
				int num = Integer.valueOf(id);
				if(!result.contains(num)){
					result.add(num);
				}
			}
		}
		return result;
	}

	private static ArrayList<Integer> getYear(ArrayList<String[]> contents) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(String[] content : contents){
			int year = Integer.valueOf(content[2]);
			if(!result.contains(year)){
				result.add(year);
			}
		}
		return result;
	}

	private static int getMin(ArrayList<Integer> years) {
		int min = 9999;
		for(int year : years){
			if(year < min){
				min = year;
			}
		}
		return min;
	}

	private static int getMax(ArrayList<Integer> years) {
		int max = -1;
		for(int year : years){
			if(year > max){
				max = year;
			}
		}
		return max;
	}

}
