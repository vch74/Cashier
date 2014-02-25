import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by andrew on 2/20/14.
 */
public class DialogInfo implements KeyListener
{
    private JDialog jDialog;
    private JTable table;
    private JScrollPane scr;

    public DialogInfo(JFrame jfrm, CashierModel model)
    {
        jDialog = new JDialog(jfrm, model.retHeader(), true);
        jDialog.setSize(800,600);
        jDialog.setLocation(200, 200);
        table = new JTable(model);
        table.setFocusable(true);

        //table.getTableHeader().setFont(f3);
        //table.setFont(f3);
        table.setRowHeight(30);
        //table.setBackground(Color.CYAN);
        table.addKeyListener(this);//new ListenerDialog(this));

        scr = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(450,400));
        jDialog.add(scr);

        //установим ширины столбцов таблицы
        int w=450;//jDialog.getWidth();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        for(int i=0;i<model.getColumnCount();i++)
            table.getColumnModel().getColumn(i).setPreferredWidth(w/100*model.retWide(i));

        jDialog.setVisible(true);

    }

    public void closeDialog()
    {
        jDialog.setVisible(false);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == 27) //клавиша Esc
        {
            jDialog.setVisible(false);
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
