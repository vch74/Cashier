import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by andrew on 2/21/14.
 */
public class ModelTotalDay extends CashierModel
{
    private String colNames[]={"Дата","Наименование","Номер","Цена","Количество","Сумма"};
    private ArrayList<String> dates;
    private ArrayList<Goods>  goods;
    public ModelTotalDay()
    {
        header = "Продажи с начала дня";
        dates = new ArrayList<String>();
        goods = new ArrayList<Goods>();
        SimpleDateFormat formBeginDay = new SimpleDateFormat("yyyy-MM-dd");
        long current = System.currentTimeMillis();
        String today=formBeginDay.format(current);

        DB bd = new DB("kiosk.db_s");

        String op="SELECT date,barcode,code,number,cost,sum(quantity) AS quantity,sum(sum) AS sum FROM carts ";
               op+="WHERE date>'"+today+"' GROUP BY date,barcode,code,number;";

        ResultSet rs = bd.query(op);

        try
        {
            while(rs.next())
            {
                Goods g = new Goods(rs.getString("barcode"));
                g.setCode(rs.getString("code"));
                g.setNumber(rs.getString("number"));
                g.setCost(rs.getFloat("cost"));
                g.setQuantity(rs.getInt("quantity"));
                dates.add(rs.getString("date"));
                goods.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        bd.closeQuery();
        wides = new ArrayList<Integer>();
        int mywides[]={20,30,20,10,10,10};
        for(int i=0;i<mywides.length;i++) wides.add(mywides[i]);
    }

    @Override
    public int getRowCount()
    {
        return dates.size();
    }

    @Override
    public int getColumnCount()
    {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        if(columnIndex==0) return dates.get(rowIndex);
        if(columnIndex==1) return goods.get(rowIndex).retName();
        if(columnIndex==2) return goods.get(rowIndex).retNumber();
        if(columnIndex==3) return goods.get(rowIndex).retCost();
        if(columnIndex==4) return goods.get(rowIndex).retQuantity();
        if(columnIndex==5) return goods.get(rowIndex).retSum();
        return null;
    }

    public String getColumnName(int idx)
    {
        if(idx>=colNames.length || idx < 0) return "";
        return colNames[idx];
    }

}
