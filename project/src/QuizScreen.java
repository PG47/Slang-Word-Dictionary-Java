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
    JButton B_slang_quiz;
    JButton B_defin_quiz;

    QuizScreen() {
        // Making container
        Container contain = this.getContentPane();
        contain.setLayout(new BoxLayout(contain,BoxLayout.Y_AXIS));

        // Make title
        JLabel title = new JLabel("CHOOSE WHICH TYPE OF QUIZ YOU WANT?");
        title.setForeground(Color.red);
        title.setFont(new Font("Gill Sans MT", Font.PLAIN, 30));
        title.setAlignmentX(CENTER_ALIGNMENT);

        // Mid panel
        JPanel mid_panel = new JPanel();
        mid_panel.setLayout(new BoxLayout(mid_panel,BoxLayout.X_AXIS));

        // Make button for slang quiz
        B_slang_quiz = new JButton("Slang Quiz");
        B_slang_quiz.addActionListener(this);
        B_slang_quiz.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));

        // Make button for definition quiz
        B_defin_quiz = new JButton("Definition Quiz");
        B_defin_quiz.addActionListener(this);
        B_defin_quiz.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        mid_panel.add(B_slang_quiz);
        mid_panel.add(B_defin_quiz);

        //Bottom panel for back button
        JPanel bottom_panel = new JPanel();
        bottom_panel.setAlignmentX(CENTER_ALIGNMENT);
        B_back = new JButton("Back");
        B_back.addActionListener(this);
        bottom_panel.add(B_back);

        //Put all int the contain
        contain.add(Box.createRigidArea(new Dimension(0, 100)));
        contain.add(title);
        contain.add(Box.createRigidArea(new Dimension(0, 100)));
        contain.add(mid_panel);
        contain.add(Box.createRigidArea(new Dimension(0, 100)));
        contain.add(bottom_panel);

        // Set Frame
        this.setTitle("QUIZ - Choose quiz?");
        this.setVisible(true);
        this.setSize(550, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == B_slang_quiz) {
            this.dispose();
            new QuestionScreen(0,1,0);
        } else if (e.getSource() == B_defin_quiz) {
            this.dispose();
            new QuestionScreen(1,1,0);
        } else if (e.getSource() == B_back) {
            this.dispose();
            new MenuScreen();
        }
    }
}
