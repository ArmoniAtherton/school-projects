/*
 * Main.java
 * 
 * TCSS 342 - Spring 2018
 * Armoni Atherton
 * Instructor: Paulo Barreto
 * Assignment-4
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * The main method will read in text from a file and will carry out compression of a file using the
 *  CodingTree class. Will output the compressed text to a file. Will decode a compressed file to.
 *  
 * @author Armoni Atherton athera@uw.edu
 * @version May 1, 2018 
 *
 */
public class Main {
	
	/**
	 * Static main method used to run the program and test the 
	 * program elements.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) throws IOException {
		File inFile = null;
		StringBuilder currentText = new StringBuilder();
		
		try {
			long startTime = System.nanoTime();
			inFile = new File("WarAndPeace.txt");
			BufferedReader fileReader = new BufferedReader(new FileReader(inFile));
			String currentLine;
			while ((currentLine = fileReader.readLine()) != null) {
				currentText.append(currentLine + "\n");

			}
			//END OF FILE MARKER TO FIX THE BUFFERING WITH ZEROS TO MAKE COMPLETE BYTE.
			currentText.append("۞");

			//This will create a huffman tree.
			CodingTree huffTree = new CodingTree(currentText.toString());
			
			//This will get the letter to binary codes for the huffman tree.
			MyHashTable<String, String> myCodes = huffTree.getMyCodes();
			
			//This will print out the codes to a file.
			writeOutCodes(myCodes);
			
			//This should get the whole code as one string.
			StringBuilder huffmanCode = getCode(currentText.toString(), myCodes);

			//This will make the String size where it can evenly be broken up.
			while (huffmanCode.length() % 8 != 0) {
				huffmanCode.append("0");
			}
			//Hold the byte objects.
			List<Byte> bytes = new ArrayList<>();
			File compressOut = new File("compress.txt");
			OutputStream compressed = new FileOutputStream(compressOut);
			
			int i = 0;
			//Will add the bytes.
			while (i < huffmanCode.length()) {
				int temp = Integer.parseInt(huffmanCode.substring(i, i + 8), 2);
				byte currentByte = (byte) temp;
				//This will make it positive again.
				bytes.add(currentByte);
				i += 8;
			}

			//This will write all bytes at once.
			int count = 0;
			byte[] temp = new byte[bytes.size()];
			for (byte b: bytes) {
				temp[count++] = b;
			}
			compressed.write(temp);
			
			//This will Count the times and size of the file.
			long totalTime = System.nanoTime() - startTime;
			double seconds =TimeUnit.MILLISECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
			System.out.println("Total Time To Compress in Milliseconds: " + seconds);
			
			double firstFileSize =  inFile.length();
			double kilobytes1 = (firstFileSize / 1024);
			System.out.println("First File size in kilobytes: " + kilobytes1);
			
			double secondFileSize =  compressOut.length();
			double kilobytes2 = (secondFileSize / 1024);
			System.out.println("Second File size in kilobytes: " + kilobytes2);
			
			double compressionPercent = kilobytes2 / kilobytes1;
			System.out.println("The compression ratio (as a percentage): " + compressionPercent * 100);
			
			/* 
			 *  COMMENT OUT TO RUN PROGAM WITH OUT THE DECODING PROCESS WILL SPEED UP VERY MUCH!
			 *  EXTRA CREDIT IMPLEMENTATION! 
			 */
			
//			This will store the decoded hashmap codes.
			long startDecodeTime = System.nanoTime();
//			HashMap<String, String> decodedMap = new HashMap<>();
//			decodedMap = getDecodedMap();
			StringBuilder decodeCodes  = getCodesFromFile(compressOut);
//			System.out.println(decodedMap);
			String decoded = huffTree.decode(decodeCodes.toString(), myCodes);
			File decodedFile = new File("DecodedFile.txt");
//			//This will write the decoded file out.
			PrintWriter write = new PrintWriter(decodedFile);
			write.print(decoded);
			write.flush();
			write.close();
//			//This will get decode time
			long totalDecodeTime = System.nanoTime() - startDecodeTime;
			double secondsToDecode =TimeUnit.MILLISECONDS.convert(totalDecodeTime, TimeUnit.NANOSECONDS);
			System.out.println("\nEXTRA CREDIT - Total Decode Time in Milliseconds: " + secondsToDecode);
			/* END OF EXTRA CREDIT IMPLEMENTATION! */
			
