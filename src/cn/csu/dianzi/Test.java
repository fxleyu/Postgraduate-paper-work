package cn.csu.dianzi;

import java.util.*;

import cn.csu.dianzi.xls.*;
import fx.leyu.tools.UnionFind;

public class Test {
	public static void main(String[] args){
		Xls data = new Xls("./��Ŀ������.xls");	// Xls�����ڶ�ȡExcel�ļ�
		int[] columns = new int[3];
		columns[0] = 0;
		columns[1] = 1;
		columns[2] = 2;
		ArrayList<String[]> content = data.getContent(columns, 1); //���ݣ����������ģ�ʱ��
		content = Filter.contentFilter(content);// �����ݽ��й���
		data.close();
		TreeMap<String, String> peopleMap = new TreeMap<String, String>();
		TreeMap<String, String> paperMap = new TreeMap<String, String>();
		int indexPeople = 0;
		int indexPaper = 0;
		for(String[] info : content){
			if(!peopleMap.containsKey(info[0])){
				peopleMap.put(info[0], String.valueOf(++indexPeople));
			}
			if(!paperMap.containsKey(info[1])){
				paperMap.put(info[1], String.valueOf(++indexPaper));
			}
		}
		new WXls("./people.xls").putContent(peopleMap); // д��
		new WXls("./paper.xls").putContent(paperMap);	// д��
		new WXls("./contents.xls").putContent(content); //д��
		TreeMap<String, String> relationMap = new TreeMap<String, String>();
		for(String[] c : content){
			String value= " "+peopleMap.get(c[0])+" ";
			if(relationMap.containsKey(c[1])){
				String temp = relationMap.get(c[1]);
				if(temp.contains(value)){
					continue;
				}else{
					value = temp + value;
				}
			}
			relationMap.put(c[1], value);
		}
		new WXls("./relation.xls").putContent(relationMap);
		UnionFind find = new UnionFind();
		getValue(find, relationMap);
		int max = 0;
		ArrayList<Integer> roots = find.getSetRoot();
		int[] nums = new int[roots.size()];
		int index = 0;
		int num1 = 0;
		int num2to4 = 0;
		int num5to16 = 0;
		int num17to64 = 0;
		int num65to256 =0;
		int maxRoot = -1;
		for(int r : roots){
			int temp = find.getSetNum(r);
			nums[index++] = temp;
			num1 += get(1, 1, temp);
			num2to4 += get(2, 4, temp);
			num5to16 += get(5, 16, temp);
			num17to64 += get(17, 64, temp);
			num65to256 += get(65, 156, temp);
			if(max < temp){
				max = temp;
				maxRoot = r;
			}
		}
		System.out.println("*****************************************************");
		System.out.println("  ������ " + peopleMap.size());
		System.out.println("  ������ " + paperMap.size());
		System.out.println("  �������� " + content.size() );
		System.out.println("  ��ϵ���� " + relationMap.keySet().size());
		System.out.println("  ��ͨ�������� " + roots.size());
		System.out.println("  ��ͨ�����нڵ�������Ϊ " + max);
		System.out.println("  ��ͨ�����нڵ��������RootΪ" + maxRoot);
		System.out.println("  �ڵ����Ϊ1����ͨ������ " + num1);
		System.out.println("  �ڵ����Ϊ2to4����ͨ������ " + num2to4);
		System.out.println("  �ڵ����Ϊ5to16����ͨ������ " + num5to16);
		System.out.println("  �ڵ����Ϊ17to64����ͨ������ " + num17to64);
		System.out.println("  �ڵ����Ϊ65to256����ͨ������ " + num65to256);
		System.out.println("*****************************************************");
		TreeMap<Integer, Integer> component = new TreeMap<Integer, Integer>();
		for(int ns : nums){
			Integer x = component.get(ns);
			if(x != null){
				component.put(ns, x+1);
			}else{
				component.put(ns, 1);
			}
		}
		Set<Integer> values = component.keySet();
		for(int value : values){
			System.out.println("�ڵ����Ϊ" + value + "����ͨ��������Ϊ" + component.get(value));
		}
		
		
		
		
		//*******************************************************************
		//	20131202
		//*******************************************************************
		//	��ȡ gaint component �ڽڵ��Ա
		ArrayList<Integer> member = find.getSetMember(maxRoot);
		TreeMap<String, String> paperPeople = getPaperSet(member, relationMap);
		TreeMap<String, String> paperYear = getPaperYear(paperPeople.keySet(), content);
		ArrayList<String[]> usingData = getUsingData(paperYear, paperPeople);
		new WXls("./data.xls").putContent(usingData);
		System.out.println("game over������");
		
		
		// 20131205
		
		System.out.println("���ݹ���ȷ���.....");
		System.out.println("��˵��һ����ͨ��������ʵ�����ж������1");
		UnionFind fx = new UnionFind();
		getValue(fx, paperPeople);
		System.out.println("��ͨ�����ĸ���Ϊ" + fx.getSetRoot().size());
		for(int root : fx.getSetRoot()){
			System.out.println("��Ϊ"+ root + "�Ľڵ���Ϊ"+ fx.getSetNum(root));
		}
	}

