import java.io.File;
import java.util.Random;


public class GameModel {
private double coin;
private char[] word;
public String[] canBeWords;
private Random generator = new Random();


//reads the file and choses a word
public GameModel(){
	this.read();
	int chose = generator.nextInt(canBeWords.length);
	word = canBeWords[chose].toCharArray();
	
}

//reads the Image file and puts possible words in array
private void read(){
	File animalPicturesFolder = new File("Image");
	
	File [] listOfAnimalNames = animalPicturesFolder.listFiles();
	canBeWords = new String[listOfAnimalNames.length];
	for(int i = 0 ; i<listOfAnimalNames.length;i++){
		//if(listOfAnimalNames[i].isFile()){
			canBeWords[i] = listOfAnimalNames[i].getName();
		//}
	}

}

//getters and setters
public char[] getWord(){
	return word;
}

public void nextWord (){
	this.read();
	int chose = generator.nextInt(canBeWords.length);
	word = canBeWords[chose].toCharArray();
}

}
