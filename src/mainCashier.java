import javax.swing.*;

public class mainCashier 
{
	public mainCashier()
	{
		new CashierUI();

	}
	
	public static void main(String args[]) 
	{
		SwingUtilities.invokeLater(new Runnable()
			{
				public void run()
				{
					new mainCashier();
					
				}
			}
		);
	}

}