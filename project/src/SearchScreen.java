import java.awt.*;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import static java.awt.Color.*;

public class SearchScreen extends JFrame implements ActionListener, DocumentListener {
    JButton B_back;
    JTextField text_field;
    JTable result_table;
    JLabel info_label;
    Slang slang_word;
    String[][] result_list;

    Object[] options = { "Search by slang", "Search by definition"};
    int choice;

    public class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            int width = getWidth();
            int height = getHeight();

            //Define the color radient and its direction
            Color color1 = BLUE;
            Color color2 = YELLOW;
            GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);

            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.setPaint(gradient);
            graphics2D.fillRect(0, 0, width, height);
        }
    }

    SearchScreen() throws Exception {
        GradientPanel contain = new GradientPanel();
        contain.setLayout(new BoxLayout(contain,BoxLayout.Y_AXIS));
        contain.setPreferredSize(new Dimension(600, 600));
        setContentPane(contain);
        slang_word = Slang.getInstance();

        //make title
        JLabel title = new JLabel();
        title.setText("Search");
        title.setForeground(Color.red);
        title.setFont(new Font("Gill Sans MT", Font.PLAIN,25));
        title.setAlignmentX(CENTER_ALIGNMENT);

        //make info label
        info_label = new JLabel();
        info_label.setText("Find the slang word you want and its meaning");
        info_label.setForeground(Color.green);
        info_label.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        info_label.setAlignmentX(CENTER_ALIGNMENT);
        info_label.setOpaque(false);

        //make textfield
        JPanel text_box = new JPanel();
        text_box.setOpaque(false);
        text_box.setBackground(Color.gray);
        JLabel info_textbox = new JLabel("Input keyword:");
        info_textbox.setForeground(YELLOW);
        text_field = new JTextField(20);
        text_field.getDocument().addDocumentListener(this);
        text_box.setLayout(new BoxLayout(text_box,BoxLayout.Y_AXIS));

        JPanel line1 = new JPanel();
        line1.setOpaque(false);
        line1.add(info_textbox);
        line1.add(text_field);

        text_box.add(line1);

        Dimension size = new Dimension(600,80);
        text_box.setMaximumSize(size);
        text_box.setPreferredSize(size);
        text_box.setMaximumSize(size);

        //make result table
        JPanel panel_table = new JPanel();
        panel_table.setBackground(Color.black);
        String[] header_column= {"Slang", "Definition"};
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
        bottom_panel.setOpaque(false);
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
        contain.add(bottom_panel);

        // set Frame
        this.setTitle("SEARCHING");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(600, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        choice = JOptionPane.showOptionDialog(this, "Select type of search", "Search type?",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
        if(choice!=0 && choice !=1) choice=0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //go back to the menu screen
        if (e.getSource() == B_back) {
            this.dispose();
            new MenuScreen();
        }
    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        handleTextChange();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        handleTextChange();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        handleTextChange();
    }

    private void handleTextChange() {
        String key = text_field.getText();
        if (key.isEmpty()) return;
        String[][] result  = null;

        if (choice ==0) { //Search slang word
            this.clearTable();
            result=slang_word.Search_by_Slang(key);
        }
        if (choice ==1) { //Search slang word by definition
            this.clearTable();
            result=slang_word.Search_by_Definition(key);
        }
        if(result!=null) {//found slang words you want
            DefaultTableModel model = (DefaultTableModel) result_table.getModel();
            for (int i = 0; i < result.length; i++) {
                String ss[] = result[i];
                model.addRow(ss);
            }
            info_label.setText("Result:"+result.length+" words");
            // Save the result in history
            try {
                for (int ii = 0; ii < result.length; ii++)
                    slang_word.Save_History(result[ii][0], result[ii][1]);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
        else info_label.setText("No result!");
    }

    //Clear the result table if we searching again
    void clearTable() {
        DefaultTableModel model = (DefaultTableModel) result_table.getModel();
        model.setRowCount(0);
    }
}
