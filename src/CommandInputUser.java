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
		ParsingCmd pc = new ParsingCmd(cmd);
        if("u".equals(pc.retCodeCmd()) || "г".equals(pc.retCodeCmd()))
		{
			if(media.currentState().retName().equals("InputUser"))
			{	
				String code = pc.parameter;
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
