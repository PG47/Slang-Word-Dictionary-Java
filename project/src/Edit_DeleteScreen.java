import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Edit_DeleteScreen extends JFrame implements ActionListener, ListSelectionListener{
    JButton B_back, B_search, B_save;
    JTextField text_field, edit_text_field;

    JTable table;
    Slang slang_word;
    DefaultTableModel model;
    String data[][];

    public  Edit_DeleteScreen() {
        slang_word = Slang.getInstance();
        //Make a container
        Container contain = this.getContentPane();
        contain.setLayout(new BoxLayout(contain, BoxLayout.Y_AXIS));

        //Make title
        JLabel title1 = new JLabel("EDIT/DELETE SLANG");
        title1.setForeground(Color.red);
        title1.setFont(new Font("Gill Sans MT", Font.PLAIN, 28));
        title1.setAlignmentX(CENTER_ALIGNMENT);
        JLabel title2 = new JLabel("Select a slang word you want to edit or remove.");
        title2.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
        title2.setAlignmentX(CENTER_ALIGNMENT);

        // Make Search text field;
        JPanel text_box = new JPanel();
        text_box.setBackground(Color.gray);
        JLabel info_textbox = new JLabel("Search slang:");
        text_field = new JTextField(20);
        B_search = new JButton("SEARCH");
        B_search.addActionListener(this);
        B_search.setMnemonic(KeyEvent.VK_ENTER);
        text_box.setLayout(new BoxLayout(text_box,BoxLayout.Y_AXIS));
        text_box.setLayout(new BoxLayout(text_box,BoxLayout.Y_AXIS));
        JPanel line1 = new JPanel();
        line1.add(info_textbox);
        line1.add(text_field);
        JPanel line2 = new JPanel();
        line2.add(B_search);
        text_box.add(line1);
        text_box.add(line2);

        //Make List slang words
        JPanel table_panel = new JPanel();
        table_panel.setBackground(Color.black);
        data = slang_word.Get_Data();
        String[] header_column= {"No.", "Slang", "Definition"};
        table = new JTable(new DefaultTableModel(data, header_column));
        model = (DefaultTableModel) table.getModel();
        table.setRowHeight(25);
        table.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);

        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);

        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);

        ListSelectionModel Selection_Model = table.getSelectionModel();
        Selection_Model.addListSelectionListener(this);

        //Make the scroll
        JScrollPane scroll = new JScrollPane(table);
        table_panel.setLayout(new GridLayout(1,1));
        table_panel.add(scroll);

        // make Back button
        JPanel bottom_panel = new JPanel();
        B_back = new JButton("Back");
        B_back.addActionListener(this);
        B_back.setFocusable(false);
        B_back.setAlignmentX(CENTER_ALIGNMENT);
        bottom_panel.add(B_back);

        //Put all into container
        contain.add(Box.createRigidArea(new Dimension(0, 5)));
        contain.add(title1);
        contain.add(Box.createRigidArea(new Dimension(0, 5)));
        contain.add(title2);
        contain.add(Box.createRigidArea(new Dimension(0, 10)));
        contain.add(text_box);
        //contain.add(Box.createRigidArea(new Dimension(0, 20)));
        //contain.add(table);
        contain.add(Box.createRigidArea(new Dimension(0, 20)));
        contain.add(table_panel);
        contain.add(Box.createRigidArea(new Dimension(0, 10)));
        contain.add(B_back);

        // set Frame
        this.setTitle("EDIT & DELETE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(600, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //go back to the menu screen
        if (e.getSource() == B_back) {
            this.dispose();
            new MenuScreen();
        }

        if (e.getSource() == B_search) {
            String key = text_field.getText();
            if (key.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Keyword can not empty!", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            Object[] options = { "Search by slang word", "Search by slang definition"};
            int choice = JOptionPane.showOptionDialog(this, "Select type of search", "Search type?",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
            String[][] result  = null;
            if (choice ==0) { //Search slang word
                result=slang_word.Search_by_Slang(key);
            }
            if (choice ==1) { //Search slang word by definition
                result=slang_word.Search_by_Definition(key);
            }
            if(result!=null) {//found slang words you want
                this.clearTable();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                for (int i = 0; i < result.length; i++) {
                    String ss[] = new String[3];
                    ss[0]=Integer.toString(i);
                    ss[1]= result[i][0];
                    ss[2]= result[i][1];
                    model.addRow(ss);
                }
            }
            else JOptionPane.showMessageDialog(this, "Can't find the slang you want", "NO RESULT", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();
        if(row==-1 || col ==-1) return;
        String choosen_slang = (String) table.getValueAt(row,1);
        Object[] options = { "Edit slang", "Delete slang"};
        int choice = JOptionPane.showOptionDialog(this, "Choose what you want to do with this slang:", "Do what?",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
        if(choice == 0) {
            Edit(row,col);
        }
        else {
            slang_word.Delete(choosen_slang,(String) table.getValueAt(row,2));
            model.removeRow(row);
            JOptionPane.showMessageDialog(this, "Deleted successfully");
        }
    }

    void clearTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }

    void Edit(int row, int col) {
        String chosen_slang = (String) table.getValueAt(row, 1);
        String old_defin = (String) table.getValueAt(row, 2);

        JDialog edit_dialog = new JDialog(this, "Edit slang word for `" + chosen_slang + "`", true);

        edit_text_field = new JTextField(20);
        B_save = new JButton("Save");

        B_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String edited_defin = edit_text_field.getText();
                if (!edited_defin.isEmpty()) {
                    slang_word.Edit(chosen_slang, old_defin, edited_defin);
                        JOptionPane.showMessageDialog(edit_dialog, "Edit successfully!");
                    edit_dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(edit_dialog, "Can't be left blank.");
                }
            }
        });

        JPanel edit_panel = new JPanel();
        edit_panel.setLayout(new BoxLayout(edit_panel, BoxLayout.Y_AXIS));
        edit_panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        edit_panel.add(new JLabel("Current definition: " + old_defin));

        JPanel edit_textbox = new JPanel();
        edit_textbox.add(new JLabel("New definition:"));
        edit_textbox.add(edit_text_field);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center-align the button
        buttonPanel.add(B_save);

        edit_panel.add(edit_textbox);
        edit_panel.add(buttonPanel);

        edit_dialog.add(edit_panel);
        edit_dialog.pack();
        edit_dialog.setLocationRelativeTo(this);
        edit_dialog.setVisible(true);
    }

}
