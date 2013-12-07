package fx.leyu.people.gather;

import java.io.*;
import java.net.*;

public class Collection {
	public String getInfo(String path) throws Exception{
		URL url=new URL(path);
		URLConnection uc=url.openConnection();
		InputStream is=uc.getInputStream();
		BufferedInputStream bis=new BufferedInputStream(is);
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
		byte [] b=new byte[10240];
		int len=-1;
		while((len=bis.read(b))!=-1){
			bos.write(b, 0, len);
		}
		is.close();
		return new String(bos.toByteArray(), "UTF-8");
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(new Collection().getInfo("http://arnetminer.org/services/person/79160?u=oyster&o=ttf"));
	}
}
