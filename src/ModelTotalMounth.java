import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by andrew on 2/25/14.
 */
public class ModelTotalMounth  extends CashierModel
{
    private String colNames[]={"Дата","Сумма"};
    private ArrayList<String> dates;
    private ArrayList<Float>  sums;

    public ModelTotalMounth()
    {
        header = "Суммы продаж с начала месяца";
        dates = new ArrayList<String>();
        sums = new ArrayList<Float>();
        SimpleDateFormat formBeginMounth = new SimpleDateFormat("yyyy-MM-01");
        long current = System.currentTimeMillis();
        String thisMounth=formBeginMounth.format(current);

        DB bd = new DB("kiosk.db_s");

        String op="SELECT substr(date,1,10) as date,sum(sum) AS sum FROM carts ";
        op+="WHERE date>'"+thisMounth+"' GROUP BY substr(date,1,10);";

        ResultSet rs = bd.query(op);

        try
        {
            while(rs.next())
            {
                dates.add(rs.getString("date"));
                sums.add(rs.getFloat("sum"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        bd.closeQuery();
        wides = new ArrayList<Integer>();
        int mywides[]={80,20};
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
        if(columnIndex==1) return sums.get(rowIndex);
        return null;
    }

    public String getColumnName(int idx)
    {
        if(idx>=colNames.length || idx < 0) return "";
        return colNames[idx];
    }

}

