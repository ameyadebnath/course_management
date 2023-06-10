package cse.web;

public class Student {
	/*Class to handle students from and to database
	 * */
	protected int id;
	protected String name,reg;
	
	public Student(int id, String name, String reg) {
		super();
		this.id = id;
		this.name = name;
		this.reg = reg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}
}
