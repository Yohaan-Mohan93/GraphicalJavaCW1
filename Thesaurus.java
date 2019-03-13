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
        Thesaurus
		find
		
  Hours spent on this assignment: 
  10 hours
*/
/******************************************************************************/
import java.util.List;

public class Thesaurus 
{
	protected List<ThesaurusWord> words;
	
	/**************************************************************************/
    /*!
      \brief
       The constructor for the Thesaurus class and is based on the code 
	   provided by UOL.
	  
	  \param words
	    The ArrayList which contains all of the words in the Thesaurus.
		
    */
    /**************************************************************************/
	public Thesaurus(List<ThesaurusWord> words)
	{
		this.words = words;
	}
	
	/**************************************************************************/
    /*!
      \brief
       Changes all characters into lower case alphabets and look for the word. 
	   This is based on code which has been provided by UOL.
	  
	  \param word
	    The word which the program is going to look for
	  
	  \return
	   The Thesaurus word which contains the word if found
		
    */
    /**************************************************************************/
	public ThesaurusWord find(String word) {
        for (int i = 0; i < words.size(); i++) {
            if(word.toLowerCase().equals(words.get(i).getWord().toLowerCase())) {
                return words.get(i);
            }
        }
        //word not found
        return null;
    }
}