
public abstract class CashierState 
{
	String name;
	String comm;
	CashierEngine ce;

	private User user;
	private String kiosk;
	
	public CashierState(String name, CashierEngine ce)
	{
		this.name = name;
		this.ce   = ce;
	}
	
	public String retName()
	{
		return name;
	}
	
	public void setComm(String comm)
	{
		this.comm = comm;
	}
	
	public String retComm()
	{
		return comm;
	}	
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public User retUser()
	{
		return user;
	}
	
	public void setKiosk(String kiosk)
	{
		this.kiosk = kiosk;
	}
	
	public String retKiosk()
	{
		return kiosk;
	}
	
	public abstract void showState();
		
}
