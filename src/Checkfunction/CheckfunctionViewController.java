package Checkfunction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.AnnotatedWildcardType;
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
	
	HashMap<String,String> myDictionary = new HashMap<String,String>();
	private List<String> englishWords;
	private String englishWord;
	private String germanWord;
	private boolean correct = false;
	private Random rnd;

	@FXML
    public void initialize() {
        BufferedReader br;

		try {
			File file = new File(".\\example.txt");
			br = new BufferedReader(new FileReader(file));
			String line;
			int language = 0;
	        while((line = br.readLine()) != null){
	            String[] splited = line.split("=");
				myDictionary.put(splited[0 + language], splited[1 - language]);
	        }
	        englishWords = new ArrayList<>(myDictionary.keySet());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rnd = new Random();
        englishWord = englishWords.get(rnd.nextInt(englishWords.size() - 1));
        germanWord = myDictionary.get(englishWord);
        word.setText(englishWord);
    }
	
	@FXML
	public void handleCheckButton(ActionEvent actionEvent) {
		if (englishWord != null) {
			if (answer.getText().trim().equals(germanWord)) {
				correction.setText("CORRECT");
				correct  = true;
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
		if (englishWords.size() > 0) {
			int index = rnd.nextInt(englishWords.size());
			englishWord = englishWords.get(index);
	        germanWord = myDictionary.get(englishWord);
		} else {
			englishWord = null;
		}
		word.setText(englishWord == null ? "" : englishWord);
        answer.setText("");
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
