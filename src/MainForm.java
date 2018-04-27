import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {

    public static JFrame frame;
    private JPanel panel1;
    private JTextField textInput;
    private JTextField textOutput;
    private JTextField textOrder;
    private JButton buttonNext;
    private JButton buttonPrev;


    private Worker worker;

    public MainForm() {
        worker = new Worker(textInput, textOutput, textOrder);

        buttonNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worker.nextButtonPressed();
            }
        });
        buttonPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worker.prevButtonPressed();
            }
        });
        textInput.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                parse();
            }
            public void removeUpdate(DocumentEvent e) {
                parse();
            }
            public void insertUpdate(DocumentEvent e) {
                parse();
            }

            public void parse() {
                worker.wordsUpdated();
            }
        });
    }

    public static void main(String[] args) {

        frame = new JFrame("Finder");

        frame.setContentPane(new MainForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

    }
}
