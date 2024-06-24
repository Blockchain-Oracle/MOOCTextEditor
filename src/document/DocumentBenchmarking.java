package document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * A class for timing the EfficientDocument and BasicDocument classes
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 */

public class DocumentBenchmarking {

	public static void main(String[] args) {

		// Run each test more than once to get bigger numbers and less noise.
		// You can try playing around with this number.
		int trials = 100;

		// The text to test on
		String textfile = "data/warAndPeace.txt";

		// The amount of characters to increment each step
		// You can play around with this
		int increment = 20000;

		// The number of steps to run.
		// You can play around with this.
		int numSteps = 20;

		// THe number of characters to start with.
		// You can play around with this.
		int start = 50000;

		// TODO: Fill in the rest of this method so that it runs two loops
		// and prints out timing results as described in the assignment
		// instructions and following the pseudocode below.
		System.out.println("numC\tbTime\teTime");
		for (int numToCheck = start; numToCheck < numSteps * increment + start; numToCheck += increment) {
			// Step 1: Print numToCheck

			System.out.print(numToCheck + "\t");

			// Step 2: Read text from file
			String text = getStringFromFile(textfile, numToCheck);

			// Step 3: Time BasicDocument
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < trials; i++) {
				BasicDocument basicDoc = new BasicDocument(text);
				basicDoc.getFleschScore();
			}
			long endTime = System.currentTimeMillis();
			long basicTime = endTime - startTime;

			// Step 4: Print BasicDocument time
			System.out.print(basicTime + "\t");

			// Step 5: Time EfficientDocument
			startTime = System.currentTimeMillis();
			for (int i = 0; i < trials; i++) {
				EfficientDocument efficientDoc = new EfficientDocument(text);
				efficientDoc.getFleschScore();
			}
			endTime = System.currentTimeMillis();
			long efficientTime = endTime - startTime;

			// Step 6: Print EfficientDocument time and newline
			System.out.println(efficientTime);
		}

	}

	/**
	 * Get a specified number of characters from a text file
	 * 
	 * @param filename The file to read from
	 * @param numChars The number of characters to read
	 * @return The text string from the file with the appropriate number of
	 *         characters
	 */
	public static String getStringFromFile(String filename, int numChars) {

		StringBuffer s = new StringBuffer();
		try {
			FileInputStream inputFile = new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(inputFile);
			BufferedReader bis = new BufferedReader(inputStream);
			int val;
			int count = 0;
			while ((val = bis.read()) != -1 && count < numChars) {
				s.append((char) val);
				count++;
			}
			if (count < numChars) {
				System.out.println("Warning: End of file reached at " + count + " characters.");
			}
			bis.close();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}

		return s.toString();
	}

}
