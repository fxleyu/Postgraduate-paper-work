package fx.leyu.people.DB;

import java.sql.*;
import java.util.*;
import fx.leyu.tools.UnionFind;

public class Select {
	Connection con;
	
	public Select(Connection con){
		this.con = con;
	}
	
	public void getTempYear(UnionFind f , int year ){
		String table = "temp_"+year;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select AuthorIds from "+table);
			int index = 1;
			while(rs.next()){
				System.out.println("�����ٶ�̫�����ӵ���� : ��� "+year+ ", �� "+ index +"��................");
				index++;
				String temp = rs.getString(1);
				temp = temp.replaceAll(",Abs", "");
				if(temp.contains(",")){
					String[] nums = temp.split(",");
					ArrayList<Integer> numList = new ArrayList<Integer>();
					for(String str : nums){
						try{
							int num = Integer.valueOf(str);
							if(num > 0 && !numList.contains(num)){
								numList.add(num);
							}
						}catch(Exception e){
							System.out.println("����ID���󣡣�");
						}
					}
					if(numList.size()>1){
						int[] results = new int[numList.size()];
						int i=0;
						for(int r : numList){
							results[i] = r;
							i++;
						}
						int min = getMin(results);
						f.add(min, min);   //Ԥ���ڰ�root�ı�
						for(int value : results){
							f.add(value, min);
						}
					}
				}
			}
		} catch (SQLException e) {
			System.out.println(" ��ȡ���ݵ��Ӵ���");
			e.printStackTrace();
		}
		
	}

	private int getMin(int[] nums) {
		int min = nums[0];
		for(int num : nums){
			if(num < min){
				min = num;
			}
		}
		return min;
	}
}