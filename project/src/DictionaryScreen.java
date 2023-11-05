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

public class DictionaryScreen extends JFrame implements ActionListener, TableModelListener {
    JButton B_back;
    JTable table;
    Slang slag_word;
    String data_copy[][];

    public DictionaryScreen() throws Exception {
        Container contain = this.getContentPane();
        slag_word = Slang.getInstance();

        //make title
        JLabel title = new JLabel();
        title.setText("DICTIONARY");
        title.setForeground(Color.red);
        title.setFont(new Font("Gill Sans MT", Font.PLAIN,30));
        title.setAlignmentX(CENTER_ALIGNMENT);

        //make dictionary label
        JLabel dic_label = new JLabel();
        dic_label.setForeground(Color.black);
        dic_label.setFont(new Font("Gill Sans MT", Font.PLAIN,15));
        dic_label.setAlignmentX(CENTER_ALIGNMENT);

        // make list slang words
        JPanel slag_table = new JPanel();
        slag_table.setBackground(Color.black);
        String data[][]= Slang.getInstance().get_Data();
        data_copy=Slang.getInstance().get_Data();
        String column[] = { "STT", "Slag", "Meaning" };
        table = new JTable(data, column);
        table.setRowHeight(25);
        table.setEnabled(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);

        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);

        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getModel().addTableModelListener(this);

        //count number of slag word
        dic_label.setText("Number of slang words: " + data.length);

        // make scoll button
        JScrollPane sb = new JScrollPane(table);
        slag_table.setLayout(new BoxLayout(slag_table, BoxLayout.X_AXIS));
        slag_table.add(sb);

        // make Back button
        JPanel bottom_panel = new JPanel();
        B_back = new JButton("Back");
        B_back.addActionListener(this);
        B_back.setFocusable(false);
        B_back.setAlignmentX(CENTER_ALIGNMENT);
        bottom_panel.add(B_back);

        //Put all in the container
        contain.setLayout(new BoxLayout(contain, BoxLayout.Y_AXIS));
        contain.add(Box.createRigidArea(new Dimension(0,10)));
        contain.add(title);
        contain.add(Box.createRigidArea(new Dimension(0,20)));
        contain.add(dic_label);
        contain.add(Box.createRigidArea(new Dimension(0,20)));
        contain.add(slag_table);
        contain.add(Box.createRigidArea(new Dimension(0,20)));
        contain.add(bottom_panel);

        // Set Frame
        this.setTitle("DICTIONARY");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(600, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == B_back) {
            this.dispose();
            new MenuScreen();
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {

    }
    /*@Override
    public void tableChanged(TableModelEvent e) {
        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();
        if (row == -1 || col == -1)
            return;
        String Data = (String) table.getValueAt(row, col);
        // System.out.println("Table element selected is: " + Data);

        if (col == 2) {
            // edit meaning
            System.out.println("Old SlangWord: \t" + row + "\t" + data[row][2]);
            System.out.println("Old SlangWord: \t" + row + "\t" + data[row][2]);
        }slag_word.set((String) table.getValueAt(row, 1), data[row][2], (String) table.getValueAt(row, 2));
        JOptionPane.showMessageDialog(this, "Updated a row.");
    } */
}