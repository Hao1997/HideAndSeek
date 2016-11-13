import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Timer;
import java.sql.Time;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JTextField;


public class GameController implements ActionListener, KeyListener{
	private boolean canDoNext = false; 
	private GameModel model;
	private GameView view;
	private int currentFocus=0 ;
	
	// initialize storages objects
	public GameController(){
		super();
		model = new GameModel();
		view = new GameView(model, this);
		view.getStorage()[0].setFocusable(true);
		view.getStorage()[0].setVisible(true);
		view.getStorage()[0].setEnabled(true);
		view.getStorage()[0].addNotify();
		view.getStorage()[0].requestFocusInWindow();
		view.setVisible(true);
		
		
		
	}
	
	//returns if storage at index n is filled or not
	private boolean isFull(int n){
		return !view.getStorage()[n].getText().equals("");
	}
	
	//changes the color of boxes whether the player gets the right or wrong answer
	private void turnColors(Color color, long delay){
		Timer timer = new Timer();
		for ( int i = 0; i<model.getWord().length; i++ ){
			
			TimerTask changeColors = new ChangeColors <JTextField>(view.getStorage()[i], color);
			timer.schedule(changeColors, delay);
		}view.repaint();
	}
	
	
	//player performs an action by pressing button or typing a letter
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource()instanceof JButton){
			JButton click = (JButton)e.getSource();
			
			
			//Press quit
			if(click.getText().equals("Quit")){
				System.exit(0);
			}
			
			//press reveal
			else if (click.getText().equals("Reveal")){
				view.updateImage("image");
				view.reveal();	
			}
			
			//press check
			else if (click.getText().equals("Check")){
				
				if (view.getCheckedButton().isEnabled()){
				boolean check = true;
				int countDown = model.getWord().length;
				while(countDown>0){
					if (!(Character.toString(model.getWord()[countDown-1]).equals
							(view.getStorage()[countDown-1].getText().toLowerCase()))){
						check = false;
					}countDown--;
				//player gets it correct
				}if (check == true){
					view.updateImage("image");
					view.invalidate();
					canDoNext = true;
				for(int countup = 0 ; countup<model.getWord().length; countup++){
					view.getStorage()[countup].setEditable(false);
				}
					
					view.getNextButton().setEnabled(true);
					view.getNextButton().setBackground(Color.WHITE);
					this.turnColors(Color.GREEN, 50);
					view.repaint();
					//player gets it wrong
				}else {
					this.turnColors(Color.RED,50);
					this.turnColors(Color.WHITE, 200);
					view.setBackground(Color.red);
					
					for (int i = 0 ; i<model.getWord().length; i ++){
					view.getStorage()[i].setText("");
					}view.getStorage()[0].requestFocusInWindow();
				}
				}				
				
				//player presses next button
			}else if (click.getText().equals("Next")){
				if (canDoNext == true ){
				view.getBoxes().setEnabled(true);
				model.nextWord();
				view.update();
				this.turnColors(Color.WHITE,50);
				int i = 0;
				while (!(view.getStorage()[i] == null)){
					view.getStorage()[i].setEditable(true);
					i++;}
				canDoNext = false;
				view.getNextButton().setEnabled(false);
				view.getCheckedButton().setEnabled(true);
				view.getNextButton().setBackground(Color.LIGHT_GRAY);
				view.getStorage()[0].requestFocusInWindow();
				}
			}
		}
	}
	
	//detects keystrokes
	public void keyTyped(KeyEvent e) {
		if(Character.isLetter(e.getKeyChar())){
			int len = model.getWord().length;
			while (len>0){
				if(view.getStorage()[len-1].hasFocus()){
					currentFocus = len;
				}	
				len--;
			}
			view.getStorage()[currentFocus%model.getWord().length].requestFocusInWindow();
					}	
	}
	public void keyReleased(KeyEvent e) {
	}
	
		
	
	public void keyPressed(KeyEvent e) {
		
		//hits enter, clicks next if player gets it correct, hits check if it hasnt been checked yet
		if (e.getKeyCode()==(KeyEvent.VK_ENTER)){
			if(canDoNext == true){
				view.getNextButton().doClick();
			}else{
				view.getCheckedButton().doClick();
			}
		}else 
			//player hits a character key, inputs letter into boxes
		if(Character.isLetter(e.getKeyChar())){
			if(isFull(currentFocus%model.getWord().length)&&view.getStorage()[currentFocus%model.getWord().length].isEditable()){
				view.getStorage()[currentFocus%model.getWord().length].setText("");
			}
		}else{
			if(view.getStorage()[currentFocus%model.getWord().length].getText().length()==1){
				view.getStorage()[currentFocus%model.getWord().length].setText("");
		}
		}
			
	
}
	
}
	
	

	

	
	
		
	

