import java.awt.Color;
import java.util.TimerTask;

import javax.swing.JComponent;

//changes the color of container
public class ChangeColors <j> extends TimerTask {
	private j container;
	private Color color;
	
	public ChangeColors(j c, Color color){
		container = c;
		this.color = color;
	}
	
	
	public void run(){
		if(container instanceof JComponent){
			JComponent temp = (JComponent)container;
			temp.setBackground(color);
		}	
	}
}
