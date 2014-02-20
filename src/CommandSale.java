import java.awt.Color;
import java.text.SimpleDateFormat;

public class CommandSale extends CashierCommand
{

	public CommandSale(CashierEngine media) 
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
			if(cmd.equals("sale") || cmd.equals("SALE") || cmd.equals("ыфду") || cmd.equals("ЫФДУ"))
			{	
				media.retUI().showMsg("", Color.RED);
				StateSales ss = (StateSales) media.currentState();
				GoodsModel gm = media.retUI().getModel();
				
				SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				long currentDate = System.currentTimeMillis();
				String curDate = form.format(currentDate);

				int vgr = gm.getRowCount();
				for(int ids=vgr-1;ids>=0;ids--)
				{
                    if(gm.retGoods(ids).sale(curDate, ss.retKiosk())) gm.delGoods(ids);
				}
				ss.getTotal();	//пересчитать итоги
				return true;
			}	
		}
		return false;
	}

}
