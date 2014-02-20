import javax.swing.JOptionPane;

public class CommandKey  extends CashierCommand
{

	public CommandKey(CashierEngine media) 
	{
		super(media);
	}

	@Override
	public boolean run(String cmd) 
	{
		if(cmd.charAt(0)=='F' && "StateSales".equals(media.currentState().retName()))
		{
			GoodsModel gm = media.retUI().getModel();
			if(cmd.substring(1).equals("121")) 			//F10-ввод количества
			{
				int quantity = Integer.parseInt(JOptionPane.showInputDialog("Введите количество"));
				if(quantity>=0) gm.retFirst().changeQuantity(quantity);
			} else if(cmd.substring(1).equals("122")) 	//F11-добавить количество
			{
				int quantity = Integer.parseInt(JOptionPane.showInputDialog("Добавить количество"));
				if(quantity>=0) gm.retFirst().changeQuantity(gm.retFirst().retQuantity()+quantity);
			} else if(cmd.substring(1).equals("113"))   //F2-ввод цены
			{
				float cost = Float.parseFloat(JOptionPane.showInputDialog("Введите цену"));
				if(cost>=0) gm.retFirst().changeCost(cost);
				media.retUI().showGoods(gm.retFirst());
			} else if(cmd.substring(1).equals("117"))   //F6-ввод наличных
			{
				float cash = Float.parseFloat(JOptionPane.showInputDialog("Введите сумму наличных от покупателя"));
				((StateSales) media.currentState()).setCash(cash);
			}
			return true;
		}
		return false;
	}

}
