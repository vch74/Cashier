import java.awt.Color;


public class CommandInputKiosk extends CashierCommand
{

	public CommandInputKiosk(CashierEngine media) 
	{
		super(media);
	}

	@Override
	public boolean run(String cmd) 
	{
		if(cmd.charAt(0)=='r' || cmd.charAt(0)=='к')
		{
			if(media.currentState().retName().equals("InputKiosk"))
			{	
				StateInputUser us = (StateInputUser) media.findFromName("InputUser");
				us.setKiosk(cmd.substring(1));
				media.changeState(us);
				media.retUI().showMsg("Зарегистрируйте Пользователя!", Color.RED);
				return true;
			}	
		}
		return false;
	}

}

