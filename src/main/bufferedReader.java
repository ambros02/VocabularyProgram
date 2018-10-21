package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class bufferedReader {
	private static Object scanner;

	public static void main(String[] args) {

		// words = new String[10];
		HashMap<String, String> myDictionary = new HashMap<String, String>();

		BufferedReader br = null;
		Vector<String> englishWordsList = new Vector<String>();

		String line;
		try {
			br = new BufferedReader(new FileReader("H:\\workspaceEclipse\\bufferedreaderGerman\\example.txt"));

			while ((line = br.readLine()) != null) {
				int posOfEqualSign = line.indexOf("=");
				String englishWord = line.substring(0, posOfEqualSign);
				String germanWord = line.substring(posOfEqualSign + 1, line.length());
				myDictionary.put(englishWord, germanWord);
				englishWordsList.add(englishWord);
			}

			// number of repeating
			int repeat;
			System.out.println("Please give in the number of words you want to check: ");
			Scanner sc = new Scanner(System.in);
			repeat = sc.nextInt();

			for (int a = 1; a <= repeat; a++) {
				// print out a word
				int randomNumber;

				// take number of entries
				int numberOfEntries = englishWordsList.size() - 1;
				randomNumber = (int) (Math.random() * numberOfEntries + 1);

				System.out.println(englishWordsList.get(randomNumber));

				String answer = myDictionary.get(englishWordsList.get(randomNumber));

				// compare answer with the word
				String userAnswer;
				Scanner scanAnswer = new Scanner(System.in);
				userAnswer = scanAnswer.nextLine();

				if ((userAnswer).equals(answer)) {
					System.out.println("That's right");
				} else {
					System.out.println("That's wrong");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
