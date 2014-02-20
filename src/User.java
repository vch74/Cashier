
public class User 
{
	private String code;
	private String fio;
	private int role;
	
	public User(String code, String fio, int role)
	{
		this.code = code;
		this.fio = fio;
		this.role = role;
	}
	
	public User(String code)
	{
		this.code = code;
		this.fio = "Иванов И.И.";
		this.role = 0;		
	}
	
	public String retCode()
	{
		return code;
	}
	
	public String retFio()
	{
		return fio;
	}
	
	public int retRole()
	{
		return role;
	}
	
}
