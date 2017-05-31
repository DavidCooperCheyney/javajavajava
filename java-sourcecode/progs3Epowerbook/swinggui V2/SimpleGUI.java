import javax.swing.*;import java.awt.event.*;import java.awt.*;public class SimpleGUI extends JFrame implements ActionListener {	private JTextArea display;	private JTextField inField;	private JButton goButton;	private GuessingGame game;	    public SimpleGUI(JApplet a) {        createInterface();        a.getContentPane().add(this); 		game = new GuessingGame();      }        public SimpleGUI(String title) { 		game = new GuessingGame();      	createInterface();    	this.pack();    	this.setTitle(title); //   	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    	this.setDefaultCloseOperation(0);    	this.show(); 		    }        private void createInterface() {        Container rPane = getContentPane();    	rPane.setLayout(new BorderLayout());        display = new JTextArea(10,30);        inField = new JTextField(10);        inField.addActionListener(this);        goButton = new JButton("Guess!");        goButton.addActionListener(this);        JPanel p = new JPanel();        p.add(new JLabel("Input you guess here: "));        p.add(inField);        rPane.add("Center",display);        rPane.add("South",p);    }        public void actionPerformed(ActionEvent e){        int myguess = Integer.parseInt(inField.getText());    		    display.append(game.guess(myguess) + "\n");    }}