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
        DictionaryUI
		main
		start
		printMainMenu
		processMenuChoice
		showAll
		lookup
		addWord
		editWord
		removeWord
		quit
		save
		invalidInput
		getUserInput
		isValidMenuChoice
		
  Hours spent on this assignment: 
  10 hours
*/
/******************************************************************************/
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DictionaryUI {
    private DictionaryEditor editor;
	private Scanner scanner;
	private boolean finished;
    private String filename;
    private List<DictionaryWord> words;
    
	/**************************************************************************/
    /*!
      \brief
       The constructor for the DictionaryUI and provided by UOL.
	  
	  \param filename
	    The filename of the dictionary file to read from.
		
    */
    /**************************************************************************/
    public DictionaryUI(String filename) throws IOException, ClassNotFoundException {
        this.filename = filename;
        words = DictionaryFileManager.readFromFile(filename);
        editor = new DictionaryEditor(words);
		scanner = new Scanner(System.in);
        finished = false;
    }
	
	/**************************************************************************/
    /*!
      \brief
       The entrance into the dictionary program and provided by UOL.
	  
	  \param args
	    The arguments passed into the program passed in through the command line.
	  
	  \return
	    Does not return anything
		
    */
    /**************************************************************************/
    public static void main(String[] args) throws Exception {
    	DictionaryUI ui = new DictionaryUI("dictionary.txt");
    	ui.start();
	}
	
	/**************************************************************************/
    /*!
      \brief
       The starting point of the dictionary program.
	  
	  \return
	   Does not return anything
    */
    /**************************************************************************/
    private void start() {
    	String input;
		while (!finished) {
			printMainMenu();
			boolean invalidInput = false;
			do {
				input = getUserInput();
				if (isValidMenuChoice(input)) {
					processMenuChoice(input);
					invalidInput = false;
				} else {
					invalidInput(input);
					invalidInput = true;
				}
			} while (invalidInput);
		}
    }
	
	/**************************************************************************/
    /*!
      \brief
       Prints out to the command line, the main menu of the dictionary program.
	  
	  \return
	   Does not return anything.
		
    */
    /**************************************************************************/
    private void printMainMenu() {
		System.out.println();
		System.out.println("DICTIONARY");
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
	private void processMenuChoice(String input) {
		int i = Integer.parseInt(input);

		if (i == 1) {
		    lookup();
		}

		if (i == 2) {
		    addWord();
		}

		if (i == 3) {
		    editWord();
		}

		if (i == 4) {
			removeWord();
		}

		if (i == 5) {
			showAll();
		}

		if (i == 6) {
            quit();
		}
	}
	
	/**************************************************************************/
    /*!
      \brief
       Prints out all of the words currently held in the dictionary.
	  
	  \return
	   Does not return anything
		
    */
    /**************************************************************************/
	//print every word in the dictionary on its own line, showing the user the word field only
	private void showAll() {
    	for (int i = 0; i < words.size(); i++) {
    		System.out.println(words.get(i).getWord());
		}
	}
	
	/**************************************************************************/
    /*!
      \brief
       Asks the user for a word to look for in the dictionary and display it and
	   its fields to the user if found. Provided by UOL.
	   
	  \return
	   Does not return anything
		
    */
    /**************************************************************************/
	private void lookup() {
    	System.out.print("Enter word: ");
    	String input = getUserInput();
    	DictionaryWord word = editor.find(input);
    	if (word != null) {
			System.out.println(word);
		} else {
    		System.out.println("Not found.");
		}
	}
	
	/**************************************************************************/
    /*!
      \brief
       Adds a word into the dictionary only if the word is not currently in the
	   dictionary.
	  
	  \return
	   Does not return anything.
		
    */
    /**************************************************************************/
	//Ask the user for a word. Check the list to see if the word is already in the list. If not ask the user for a definition and a usage example.
	//If the word is in the list tell the user their word is already in the dictionary and end the method.
	private void addWord() 
	{
		System.out.println("Enter word: ");
		String input = getUserInput();
		DictionaryWord inputWord = editor.find(input);
		if(inputWord == null)
		{	
			System.out.println("What is the Definition?");
			String definition = getUserInput();
			
			System.out.println("What is an Usage Example?");
			String usageExample = getUserInput();
			
			DictionaryWord newWord = new DictionaryWord(input, definition, usageExample);
			editor.add(newWord);
			
			System.out.println(input + " was added to the dictionary");
		}
		else
		{
			System.out.println("This word is already in the Dictionary.");
		}
		
	}
	
	/**************************************************************************/
    /*!
      \brief
       Allows the user edit any of the words currently held in the dictionary.
	  
	  \return
	   Does not return anything
		
    */
    /**************************************************************************/
	private void editWord() 
	{
		boolean defineEdit = false, usageEdit = false;
		System.out.println("Enter word: ");
		String input = getUserInput();
		DictionaryWord inputWord = editor.find(input);
		if(inputWord != null)
		{
			System.out.println("Do you want to edit the definition (y/n)");
			String answer = getUserInput();
			if(answer.equals("y") || answer.equals("yes"))
			{
				System.out.println("What is the new definition?");
				String newDefine = getUserInput();
				editor.setDefinition(input, newDefine);
				defineEdit = true;
				
			}
			
			System.out.println("Do you want to edit the usage example (y/n)");
			answer = getUserInput();
			if(answer.equals("y") || answer.equals("yes"))
			{
				System.out.println("What is the new usage example?");
				String newExample = getUserInput();
				editor.setDefinition(input, newExample);
				usageEdit = true;
			}
			
		}
		else
		{
			System.out.println("This word is not in the Dictionary.");
		}
		
		if(inputWord != null)
		{
			System.out.println("Word: " + inputWord.getWord());
			if(defineEdit)
			{
				System.out.println("New Definition: " + inputWord.getDefinition());
			}
			else
			{
				System.out.println("Definition: " + inputWord.getDefinition());
			}
			if(usageEdit)
			{
				System.out.println("New Usage Example: " + inputWord.getUsageExample());
			}
			else
			{
				System.out.println("Usage Example: " + inputWord.getUsageExample());
			}
		}
	}
	/**************************************************************************/
    /*!
      \brief
       Allows the user to remove any of the word currently in the dictionary.
	  
	  \return
	   Does not return anything.
		
    */
    /**************************************************************************/
	private void removeWord() 
	{
		System.out.println("Enter word: ");
		String input = getUserInput();
		DictionaryWord inputWord = editor.find("input");
		if(inputWord != null)
		{
			editor.removeWord(input);
			System.out.println(input + " has been removed from the dictionary.");
		}
		else
		{
			System.out.println("This word is not in the dictionary.");
		}
	}
	
	/**************************************************************************/
    /*!
      \brief
       Allows the user to exit from the dictionary program and also save the 
	   changes to the dictionary file.
	  
	  \return
	   Does not return anything.
		
    */
    /**************************************************************************/
	private void quit() {
		System.out.println("Do you want to save before exiting(y/n)? ");
		String answer = getUserInput();
		if(answer.equals("y") || answer.equals("yes"))
		{
			save();
			finished = true;
			scanner.close();
			System.out.println(filename + " has been saved.");
		}
		else
		{
			finished = true;
			scanner.close();
			System.out.println(filename + " has not been saved");
		}
		
		System.out.println("Thank for using the Dictionary Application");
    }
	
	/**************************************************************************/
    /*!
      \brief
       Saves the ArrayList to the dictionary file. Provided by UOL
	  
	  \return
	   Does not return anything
		
    */
    /**************************************************************************/
    private void save() {
        DictionaryFileManager.writeToFile(filename, words);
    }
	
	/**************************************************************************/
    /*!
      \brief
       Gets the user input from the terminal. Provided by UOL.
	  
	  \return
	   The string which contains the user's input.
		
    */
    /**************************************************************************/
	private String getUserInput() {
		return scanner.nextLine();
	}
	
	/**************************************************************************/
    /*!
      \brief
       Tells the program what to do when the user's input is invalid. Provided by UOL.
	  
	  \return
	   Does not return anything.
		
    */
    /**************************************************************************/
	private void invalidInput(String input) {
    	System.out.println("I don't know what to do with '" + input + "'.");
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
	private boolean isValidMenuChoice(String s) {
		try {
			int i = Integer.parseInt(s);
			if (i > 0 && i < 7) {
				return true;
			} else {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
