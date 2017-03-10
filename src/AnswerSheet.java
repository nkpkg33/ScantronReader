import java.util.ArrayList;

/***
 * A class to represent a set of answers from a page
 */
public class AnswerSheet {
	
	private ArrayList<String> answers = new ArrayList<String>();
	private AnswerSheet key;
	private int numCorrect, numIncorrect;
	private double percentCorrect, percentIncorrect;
	private static int[] correctAnswers = new int[100];
	
	public AnswerSheet(){
		
	}
	
	public void addAnswer(int answer){
		if (answer == 0) answers.add("A");
		else if (answer == 1) answers.add("B");
		else if (answer == 2) answers.add("C");
		else if (answer == 3) answers.add("D");
		else if (answer == 4) answers.add("E");
	}
	
	public int[] getCorrectAnswers(){
		
		return correctAnswers;
		
	}
	
	public String get(int index){
		return answers.get(index);
	}

	public int getNumCorrect() {
		return numCorrect;
	}

	public int getNumIncorrect() {
		return numIncorrect;
	}

	public double getPercentCorrect() {
		return percentCorrect;
	}

	public double getPercentIncorrect() {
		return percentIncorrect;
	}

	public void print(){
		for(int i = 0; i < answers.size(); i++)
			System.out.print(answers.get(i) + " ");
		System.out.println();
	}
	
	public void print(int[] arr){
		for(int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
	}

	public int getNumQuestions() {
		return answers.size();
	}

	public void setKey(AnswerSheet key) {
		this.key = key;
	}
	
	public void calculateData(){
		int score = 0;
		for(int q = 0; q < key.getNumQuestions(); q++){
			if(key.get(q) == this.get(q)){	
				score++;
				
				this.correctAnswers[q] = 1;
			}
			
			else{
				
				this.correctAnswers[q] = 0;
				
			}
			
		}
		
		this.numCorrect = score;
		this.numIncorrect = this.getNumQuestions() - this.numCorrect;
		this.percentCorrect = (double) numCorrect / (double) this.getNumQuestions();
		this.percentIncorrect = (double) numIncorrect / (double) this.getNumQuestions();
		
	}
	
}
