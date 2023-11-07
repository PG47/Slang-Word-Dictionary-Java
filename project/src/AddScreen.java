import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class AddScreen extends JFrame implements ActionListener {
    Slang slang_word;
    JButton B_back, B_add;
    JTextField textField_slang, textField_definition;

    AddScreen() {
        // Make Container
        slang_word = Slang.getInstance();
        Container contain = this.getContentPane();
        JLabel title = new JLabel("Add new Slang word");
        title.setForeground(Color.red);
        title.setFont(new Font("Gill Sans MT", Font.PLAIN, 30));
        title.setAlignmentX(CENTER_ALIGNMENT);

        // make a panel where you inform the slang word
        JPanel form = new JPanel();
        form.setBackground(Color.gray);
        form.setLayout(new BoxLayout(form,BoxLayout.Y_AXIS));

        //make for slang input
        JLabel slang_label = new JLabel("Slang word:");
        textField_slang = new JTextField("",20);
        addHint(textField_slang,"Enter slang word here!");

        //make for definition input
        JLabel defin_label = new JLabel("Definition:");
        textField_definition = new JTextField("",20);
        addHint(textField_definition,"Enter definition here!");

        //put slang and definition into form
        JPanel line1 = new JPanel();
        line1.add(slang_label);
        line1.add(textField_slang);

        JPanel line2 = new JPanel();
        line2.add(defin_label);
        line2.add(textField_definition);

        form.add(line1);
        form.add(line2);

        // make Back button & Add button
        JPanel bottom_panel = new JPanel();
        B_back=new JButton("Back");
        B_back.addActionListener(this);
        B_back.setFocusable(false);
        B_back.setAlignmentX(CENTER_ALIGNMENT);

        B_add=new JButton("Add");
        B_add.addActionListener(this);
        B_add.setFocusable(false);
        B_add.setAlignmentX(CENTER_ALIGNMENT);

        bottom_panel.add(B_back);
        bottom_panel.add(B_add);

        //Put all in the cointainer
        contain.setLayout(new BoxLayout(contain, BoxLayout.Y_AXIS));
        contain.add(Box.createRigidArea(new Dimension(0, 10)));
        contain.add(title);
        contain.add(Box.createRigidArea(new Dimension(0, 10)));
        contain.add(form);
        contain.add(Box.createRigidArea(new Dimension(0, 10)));
        contain.add(bottom_panel);

        // Set Frame
        this.setTitle("Add Slang word");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //go back to the menu screen
        if (e.getSource() == B_back) {
            this.dispose();
            new MenuScreen();
        }
        if (e.getSource() == B_add) {
            String slang = textField_slang.getText();
            String definition = textField_definition.getText();
            if(slang.isEmpty() || definition.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Slang and Definition can't empty", "EMPTY INPUT!",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Check if the slang word is already exist
            if(slang_word.Check_Slang(slang)) {
                Object[] options = {"Overwrite it", "Make a new definition for it"};
                int choice = JOptionPane.showOptionDialog(this,
                        "Slang word have already exist!", "Oops",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                if (choice == 0) { //Overwrite
                    slang_word.Overwrite(slang,definition);
                }
                if (choice == 1) {
                    slang_word.Duplicate(slang,definition);
                }
                JOptionPane.showMessageDialog(this, "Done!");
            } else {
                slang_word.Add_New(slang,definition);
                JOptionPane.showMessageDialog(this, "Add success!");
            }
        }
    }

    public static void addHint(JTextField textField, String hint) {
        textField.setText(hint);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(hint)) {
                    textField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(hint);
                }
            }
        });
    }
}
