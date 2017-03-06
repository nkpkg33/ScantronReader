import processing.core.PImage;

/***
 * Class to perform image processing for optical mark reading
 * 
 */
public class OpticalMarkReader {
	
	private int firstX = 126, firstY = 472;
	private int bubbleHeight = 18, bubbleWidth = 18;
	private int horizontalSpace = 38, verticalSpace = 37;
	private int numAnswers = 5, numQuestions = 100, numQuestionsPerCol = 25;

	/***
	 * Method to do optical mark reading on page image.  Return an AnswerSheet object representing the page answers.
	 * @param image
	 * @return
	 */
	public AnswerSheet processPageImage(PImage image) {
		image.filter(PImage.GRAY);
		AnswerSheet answersheet = new AnswerSheet();
		
		for(int i = 0; i < numQuestions; i++){
			int darkestValue = Integer.MAX_VALUE, darkestIndex = 0;
			int row = i % numQuestionsPerCol;
			
			for(int j = 0; j < numAnswers; j++){
				int value = getSumAt(firstY + row * (verticalSpace), firstX + j * (horizontalSpace), image);
				if (value < darkestValue){
					darkestValue = value;
					darkestIndex = j;
				}
			}
			
			answersheet.addAnswer(darkestIndex);
		}
		
		return answersheet;
	}
	
	private int getSumAt(int row, int col, PImage image) {
		int sum = 0;
		for(int i = 0; i < bubbleHeight; i++){
			for(int j = 0; j < bubbleWidth; j++){
				sum += getPixelAt(row+i, col+j, image);
			}
		}
		return sum;
	}

	public int getPixelAt(int row, int col, PImage image){
		image.loadPixels();
		int index = row * image.width + col;
		return image.pixels[index] & 255;
	}
}
