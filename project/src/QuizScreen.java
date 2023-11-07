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
import javax.swing.JPanel;

public class QuizScreen extends JFrame implements ActionListener {
    JButton B_back;
    int score=0;
    int index=1;

    QuizScreen() {
        // Making container
        Container contain = this.getContentPane();
        contain.setLayout(new BoxLayout(contain,BoxLayout.Y_AXIS));

        // Make title
        JLabel title = new JLabel("Quiz "+Integer.toString(index)+"/10");
        title.setForeground(Color.red);
        title.setFont(new Font("Gill Sans MT", Font.PLAIN, 30));
        title.setAlignmentX(CENTER_ALIGNMENT);

        

        // Set Frame
        this.setTitle("QUIZ");
        this.setVisible(true);
        this.setSize(600, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }
}
