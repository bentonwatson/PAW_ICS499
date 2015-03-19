package core;



import java.util.ArrayList;
import java.util.Collections;

/**
 * This class provides several operations in the context of single word.
 * This class is expected to work against multiple languages so the implementation
 * should not rely on the assumption that the string contain characters.
 */

public class WordProcessor {

	//word represents the string we are processing in this class
	private String word;

	// logicalChars are derived from the word
	// word can also be derived from logicalChars
	// these two are dependent on each other
	// if one changes, the other changes
	private ArrayList<String> logicalChars = new ArrayList<>();

	/**
	 * Default constructor
	 */
	public WordProcessor()
	{

	}

	/**
	 * Overloaded constructor that takes the word
	 * @param a_word
	 */

	public WordProcessor(String a_word)
	{
		setWord(a_word);
	}

	/**
	 * Overloaded constructor that takes the logical characters as input
	 * @param some_logical_chars
	 */
	public WordProcessor(ArrayList<String> some_logical_chars)
	{
		setLogicalChars(some_logical_chars);
	}

	/**
	 * set method for the word
	 * @param a_word
	 */
	public void setWord(String a_word)
	{
		word = a_word;
		parseToLogicalChars();
	}

	/**
	 * set method for the logical characters
	 * @param some_logical_chars
	 */
	public void setLogicalChars(ArrayList<String> some_logical_chars)
	{
		String newWord = "";
		logicalChars = some_logical_chars;
		for (String string : logicalChars) {
			newWord += string;
		}
		word = newWord;
	}

	/**
	 * get method for the word
	 * @return
	 */
	public String getWord()
	{
		return word;
	}

	/**
	 * get method for the logical characters
	 * @return
	 */
	public ArrayList<String> getLogicalChars()
	{
		return logicalChars;
	}

	/**
	 * Returns the length of the word
	 * length = number of logical characters
	 * @return
	 */
	public int getLength()
	{
		return logicalChars.size();
	}

	/**
	 * Returns the number of code points in the word
	 * @return
	 */
	public int getCodePointLength()
	{
		return word.codePointCount(0, word.length());
	}

	/**
	 * This method breaks the input word into logical characters
	 * For Engligh,
	 * 	  convert the string to char array
	 * 	  and convert each char to a string
	 *    and populate logicalChars
	 */
	public void parseToLogicalChars()
	{
		logicalChars = new ArrayList<>();
		for (int i = 0; i < word.length(); i++) {
			char [] ch = Character.toChars(word.codePointAt(i));
			if(Character.charCount(word.codePointAt(i)) == 2){
				i++;
			}
			String newChar = new String(ch);
			logicalChars.add(newChar);
		}

	}

	/**
	 * If the word starts with the logical character, 
	 * this method returns true.
	 * @param start_char
	 * @return
	 */
	public boolean startsWith(String start_char)
	{
		return word.startsWith(start_char);
	}

	/**
	 * If the word ends with the logical character, 
	 * this method returns true.
	 * @param start_char
	 * @return
	 */
	public boolean endsWith(String end_string)
	{
		return word.endsWith(end_string);
	};

	/**
	 * This method checks whether the sub_string or logical character
	 * is contained within the word
	 * @param sub_string
	 * @return
	 */
	public boolean containsString(String sub_string)
	{
		ArrayList<String> inputChars = parseInputString(sub_string);
		return containsLogicalCharSequence(inputChars);
	}

	/**
	 * This method checks whether the sub_string or logical character
	 * is contained within the word
	 * @param sub_string
	 * @return
	 */
	public boolean containsChar(String sub_string)
	{
		for (String string : logicalChars) {
			if (string.equals(sub_string)){
				return true;
			}
		}
		return false;
	}

	/**
	 * This method checks whether the logical characters
	 * are contained within the string/word.
	 * is contained within the word
	 * @param sub_string
	 * @return
	 */
	public boolean containsLogicalChars(ArrayList<String> logical_chars)
	{
		for (String string : logical_chars) {
			if(containsChar(string)){
				continue;
			}
			return false;
		}
		return true;
	}


	/**
	 * This method checks whether *ALL* the logical characters
	 * are contained within the string/word.
	 * is contained within the word
	 * @param sub_string
	 * @return
	 */
	public boolean containsAllLogicalChars(ArrayList<String> logical_chars)
	{
		return containsLogicalChars(logical_chars);
	}

