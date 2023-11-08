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
    JButton B_close;
    Slang slang_word = Slang.getInstance();

    MenuScreen() {
        //Make a contain
        Container contain = this.getContentPane();
        contain.setLayout(new BoxLayout(contain, BoxLayout.Y_AXIS));
        
        // Label
        JLabel title = new JLabel("Slang Program");
        title.setForeground(Color.red);
        title.setFont(new Font("Gill Sans MT", Font.PLAIN, 30));
        title.setAlignmentX(CENTER_ALIGNMENT);

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
        JPanel mid_panel = new JPanel();
        mid_panel.setLayout(new GridLayout(4, 2, 8, 8));
        mid_panel.add(b1);
        mid_panel.add(b2);
        mid_panel.add(b3);
        mid_panel.add(b4);
        mid_panel.add(b5);
        mid_panel.add(b6);
        mid_panel.add(b7);
        mid_panel.add(b8);

        //Close program button
        JPanel bottom_panel = new JPanel();
        bottom_panel.setAlignmentX(CENTER_ALIGNMENT);
        B_close = new JButton("Close");
        B_close.addActionListener(this);
        bottom_panel.add(B_close);

        Dimension size2 = new Dimension(500, 450);
        mid_panel.setMaximumSize(size2);
        mid_panel.setPreferredSize(size2);
        mid_panel.setMinimumSize(size2);
        
        //Put all in the contain
        contain.add(Box.createRigidArea(new Dimension(0, 10)));
        contain.add(title);
        contain.add(Box.createRigidArea(new Dimension(0, 20)));
        contain.add(mid_panel);
        contain.add(bottom_panel);

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
        if(e.getSource() == b2) {
            this.dispose();
            try {
                new SearchScreen();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        if(e.getSource() == b3) {
            this.dispose();
            try {
                new HistoryScreen();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        if(e.getSource() == b4) {
            this.dispose();
            try {
                new AddScreen();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        if(e.getSource() == b5) {
            this.dispose();
            try {
                new Edit_DeleteScreen();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        if(e.getSource() == b6) {
            int choice = JOptionPane.showConfirmDialog(this, "Do you really want to reset Slang Word Dictionary?", "Reset?",
                    JOptionPane.YES_NO_OPTION);
            if (choice==0) {
                slang_word.Reset();
                JOptionPane.showMessageDialog(this, "Slang word dictionary had been reset.", "DONE!", JOptionPane.PLAIN_MESSAGE);
            }
        }
        if(e.getSource() == b7) {
            this.dispose();
            try {
                new RandomScreen();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        if(e.getSource() == b8) {
            this.dispose();
            try {
                new QuizScreen();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        if (e.getSource() == B_close) {
            this.dispose();
            return;
        }
    }
}
