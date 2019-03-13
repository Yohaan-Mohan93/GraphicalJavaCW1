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
        ThesaurusEditor
		add
		delete
		setAntonyms
		setSynonyms
		setWord
		removeWord
		
  Hours spent on this assignment: 
  10 hours
*/
/******************************************************************************/
import java.util.List;

public class ThesaurusEditor extends Thesaurus
{
	/**************************************************************************/
    /*!
      \brief
       The constructor for the ThesaurusEditor class which extends the Thesaurus
	   constructor.
      
      \param words
        An ArrayList of words taken from the Thesaurus.txt file
		
    */
    /**************************************************************************/
	public ThesaurusEditor(List<ThesaurusWord> words)
    {
    	super(words);
    }
	
	/**************************************************************************/
    /*!
      \brief
       The method which adds a word to the ArrayList of Thesaurus words.
      
      \param word
        The word which is going to be added to the ArrayList
	  
	  \return
	    Not Applicable 
		
    */
    /**************************************************************************/
	public void add(ThesaurusWord word)
	{
		words.add(word);
	}
	
	/**************************************************************************/
    /*!
      \brief
       The method which deletes a word to the ArrayList of Thesaurus words.
      
      \param word
        The word which is going to be removed to the ArrayList
	  
	  \return
	    Does not return anything 
		
    */
    /**************************************************************************/
	public void delete(ThesaurusWord word)
	{
		words.remove(word);
	}
	
	/**************************************************************************/
    /*!
      \brief
       The method which sets list of antonyms to a particular word in the 
	   ArrayList of Thesaurus words.
      
      \param word
        The word whose usage example is going to be changed
	  
	  \param antonyms
	    An ArrayList which contains the set of antonyms to be added.
	  
	  \return
	    Does not return anything 
		
    */
    /**************************************************************************/
	public void setAntonyms(String word, List<String> antonyms)
	{
		ThesaurusWord thesaurusWord = find(word);
		if(thesaurusWord != null)
		{
			thesaurusWord.setAntonyms(antonyms);
		}
	}
	
	/**************************************************************************/
    /*!
      \brief
       The method which sets a list of synonyms to  a particular word in the 
	   ArrayList of Thesaurus words.
      
      \param word
        The word whose usage example is going to be changed
	  
	  \param synonyms
	    An ArrayList which contains the set of synonyms to be added.
	  
	  \return
	    Does not return anything 
		
    */
    /**************************************************************************/
	public void setSynonyms(String word, List<String> synonyms)
	{
		ThesaurusWord thesaurusWord = find(word);
		if(thesaurusWord != null)
		{
			thesaurusWord.setAntonyms(synonyms);
		}
	}
	
	/**************************************************************************/
    /*!
      \brief
       The method which adds a new Thesaurus word to the Thesaurus ArrayList
	   with its antonyms and synonyms already defined. This method was 
	   provided by UOL.
	  
	  \param antonyms
	    An ArrayList which contains the set of antonyms to be added.
	  
	  \param synonyms
	    An ArrayList which contains the set of synonyms to be added.
	
	  \param word
        The word that is going to added to the ArrayList
	  
	  \return
	    Does not return anything 
		
    */
    /**************************************************************************/
	public void setWord(String word, List<String> antonyms, List<String> synonyms)
	{
		ThesaurusWord thesaurusWord = find(word);
		if(thesaurusWord != null)
		{
			thesaurusWord.setAntonyms(antonyms);
			thesaurusWord.setSynonyms(synonyms);
		}
		else
		{
			add(new ThesaurusWord(word, antonyms, synonyms));
		}
	}
	
	/**************************************************************************/
    /*!
      \brief
       The method which removes the word which has been passed in from the 
	   Thesaurus ArrayList. This method was provided by UOL.
      
      \param word
        The word which is going to be removed
	  
	  \return
	    Does not return anything 
		
    */
    /**************************************************************************/
	public void removeWord(String word)
	{
		ThesaurusWord inputWord = find(word);
		if(inputWord != null)
		{
			delete(inputWord);
		}
	}
}
