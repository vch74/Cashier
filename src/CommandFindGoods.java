import java.awt.Color;

public class CommandFindGoods  extends CashierCommand
{

	public CommandFindGoods(CashierEngine media) 
	{
		super(media);
	}

	@Override
	public boolean run(String cmd) 
	{
		if(media.currentState().retName().equals("StateSales"))
		{	
			int len = cmd.length();
			if(len==0) return false;
			for(int i=0; i<len ;i++)
			{	
				if(cmd.charAt(i)<'0' || cmd.charAt(i)>'9') return false;
			}
			Goods g = new Goods(cmd);
			media.retUI().showGoods(g);
			if(g.retCode().equals("")) return false;
			media.retUI().showMsg("Не забудьте оформить продажу!!!", Color.RED);
			ModelGoods gm = media.retUI().getModel();
			gm.addGoods(g);
			return true;
		}
		return false;
	}

}
