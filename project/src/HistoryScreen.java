import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class HistoryScreen extends JFrame implements ActionListener{
    JButton B_back;
    JButton B_clear;
    JTable table;
    Slang slang_word;

    public HistoryScreen() throws Exception {
        Container contain = this.getContentPane();
        slang_word = Slang.getInstance();

        //make title
        JLabel title = new JLabel();
        title.setText("SEARCHING HISTORY");
        title.setForeground(Color.red);
        title.setFont(new Font("Gill Sans MT", Font.PLAIN,30));
        title.setAlignmentX(CENTER_ALIGNMENT);


        // make list slang words
        JPanel slang_table = new JPanel();
        slang_table.setBackground(Color.black);
        String data[][]= Slang.getInstance().Read_History();
        String header_column[] = { "Date", "Slag", "Defintion" };
        table = new JTable(new DefaultTableModel(data, header_column));
        table.setRowHeight(25);
        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);

        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);

        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);


        // make scoll panel
        JScrollPane scroll = new JScrollPane(table);
        slang_table.setLayout(new BoxLayout(slang_table, BoxLayout.X_AXIS));
        slang_table.add(scroll);

        JPanel bottom_panel = new JPanel();
        // make Back button
        B_back = new JButton("Back");
        B_back.addActionListener(this);
        B_back.setFocusable(false);
        B_back.setAlignmentX(CENTER_ALIGNMENT);
        // make Clear history button
        B_clear = new JButton("Clear history");
        B_clear.addActionListener(this);
        B_clear.setFocusable(false);
        B_clear.setAlignmentX(CENTER_ALIGNMENT);
        bottom_panel.add(B_back);
        bottom_panel.add(B_clear);

        //Put all in the container
        contain.setLayout(new BoxLayout(contain, BoxLayout.Y_AXIS));
        contain.add(Box.createRigidArea(new Dimension(0,10)));
        contain.add(title);
        contain.add(Box.createRigidArea(new Dimension(0,20)));
        contain.add(slang_table);
        contain.add(Box.createRigidArea(new Dimension(0,20)));
        contain.add(bottom_panel);

        // Set Frame
        this.setTitle("HISTORY");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(600, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        if(data.length==0)
            JOptionPane.showMessageDialog(this, "Can't find search history file", "EMPTY!", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == B_back) {
            this.dispose();
            new MenuScreen();
        }
        if (e.getSource() == B_clear) {
            int res = slang_word.Clear_History();
            if (res == 0) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                JOptionPane.showMessageDialog(this, "Search-history file has been deleted", "CLEAR!", JOptionPane.PLAIN_MESSAGE);
            }
            if (res == 1)
                JOptionPane.showMessageDialog(this, "Can't delete search history file", "ERROR!", JOptionPane.ERROR_MESSAGE);
            if (res == 2)
                JOptionPane.showMessageDialog(this, "No search history file found to delete", "NO FILE!", JOptionPane.PLAIN_MESSAGE);
        }
    }
}