package fx.leyu.people.gather;

import java.util.*;

import fx.leyu.people.data.bean.*;

public class Handle {

	public String[] cutString(String context){
		String[] result = new String[2];
		result[0] = "";
		result[1] = "";
		String[] temp = context.split("\"PubList\":");
		String t= temp[0];
		if(temp.length<2){
			System.out.println(context);
			return result;
		}
		if(temp[1].contains(",\"Hindex\":")){
			temp = temp[1].split(",\"Hindex\":");
			result[0] = t+"\"Hindex\":"+temp[1];
		}
		result[1] = temp[0];
		return result;
	}
	
	public People getPeople(String str){
		str = str.replaceAll("[\\[{}\\]\"]", "");
		str = str.replace("http:", "");
		String temp[] = str.split(":");
		HashMap<String, String> result = new HashMap<String,String>();
		String key="";
		String value="";
		for(int i = 0; i < temp.length; i++){
			if(i == 0){
				key=temp[i].toLowerCase();
				continue;
			}
			if(i == temp.length-1){
				value = temp[i];
				result.put(key, value);
				value = "";
				continue;
			}
			if(!temp[i].contains(",")){
				value=temp[i]+":";
				continue;
			}
			String[] tem = temp[i].split(",");
			for(int j=0; j<tem.length-2; j++){
				value +=tem[j]+",";
			}
			value +=tem[tem.length-2];
			result.put(key, value);
			value = "";
			key = tem[tem.length-1].toLowerCase();
		}
		String id = result.get("id");
		String name = result.get("name");
		People people=null;
		if(id != null && name != null){
			people = new People(Integer.valueOf(id), name);
		}
		if(people!=null){
			people.alias = result.get("alias");
			people.phone = result.get("phone");
			people.fax = result.get("fax");
			people.email = result.get("email");
			people.homepage = result.get("homepage");
			people.position = result.get("position");	
		}
		if(people == null)
			people = new People( -1 , "fxleyu");
		//System.out.println(people.id +">>>"+people.name);
		return people;
	}
	
	public ArrayList<Publication> getPublication(String str){
		ArrayList<Publication> list = new ArrayList<Publication>();
		String[] temps =str.split("\"Id\"");
		String[] t = new String[temps.length-1];
		for(int i=0;i<t.length;i++){
			t[i]="\"Id\""+temps[i+1];
		}
		for(String fx : t){
			fx = fx.replaceAll("[\\[{}\\]\"]", "");
			String temp[] = fx.split(":");
			HashMap<String, String> result = new HashMap<String,String>();
			String key="";
			String value="";
			for(int i = 0; i < temp.length; i++){
				if(i == 0){
					key=temp[i].toLowerCase();
					continue;
				}
				if(i == temp.length-1){
					value = temp[i];
					result.put(key, value);
					value = "";
					continue;
				}
				if(!temp[i].contains(",")){
					value=temp[i]+":";
					continue;
				}
				String[] tem = temp[i].split(",");
				for(int j=0; j<tem.length-2; j++){
					value +=tem[j]+",";
				}
				value +=tem[tem.length-2];
				result.put(key, value);
				value = "";
				key = tem[tem.length-1].toLowerCase();
			}
			String id = result.get("id");
			Publication publication=null;
			if(id != null){
				publication = new Publication(Integer.valueOf(id));
			}
			if(publication!=null){
				publication.title = result.get("title");
				publication.authors = result.get("authors");
				publication.pubkey = result.get("pubkey");
				publication.abs = result.get("abs");
				publication.jconfname = result.get("jconfname");
				publication.authorids = result.get("authorids");
				if(result.get("startpage") != null)
				publication.startpage = Integer.valueOf(result.get("startpage"));
				if(result.get("endpage") != null)
				publication.endpage = Integer.valueOf(result.get("endpage"));
				if(result.get("pubyear") != null)
				publication.pubyear = Integer.valueOf(result.get("pubyear"));
				if(result.get("pages") != null)
				publication.pages = Integer.valueOf(result.get("pages"));
				if(result.get("citedby") != null)
				publication.citedby = Integer.valueOf(result.get("citedby"));
				list.add(publication);
			}
		}
		return list;
	}
}
