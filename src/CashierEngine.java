import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class CashierEngine implements ActionListener,KeyListener 
{
	private CashierUI ui;
	private CashierState _currentState;
	
	Vector <CashierCommand> commands;
	Vector <CashierState>   states;
	
	public CashierEngine(CashierUI ui)
	{
		this.ui = ui;
		commands = new Vector<CashierCommand>();
		states = new Vector<CashierState>();
		//регистрация состояний
		regState(new StateInputKiosk("InputKiosk", this));
		regState(new StateInputUser("InputUser", this));
		regState(new StateSales("StateSales",this));
		//регистрация команд
		regCommand(new CommandInputKiosk(this));
		regCommand(new CommandInputUser(this));
		regCommand(new CommandFindGoods(this));
		regCommand(new CommandDelGoods(this));
		regCommand(new CommandKey(this));
		regCommand(new CommandSale(this));
		//устанавливаем начальное состояние
		_currentState = findFromName("InputKiosk");
		_currentState.showState();
		
	}

	public CashierUI retUI()
	{
		return ui;
	}
	
	public CashierState currentState()
	{
		return _currentState;
	}
	
	public void changeState(CashierState state)
	{
		_currentState = state;
		state.showState();
	}
	
	public CashierState findFromName(String name)
	{
		for(CashierState cs:states)
		{	if(name.equals(cs.retName()))	
				return cs;
		}
		return null;
	}
	
	public void regCommand(CashierCommand command)
	{
		commands.add(command);
	}
	
	public void regState(CashierState state)
	{
		states.add(state);
	}
	
	public boolean runCommand(String cmd)
	{
		for(CashierCommand c:commands)
		{
			if(c.run(cmd)) return true;
		}
		return false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		runCommand(ui.getTextCommand());
		_currentState.showState();
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int keycode = e.getKeyCode();
		if(keycode>111 && keycode<124) //функциональные клавиши
		{
			runCommand("F"+e.getKeyCode());
			_currentState.showState();
		}
		else if(keycode==127)			//клавиша delete/del
		{
			runCommand("del");
			_currentState.showState();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		ui.showKeyBarcode();		
	}

}