			/* THIS WILL ALLOW FOR THE TESTING OF CODING TREE! */
			//testCodingTree();
			
			/* THIS WILL ALLOW FOR THE TESTING OF MYHASHTABLE! */
			//testMyHashTable();

			compressed.close();
			fileReader.close();
		}  finally {}
	}
	
	/**
	 * This method is used for testing the coding tree class to make sure that there is no
	 * flaws or errors when creating the huffman tree.
	 */
	public static void testCodingTree() {
		System.out.println("\nTESTING CODING TREE!");
		System.out.println("This will test a the Coding Tree based off small words...");
		CodingTree test1 = new CodingTree("hello\n");
		MyHashTable <String, String> temp1 = test1.getMyCodes();
		System.out.println(temp1.toString());
		//This will test another
		System.out.println("\nThis will test a with a bigger huffman tree due to the bigger string...");
		System.out.println("The smallest binary numbers should we associated with 'a', 'z' and ' '....");
		CodingTree test2 = new CodingTree("aaaaaaaaaaaaa \nToday is a great day         zzzzzzzzzzzzz\n");
		temp1 =  test2.getMyCodes();
		System.out.println(temp1);
		
		System.out.println("\nThis will test with a variation of different letters..");
		CodingTree test3 = new CodingTree("Hey how are you doing today \n I am very good thankyou for asking what wh hey hey...+-e!@#$%^&\n");
		temp1 =  test3.getMyCodes();
		System.out.println(temp1);
		
		System.out.println("END OF TESTING!");
		
	}
	public static void testMyHashTable() {
		System.out.println("\nTESTING CODING TREE!");
		System.out.println("Testing that my hash table is Generic: ");
		System.out.println("Creating a hash table with types String, String......");
		MyHashTable<String, String> test1 = new MyHashTable<>(100);
		System.out.println("Should print: Hello, One");
		
		System.out.println("Creating a hash table with types Integer, String......");
		MyHashTable<Integer, String> test2 = new MyHashTable<>(100);
		System.out.println("Should print: 100, One");
		test2.put(100, "One");
		System.out.println("OUTPUT: " + test2.toString());
		
		System.out.println("Testing Put function:");
		System.out.println("Try adding to test hash map... will add ! * bananas apple");
		test1.put("!", "*");
		test1.put("bananas", "apple");
		System.out.println("OUTPUT: " + test1.toString());
		
		System.out.println("Testing Get Function: ");
		System.out.println("Will try to get bananas... should print out apple:");
		System.out.println("OUTPUT: " + test1.get("bananas"));
		System.out.println("Try to get something that doesnt exist.. should print null");
		System.out.println("OUTPUT: " + test1.get("hey"));
		
		System.out.println("Testing the contains method: ");
		System.out.println("Will see if map conatains bananas: Should print true....");
		System.out.println("OUTPUT: " + test1.containsKey("bananas"));
		System.out.println("Testing if does not contain key: for example bana should return false...");
		System.out.println("OUTPUT: " + test1.containsKey("bana"));
		
		System.out.println("Testing Stats: ");
		System.out.println("Should print entries are 2, number of buckets should be 100, "
				+ "fill percentage should be 2, max linear prob should be 1");
		System.out.print("OUTPUT: ");
		test1.stats();
		
		System.out.println("Testing to String: ");
		System.out.println("OUTPUT: " + test1.toString());
	}
	
	/**
	 * This method will be called to write out the codes generated from the Huffman code and will write
	 * the binary codes associated with the character attached to it.
	 * 
	 * @param myCodes The Hash Map that has the binary codes connected to the specific character.
	 * @throws FileNotFoundException This will be thrown if file you are writing out to cant be written to.
	 */
	private static void writeOutCodes(MyHashTable<String, String> myCodes) throws FileNotFoundException {
		File codesOut = new File("codes.txt");
		PrintWriter wrteCodes = new PrintWriter(codesOut);
//		System.out.println(myCodes.toString());
		wrteCodes.print(myCodes.toString());
		wrteCodes.flush();
		wrteCodes.close();
	}
	
	/**
	 * This will convert the string to the corresponding binary number into a string.
	 * 
	 * @param currentText The whole file as a string.
	 * @param myCodes The map of characters and there corresponding binary numbers.
	 * @return The completed file translated from characters into binary numbers.
	 */
	private static StringBuilder getCode(String currentText, MyHashTable<String, String> myCodes) {
		StringBuilder huffmanCode = new StringBuilder();
		int length = currentText.length();
		int start = 0;
		boolean flag = true;
		boolean invaildChar = false;
//		3226617
		for(int i = 0; i < length; i++) {
			char currentChar = currentText.charAt(i);
			flag = containsLetter(currentChar);
			if (invaildChar == true && flag == true) {
				invaildChar  = false;
				flag = false;
			}
			//this will check if 
			if (!flag) {
				if (containsLetter(currentText.charAt(i)) == false) {
					invaildChar = true;
				}
				if (i == 0) {
					i++;
					huffmanCode.append(myCodes.get(currentText.substring(start, i)));
					i--;
//					start = i;
				} else {
				huffmanCode.append(myCodes.get(currentText.substring(start, i)));
				start = i;
				}

			} 
		}
		
		huffmanCode.append(myCodes.get("۞"));
		return huffmanCode;
	}
	
	private static boolean containsLetter(char theLetter) {
		boolean flag = false; 
		if ((theLetter >= 'a' && theLetter <= 'z')  || (theLetter >= 'A' && theLetter <= 'Z') 
			|| (theLetter >= '0' && theLetter <= '9') || theLetter == '\'' || theLetter == '-') {
			flag = true;
		}
		return flag;
		
	}
	/**
	 * This will take in the compressed file and will convert it back into its binary representation 
	 * that will be stored in as a string.
	 * 
	 * @param compressedFile the compressed file to read in binary numbers.
	 * @return the compressed file as a string representation of binary numbers.
	 * @throws IOException if file cannot be opened.
	 */
	private static StringBuilder getCodesFromFile(File compressedFile) throws IOException {
		File compress = new File(compressedFile.getAbsolutePath());
		FileInputStream fileInputStream = new FileInputStream(compress);
		
		byte[] bytes = new byte[(int)compress.length()];
		fileInputStream.read(bytes, 0, bytes.length);
		StringBuilder binaryNum = new StringBuilder();
		int length = bytes.length;
		for (int i = 0; i < length; i++) {
			binaryNum.append(Integer.toBinaryString((bytes[i] & 0xFF) + 0x100).substring(1));
		}
//		System.out.println(binaryNum.toString());
		fileInputStream.close();
		return binaryNum;
	}
	
	/**
	 * This method will  go through the file of codes that map a character to a binary number. Will read in the file
	 * and read in the characters and binary numbers as a string. Using the string of values it will reassign the file
	 * into a Hash map for decoding.
	 * 
	 * @return the Hash map that will allow for decoding a string of binary numbers.
	 * @throws IOException if the file cannot be found.
	 */
	private static HashMap<Character, String> getDecodedMap() throws IOException {
		File decoderCodes = new File(new File("codes.txt").getAbsolutePath());
		BufferedReader readInFile = new BufferedReader(new FileReader(decoderCodes));
		HashMap<Character, String> decodedMap = new HashMap<>();
		
	    String temp;
		StringBuilder parseString = new StringBuilder();
		while ((temp = readInFile.readLine()) != null) {
				parseString.append(temp);
		 }
		 
		 StringBuilder binaryNum = new StringBuilder();
		 Character tempChar = null;
		 boolean flag = false;
		 int length = parseString.length();
		 //Index start at 1 to not get { length - 1 to not get }
		 for (int i = 1; i < length; i++) {
			 //This will check if first index.
			 if (i == 1) {
				 tempChar = parseString.charAt(1);
				 i += 2;
				 //This will get the char for each binary number.
			 } else if (flag) {
				 if (parseString.charAt(i) == '=') {
					 i--;
					 tempChar =  parseString.charAt(i);
					 flag = false;
					 i+=2;
				 } else {
				 tempChar = parseString.charAt(i);
				 flag = false;
				 i += 2;
				 }
			 }
			 Character c = parseString.charAt(i);
			 if (c.equals('1') || c.equals('0')) {
				 binaryNum.append(c);
			 } else {
//				 System.out.println("map: "+tempChar + ":" + binaryNum.toString());
				 decodedMap.put(tempChar, binaryNum.toString());
				 binaryNum.delete(0, binaryNum.length());
				 tempChar = null;
				 flag = true;
				 i++;
			 }
		 }
		 readInFile.close();
		return decodedMap;
	}
}
