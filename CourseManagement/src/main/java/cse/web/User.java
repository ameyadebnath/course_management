package cse.web;

public class User {
	/*
	 * Class Utilized to fetch and process users of database
	 * */
	
	protected int id;
	protected String username;
	protected String password;
	/*
	 * Type: "1" indicates student account
	 * Type: "2" indicates teacher account
	 * Type: "3" indicates admin account
	 * */
	protected String type;
	
	public User(int id, String username, String password, String type) {
		this.id = id;
		this.password = password;
		this.username = username;
		this.type = type;
	}
	
	public User(String username, String password, String type) {
		this.password = password;
		this.username = username;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
