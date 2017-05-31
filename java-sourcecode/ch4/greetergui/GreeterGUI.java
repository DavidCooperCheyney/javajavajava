import javax.swing.*;import java.awt.*;import java.awt.event.*;public class GreeterGUI extends JFrame implements ActionListener {    private JTextArea display;    private JTextField inField;    private JButton goButton;    private Greeter greeter;	        public GreeterGUI(String title) {        greeter = new Greeter();      	buildGUI();        setTitle(title);        pack();        show();    }            private void buildGUI() {        Container contentPane = getContentPane();    	contentPane.setLayout(new BorderLayout());        display = new JTextArea(10,30);        inField = new JTextField(10);        inField.addActionListener(this);        goButton = new JButton("Click here for a greeting!");        goButton.addActionListener(this);        JPanel inputPanel = new JPanel();        inputPanel.add(new JLabel("Input your name here: "));        inputPanel.add(inField);        inputPanel.add(goButton);        contentPane.add("Center", display);        contentPane.add("South", inputPanel);    }        public void actionPerformed(ActionEvent e) {        if (e.getSource() == goButton || e.getSource() == inField) {            String name = inField.getText();            display.append(greeter.greet(name) + "\n");        }    }}