package model;

public class Sale {
	private int id;
	private String no;
	private String name;
	public final int SALE_TRANSECTION=20;
	public final int ITEM_SIZE=50;
	
	public Sale() {
		super();
	}
	
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
	public byte[] getNoInBytes() {
		byte[] transactionBytes = new byte[SALE_TRANSECTION];
		System.arraycopy(this.no.getBytes(), 0, transactionBytes, 0, this.no.length());

		return transactionBytes;
	}
	public byte[] getNameInBytes() {
		byte[] transactionBytes = new byte[ITEM_SIZE];
		System.arraycopy(this.name.getBytes(), 0, transactionBytes, 0, this.name.length());
		
		return transactionBytes;
	}

	@Override
	public String toString() {
		System.out.println("Sale [id=" + id + ", no=" + no + ", name=" + name + "]");
		return "Sale [id=" + id + ", no=" + no + ", name=" + name + "]";
	}
	
	
}
