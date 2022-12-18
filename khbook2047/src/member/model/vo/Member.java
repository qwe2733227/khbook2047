package member.model.vo;

public class Member implements java.io.Serializable {
//	ID     NOT NULL VARCHAR2(100) 
//	PASSWD          VARCHAR2(100) 
//	NAME            VARCHAR2(100) 
//	EMAIL           VARCHAR2(200)
	private static final long serialVersionUID = 1L;
	private String id;
	private String passwd;
	private String name;
	private String email;
	
	public Member() {}
	public Member(String id, String passwd, String name, String email) {
		super();
		this.id= id;
		this.passwd= passwd;
		this.name= name;
		this.email= email;
	}
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", passwd=" + passwd + ", name=" + name + ", email=" + email + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
