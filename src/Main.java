import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;

public class Main {
	public static final String PDF_PATH = "/omrtest.pdf";
	public static OpticalMarkReader markReader = new OpticalMarkReader();
	public static int numSheets = 0;
	public static int[] numStudentsCorrect = new int[100];
	public static double[] percentStudentsCorrect = new double[100];
	
	public static void main(String[] args) {
		System.out.println("Welcome!  I will now auto-score your pdf!");
		System.out.println("Loading file..." + PDF_PATH);
		ArrayList<PImage> images = PDFHelper.getPImagesFromPdf(PDF_PATH);
		numSheets = images.size() - 1;

		System.out.println("Scoring all pages...");
		scoreAllPages(images);

		System.out.println("Complete!");
		
		// Optional:  add a saveResults() method to save answers to a csv file
	}
	
	/*public static void writeDataToFile(String filePath, String data) {
		File outFile = new File(filePath);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
			writer.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public static int getNumSheets(){
		return numSheets;
	}

	/***
	 * Score all pages in list, using index 0 as the key.
	 * 
	 * NOTE:  YOU MAY CHANGE THE RETURN TYPE SO YOU RETURN SOMETHING IF YOU'D LIKE
	 * 
	 * @param images List of images corresponding to each page of original pdf
	 */
	private static void scoreAllPages(ArrayList<PImage> images) {
		ArrayList<AnswerSheet> scoredSheets = new ArrayList<AnswerSheet>();

		// Score the first page as the key
		AnswerSheet key = markReader.processPageImage(images.get(0));		

		for (int i = 1; i < images.size(); i++) {
			PImage image = images.get(i);

			AnswerSheet answers = markReader.processPageImage(image);
			answers.setKey(key);
			answers.calculateData();
			scoredSheets.add(answers);
		}
		
		updateCorrectArrays(scoredSheets);

		CSVDataWriter.addStudentSheets("Output.csv", scoredSheets);

		CSVDataWriter.writeToItemAnalysis("ItemAnalysis.csv", itemAnalysis(scoredSheets));
	}
	
	public static String[][] itemAnalysis(ArrayList<AnswerSheet> scoredSheets){
		
		String[][] analysis = new String[101][3];
		
		analysis[0][0] = "Question Number";
		
		analysis[0][1] = "Number of Correct Students";
		
		analysis[0][2] = "Percent of Correct Students";
		
		for (int i = 1; i < analysis.length; i++){
			
			analysis[i][0] = i + "";
			
			analysis[i][1] = numStudentsCorrect[i-1] + "";
			
			analysis[i][2] = percentStudentsCorrect[i-1] + "";
			
		}
		
		return analysis;
		
		
	}
	
	public static void updateCorrectArrays(ArrayList<AnswerSheet> scoredSheets){
		
		int correctCounter = 0;
		
		for (int i = 0; i < numStudentsCorrect.length; i++){
			
			for (int c = 0; c < scoredSheets.size(); c++){
				
				if (scoredSheets.get(c).getCorrectAnswers()[i] == 1) correctCounter++;
				
			}
			
			numStudentsCorrect[i] = correctCounter;
			percentStudentsCorrect[i] = (correctCounter/(double)scoredSheets.size()) * 100;
			
			correctCounter = 0;
			
		}
		
	}
}