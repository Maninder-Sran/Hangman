/*
 * Name: Maninder Sran
 * Date: June 08, 2016
 * Desc: Variation of Hangman that includes a scoring system 
 *       and different difficulty settings
 */
package hangman;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Hangman {
    static String name;
    static ArrayList<String> countriesList  = new ArrayList();
    static ArrayList<String> houseHoldList  = new ArrayList();
    static ArrayList<String> sportsList     = new ArrayList();
    static ArrayList<String> languagesList  = new ArrayList(); 
    static ArrayList<String> schoolList     = new ArrayList(); 
    static ArrayList<String> automobileList = new ArrayList();
    static String[]          names          = new String[15];
    static String[]          scores         = new String[15];
    
    public static void main(String[] args) {
	
	Scanner input = new Scanner(System.in);
	
	System.out.println ("\t\t\t\tWelcome to Hangman!!!!\n\t\t\t\t----------------------\nEnter your name: ");
	name = input.nextLine ();
      
	init();
	gameMode();
    }
    public static void init()
    {
	WordBank bank = new WordBank();
	
	bank.readData("C:\\Users\\Maninder\\Documents\\NetBeansProjects\\Hangman\\src\\hangman\\Countries.txt",countriesList);
	bank.readData("C:\\Users\\Maninder\\Documents\\NetBeansProjects\\Hangman\\src\\hangman\\HouseHoldLists.txt",houseHoldList);
	bank.readData("C:\\Users\\Maninder\\Documents\\NetBeansProjects\\Hangman\\src\\hangman\\SportsList.txt",sportsList);
	bank.readData("C:\\Users\\Maninder\\Documents\\NetBeansProjects\\Hangman\\src\\hangman\\Languages.txt",languagesList);
	bank.readData("C:\\Users\\Maninder\\Documents\\NetBeansProjects\\Hangman\\src\\hangman\\SchoolList.txt",schoolList);
	bank.readData("C:\\Users\\Maninder\\Documents\\NetBeansProjects\\Hangman\\src\\hangman\\AutomobileList.txt",automobileList);
        /*
        bank.readData("E:/Hangman/src/hangman/Countries.txt",countriesList);
	bank.readData("E:/Hangman/src/hangman/HouseHoldLists.txt",houseHoldList);
	bank.readData("E:/Hangman/src/hangman/SportsList.txt",sportsList);
	bank.readData("E:/Hangman/src/hangman/Languages.txt",languagesList);
	bank.readData("E:/Hangman/src/hangman/SchoolList.txt",schoolList);
	bank.readData("E:/Hangman/src/hangman/AutomobileList.txt",automobileList);*/
    }
    public static void gameMode()
    {
	Scanner input = new Scanner(System.in);
	String selection;
	
	System.out.println("\nChoose the category of words you would like to guess: ");
	System.out.println("1.Countries 2.House Hold Items 3.Sports 4.Languages 5.School 6.Automobile");
	System.out.println("\nEnter the name of the category from which you would like to guess: ");
	selection = input.nextLine();
	
	if(selection.equalsIgnoreCase("Countries")||selection.equals("1"))
        {
		start(countriesList);
        }
        else if(selection.equalsIgnoreCase("House Hold Items")||selection.equals("2"))
        {
                start(houseHoldList);
        }
        else if(selection.equalsIgnoreCase("Sports")||selection.equals("3"))
        {	
		start(sportsList);
        }
        else if(selection.equalsIgnoreCase("Languages")||selection.equals("4"))
        {	 
                start(languagesList);
        }
        else if(selection.equalsIgnoreCase("School")||selection.equals("5"))
        {
		start(schoolList);
        }
        else if(selection.equalsIgnoreCase("Automobile")||selection.equals("6"))
        {
        	start(automobileList);
        }
        else
        {
	    	System.out.println("Thank you for playing.");
        }
    }
    
 public static void start (ArrayList data)
 {
    int counter = 0,numCorrect;
    boolean correct = false;
    
    Scanner input = new Scanner(System.in);
    Tracker init = new Tracker();
    String guess,guessWord,choice,wordList[] = new String [data.size()];
    
    init.setLives(4,'n');
    
    for(int i=0;i<wordList.length;i++)
    {
	wordList[i] = String.valueOf(data.get(i));
    }
      
    guessWord = wordList [(int) (Math.random () * (wordList.length - 1)) + 1];
    boolean[] wordChars = new boolean [guessWord.length ()];
	
    System.out.println ("The word is " + guessWord.length () + " characters long.");
	
    do
    {
	++counter;
	numCorrect = 0;
	    
	System.out.print ("\nKey in one character or your guess word: ");
	guess = input.nextLine ();
	   
	System.out.print ("Trial " + counter + ": ");
	printWord (guessWord, guess, wordChars,data,counter);
	System.out.println();   
	for (int i = 0 ; i < wordChars.length ; i++)
	{
	    if (wordChars [i])
	    {
		numCorrect++;
	    }
	    if (numCorrect == guessWord.length ())
	    {
		correct = true;
		    
		System.out.println ("\nCongratulations " + name + "!!!");
		System.out.println ("You got it in " + counter + " trials.");
		System.out.println ("\nWould you like to play again?(Y/N)");
		choice = input.next();
		    
		if (choice.equalsIgnoreCase("Y"))
		{
                     System.out.println("Would you like to choose a new category.(Y/N)");
                     choice = input.next();
        
                     if(choice.equalsIgnoreCase("Y"))
                     { 
                        gameMode();
                     }
                     else if(choice.equalsIgnoreCase("N"))
                     {
                        start(data);
                     }
                }
		else if (choice.equalsIgnoreCase("N"))
		{
                    WordBank leaderboard = new WordBank();
                    leaderboard.setLeaders(name,init.getScore(),names,scores);
		    System.exit (-1);
		}
		else
		{
		    System.exit (-1);
		}
	    }

	}
    }
    while (!correct);
}
 public static void gameOver(String word,ArrayList data)
 {
    WordBank user = new WordBank();
    Scanner input = new Scanner(System.in); 
    int choice = 0;
    
    System.out.println ("\nUnfortunately " + name +" you were unable to guess the word |"+word+"| in 6 attempts");
    System.out.println ("\nWhat would you like to do next?");
    System.out.println("Press: 1 for Play Again---2 for Show Categories---3 for Show Leaderboard---4 for Exit");
    try{
    choice = input.nextInt();
    }
    catch(InputMismatchException e)
    {
        System.out.println("Please enter the number for the corresponding option.");
        gameOver(word,data);
    }		    
    if (choice==1)
    {
        start(data);
    }
    else if(choice==2)
    {
        gameMode();
    }
    else if(choice==3)
    {
        user.showLeaderBoard("C:\\Users\\Maninder\\Documents\\NetBeansProjects\\Hangman\\src\\hangman\\Leaderboard.txt",names,scores);
    }
    else if (choice==4)
    {
	System.exit (-1);
    }
    else
    {
	System.exit (-1);
    }
 }
  public static void printWord (String word, String input, boolean[] wordChars,ArrayList data,int counter)
    {
	boolean lifeDecrease = true;
	Tracker score = new Tracker();
      
	for (int i = 0 ; i < wordChars.length ; i++)
	{
	    if (input.equalsIgnoreCase(word))
	    {
		for (int j = 0 ; j < wordChars.length ; j++)
		{
		    wordChars [j] = true;
		    score.setScore(word.length()-counter);
		    
		}
		lifeDecrease = false;
	    }
	    else if (input.equalsIgnoreCase (String.valueOf (word.charAt (i))))
	    {
		wordChars [i] = true;
		lifeDecrease = false;
		score.setScore(word.length()-counter);
	    }
	}
	if(lifeDecrease)
	{
	    Tracker screen = new Tracker();
	    screen.setLives(1,'+');
	    if(screen.getLives()>=10)
	    {
		gameOver(word,data);
	    }
	}
	for (int i = 0 ; i < wordChars.length ; i++)
	{
	    if (wordChars [i])
	    {
		System.out.print (word.charAt (i));
	    }
	    else
	    {
		System.out.print ("_ ");
	    }
	}
    }
  
}
