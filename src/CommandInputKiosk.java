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
		ParsingCmd pc = new ParsingCmd(cmd);
        if("r".equals(pc.retCodeCmd()) || "к".equals(pc.retCodeCmd()))
		{
			if(media.currentState().retName().equals("InputKiosk"))
			{	
				StateInputUser us = (StateInputUser) media.findFromName("InputUser");
				us.setKiosk(pc.retParameter());
				media.changeState(us);
				media.retUI().showMsg("Зарегистрируйте Пользователя!", Color.RED);
				return true;
			}	
		}
		return false;
	}

}

