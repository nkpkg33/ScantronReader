import java.util.ArrayList;

/***
 * A class to represent a set of answers from a page
 */
public class AnswerSheet {
	
	private ArrayList<String> answers = new ArrayList<String>();
	
	public AnswerSheet(){
		
	}
	
	public void addAnswer(int answer){
		if (answer == 0) answers.add("A");
		else if (answer == 1) answers.add("B");
		else if (answer == 2) answers.add("C");
		else if (answer == 3) answers.add("D");
		else if (answer == 4) answers.add("E");
	}
	
	public String get(int index){
		return answers.get(index);
	}

	public void print(){
		for(int i = 0; i < answers.size(); i++)
			System.out.print(answers.get(i) + " ");
		System.out.println();
	}

	public int getNumQuestions() {
		return answers.size();
	}
}
