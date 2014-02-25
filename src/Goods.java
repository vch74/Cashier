import java.sql.ResultSet;
import java.sql.SQLException;


public class Goods 
{
	String barcode;
	String code;
	String name;
	String number;
	float mediacost;
	float cost;
	int quantity;
	float sum;
	
	public Goods(String barcode, String code, String name, String number, 
				 float mediacost, float cost, int quantity)
	{
		this.barcode = barcode;
		this.code = code;
		this.name = name;
		this.number = number;
		this.mediacost = mediacost;
		this.cost = cost;
		this.quantity = quantity;
		sum = cost*quantity;
	}
	
	public Goods(String barcode)
	{
		this.barcode = barcode;
		this.code = "";
		this.name = "";
		this.number = "";
		this.mediacost = 0f;
		this.cost = 0f;
		this.quantity = 0;

		DB bd = new DB("kiosk.db");
		
		String op="SELECT g.code AS code,g.name AS name,b.number AS number,b.mediacost AS mediacost,b.cost AS cost,g.mrc AS mrc ";
		op = op + "FROM barcodes as b LEFT JOIN goods AS g ";
		op = op + "ON g.code=b.code WHERE b.barcode='"+barcode+"';";
		
		ResultSet rs = bd.query(op);
		
		try 
		{
			while(rs.next())
			{
				this.code = rs.getString("code");
				this.name = rs.getString("name");
				this.number = rs.getString("number");
				this.mediacost = rs.getFloat("mediacost");
				this.cost = rs.getFloat("cost");
				this.quantity = 1;
				sum = cost*quantity;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		bd.closeQuery();
	}

	public String retBarcode()
	{
		return(barcode);
	}

    public void setBarcode(String barcode)
    {
        this.barcode = barcode;
    }

	public String retCode()
	{
		return(code);
	}

    public void setCode(String code)
    {
        this.code = code;
    }

    public String retName()
	{
		return(name);
	}

    public void setName(String name)
    {
        this.name = name;
    }

    public String retNumber()
	{
		return(number);
	}

    public void setNumber(String number)
    {
        this.number = number;
    }

    public float retMediacost()
	{
		return(mediacost);
	}

    public void setMediacost(float mediacost)
    {
        this.mediacost = mediacost;
    }

    public float retCost()
	{
		return(cost);
	}

    public void setCost(float cost)
    {
        this.cost = cost;
        sum = cost*quantity;
    }

    public int retQuantity()
	{
		return(quantity);
	}

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
        sum =cost*quantity;
    }

    public float retSum()
	{
		return(sum);
	}

    public boolean sale(String date, String kiosk)
    {
        DB bd = new DB("kiosk.db_s");
        String op = "INSERT INTO carts (active,date,kiosk,barcode,code,number,mediacost,cost,quantity,sum) VALUES ";
        op+="(1,";
        op+="'"+date+"',";
        op+="'"+kiosk+"',";
        op+="'"+barcode+"',";
        op+="'"+code+"',";
        op+="'"+number+"',";
        op+=mediacost+",";
        op+=cost+",";
        op+=quantity+",";
        op+=sum;
        op+=");";
        return(bd.modify(op)>0);
    }
}
