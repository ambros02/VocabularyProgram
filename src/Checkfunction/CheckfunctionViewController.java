package Checkfunction;

import java.util.HashMap;

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

    private void initalize() {
        BufferedReader br = new BufferedReader(new FileReader("H:\\workspaceEclipse\\bufferedreaderGerman\example.txt"));
        while((line = br.readLine()) != null){
            String[] splited = line.split('=');
            myDictionary.put(splited[0], splited[1]);
        }
        englishWords = myDictionary.keySet();
    }
	
	@FXML
	public void handleCheckButton(ActionEvent actionEvent) {
		correction.setText("hello world");
		
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
