
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;



public class GameView extends JFrame {
	private GameModel model;
	private GameController controller;
	
	private JTextField textBox;
	private JTextField [] storage;
	private JLabel image;
	private JPanel lowerPanel;
	private JButton next = new JButton("Next");
	private JButton check = new JButton("Check");
	private JButton reveal = new JButton("Reveal");
	private JButton quit = new JButton("Quit");
	private JPanel boxes;
	
	private char[]word;
	
	
	//Builds the GUI of the game
	public GameView(GameModel model, GameController controller ){
		super("Hide and Seek");
		//initialize components
		this.model = model;
		this.controller = controller;
		word = model.getWord();
		
		boxes = new JPanel();
		boxes.setLayout(new FlowLayout());

		lowerPanel = new JPanel();
		storage = new JTextField[100];
		
		//Set Size and default actions
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setResizable(true);
		setLayout(new BorderLayout());
		setBackground(Color.BLUE);
		
		//initialize check, nex, quit, reveal buttons
		JPanel option2 = new JPanel();
		option2.setBackground(new Color(13, 13, 201));
		option2.setLayout(new GridLayout(1,2));
		
		JPanel option1 = new JPanel();
		option1.setBackground(new Color(13, 13, 201));
		option1.setLayout(new GridLayout(1,2));
		
		reveal.addActionListener(controller);
		reveal.setBackground(Color.WHITE);

		quit.addActionListener(controller);
		quit.setBackground(Color.white);
		
		next.addActionListener(controller);
		next.setBackground(Color.LIGHT_GRAY);
		next.setEnabled(false);
				
		check.addActionListener(controller);
		check.setEnabled(true);
		check.setBackground(Color.LIGHT_GRAY);
		
		
		option1.add(quit);
		option1.add(reveal);	
		option2.add(check);
		option2.add(next);
		
		// initalize lower part, boxes, and options
		lowerPanel.setLayout(new BorderLayout());
		lowerPanel.add(boxes, BorderLayout.CENTER);
		lowerPanel.add(option2 , BorderLayout.LINE_END);
		lowerPanel.add(option1, BorderLayout.WEST);
		lowerPanel.setBackground(new Color(13, 13, 201));
		lowerPanel.addNotify();
		
		//create image and add lower panel
		createLetterBoxes(model.getWord());
		image = createGuessImage("hidden");
		image.setBackground(Color.BLUE);
		
		add(lowerPanel, BorderLayout.SOUTH);
		add(image , BorderLayout.CENTER);
		getContentPane().setBackground(new Color(13,13,201));	
		storage[0].requestFocusInWindow();
	}
	
	
	//creates the character boxes
	private void createLetterBoxes(char[] word){
		int numberOfBoxes = word.length;
		
		for (int numBoxes = 0; numBoxes < numberOfBoxes; numBoxes++){
			textBox = new JTextField(2);
			textBox.addKeyListener(controller);
			textBox.addNotify();
			textBox.setFont(new Font ("TimesNewRomain",Font.PLAIN, 30 ));
			textBox.setHorizontalAlignment(JTextField.CENTER);
			textBox.addActionListener(controller);
			storage[numBoxes] = textBox;
			boxes.add(textBox);
			textBox.setDocument(new TextFieldCustom());
			

		}
		
	}
	
	//chose type of image hidden or revealed for parameter
	//creates the image
	private JLabel createGuessImage(String s){
		String word = String.valueOf(model.getWord());
		ImageIcon imageTemp = new ImageIcon("Image"+"/" + word + "/" + word + "_" +s+".png");
		ImageIcon imagecon = new ImageIcon(imageTemp.getImage().getScaledInstance(500, 400,java.awt.Image.SCALE_REPLICATE));
		return new JLabel(imagecon);
		
	}
	
	//updates the image, chose whether it is revealed or hidden
	public void updateImage(String s){
		remove(image);
		image = createGuessImage(s);
		add(image , BorderLayout.CENTER);
		revalidate();
		repaint();
	}


//change words	
	public void update (){
		this.word = model.getWord();
		int i = 0;
		//empties the contents of the storage(stores the letters of current image)
		while (!(storage[i] == null)){
			storage[i]=null;
			i++;}
			
		//remove current letter boxes and creates new one with images
		boxes.removeAll();
		createLetterBoxes(model.getWord());
		updateImage("hidden");
		
		lowerPanel.add(boxes,BorderLayout.CENTER);
		add(lowerPanel, BorderLayout.SOUTH);
		revalidate();
		repaint();
	}
	
	
	//reveals the image 
	public void reveal(){
		for(int cursor = 0 ; cursor < (word.length)  ; cursor ++){
			storage[cursor].setText(Character.toString(word[cursor]));
			storage[cursor].setEnabled(false);
		}
		
	}
	

//getters and setters	
	public JButton getCheckedButton(){
		return check;
	}
	
	public JButton getNextButton(){
		return next;
	}
	
	public JButton getQuitButton(){
		return quit;
	}
	
	public JButton getRevealButton(){
		return reveal;
	}
	
	
	public JTextField[] getStorage(){
		return storage;
	}
	
	
	public JPanel getBoxes(){
		return boxes;
	}
	
	
}
