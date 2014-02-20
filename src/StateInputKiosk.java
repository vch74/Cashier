import java.awt.Color;


public class StateInputKiosk extends CashierState 
{
		
	public StateInputKiosk(String name, CashierEngine ce) 
	{
		super(name, ce);
	}

	@Override
	public void showState() 
	{
		//ce.retUI().showKiosk();
		ce.retUI().showMsg("Зарегистрируйте киоск!", Color.RED);
	}
		
}
