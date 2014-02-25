import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by andrew on 2/25/14.
 */
public abstract class CashierModel extends AbstractTableModel
{
    protected String header;
    protected ArrayList<Integer> wides;

    public String retHeader()
    {
        return header;
    }

    public int retWide(int idx)
    {
        if(idx>=wides.size() || idx < 0) return 0;
            return(wides.get(idx));
    }
}
