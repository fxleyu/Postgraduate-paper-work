package cn.csu.dianzi.matlab;

import java.util.*;

import cn.csu.dianzi.xls.WXls;
import cn.csu.dianzi.xls.Xls;

public class Data {
	
	public static void main(String[] args){
		transform("edge.xls", "fx.xls");
		System.out.println("game over!!!");
	}

	private static void transform(String src, String dest) {
		Xls read = new Xls(src);
		ArrayList<String[]> content = read.getContent(new int[]{0,1,2}, 0);
		HashMap<Integer, Integer> transformNumber = new HashMap<Integer, Integer>();
		ArrayList<int[]> temp = new ArrayList<int[]>();
		for(String[] c : content){
			int[] cell = new int[3];
			cell[0] = Integer.valueOf(c[0].trim());
			cell[1] = Integer.valueOf(c[1].trim());
			cell[2] = Integer.valueOf(c[2].trim());
			putTransform(cell[0], transformNumber);
			putTransform(cell[1], transformNumber);
			temp.add(cell);
		}
		content = new ArrayList<String[]>();
		for(int[] t : temp){
			String[] cell = new String[3];
			cell[0] = String.valueOf(transformNumber.get(t[0]));
			cell[1] = String.valueOf(transformNumber.get(t[1]));
			cell[2] = String.valueOf(t[2]);
			content.add(cell);
		}
		WXls write = new WXls(dest);
		write.putContent(content);
	}

	private static void putTransform(int a,
			HashMap<Integer, Integer> map) {
		if(!map.keySet().contains(a)){
			map.put(a, map.keySet().size()+1);
		}
	}
}
