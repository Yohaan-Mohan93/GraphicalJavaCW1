/******************************************************************************/
/*!
\file   DictionaryEditor.java
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
        DictionaryEditor
		add
		delete
		setUsageExample
		setDefinition
		removeWord
		setWord
		setWord
    
    
  Hours spent on this assignment: 
  10 hours
*/
/******************************************************************************/
import java.util.List;

public class DictionaryEditor extends Dictionary {

    /**************************************************************************/
    /*!
      \brief
       The constructor for the DictionaryEditor class which extends the Dictionary
	   constructor.
      
      \param words
        An ArrayList of words taken from the dictionary.txt file
		
    */
    /**************************************************************************/
	public DictionaryEditor(List<DictionaryWord> words)
    {
    	super(words);
    }
    
	/**************************************************************************/
    /*!
      \brief
       The method which adds a word to the ArrayList of dictionary words. This
	   method was provided by UOL.
      
      \param word
        The word which is going to be added to the ArrayList
	  
	  \return
	    Not Applicable 
		
    */
    /**************************************************************************/
	public void add(DictionaryWord word) {
	    words.add(word);
	}
	
	/**************************************************************************/
    /*!
      \brief
       The method which deletes a word to the ArrayList of dictionary words. This
	   method was provided by UOL.
      
      \param word
        The word which is going to be removed to the ArrayList
	  
	  \return
	    Does not return anything 
		
    */
    /**************************************************************************/
	public void delete(DictionaryWord word) {
	    words.remove(word);
    }
	
	/**************************************************************************/
    /*!
      \brief
       The method which sets an usage example to  a particular word in the 
	   ArrayList of dictionary words. This method was provided by UOL.
      
      \param word
        The word whose usage example is going to be changed
	  
	  \param usageExample
	    The example which is going to be added to word
	  
	  \return
	    Does not return anything 
		
    */
    /**************************************************************************/
    public void setUsageExample(String word, String usageExample) {
        DictionaryWord dictionaryWord = find(word);
        if (dictionaryWord != null) {
            dictionaryWord.setUsageExample(usageExample);
        }
    }
	
	/**************************************************************************/
    /*!
      \brief
       The method which sets a definition to  a particular word in the 
	   ArrayList of dictionary words. This method was provided by UOL.
      
      \param word
        The word whose definition is going to be changed
	  
	  \param definition
	    The definition which is going to be added to word
	  
	  \return
	    Does not return anything 
		
    */
    /**************************************************************************/
    public void setDefinition(String word, String definition) {
        DictionaryWord dictionaryWord = find(word);
        if (dictionaryWord != null) {
            dictionaryWord.setDefinition(definition);
        }
    }
	
	/**************************************************************************/
    /*!
      \brief
       The method which removes the word which has been passed in from the 
	   dictionary ArrayList. This method was provided by UOL.
      
      \param word
        The word which is going to be removed
	  
	  \return
	    Does not return anything 
		
    */
    /**************************************************************************/
    public void removeWord(String word) {
        DictionaryWord dictionaryWord = find(word);
        if (dictionaryWord != null) {
            delete(dictionaryWord);
        }
    }
	
	/**************************************************************************/
    /*!
      \brief
       The method which adds a new dictionary word to the dictionary ArrayList
	   with its definition and usageExample already defined. This method was 
	   provided by UOL.
	  
	  \param definition
	    The definition which is going to be added to word
	  
	  \param usageExample
	    The usageExample which is going to be added to word
	
	  \param word
        The word that is going to added to the ArrayList
	  
	  \return
	    Does not return anything 
		
    */
    /**************************************************************************/
    public void setWord(String word, String definition, String usageExample) {
        DictionaryWord dictionaryWord = find(word);

        if (dictionaryWord != null) { //update existing word
            dictionaryWord.setDefinition(definition);
            dictionaryWord.setUsageExample(usageExample);
        } else { //word isn't already in the dictionary
            add(new DictionaryWord(word, definition, usageExample));
        }
    }
}
