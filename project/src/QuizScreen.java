import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import static java.awt.Color.*;

public class QuizScreen extends JFrame implements ActionListener {
    JButton B_back;
    JButton B_slang_quiz;
    JButton B_defin_quiz;

    public class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            int width = getWidth();
            int height = getHeight();

            //Define the color radient and its direction
            Color color1 = PINK;
            Color color2 = BLUE;
            GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);

            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.setPaint(gradient);
            graphics2D.fillRect(0, 0, width, height);
        }
    }

    QuizScreen() {
        // Making container
        GradientPanel contain = new GradientPanel();
        contain.setLayout(new BoxLayout(contain,BoxLayout.Y_AXIS));
        contain.setPreferredSize(new Dimension(600, 600));
        setContentPane(contain);

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
        bottom_panel.setOpaque(false);
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
        this.setSize(700, 500);
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
