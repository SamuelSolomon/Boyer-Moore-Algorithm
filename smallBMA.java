//Prints each step of the Boyer-Moore process using data from the BMA class 
//output was created for use in the command line, other useage may make text line up improperly

public class smallBMA{
	public static void main(String[] args){
		String pattern = "wood";
		String text = "How much wood would a wood chuck chuck?";
		String c = "";
		BMA bma = new BMA(pattern, text);
		//formatting for each step of the process
			while (bma.state != -1){
				System.out.println(text);
				for(int i = bma.input-1; i >= 0; i--){
					System.out.print(" ");
				}
				System.out.print("^\n");
				for(int i = bma.input - pattern.length(); i >= 0; i--){
					System.out.print(" ");
				}
				if (!(bma.input > bma.txt.length) && (bma.pat[bma.state] == bma.txt[bma.input])){
					System.out.print(c);
					c = c + " ";
				} else {
					c = "";
				}
				System.out.print(pattern + "\n");
				//end of formatting
				bma.stateChange();
			}
		//functions correctly and prints out correct indicies
		System.out.println("Indices of \""+pattern+"\": " + bma.total.toString());
	}
}