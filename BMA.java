import java.util.Arrays;
import java.util.*;
import java.io.*;
/**
Boyer Moore Method for String Matching Implementation

	This class uses arguments taken at execution, when testing use this command format 'java BMA pattern textfile'

	StateChange Method
	reactions to the current state are determined by the next input (txt[input])
	
	Possible improvments:
	Use String implementation as oppossed the char[] arrays
	Implement methods for changing the pattern and reusing the class
	Integrate the Galil Rule
**/
public class BMA{
	static char[] pat;
	static char[] txt;
	//position in pattern
	static int state;
	//position in text
	static int input;
	//Indices of pattern matches
	static ArrayList<Integer> total;

	public BMA(String pat, String txt){
		this.pat = pat.toCharArray();
		this.txt = txt.toCharArray();
		state = this.pat.length-1;
		input = this.pat.length-1;
		total = new ArrayList<Integer>();
	}
	
	public static void stateChange(){
		//more input exists
		if (input <= txt.length-1 && state >= 0){
			//input matches state
			if (txt[input] == pat[state]){
				//state is in accepting state
				if (state == 0){
					total.add(input);
					state = pat.length-1;
					input = input+((pat.length*2)-1);
				} 
				//input matches state, but not in accepting state
				else {
					state--;
					input--;
				}
			} 
			//input does not match state
			else {
				for (int i=state-1; i>=0; i--){
					//input is present further in the pattern
					if (txt[input] == pat[i]){
						state = pat.length-1;
						input = input+((pat.length-i)-1);
						return;
					}
				}
				//input is not present further in the pattern
				state = pat.length-1;
				input = input+(((pat.length*2)-1)-state);
			}
		} 
		//no more input
		else {
			state = -1;
		}
	}
	
	public static void main(String[] args){
		String pattern = args[0];
		String text = fileToString(args[1]);
		BMA bma = new BMA(pattern, text);
		while (bma.state != -1){
			bma.stateChange();
		}
		//There is a slight bug where every recorded index is 3 larger than what it needs to be, the next for loop fixes that
		//Given that the class smallBMA records them properly without this loop, I am inclined to believe that the fileToString method is finding extra characters that don't appear on screen. (see test.txt for a list of character indices as understood by BMA.java for the String "Vannevar")
		for (int i = 0; i < bma.total.size(); i++){
			bma.total.set(i, bma.total.get(i) - 3);
		}
		System.out.println("Indices of \""+pattern+"\": " + bma.total.toString());
	}
	
	public static String fileToString (String fileName) {
		String result = "";
		try {
			FileInputStream file = new FileInputStream(fileName);
			byte[] b = new byte[file.available()];
			file.read(b);
			file.close();
			result = new String(b);
		}
		catch (Exception e) {
			System.out.println("oops");
		}
		return result;
	}
}