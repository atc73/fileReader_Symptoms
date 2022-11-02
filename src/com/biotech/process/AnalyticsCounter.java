package com.biotech.process;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AnalyticsCounter {

	public static void main(String args[]) throws Exception {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Quel fichier voulez-vous traiter ? Pensez à l'extension.");
		String intputFile = scanner.next();
		// first get input
		ArrayList<String> reader = new ReadSymptomDataFromFile().getSymptoms(intputFile);

		HashMap<String, Integer> symptomsOccurences = new HashMap<String, Integer>();

		reader.forEach(symptom -> {
			if(symptomsOccurences.containsKey(symptom)) {
				symptomsOccurences.put(symptom, symptomsOccurences.get(symptom) + 1);
			} else {
				symptomsOccurences.put(symptom, 1);
			}
		});

		System.out.println("Dans quel fichier souhaitez-vous afficher les résultats ?");
		String outputFile = scanner.next();

		
		// next generate output
		FileWriter writer = new FileWriter (outputFile);

		symptomsOccurences.forEach((key, value) -> {
			try {
				writer.write(key + " : " + value + "\n");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		writer.close();
	}
}
