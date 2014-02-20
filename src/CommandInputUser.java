import java.awt.Color;


public class CommandInputUser extends CashierCommand
{

	public CommandInputUser(CashierEngine media) 
	{
		super(media);
	}

	@Override
	public boolean run(String cmd) 
	{
		if(cmd.charAt(0)=='u' || cmd.charAt(0)=='г')
		{
			if(media.currentState().retName().equals("InputUser"))
			{	
				String code = cmd.substring(1);
				User u = new User(code);
				if(u.retRole() == 0)
				{	
					StateSales us = (StateSales) media.findFromName("StateSales");
					us.setKiosk(((StateInputUser) media.currentState()).retKiosk());
					us.setUser(u);
					media.changeState(us);
					media.retUI().showMsg("Можно вводить продажи!", Color.GREEN);
					return true;
				}
			}	
			
		}
		return false;
	}

}
