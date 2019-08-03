package sit.int690.first;

public class Sale {
	private int id;
	private String no;
	private String name;
	
	public Sale(int id, String no, String name) {
		super();
		this.id = id;
		this.no = no;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		System.out.println(this.id + " " + this.no + " " + this.name);
		return "";
	}
	
}
