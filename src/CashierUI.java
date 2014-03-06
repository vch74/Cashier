import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;


public class CashierUI {
	private JFrame mainPanel;
	private JPanel headPanel;
	private JPanel panel1;
	private JLabel kioskNumber;
	private JLabel txtDate;
	private SimpleDateFormat formatter;
	private JLabel versionNumber;
	private JLabel msg;
	private JPanel panel3;
	private JPanel panelGoods;
	private JLabel brcValue;
	private JLabel name;
	private JLabel number;
	private JLabel cost;
	private JPanel subPanel;
	private JPanel headsub;
	private JLabel state;
	private JLabel user;
	private JScrollPane scr;
	private JTable table;
	private JPanel subTotal, subt1, subt2,subt3, subt4;
	private JLabel hDay,vDay, hMounth, vMounth, hTotal, vTotal, hCash, vCash, hChange, vChange;
	private JPasswordField cmd;
	
	private CashierEngine ce;
	
	CashierUI()
	{
		
		//Создаем шрифты
		Font f1 = new Font("Arial",0,60);
		Font f2 = new Font("Arial",0,30);
		Font f3 = new Font("Arial",0,20);
		Font f4 = new Font("Arial",0,15);
		
		
		//создаем главную панель
		mainPanel = new JFrame("Cashier");
		BorderLayout bl = new BorderLayout();
		mainPanel.setLayout(bl);

		//создаем head-панель
		headPanel = new JPanel();
		headPanel.setLayout(new BorderLayout());
		
		//Размещаем первую строку панели
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,3));
		
		kioskNumber = new JLabel();
		kioskNumber.setFont(f4);
		panel1.add(kioskNumber);

		txtDate = new JLabel();
		txtDate.setFont(f4);		
		txtDate.setHorizontalAlignment(SwingConstants.CENTER);
		formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
		//Будет вызываться каждую секунду
		javax.swing.Timer timer = new javax.swing.Timer( 100, new ActionListener()
		  {
		    public void actionPerformed(ActionEvent e)
		    {
		    	txtDate.setText(formatter.format(System.currentTimeMillis()));
		    }
		  } );	
		timer.start();
		panel1.add(txtDate);

		versionNumber = new JLabel();
		versionNumber.setFont(f4);
		versionNumber.setText("Версия: 0.1");
		versionNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(versionNumber);

		headPanel.add("North",panel1);
		
		//Вторая строка панели
		msg = new JLabel();
		msg.setFont(f2);
		msg.setHorizontalAlignment(SwingConstants.CENTER);
		headPanel.add("Center",msg);
		
		//Третья строка панели
		panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		
		panelGoods = new JPanel();
		panelGoods.setLayout(new GridLayout(3,1));
		
		brcValue = new JLabel();
		brcValue.setFont(f3);
		panelGoods.add(brcValue);

		name = new JLabel();
		name.setFont(f2);
		panelGoods.add(name);
		
		number = new JLabel();
		number.setFont(f3);
		panelGoods.add(number);
		
		panel3.add("West",panelGoods);
		
		cost = new JLabel();
		cost.setFont(f1);
		panel3.add("East",cost);

		headPanel.add("South",panel3);		
		mainPanel.add("North",headPanel);
		
		//Панель состояния
		subPanel = new JPanel();
		subPanel.setLayout(new BorderLayout());
		headsub = new JPanel();
		headsub.setLayout(new GridLayout(1,2));
		state = new JLabel();
		state.setFont(f3);
		user = new JLabel();
		user.setFont(f3);
		headsub.add(state);
		headsub.add(user);
		subPanel.add("North",headsub);
		
		table = new JTable(new ModelGoods());
		table.setFocusable(false);
		table.getTableHeader().setFont(f3);
		table.setFont(f3);
		table.setRowHeight(30);
		//table.setBackground(Color.CYAN);
		scr = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(new Dimension(450,400));
		subPanel.add("Center",scr);
		subTotal = new JPanel();
		subTotal.setLayout(new GridLayout(1,4));

		subt1 = new JPanel();
		subt1.setLayout(new GridLayout(4,1));
		hDay = new JLabel();
		hDay.setText("Итого с начала дня");
		hDay.setFont(f3);
		vDay = new JLabel();
		vDay.setFont(f3);
		hMounth = new JLabel();
		hMounth.setText("Итого с начала месяца");
		hMounth.setFont(f3);
		vMounth = new JLabel();
		vMounth.setFont(f3);
		subt1.add(hDay); subt1.add(vDay); subt1.add(hMounth);subt1.add(vMounth);
		subTotal.add(subt1);
		
		subt2 = new JPanel();
		subt2.setLayout(new GridLayout(2,1));
		hTotal = new JLabel();
		hTotal.setText("Итого");
		hTotal.setFont(f3);
		vTotal = new JLabel(); 
		vTotal.setFont(f2);
		subt2.add(hTotal); subt2.add(vTotal);
		subTotal.add(subt2);
		
		subt3 = new JPanel();
		subt3.setLayout(new GridLayout(2,1));
		hCash = new JLabel();
		hCash.setText("Наличные");
		hCash.setFont(f3);
		vCash = new JLabel();
		vCash.setFont(f2);
		subt3.add(hCash); subt3.add(vCash);
		subTotal.add(subt3);
		
		subt4 = new JPanel();
		subt4.setLayout(new GridLayout(2,1));
		hChange = new JLabel();
		hChange.setText("Сдача");
		hChange.setFont(f3);
		vChange = new JLabel();
		vChange.setFont(f2);
		subt4.add(hChange); subt4.add(vChange);
		subTotal.add(subt4);
		
		subPanel.add("South",subTotal);

		mainPanel.add("Center",subPanel);

		//Командная строка
		cmd = new JPasswordField(80);
		mainPanel.add("South",cmd);
		

		//панель должна закрывать весь экран
		mainPanel.setResizable(false);
		mainPanel.setSize(1200,600);
		//if (!mainPanel.isDisplayable()) mainPanel.setUndecorated(true);
		//GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(mainPanel);
		
		//действия панели при закрытии
		mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//показать панель
		mainPanel.setVisible(true);

		//установим ширины столбцов таблицы
		int w=mainPanel.getContentPane().getWidth();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getColumnModel().getColumn(0).setPreferredWidth(w/100*50);
		table.getColumnModel().getColumn(1).setPreferredWidth(w/100*17);
		table.getColumnModel().getColumn(2).setPreferredWidth(w/100*11);
		table.getColumnModel().getColumn(3).setPreferredWidth(w/100*11);
		table.getColumnModel().getColumn(4).setPreferredWidth(w/100*11);
		
		ce = new CashierEngine(this);
		cmd.addActionListener(ce);
		cmd.addKeyListener(ce);
	}

    public JFrame retFrame()
    {
        return mainPanel;
    }
	
	public void showMsg(String textmsg, Color color)
	{
		msg.setText(textmsg);
		msg.setForeground(color);
	}

	public void showKiosk(String kiosk)
	{
		kioskNumber.setText("Киоск: "+kiosk);
	}
	
	public void showGoods(Goods g)
	{
		if(g != null)
		{
			this.brcValue.setText(g.retBarcode());
			this.name.setText(g.name);
			this.number.setText(g.number);
			this.cost.setText(String.format("%10.2f", g.cost));
		}
		else
		{
			this.brcValue.setText("");
			this.name.setText("");
			this.number.setText("");
			this.cost.setText(String.format("%10.2f", 0f));
		}	
	}
	
	public void showKeyBarcode()
	{
		String txt;
		txt = cmd.getText();
		if(txt.length()==0) return;
		for(int i=0;i<txt.length();i++)
			if(txt.charAt(i)<'0' || txt.charAt(i)>'9') return;
		this.brcValue.setText(txt);
	}
	
	public void showBarcode(String barcode)
	{
		brcValue.setText(barcode);
	}
	
	public void showUser(User u)
	{
		this.user.setText(u.retFio());
	}
	
	public void showState(String txtState)
	{
		this.state.setText(txtState);
	}
	
	public void showTotal(float totalMounth, float totalDay, float total, float cash)
	{
		this.vMounth.setText(String.format("%10.2f", totalMounth));
		this.vDay.setText(String.format("%10.2f", totalDay));
		this.vTotal.setText(String.format("%10.2f", total));
		this.vCash.setText(String.format("%10.2f", cash));
		if(cash<total)
			this.vChange.setText(String.format("%10.2f", 0f));
		else
			this.vChange.setText(String.format("%10.2f", cash-total));		
	}
	
	public ModelGoods getModel()
	{
		return (ModelGoods) table.getModel();
	}
	
	public String getTextCommand()
	{
		String txt = cmd.getText();
        if(txt.length()>50) txt="";
		cmd.setText("");
		return txt;
	}
}
