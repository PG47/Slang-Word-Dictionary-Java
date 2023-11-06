import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SearchScreen extends JFrame implements ActionListener {
    JButton B_back;
    JButton B_search;
    JTextField text_field;
    JTable result_table;
    JLabel info_label;
    Slang slang_word;
    String[][] result_list;

    DefaultTableModel model;
    SearchScreen() throws Exception {
        Container contain = this.getContentPane();
        slang_word = Slang.getInstance();

        //make title
        JLabel title = new JLabel();
        title.setText("Search");
        title.setForeground(Color.red);
        title.setFont(new Font("Gill Sans MT", Font.PLAIN,25));
        title.setAlignmentX(CENTER_ALIGNMENT);

        //make info label
        info_label = new JLabel();
        info_label.setText("Find the slang word you want and it meanings");
        info_label.setForeground(Color.green);
        info_label.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        info_label.setAlignmentX(CENTER_ALIGNMENT);

        //make textfield
        JPanel text_box = new JPanel();
        text_box.setBackground(Color.gray);
        JLabel info_textbox = new JLabel("Input keyword:");
        text_field = new JTextField(20);
        B_search = new JButton("SEARCH");
        B_search.addActionListener(this);
        B_search.setMnemonic(KeyEvent.VK_ENTER);
        text_box.setLayout(new BoxLayout(text_box,BoxLayout.Y_AXIS));

        JPanel line1 = new JPanel();
        line1.add(info_textbox);
        line1.add(text_field);

        JPanel line2 = new JPanel();
        line2.add(B_search);

        text_box.add(line1);
        text_box.add(line2);

        Dimension size = new Dimension(600,80);
        text_box.setMaximumSize(size);
        text_box.setPreferredSize(size);
        text_box.setMaximumSize(size);

        //make result table
        JPanel panel_table = new JPanel();
        panel_table.setBackground(Color.black);
        String[] header_column= {"Slang word", "Meaning"};
        result_table = new JTable(new DefaultTableModel(header_column,0));
        result_table.setRowHeight(25);
        result_table.setEnabled(false);
        result_table.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        result_table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        result_table.getColumnModel().getColumn(0).setPreferredWidth(40);
        result_table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        result_table.getColumnModel().getColumn(1).setPreferredWidth(60);

        //Make the scrollpanel in case the result is too long
        JScrollPane scroll = new JScrollPane(result_table);
        panel_table.setLayout(new GridLayout(1,1));
        panel_table.add(scroll);

        // make Back button
        JPanel bottom_panel = new JPanel();
        B_back = new JButton("Back");
        B_back.addActionListener(this);
        B_back.setFocusable(false);
        B_back.setAlignmentX(CENTER_ALIGNMENT);
        bottom_panel.add(B_back);

        //Put all in the container
        contain.setLayout(new BoxLayout(contain, BoxLayout.Y_AXIS));
        contain.add(Box.createRigidArea(new Dimension(0, 10)));
        contain.add(title);
        contain.add(Box.createRigidArea(new Dimension(0, 10)));
        contain.add(info_label);
        contain.add(Box.createRigidArea(new Dimension(0, 10)));
        contain.add(text_box);
        contain.add(Box.createRigidArea(new Dimension(0, 10)));
        contain.add(panel_table);
        contain.add(Box.createRigidArea(new Dimension(0, 10)));
        contain.add(B_back);

        // set Frame
        this.setTitle("SEARCHING");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(600, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    //Clear the result table if we searching again
    void clearTable() {
        int num_row = model.getRowCount();
        for (int i = num_row - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
}