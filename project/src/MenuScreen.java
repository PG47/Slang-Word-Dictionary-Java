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

import static java.awt.Color.*;
import javax.swing.border.Border;

public class MenuScreen extends JFrame implements ActionListener {
    JButton b1, b2, b3, b4, b5, b6, b7, b8;
    JButton B_close;
    Slang slang_word = Slang.getInstance();

    public class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            int width = getWidth();
            int height = getHeight();

            //Define the color radient and its direction
            Color color1 = RED;
            Color color2 = GREEN;
            GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);

            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.setPaint(gradient);
            graphics2D.fillRect(0, 0, width, height);
        }
    }

    MenuScreen() {
        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(new BoxLayout(gradientPanel,BoxLayout.Y_AXIS));
        gradientPanel.setPreferredSize(new Dimension(600, 600));
        
        // title Label
        JLabel title = new JLabel("Slang Program");
        title.setForeground(Color.blue);
        title.setFont(new Font("Gill Sans MT", Font.PLAIN, 30));
        JPanel top_panel = new JPanel();
        title.setAlignmentX(CENTER_ALIGNMENT);
        top_panel.add(title);
        top_panel.setOpaque(false);

        gradientPanel.setVisible(true);
        top_panel.setVisible(true);
        title.setVisible(true);


        //8 button for menu options
        b1 = new JButton("Dicitonary");
        b1.addActionListener(this);
        b1.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        b1.setFocusable(false);
        b1.setBackground(green);
        b1.setForeground(red);
        b1.setBorder(new RoundButton(30));

        b2 = new JButton("Search slang");
        b2.addActionListener(this);
        b2.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        b2.setFocusable(false);
        b2.setBackground(green);
        b2.setForeground(red);
        b2.setBorder(new RoundButton(30));

        b3 = new JButton("History");
        b3.addActionListener(this);
        b3.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        b3.setFocusable(false);
        b3.setBackground(green);
        b3.setForeground(red);
        b3.setBorder(new RoundButton(30));

        b4 = new JButton("Add slane");
        b4.addActionListener(this);
        b4.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        b4.setFocusable(false);
        b4.setBackground(green);
        b4.setForeground(red);
        b4.setBorder(new RoundButton(30));

        b5 = new JButton("Edit/Delete");
        b5.addActionListener(this);
        b5.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        b5.setFocusable(false);
        b5.setBackground(green);
        b5.setForeground(red);
        b5.setBorder(new RoundButton(30));

        b6 = new JButton("Reset dictionary");
        b6.addActionListener(this);
        b6.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        b6.setFocusable(false);
        b6.setBackground(green);
        b6.setForeground(red);
        b6.setBorder(new RoundButton(30));

        b7 = new JButton("Random slang word");
        b7.addActionListener(this);
        b7.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
        b7.setFocusable(false);
        b7.setBackground(green);
        b7.setForeground(red);
        b7.setBorder(new RoundButton(30));

        b8 = new JButton("Quiz");
        b8.addActionListener(this);
        b8.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        b8.setFocusable(false);
        b8.setBackground(green);
        b8.setForeground(red);
        b8.setBorder(new RoundButton(30));

        // Panel for Button
        JPanel mid_panel = new JPanel();
        mid_panel.setOpaque(false);
        mid_panel.setLayout(new GridLayout(4, 2, 8, 8));
        mid_panel.add(b1);
        mid_panel.add(b2);
        mid_panel.add(b3);
        mid_panel.add(b4);
        mid_panel.add(b5);
        mid_panel.add(b6);
        mid_panel.add(b7);
        mid_panel.add(b8);

        Dimension size2 = new Dimension(600, 450);
        mid_panel.setMaximumSize(size2);
        mid_panel.setPreferredSize(size2);
        mid_panel.setMinimumSize(size2);

        //Close program button
        JPanel bottom_panel = new JPanel();
        bottom_panel.setOpaque(false);
        bottom_panel.setAlignmentX(CENTER_ALIGNMENT);
        B_close = new JButton("Close");
        B_close.addActionListener(this);
        bottom_panel.add(B_close);
        Dimension size3 = new Dimension(600, 50);
        bottom_panel.setMaximumSize(size3);
        bottom_panel.setPreferredSize(size3);
        bottom_panel.setMinimumSize(size3);
        
        //Put all in the contain
        gradientPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        gradientPanel.add(top_panel);
        gradientPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        gradientPanel.add(mid_panel,BorderLayout.CENTER);
        gradientPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        gradientPanel.add(bottom_panel);
        setContentPane(gradientPanel);

        // Set Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Menu Screen");
        this.setVisible(true);
        this.setSize(600, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);


    }

    class RoundButton implements Border {
        private int r;

        RoundButton(int r) {
            this.r = r;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.r + 1, this.r + 1, this.r + 2, this.r);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y,
                                int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, r, r);
        }
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
            int choice = JOptionPane.showConfirmDialog(this, "Do you really want to reset the Slang Dictionary?", "Reset?",
                    JOptionPane.YES_NO_OPTION);
            if (choice==0) {
                slang_word.Reset();
                JOptionPane.showMessageDialog(this, "Slang word dictionary has been reset.", "DONE!", JOptionPane.PLAIN_MESSAGE);
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