	/**
	 * This method checks whether *ALL* the logical characters
	 * are contained within the string/word.
	 * is contained within the word
	 * @param sub_string
	 * @return
	 */
	public boolean containsLogicalCharSequence(ArrayList<String> logical_chars)
	{	
		String tempWord = "";
		for (String string : logical_chars) {
			tempWord += string;
		}
		return word.contains(tempWord);
	};

	/**
	 * This method checks whether a word can be made out of the original word
	 * example:  original word = POST;   a_word = POT
	 * @param a_word
	 * @return
	 */
	public boolean canMakeWord(String a_word)
	{
		//parse the a_word into logical characters
		// and call containsLogicalChars on those logical characters		
		ArrayList<String> inputChars = parseInputString(a_word);
		return containsLogicalChars(inputChars);
	}

	/**
	 * This method checks whether all the words in the collection
	 * can be made out of the original word
	 * example:  original word = POST;   a_word = POT; STOP; TOP; SOP
	 * @param a_word
	 * @return
	 */
	public boolean canMakeAllWords(ArrayList<String> some_words)
	{
		// same as above method 
		// but works on the entire collection
		for (String string : some_words) {
			if(canMakeWord(string)){
				continue;
			}
			return false;
		}
		return true;
	}

	/**
	 * returns true if the word contains the space
	 * @return
	 */
	public boolean containsSpace()
	{
		for (String string : logicalChars) {
			if(string.matches("\\s+")){
				return true;
			}
		}
		return false;
	};

	/**
	 * returns true if the word contains the space
	 * @return
	 */
	public boolean isPalindrome()
	{
		// find the logical characters of the word: we already have those
		// reverse the array list of those logical characters
		// in a loop, keep comparing 1 to N; 1+1, N-2 and so on
		// 
		int i = 0;
		int j = logicalChars.size() - 1;
		while (i < j) {
			if(logicalChars.get(i++).equals(logicalChars.get(j--))){
				continue;
			}
			return false;
		}
		return true;
	}

	/**
	 * returns true if the word_2 is an anagram of the word
	 * @return
	 */
	public boolean isAnagram(String word_2)
	{
		// split word_2 into logical characters
		// call containsAllLogicalChars
		ArrayList<String> inputChars = parseInputString(word_2);
		return isAnagram(inputChars);
	}

	/**
	 * returns true if the logical_chars are contained with in the word
	 * @return
	 */

	public boolean isAnagram(ArrayList<String>  logical_chars)
	{
		//// call containsAllLogicalChars
		return (logical_chars.size() == logicalChars.size() && containsLogicalChars(logical_chars));
	}




	// String manipulation methods
	/**
	 * strips of leading and trailing spaces
	 * @return
	 */
	public String trim()
	{
		setWord(word.trim());
		return word;
	}

	/**
	 * strips of all spaces in the word
	 * @return
	 */
	public String stripSpaces()
	{			
			setWord(word.replaceAll("\\s+", ""));
		return word;
	}

	/**
	 * strips of all special characters and symbols from the word
	 * @return
	 */
	public String stripAllSymbols()
	{
		String newWord = "";
		for (int i = 0; i < logicalChars.size(); i++) {
			if(logicalChars.get(i).matches("[^\\p{L}\\p{N}]+")){
				logicalChars.remove(i--);
				continue;
			}
			newWord += logicalChars.get(i);
		}
		setWord(newWord);
		return word;
	};

	/**
	 * Reverse the word and returns a new word
	 * @return
	 */
	public String reverse()
	{
		// you already have logicalChars
		// reverse that array list
		// add the logical characters together
		// return that new string
		@SuppressWarnings("unchecked")
		ArrayList<String> reverseList = (ArrayList<String>) logicalChars.clone();
		Collections.reverse(reverseList);
		String reversedWord = "";
		for (String string : reverseList) {
			reversedWord += string;
		}
		return reversedWord;
	}

	/**
	 * Replaces a specific sub-string with a substitute_string
	 * if the sub-string is not found, it does nothing
	 * It does not disturb the original string
	 * It returns a new string
	 * @return
	 */
	public String replace(String sub_string, String substitute_string)
	{
		String newWord = word;	
		return newWord.replace(sub_string, substitute_string);
	}

	/**
	 * Add a logical character at the specified index
	 * It does not disturb the original string
	 * It returns a new string
	 * @return
	 */
	public String addCharacterAt(int index, String a_logical_char)
	{
		@SuppressWarnings("unchecked")
		ArrayList<String> tempList = (ArrayList<String>) logicalChars.clone();
		tempList.add(index, a_logical_char);
		String newWord = "";
		for (String string : tempList) {
			newWord += string;
		}
		return newWord;
	}

