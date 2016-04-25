package Diss.questions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MathQuestions {

	
	private static Object[] QnA = new Object[50] ;
	private static int index = 0;
	public static int currentQuestion =0;
	
	
	public MathQuestions(){
		try {
			load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getQuestion(int currentQuestion){
		String question="";
		String[] temp= (String[])QnA[currentQuestion-1];
		question = temp[0];
		return question;
	}
	public static void answerFinder(){
		//QnA = currentQuestion.split(",");
////		System.out.println(QnA[index]);
//		String[] temp= (String[])QnA[0];
//		System.out.println(temp[0]+temp[1]);
//		currentQuestion = temp[0];

	}
	
	public static void load() throws IOException{
		index=0;
		 @SuppressWarnings("resource")
		BufferedReader in = new BufferedReader(new FileReader("QnA.txt"));
		 String currentLine;
		 while(( currentLine = in.readLine()) != null){
			 if(currentLine.charAt(0) != '#'){
				 String[] Question = currentLine.split(",");
				 QnA[index] = Question;
				 index++;
			 }
		 }
	}

	public int getRandomQuestion() {
		
		int rand = (int) Math.floor(Math.random()*(index-1));
		System.out.println("random value "+(rand+1));
		return (rand+1);
	}

	public int getCurrentAnswer(int currentQuestion) {
		int answer=0;
		String[] temp= (String[])QnA[currentQuestion-1];
		String value = temp[1];
		value= value.replaceAll(" ","");
		answer = Integer.parseInt(value);
		return answer;
	}
	
	public String getCurrentSign(int currentQuestion) {
		String sign="";
		String[] temp= (String[])QnA[currentQuestion-1];
		String value = temp[2];
		sign= value.replaceAll(" ","");
		return sign;
	}
	
}
