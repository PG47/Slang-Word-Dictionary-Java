import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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

import static java.awt.SystemColor.text;

public class MenuScreen extends JFrame implements ActionListener {
    JButton b1, b2, b3, b4, b5, b6, b7, b8;
    Slang slag_word;

    MenuScreen() {
        slag_word = Slang.getInstance();
        // Label
        JLabel label = new JLabel("Slang Program");
        label.setForeground(Color.red);
        label.setFont(new Font("Gill Sans MT", Font.PLAIN, 30));
        label.setAlignmentX(CENTER_ALIGNMENT);

        //8 button for menu options
        b1 = new JButton("Dicitonary");
        b1.addActionListener(this);
        b1.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        b1.setFocusable(false);

        b2 = new JButton("Search slang");
        b2.addActionListener(this);
        b2.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        b2.setFocusable(false);

        b3 = new JButton("History");
        b3.addActionListener(this);
        b3.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        b3.setFocusable(false);

        b4 = new JButton("Add slane");
        b4.addActionListener(this);
        b4.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        b4.setFocusable(false);

        b5 = new JButton("Edit/Delete");
        b5.addActionListener(this);
        b5.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        b5.setFocusable(false);

        b6 = new JButton("Reset dictionary");
        b6.addActionListener(this);
        b6.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        b6.setFocusable(false);

        b7 = new JButton("Random slang word");
        b7.addActionListener(this);
        b7.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
        b7.setFocusable(false);

        b8 = new JButton("Quiz");
        b8.addActionListener(this);
        b8.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        b8.setFocusable(false);

        // Panel for Button
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(4, 2, 8, 8));
        panelCenter.add(b1);
        panelCenter.add(b2);
        panelCenter.add(b3);
        panelCenter.add(b4);
        panelCenter.add(b5);
        panelCenter.add(b6);
        panelCenter.add(b7);
        panelCenter.add(b8);

        Dimension size2 = new Dimension(500, 450);
        panelCenter.setMaximumSize(size2);
        panelCenter.setPreferredSize(size2);
        panelCenter.setMinimumSize(size2);
        Container con = this.getContentPane();
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(label);
        con.add(Box.createRigidArea(new Dimension(0, 20)));
        con.add(panelCenter);

        // Set Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Menu Screen");
        this.setVisible(true);
        this.setSize(600, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1) {
            this.dispose();
            try {
                new DictionaryScreen();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
