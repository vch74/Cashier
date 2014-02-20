import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class GoodsModel extends AbstractTableModel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String colNames[]={"Наименование","Номер","Цена","Количество","Сумма","code","barcode","mediacost"};
	private Vector<Goods> cart;
	
	public GoodsModel()
	{
		super();
		cart = new Vector<Goods>();
	}

	@Override
	public int getRowCount() 
	{
		return cart.size();
	}

	@Override
	public int getColumnCount() 
	{
		return 5; //colNames.length;
	}

	public String getColumnName(int idx)
	{
        if(idx>=colNames.length || idx < 0) return "";
		return colNames[idx];
	}

    public Goods retGoods(int idx)
    {
        if(idx>=cart.size() || idx < 0) return null;
        return cart.elementAt(idx);
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex==0) return(cart.get(rowIndex).name);
		if(columnIndex==1) return(cart.get(rowIndex).number);
		if(columnIndex==2) return(cart.get(rowIndex).cost);
		if(columnIndex==3) return(cart.get(rowIndex).quantity);
		if(columnIndex==4) return(cart.get(rowIndex).sum);
		if(columnIndex==5) return(cart.get(rowIndex).code);
		if(columnIndex==6) return(cart.get(rowIndex).barcode);
		if(columnIndex==7) return(cart.get(rowIndex).mediacost);
		return null;
	}
	
	public void addGoods(Goods g)
	{
		cart.insertElementAt(g,0);
	}
	
	public void delGoods()
	{
		if(cart.size()>0) cart.remove(0);
	}

    public void delGoods(int idx)
    {
        if(cart.size()>0 && idx>=0 && idx<cart.size()) cart.remove(idx);
    }

	public float sumOfCart()
	{
		float su=0;
		for(Goods ca:cart)
		{
			su+=ca.sum;
		}
		return su;
	}
	
	public Goods retFirst()
	{
		if(cart.size()>0) return cart.get(0);
		return null;
	}
	
	public void clear()
	{
		cart.clear();
	}

}
