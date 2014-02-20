
public abstract class CashierCommand 
{
	CashierEngine media;
	
	public CashierCommand(CashierEngine media)
	{
		this.media = media;
	}
	
	public abstract boolean run(String cmd);
	
}