	private static ArrayList<String[]> getUsingData(
			TreeMap<String, String> paperYear,
			TreeMap<String, String> paperPeople) {
		ArrayList<String[]> result = new ArrayList<String[]>();
		Set<String> papers = paperYear.keySet();
		for(String paper : papers){
			String[] temp = new String[3];
			temp[0] = paper;
			temp[1] = paperPeople.get(paper);
			temp[2] = paperYear.get(paper);
			result.add(temp);
		}
		return result;
	}

	private static TreeMap<String, String> getPaperYear(
			Set<String> paperSet, ArrayList<String[]> content) {
		TreeMap<String, String> result = new TreeMap<String, String>();
		for(String[] info : content){
			if(paperSet.contains(info[1])){
				result.put(info[1], info[2]);
			}
		}
		return result;
	}

	private static TreeMap<String, String> getPaperSet(ArrayList<Integer> member,
			TreeMap<String, String> relationMap) {
		TreeMap<String, String> result = new TreeMap<String, String>();
		Set<String> paperKey = relationMap.keySet();
		for(String paper : paperKey){
			String value = relationMap.get(paper);
			String[] ids = value.trim().split("  ");
			if(ids.length > 1){
				int flag = 0;
				String ps = "";
				for(String id : ids){
					if(member.contains(Integer.valueOf(id))){
						ps += " "+id+" ";
						flag++;
					}
				}
				if(flag > 1){
					result.put(paper, ps.trim());
				}
			}	
		}
 		return result;
	}

	private static int get(int i, int j, int temp) {
		int result = 0;
		if(temp >= i && temp <= j){
			result = 1;
		}
		return result;
	}

	private static void getValue(UnionFind find, TreeMap<String,String> relationMap) {
		Set<String> keys = relationMap.keySet();
		for(String key : keys){
			String value = relationMap.get(key);
			addUnionFind(find, value.trim());
		}
	}

	private static void addUnionFind(UnionFind find, String value) {
		int[] ids = getInt(value.split("  "));
		int min = getMin(ids);
		find.add(min, min);
		for(int id : ids){
			find.add(id, min);
		}
	}

	private static int getMin(int[] ids) {
		int min =ids[0];
		for(int id : ids){
			if(id < min){
				min = id;
			}
		}
		return min;
	}

	private static int[] getInt(String[] ids) {
		int[] result = new int[ids.length];
		try{
			for(int i=0; i<ids.length; i++){
				result[i] = Integer.valueOf(ids[i]);
			}
		}catch(Exception e){
			System.out.println("ids ת��Ϊ���ִ��󣡣�");
		}
		return result;
	}
}
