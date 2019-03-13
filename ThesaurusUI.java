/******************************************************************************/
/*!
\file   CirclesGUI.java
\author Yohaan Mohan
\par    email: mohan011\@mymail.sim.edu.sg
\par    Student Number: 160291137
\par    Course: C02220
\par    Coursework Assignment #1
\date   07/12/2017
\brief  
    This file contains the implementation of the following methods for the 
    coursework.
      
    Methods include:
        ThesaurusUI
		start
		printMainMenu
		processMenuChoice
		lookup
		createList
		addWord
		editWord
		removeWord
		showAllWords
		quit
		getUserInput
		save
		invaildInput
		isVaildMenuChoice
		main
		
  Hours spent on this assignment: 
  10 hours
*/
/******************************************************************************/
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class ThesaurusUI 
{
	private ThesaurusEditor tEditor;
	private Scanner scanner;
	private boolean finished;
	private String tFilename;
	private List<ThesaurusWord> tWords;
	
	/**************************************************************************/
    /*!
      \brief
       The constructor for the ThesaurusUI.
	  
	  \param filename
	    The filename of the Thesaurus file to read from.
		
    */
    /**************************************************************************/
	public ThesaurusUI(String filename) throws IOException, ClassNotFoundException
	{
		this.tFilename = filename;
		tWords = ThesaurusFileManager.readFromFile(tFilename);
		tEditor = new ThesaurusEditor(tWords);
		scanner = new Scanner(System.in);
		finished = false;
	}
	
	/**************************************************************************/
    /*!
      \brief
       The starting point of the Thesaurus program.
	  
	  \return
	   Does not return anything
    */
    /**************************************************************************/
	private void start()
	{
		String input;
		while(!finished)
		{
			printMainMenu();
			boolean invalidInput = false;
			do {
				input = getUserInput();
				if (isVaildMenuChoice(input)) {
					processMenuChoice(input);
					invalidInput = false;
				} else {
					invaildInput(input);
					invalidInput = true;
				}
			} while (invalidInput);
		}
	}
	
	/**************************************************************************/
    /*!
      \brief
       Prints out to the command line, the main menu of the Thesaurus program.
	  
	  \return
	   Does not return anything.
		
    */
    /**************************************************************************/
	private void printMainMenu()
	{
		System.out.println();
		System.out.println("THESAURUS");
		System.out.println("Select one of the following options:");
		System.out.println("1) Look up a word");
		System.out.println("2) Add word");
		System.out.println("3) Edit word");
		System.out.println("4) Remove word");
		System.out.println("5) View all words");
		System.out.println("6) Exit");
	}
	
	/**************************************************************************/
    /*!
      \brief
       Takes in the choice from the user and decides whether it is a valid choice
	   for the menu.
	  
	  \param input
	   The choice of the user passed to the program from the terminal.
	  
	  \return
	   Does not return anything
		
    */
    /**************************************************************************/
	private void processMenuChoice(String input)
	{
		int choice = Integer.parseInt(input);
		
		switch(choice)
		{
			case 1 :
				lookUp();
				break;
			case 2 :
				addWord();
				break;
			case 3 :
				editWord();
				break;
			case 4 :
				removeWord();
				break;
			case 5 :
				showAllWords();
				break;
			case 6 :
				quit();
				break;
		}
	}
	
	/**************************************************************************/
    /*!
      \brief
       Asks the user for a word to look for in the Thesaurus and display it and
	   its fields to the user if found.
	   
	  \return
	   Does not return anything
		
    */
    /**************************************************************************/
	private void lookUp()
	{
		System.out.println("Enter the word to search: ");
		String input = getUserInput();
		ThesaurusWord inputWord = tEditor.find(input);
		if(inputWord != null)
		{
			System.out.println(inputWord);
		}
		else
		{
			System.out.println(input + " was not found in the Thesaurus");
		}
	}
	
	/**************************************************************************/
    /*!
      \brief
       Creates an ArrayList of what the user types into the terminal.
	  
	  \return
	   An ArrayList of Strings for either antonyms or synonyms.
		
    */
    /**************************************************************************/
	private List<String> createList()
	{
		List<String> result = new ArrayList<String>();
		boolean finished = false;
		
		while(!finished)
		{
			String newWord = getUserInput();
			if(newWord.equals(""))
			{
				finished = true;
			}
			else
			{
				result.add(newWord);
			}
		}
		
		return result;
	}
	
	/**************************************************************************/
    /*!
      \brief
       Adds a word into the Thesaurus only if the word is not currently in the
	   Thesaurus.
	  
	  \return
	   Does not return anything.
		
    */
    /**************************************************************************/
	private void addWord()
	{
		System.out.println("Enter the word to search: ");
		String input = getUserInput();
		ThesaurusWord inputWord = tEditor.find(input);
		if(inputWord == null)
		{	
			System.out.println("What are the synonyms?");
			List<String> synonyms = createList();
			
			System.out.println("What are the antonyms");
			List<String> antonyms = createList();
			
			ThesaurusWord newWord = new ThesaurusWord(input,synonyms,antonyms);
			tEditor.add(newWord);
			
			System.out.println(input + " was added to the Thesaurus");
		}
		else
		{
			System.out.println("This word is already in the Thesaurus");
		}
	}
	
	/**************************************************************************/
    /*!
      \brief
       Allows the user edit any of the words that are currently held in the 
	   Thesaurus.
	  
	  \return
	   Does not return anything
		
    */
    /**************************************************************************/
	private void editWord()
	{
		boolean synonymEdit = false, antonymEdit = false;
		
		System.out.println("Enter word: ");
		String input = getUserInput();
		ThesaurusWord inputWord = tEditor.find(input);
		if(inputWord != null)
		{
			System.out.println("Do you want to edit the synonyms (y/n)");
			String answer = getUserInput();
			if(answer.equals("y"))
			{
				synonymEdit = true;
				System.out.println("What are the synonyms?");
				List<String> synonyms = createList();
				tEditor.setSynonyms(input, synonyms);
				
			}
			System.out.println("Do you want to edit the antonyms (y/n)");
			answer = getUserInput();
			if(answer.equals("y"))
			{
				antonymEdit = true;
				System.out.println("What are the antonyms");
				List<String> antonyms = createList();
				tEditor.setAntonyms(input, antonyms);
			}
			
		}
		else
		{
			System.out.println("This word is not in the Thesaurus.");
		}
		
		if(input != null)
		{
			System.out.println("Word: " + inputWord.getWord());
			if(synonymEdit)
			{
				System.out.println("New Synonyms: " + inputWord.getSynonyms());
			}
			else
			{
				System.out.println("Synonyms: " + inputWord.getSynonyms());
			}
			if(antonymEdit)
			{
				System.out.println("New Antonyms: " + inputWord.getAntonyms());
			}
			else
			{
				System.out.println("Antonyms: " + inputWord.getAntonyms());
			}
		}
		
	}
	
	/**************************************************************************/
    /*!
      \brief
       Allows the user to remove any of the words that are currently in the 
	   Thesaurus.
	  
	  \return
	   Does not return anything.
		
    */
    /**************************************************************************/
	private void removeWord()
	{
		System.out.println("Enter word to remove : ");
		String input = getUserInput();
		ThesaurusWord inputWord = tEditor.find("input");
		if(inputWord != null)
		{
			tEditor.removeWord(input);
			System.out.println(input + " has been removed from the Thesaurus.");
		}
		else
		{
			System.out.println("This word is not in the Thesaurus.");
		}
	}
	
	/**************************************************************************/
    /*!
      \brief
       Prints out all of the words currently held in the Thesaurus.
	  
	  \return
	   Does not return anything
		
    */
    /**************************************************************************/
	private void showAllWords()
	{
		for(int x = 0; x < tWords.size(); x++)
		{
			System.out.println(tWords.get(x).getWord());
		}
	}
	
	/**************************************************************************/
    /*!
      \brief
       Allows the user to exit from the Thesaurus program and also save the 
	   changes to the Thesaurus file.
	  
	  \return
	   Does not return anything.
		
    */
    /**************************************************************************/
	private void quit()
	{
		System.out.println("Do you want to save before exiting(y/n)? ");
		String answer = getUserInput();
		if(answer.equals("y") || answer.equals("yes"))
		{
			save();
			finished = true;
			scanner.close();
			System.out.println(tFilename + " has been saved.");
		}
		else
		{
			finished = true;
			scanner.close();
			System.out.println(tFilename + " has not been saved");
		}
		
		System.out.println("Thank for using the Thesaurus Application");
	}
	
	/**************************************************************************/
    /*!
      \brief
       Gets the user input from the terminal. Provided by UOL.
	  
	  \return
	   The string which contains the user's input.
		
    */
    /**************************************************************************/
	private String getUserInput()
	{
		return scanner.nextLine();
	}
	
	/**************************************************************************/
    /*!
      \brief
       Saves the ArrayList to the Thesaurus file. Provided by UOL.
	  
	  \return
	   Does not return anything
		
    */
    /**************************************************************************/
	private void save()
	{
		ThesaurusFileManager.writeToFile(tFilename, tWords);
	}
	
	/**************************************************************************/
    /*!
      \brief
       Tells the program what to do when the user's input is invalid. Provided by UOL.
	  
	  \return
	   Does not return anything.
		
    */
    /**************************************************************************/
	private void invaildInput(String input)
	{
		System.out.println("Input: " + " was not vaild");
	}
	
	/**************************************************************************/
    /*!
      \brief
       Checks to see if the input is a valid choice for the menu. Provided by UOL.
	  
	  \param s
	   A string which contains the user's input
	   
	  \return
	   A boolean which tells the program if the user made a valid choice
		
    */
    /**************************************************************************/
	private boolean isVaildMenuChoice(String input)
	{
		try
		{
			int i = Integer.parseInt(input);
			if( i > 0 && i < 7)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	/**************************************************************************/
    /*!
      \brief
       The entrance into the Thesaurus program and provided by UOL.
	  
	  \param args
	    The arguments passed into the program passed in through the command line.
	  
	  \return
	    Does not return anything
		
    */
    /**************************************************************************/
	public static void main(String[] args) throws Exception {
    	ThesaurusUI ui = new ThesaurusUI("thesaurus.txt");
    	ui.start();
	}
}