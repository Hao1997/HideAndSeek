
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

//custom textboxes for game
public class TextFieldCustom extends PlainDocument{
	private final int limit = 1;
	
	
	public TextFieldCustom ()	{
		super();
	}
	
	
	//make sure the boxe can only hold 1 letter, centered
	public void insertString(int offset, String str, AttributeSet att)throws BadLocationException{
		if(str == null){
			return;
		}if((getLength() + str.length()) <= limit){
			super.insertString(offset, str, att);
		}
	}
	
}
