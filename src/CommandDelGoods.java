import java.awt.Color;


public class CommandDelGoods extends CashierCommand 
{
	public CommandDelGoods(CashierEngine media) 
	{
		super(media);
	}

	@Override
	public boolean run(String cmd) 
	{
		int len = cmd.length();
		if(len==0) return false;
		if(cmd.equals("del") || cmd.equals("DEL") || cmd.equals("вуд") || cmd.equals("ВУД"))
		{	
			media.retUI().showMsg("Не забудьте оформить продажу!!!", Color.RED);
			ModelGoods gm = media.retUI().getModel();
			gm.delGoods();
			media.retUI().showGoods(gm.retFirst());
			return true;
		}
		return false;
	}

}
