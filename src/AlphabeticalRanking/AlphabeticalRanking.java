/**
 *  Programmer: Louis Wong
 *  Filename: AlphabeticalRanking.java
 *  Purpose: Returns the alphabetical ranking of a word through the public
 *  method, getRank(String input)
 */
package AlphabeticalRanking;

import java.util.HashMap;

public class AlphabeticalRanking {

	HashMap<Character, Integer> letterCounts;	// Stores the letters of the
												// word and the number of
												// repetition

	public AlphabeticalRanking(){}	// Empty constructor

	/**
	 *  getRank(String input)
	 *  Returns the rank of the parameter input, by first initializing the
	 *  HashMap then calling calculateRank with the character array
	 *  representation of the input string.
	 *
	 * @param input: what the user inputs.
	 * @return the rank of the input string.
	 */
	public long getRank(String input)
	{
		return calculateRank(initializeLetterCounts(input));
	}

	/**
	 *  initializeLetterCounts(String input)
	 *  Iterates through the char array of the input string and puts it in the
	 *  HashMap with value 1. If it already exists in the HashMap, increment
	 *  its value.
	 * @param input: what the user inputs.
	 * @return the char array representation of the input string.
	 */
	private char[] initializeLetterCounts(String input)
	{
		letterCounts = new HashMap<Character, Integer>();
		char[] inputArray = input.toCharArray();
		for(char a : inputArray)
		{
			if(letterCounts.containsKey(a))
			{
				letterCounts.put(a, letterCounts.get(a)+1);
			}
			else
			{
				letterCounts.put(a, 1);
			}
		}

		return inputArray;
	}

	/**
	 *  calculateRank(char[] inputArray)
	 *  Iterates through the input char array and checks the HashMap to see if
	 *  there were supposed to be another letter that should have come before
	 *  it. If there was then get the number of permutations of the remaining
	 *  characters in addition to the current character and with the absence
	 *  of the character that should have been there. This number is added
	 *  to the rank.
	 *  Now we check if the count of the letter in the HashMap is <= 1. If it
	 *  is remove it from the HashMap. Otherwise decrement the count of the
	 *  current char in the HashMap and continue on.
	 *
	 * @param inputArray: this is the character array representation of the
	 * input.
	 * @return the rank.
	 */
	private long calculateRank(char[] inputArray)
	{
		long rank = 1;
		if(letterCounts == null || letterCounts.isEmpty())
		{
			return 0;
		}

		for(char a : inputArray)
		{
			for(char b : letterCounts.keySet())
			{
				if(b < a)
				{
					rank += getSumOfValuesFactorial()/getProductOfDuplicateFactorial(b);
				}
			}
			if(letterCounts.get(a) <= 1)
			{
				letterCounts.remove(a);
			}
			else
			{
				letterCounts.put(a, letterCounts.get(a)-1);
			}
		}

		return rank;
	}

	/**
	 * factorial(int n)
	 * Returns n!
	 * @param n: the number to return n!
	 * @return n!
	 */
	private long factorial(int n)
	{
		long ret = 1;
        for (int i = 1; i <= n; ++i) ret *= i;
        return ret;
	}

	/**
	 * getSumOfValuesFactorial()
	 * Gets the total amount of combinations of the remaining characters.
	 * Pretty much n! where n is the amount of combinations of the
	 * remaining characters.
	 * @return
	 */
	private long getSumOfValuesFactorial()
	{
		int total = 0;
		for(Integer a : letterCounts.values())
		{
			total += a;
		}
		total -= 1;

		return factorial(total);
	}

	/**
	 * getProductOfDuplicateFactorial(Character toBeReplaced)
	 * Decrements the count of the character that should have been there in
	 * the HashMap then gets the sum of the factorial of each value in the
	 * HashMap.
	 * @param toBeReplaced: This is the character that should have been in
	 * place of where the current position of the input string.
	 * @return the sum of the factorial of each value in the
	 * HashMap.
	 */
	private long getProductOfDuplicateFactorial(Character toBeReplaced)
	{
		long total = 1;	//can't divide by 0
		letterCounts.put(toBeReplaced, letterCounts.get(toBeReplaced)-1);

		for(Integer a : letterCounts.values())
		{
			if(a > 1)
			{
				total *= factorial(a);
			}
		}
		letterCounts.put(toBeReplaced, letterCounts.get(toBeReplaced)+1);
		return total;
	}
}
