
public class StateInputUser extends CashierState
{
	
	public StateInputUser(String name, CashierEngine ce) 
	{
		super(name, ce);
	}
	
	@Override
	public void showState() 
	{
		ce.retUI().showKiosk(super.retKiosk());
		
	}

	
}
