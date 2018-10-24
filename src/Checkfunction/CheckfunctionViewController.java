package Checkfunction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CheckfunctionViewController {
	
	@FXML
	private TextField word;
	@FXML
	private TextField answer;
	@FXML
	private TextField correction;
	
	private boolean running = false;
	
	HashMap<String,String> myDictionary = new HashMap<String,String>();
	private List<String> englishWords;
	private String englishWord;
	private String germanWord;
	private boolean correct = false;
	private Random rnd = new Random();

	@FXML
    public void initialize() {
    }
	
	@FXML
	public void handleStartButton(ActionEvent actionEvent) {
		if (!running) {
			running = true;
			BufferedReader br;
	
			try {
				//TODO change to resource folder
				br = new BufferedReader(new FileReader(new File(".\\example.txt")));
				String line;
				//TODO implement language check
				// switches key and value if language is 1 (only 1 and 0 as values allowed)
				int language = 0;
		        while((line = br.readLine()) != null){
		            String[] splited = line.split("=");
					myDictionary.put(splited[0 + language], splited[1 - language]);
		        }
		        
			} catch (IOException e) {
				System.out.println("An error occured while trying to read the example.txt file");
				e.printStackTrace();
			}
			
			englishWords = new ArrayList<>(myDictionary.keySet());

			englishWord = englishWords.get(rnd.nextInt(englishWords.size()));
	        germanWord = myDictionary.get(englishWord);
	        word.setText(englishWord);
	        answer.setEditable(true);
		}
	}
	
	@FXML
	public void handleStopButton(ActionEvent actionEvent) {
		if (running) {
			running = false;
			word.setText("");
			answer.setText("");
			answer.setEditable(false);
	        correction.setText("");
		}
		
	}
	
	@FXML
	public void handleCheckButton(ActionEvent actionEvent) {
		if (running) {
			if (answer.getText().trim().equals(germanWord.trim())) {
				correction.setText("CORRECT");
				correct = true;
			} else {
				correction.setText("FALSE");
			}
			answer.setEditable(false);
		}
	}
	
	@FXML
	public void handleNextButton() {
		if (!answer.isEditable()) {
			if (correct) {
				correct = false;
				englishWords.remove(englishWord);        
			} else {
				englishWords.add(englishWord);
			}
			answer.setEditable(true);
		}
		
		if (!englishWords.isEmpty()) {
			int index = rnd.nextInt(englishWords.size());
			englishWord = englishWords.get(index);
	        germanWord = myDictionary.get(englishWord);
		} else {
			running = false;
		}
		
		word.setText(!running ? "" : englishWord);
        answer.setText("");
        if (!running) {
        	answer.setEditable(false);
        }
        correction.setText("");
	}
	
	public TextField getWord() {
		return word;
	}
	public void setWord(TextField word) {
		this.word = word;
	}
	public TextField getAnswer() {
		return answer;
	}
	public void setAnswer(TextField answer) {
		this.answer = answer;
	}
	public TextField getCorrection() {
		return correction;
	}
	public void setCorrection(TextField correction) {
		this.correction = correction;
	}
	
}
