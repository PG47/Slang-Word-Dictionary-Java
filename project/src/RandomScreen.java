import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static java.awt.Color.*;

public class RandomScreen extends JFrame implements ActionListener {
    JButton B_back, B_reroll;
    Slang slang_word = Slang.getInstance();
    JLabel l2, l4;

    public class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            int width = getWidth();
            int height = getHeight();

            //Define the color radient and its direction
            Color color1 = red;
            Color color2 = blue;
            GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);

            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.setPaint(gradient);
            graphics2D.fillRect(0, 0, width, height);
        }
    }

    RandomScreen() {
        //set Container
        GradientPanel contain = new GradientPanel();
        contain.setLayout(new BoxLayout(contain,BoxLayout.Y_AXIS));
        contain.setPreferredSize(new Dimension(600, 600));
        setContentPane(contain);

        //make title
        JPanel top_panel = new JPanel();
        top_panel.setMaximumSize(new Dimension(500,500));
        JLabel title = new JLabel("RADOM SLANG WORD");
        title.setFont(new Font("Gill Sans MT", Font.PLAIN,30));
        title.setForeground(GREEN);
        title.setAlignmentX(CENTER_ALIGNMENT);
        top_panel.add(title);

        // Make middle panel
        String random_slang[] = slang_word.Random();
        JPanel mid_panel = new JPanel();
        mid_panel.setOpaque(false);
        mid_panel.setBackground(Color.gray);
        mid_panel.setLayout(new BoxLayout(mid_panel,BoxLayout.Y_AXIS));

        //Slang word
        JPanel line1= new JPanel();
        line1.setOpaque(false);
        JLabel l1 = new JLabel("Slang:");
        l2 = new JLabel(random_slang[0]);
        l2.setForeground(Color.blue);
        l1.setFont(new Font("Gill Sans MT", Font.PLAIN, 25));
        l2.setFont(new Font("Gill Sans MT", Font.PLAIN, 25));
        line1.add(l1);
        line1.add(l2);

        //Definition panel
        JPanel line2= new JPanel();
        line2.setOpaque(false);
        JLabel l3 = new JLabel("Defintion:");
        l4 = new JLabel(random_slang[0]);
        l4.setForeground(YELLOW);
        l3.setFont(new Font("Gill Sans MT", Font.PLAIN, 25));
        l4.setFont(new Font("Gill Sans MT", Font.PLAIN, 25));
        line2.add(l3);
        line2.add(l4);

        //Put in the mid panell
        mid_panel.add(line1);
        mid_panel.add(line2);

        //Bottom panel for back and reroll button
        JPanel bottom_panel = new JPanel();
        bottom_panel.setOpaque(false);
        bottom_panel.setAlignmentX(CENTER_ALIGNMENT);
        B_back = new JButton("Back");
        B_reroll = new JButton("Reroll");
        B_back.addActionListener(this);
        B_reroll.addActionListener(this);
        bottom_panel.add(B_back);
        bottom_panel.add(B_reroll);

        //Put all into the container
        contain.add(title);
        contain.add(Box.createRigidArea(new Dimension(0, 10)));
        contain.add(mid_panel);
        contain.add(Box.createRigidArea(new Dimension(0, 10)));
        contain.add(bottom_panel);

        // Set Frame
        this.setTitle("Ramdom slang_words");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(400, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == B_back) {
            this.dispose();
            new MenuScreen();
        } else if (e.getSource() == B_reroll) {
            String s[] = slang_word.Random();
            l2.setText(s[0]);
            l4.setText(s[1]);
        }
    }

}
