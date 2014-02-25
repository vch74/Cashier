import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


public class StateSales  extends CashierState
{
	private User user;
	private String kiosk;
	private float cash;
	private float totalMounth;
	private float totalDay;
	
	public void getTotal()
	{
		DB bd = new DB("kiosk.db_s");
		SimpleDateFormat formBeginMounth = new SimpleDateFormat("yyyy-MM-01");
		SimpleDateFormat formBeginDay = new SimpleDateFormat("yyyy-MM-dd");
		long current = System.currentTimeMillis();
		String beginMounth=formBeginMounth.format(current);
		String beginDay=formBeginDay.format(current);

		String op="SELECT SUM(sum) as sum FROM carts WHERE date>'"+beginMounth+"';";
		
		ResultSet rs = bd.query(op);
		
		try 
		{
			while(rs.next())
			{
				this.totalMounth = rs.getFloat("sum");
			}
		} catch (SQLException e) {
			this.totalMounth = 0f;
			this.totalDay = 0f;
			e.printStackTrace();
		}

		op="SELECT SUM(sum) as sum FROM carts WHERE date>'"+beginDay+"'";
		
		ResultSet rs1 = bd.query(op);
		
		try 
		{
			while(rs1.next())
			{
				this.totalDay = rs1.getFloat("sum");
			}
		} catch (SQLException e) {
			this.totalMounth = 0f;
			this.totalDay = 0f;
			e.printStackTrace();
		}
		
		bd.closeQuery();
		
	}
	
	public StateSales(String name, CashierEngine ce) 
	{
		super(name, ce);
		cash = 0f;
		
	}
	
	public void setKiosk(String kiosk)
	{
		this.kiosk = kiosk;
	}
	
	public String retKiosk()
	{
		return(kiosk);
	}
	
	public void setUser(User u)
	{
		user = u;
		cash = 0f;
		getTotal();
	}
	
	public void setCash(float cash)
	{
		this.cash = cash;
	}
	
	public float retCash()
	{
		return(cash);
	}
	
	public float retTotalMounth()
	{
		return(totalMounth);
	}
	
	public float retTotalDay()
	{
		return(totalDay);
	}
	
	@Override
	public void showState()
	{
		ce.retUI().showState("Продажа");
		ce.retUI().showUser(user);
		ce.retUI().showKiosk(kiosk);
		ModelGoods gm = ce.retUI().getModel();
		if(gm.getRowCount()==0)
		{
			ce.retUI().showGoods(null);
			ce.retUI().showBarcode("");
		}
		else
		{
			ce.retUI().showGoods(gm.retFirst());
			ce.retUI().showBarcode(gm.retFirst().retBarcode());
		}
		gm.fireTableDataChanged();
		ce.retUI().showTotal(totalMounth, totalDay, gm.sumOfCart(), cash);
	}

}

