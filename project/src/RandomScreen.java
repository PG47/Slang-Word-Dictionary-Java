import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RandomScreen extends JFrame implements ActionListener {
    JButton B_back, B_reroll;
    Slang slang_word = Slang.getInstance();
    JLabel l2, l4;

    RandomScreen() {
        //set Container
        Container contain = this.getContentPane();
        contain.setLayout(new BoxLayout(contain, BoxLayout.Y_AXIS));

        //make title
        JPanel top_panel = new JPanel();
        top_panel.setMaximumSize(new Dimension(500,500));
        JLabel title = new JLabel("RADOM SLANG WORD");
        title.setFont(new Font("Gill Sans MT", Font.PLAIN,30));
        title.setBackground(Color.GREEN);
        title.setForeground(Color.RED);
        title.setAlignmentX(CENTER_ALIGNMENT);
        top_panel.add(title);

        // Make middle panel
        String random_slang[] = slang_word.Random();
        JPanel mid_panel = new JPanel();
        mid_panel.setBackground(Color.gray);
        mid_panel.setLayout(new BoxLayout(mid_panel,BoxLayout.Y_AXIS));

        //Slang word
        JPanel line1= new JPanel();
        JLabel l1 = new JLabel("Slang:");
        l2 = new JLabel(random_slang[0]);
        l2.setForeground(Color.blue);
        l1.setFont(new Font("Gill Sans MT", Font.PLAIN, 25));
        l2.setFont(new Font("Gill Sans MT", Font.PLAIN, 25));
        line1.add(l1);
        line1.add(l2);

        //Definition panel
        JPanel line2= new JPanel();
        JLabel l3 = new JLabel("Defintion:");
        l4 = new JLabel(random_slang[0]);
        l4.setForeground(Color.gray);
        l3.setFont(new Font("Gill Sans MT", Font.PLAIN, 25));
        l4.setFont(new Font("Gill Sans MT", Font.PLAIN, 25));
        line2.add(l3);
        line2.add(l4);

        //Put in the mid panell
        mid_panel.add(line1);
        mid_panel.add(line2);

        //Bottom panel for back and reroll button
        JPanel bottom_panel = new JPanel();
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
