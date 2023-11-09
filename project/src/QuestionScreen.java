import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class QuestionScreen extends JFrame implements ActionListener {
    JButton B1,B2,B3,B4;
    JButton B_back;
    String ques[];
    int index=0;
    int Q_type=0;
    int score=0;

    QuestionScreen(int type, int num, int scr) {
        Q_type=type;
        index=num;
        score=scr;

        //make a radom question
        ques = (Slang.getInstance()).Quiz(type);

        //Make container
        Container contain = this.getContentPane();
        contain.setLayout(new BoxLayout(contain, BoxLayout.Y_AXIS));

        //Make Question title
        JLabel title = new JLabel("Quiz " + Integer.toString(num) +"/10");
        title.setForeground(Color.red);
        title.setFont(new Font("Gill Sans MT", Font.PLAIN, 30));
        title.setAlignmentX(CENTER_ALIGNMENT);

        //Make label for question
        JLabel Question_label;
        if(type==0) {
            Question_label = new JLabel("What does `" + ques[0] + "` mean?");
        } else {
            Question_label = new JLabel("Slang word for `" + ques[0] + "` is?");
        }
        Question_label.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        Question_label.setAlignmentX(CENTER_ALIGNMENT);

        //Make answer
        B1 = new JButton("A. "+ques[2]);
        B2 = new JButton("B. "+ques[3]);
        B3 = new JButton("C. "+ques[4]);
        B4 = new JButton("D. "+ques[5]);
        B1.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
        B2.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
        B3.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
        B4.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
        B1.addActionListener(this);
        B2.addActionListener(this);
        B3.addActionListener(this);
        B4.addActionListener(this);

        //Make mid panel
        JPanel mid_panel = new JPanel();
        mid_panel.setLayout(new GridLayout(2, 2, 10, 10));
        mid_panel.add(B1);
        mid_panel.add(B2);
        mid_panel.add(B3);
        mid_panel.add(B4);

        //Bottom panel for back and button
        JPanel bottom_panel = new JPanel();
        bottom_panel.setAlignmentX(CENTER_ALIGNMENT);
        B_back = new JButton("Back");
        B_back.addActionListener(this);
        bottom_panel.add(B_back);

        //Put all in the container
        contain.add(Box.createRigidArea(new Dimension(0, 100)));
        contain.add(title);
        contain.add(Box.createRigidArea(new Dimension(0, 30)));
        contain.add(Question_label);
        contain.add(Box.createRigidArea(new Dimension(0, 50)));
        contain.add(mid_panel);
        contain.add(Box.createRigidArea(new Dimension(0, 50)));
        contain.add(bottom_panel);

        // Set Frame
        this.setTitle("Question " + Integer.toString(num));
        this.setVisible(true);
        this.setSize(600, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == B1) {
            this.Answer(2);
        } else if (e.getSource() == B2) {
            this.Answer(3);
        } else if (e.getSource() == B3) {
            this.Answer(4);
        } else if (e.getSource() == B4) {
            this.Answer(5);
        }
        if (e.getSource() == B_back) {
            this.dispose();
            new MenuScreen();
        }
    }

    public void Answer(int choose) {
        if(ques[choose].equals(ques[1])) {
            JLabel text = new JLabel("CORRECT!");
            text.setForeground(Color.GREEN);
            JOptionPane.showMessageDialog(this,text);
            score=score+10;
        } else {
            JTextPane textPane = new JTextPane();
            StyledDocument doc = textPane.getStyledDocument();
            Style redStyle = textPane.addStyle("Redstyle", null);
            StyleConstants.setForeground(redStyle, Color.RED);
            try {
                doc.insertString(doc.getLength(), "WRONG!\n", redStyle);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            Style blackStyle = textPane.addStyle("Blackstyle", null);
            StyleConstants.setForeground(blackStyle, Color.BLACK);
            try {
                doc.insertString(doc.getLength(), "Answer: "+ ques[1], blackStyle);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, textPane);
        }
        index++;
        if(index<11) { //next question
            this.dispose();
            new QuestionScreen(Q_type,index,score);
        } else { //check score
            JTextPane textPane = new JTextPane();
            StyledDocument doc = textPane.getStyledDocument();
            Style line1 = textPane.addStyle("Blackstyle", null);
            StyleConstants.setForeground(line1, Color.BLACK);
            try {
                doc.insertString(doc.getLength(), "Your score:"+ Integer.toString(score) +"/100\n" , line1);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            String comment = "";
            Style line2 = textPane.addStyle("Colorstyle", null);
            if(score==100) {
                comment = "YOU ARE A GENIUS!";
                StyleConstants.setForeground(line2, Color.blue);
            }else if(score>=80) {
                comment ="WELL DONE!";
                StyleConstants.setForeground(line2, Color.yellow);
            }
            else {
                comment = "TRY BETTER NEXT TIME!";
                StyleConstants.setForeground(line2, Color.gray);
            }
            try {
                doc.insertString(doc.getLength(), comment, line2);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, textPane,"Quiz completed",JOptionPane.PLAIN_MESSAGE);
            this.dispose();
            new MenuScreen();
        }
    }
}
