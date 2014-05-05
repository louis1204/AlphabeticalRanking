package main;

import java.util.Scanner;

import AlphabeticalRanking.AlphabeticalRanking;

public class Main {
	public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        String input;
        String cont;
        AlphabeticalRanking ar = new AlphabeticalRanking();

        while(true)
        {
	        System.out.print("Enter a word followed by a return: ");
	        input = keyboard.next().toUpperCase().trim();

	        if(!input.matches("[a-zA-Z]*"))	// Check if input is valid
	        {
	        	System.out.println("Only input letters A-Z or a-z\n");
	        }
	        else
	        {
	        	System.out.println("Ranking = " + ar.getRank(input) + "\n");

	            do{
		        	System.out.print("Input another word? (y/n) ");
		        	cont = keyboard.next().toLowerCase().trim();
		        }while(!cont.matches("[y|n]"));
		        if(cont.matches("n"))
		        	break;
	        }
        }

        System.out.println("Goodbye");
        keyboard.close();
    }
}