	/**
	 * Add a logical character at the end of the word
	 * It does not disturb the original string
	 * It returns a new string
	 * @return
	 */
	public String addCharacterAtEnd(String a_logical_char)
	{
		@SuppressWarnings("unchecked")
		ArrayList<String> tempList = (ArrayList<String>) logicalChars.clone();
		tempList.add(a_logical_char);
		String newWord = "";
		for (String string : tempList) {
			newWord += string;
		}
		return newWord;
	}

	/**
	 * Compares the given word with the original word
	 * If there is a match on any logical character, it returns true
	 * @return
	 */
	public boolean isIntersecting(String word_2)
	{
			if(getIntersectingRank(word_2) > 0){
				return true;
			}
		return false;
	}

	/**
	 * Compares the given word with the original word
	 * And returns the count of matches on the logical characters
	 * @return
	 */
	public int getIntersectingRank(String word_2)
	{
		int count = 0;
		ArrayList<String> inputWord = parseInputString(word_2);
		for (String string : inputWord) {
			if(containsChar(string)){
				count++;
			}
		}
		return count;
	}


	/**
	 * This method gets a logical character at the specified index
	 * @param index
	 * @return
	 */
	public String logicalCharAt(int index)
	{
		return logicalChars.get(index);
	}

	/**
	 * This method gets a unicode code point at the specified index
	 * @param index
	 * @return
	 */ 
	public int	codePointAt(int index)
	{
		return word.codePointAt(index);
	}

	// Returns the position at which the first logical character is appearing in the string

	/**
	 * This method returns the index at which the logical character is appearing
	 * It returns the first appearance of the logical character
	 * @param index
	 * @return
	 */ 
	public int indexOf(String logical_char)
	{
		for (int i = 0; i < logicalChars.size(); i++) {
			if(logicalChars.get(i).equals(logical_char)){
				return i;
			}
		}
		return -1;
	}

	/**
	 * This method compares two strings lexicographically.
	 * It is simplay a wrapper on java compareTo
	 * @param word_2
	 * @return
	 */
	public int compareTo(String word_2)
	{
		return word.compareTo(word_2);
	}


	/**
	 * This method compares two strings lexicographically, ignoring case differences.
	 * It is simplay a wrapper on java compareTo
	 * @param word_2
	 * @return
	 */
	public int	compareToIgnoreCase(String word_2)
	{
		return word.compareToIgnoreCase(word_2);
	}

	/**
	 * This method takes one collection and returns another randomized collection
	 * of string (or logical characters)
	 * @param some_strings
	 * @return
	 */
	public ArrayList<String> randomize(ArrayList<String> some_strings)
	{
		@SuppressWarnings("unchecked")
		ArrayList<String> tempList = (ArrayList<String>) logicalChars.clone();
		Collections.shuffle(tempList);
		return tempList;
	}

	/**
	 * This method splits the word into a 2-dimensional matrix
	 * based on the number of columns
	 * @param no_of_columns
	 * @return
	 */
	public String[][]  splitWord(int no_of_columns)
	{
		int numOfRows = Math.floorDiv(logicalChars.size(), no_of_columns)+1;
		String[][] wordArraySplit = new String[numOfRows][no_of_columns];
		int k = 0;
		for (int i = 0; i < wordArraySplit.length; i++) {
			for (int j = 0;j < wordArraySplit[0].length && k < logicalChars.size();) {
				wordArraySplit[i][j] = logicalChars.get(k);
				k = (i * wordArraySplit[0].length) + j++;
			}
		}
		return wordArraySplit;
	}

	/**
	 * Returns the string representation of WordProcessor
	 * Basially, prints the word and logicalChars
	*/
	@Override
	public String toString() {
		return word + ", " + logicalChars;
	}

	/**
	 * compares two strings; wrapper on the java method
	 */
	public boolean equals(String word_2)
	{
		return word.equals(word_2);
	}

	/**
	 * compares two strings after reversing the original word
	 */
	public boolean reverseEquals(String word_2)
	{
		String reversedWord = reverse();
		return reversedWord.equals(word_2);
	}
	
	public ArrayList<String> parseInputString(String a_word){
		String wordPlaceholder =  word;
		setWord(a_word);
		ArrayList<String> inputChars = logicalChars;
		setWord(wordPlaceholder);
		return inputChars;
	}
	/**
	 * This is the main method for testing all of the above methods
	 */
}
