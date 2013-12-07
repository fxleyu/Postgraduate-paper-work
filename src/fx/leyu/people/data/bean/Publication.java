package fx.leyu.people.data.bean;

public class Publication {

	public int id;
	public String title;
	public int startpage;
	public int endpage;
	public int pubyear;
	public int pages;
	public String authors;
	public int citedby;
	public String pubkey;
	public String jconfname;
	public String authorids;
	public String abs;
	
	public Publication(int id){
		this.id = id;
	}
}
