import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVDataWriter {
	private static boolean DEBUG = false;
	private static double[][] outputData;
	private static String[] columnHeaders = {"Student #", "# Correct", "# Incorrect", "% Correct", "% Incorrect"};
		
	public static void writeDataToFile(String filePath, String data) {
		File outFile = new File(filePath);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
			writer.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addStudentSheets(String filePath, ArrayList<AnswerSheet> sheets){
		outputData = new double[Main.getNumSheets()][5];
		for(int i = 0; i < sheets.size(); i++){
			AnswerSheet sheet = sheets.get(i);
			outputData[i][0] = i;
			outputData[i][1] = sheet.getNumCorrect();
			outputData[i][2] = sheet.getNumIncorrect();
			outputData[i][3] = sheet.getPercentCorrect();
			outputData[i][4] = sheet.getPercentIncorrect();
		}
		writeDataToFile(filePath, convertToString(outputData));
	}
	
	public static String convertToString(double[][] arr){
		
		String output = "";
		
		for (int c = 0; c < columnHeaders.length; c++){
			
			output += columnHeaders[c] + ", ";
			
		}
		
		output += "\n";
		
		for (int i = 0; i < arr.length; i++){
			
			for (int j = 0; j < arr[0].length; j++){
				
				output += arr[i][j] + ", ";
				
			}
			
			output += "\n";
			
		}
		
		return output;
		
	}
	
	public static String convertToString(String[][] arr){
		
		String output = "";
		
		output += "\n";
		
		for (int i = 0; i < arr.length; i++){
			
			for (int j = 0; j < arr[0].length; j++){
				
				output += arr[i][j] + ", ";
				
			}
			
			output += "\n";
			
		}
		
		return output;
		
	}

	public static void writeToItemAnalysis(String filepath, String[][] is) {
		
		writeDataToFile("ItemAnalysis.csv", convertToString(is));
		
	}
}