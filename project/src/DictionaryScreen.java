import java.awt.*;
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

import static java.awt.Color.*;

public class DictionaryScreen extends JFrame implements ActionListener{
    JButton B_back;
    JTable table;
    Slang slang_word;

    public class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            int width = getWidth();
            int height = getHeight();

            //Define the color radient and its direction
            Color color1 = BLUE;
            Color color2 = GREEN;
            GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);

            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.setPaint(gradient);
            graphics2D.fillRect(0, 0, width, height);
        }
    }

    public DictionaryScreen() throws Exception {
        GradientPanel contain = new GradientPanel();
        contain.setLayout(new BoxLayout(contain,BoxLayout.Y_AXIS));
        contain.setPreferredSize(new Dimension(600, 600));
        setContentPane(contain);
        slang_word = Slang.getInstance();

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
        String data[][]= Slang.getInstance().Get_Data();
        String header_column[] = { "No.", "Slag", "Definition" };
        table = new JTable(data, header_column);
        table.setRowHeight(25);
        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);

        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);

        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);

        //count number of slag word
        dic_label.setText("Number of slang words: " + data.length);

        // make scoll panel
        JScrollPane scroll = new JScrollPane(table);
        slag_table.setLayout(new BoxLayout(slag_table, BoxLayout.X_AXIS));
        slag_table.add(scroll);

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
}