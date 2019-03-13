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
        main
        go
        mouseClicked
        CircleDrawPanel
        DiameterListener.actionPerformed
        RandomColourListener.actionPerformed
    
    
  Hours spent on this assignment: 
  10 hours
*/
/******************************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CirclesGUI implements MouseListener{
	JFrame frame;
	CircleDrawPanel drawPanel;
	private Color color = Color.red;
	private int X; private int Y;
	private int diameter = 100;

	/**************************************************************************/
    /*!
      \brief
       The main entrance for the CirclesGUI program and provided by UOL
      
      \param args
        arguments given to the program from the command line
      
      \return
        Does not return anything
    */
    /**************************************************************************/
	public static void main (String[] args){
		CirclesGUI gui = new CirclesGUI();
		gui.go();
	}
	
	/**************************************************************************/
    /*!
      \brief
       The method which creates the main JFrame GUI for the program, most of 
	   which is provided by UOL.
      
      \return
        Does not return anything
    */
    /**************************************************************************/
	//this method sets up the JFrame, including adding any components and listeners.
	public void go(){
		//The next two lines were provided by UOL
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//The next two lines were provided by UOL
		drawPanel = new  CircleDrawPanel();
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		
		JButton diameterBut = new JButton("Click Here to Resize the Circle");
		diameterBut.addActionListener(new DiameterListener());
		frame.getContentPane().add(BorderLayout.NORTH,diameterBut);
		
		JButton colorBut = new JButton("Click Here to Change the Circle's Color");
		colorBut.addActionListener(new RandomColourListener());
		frame.getContentPane().add(BorderLayout.SOUTH, colorBut);
		
		//The next line was provided by UOL
		drawPanel.addMouseListener(this);
		
		//The next two lines were provided by UOL
		frame.setSize(600,600);
		frame.setVisible(true);
	}
		
	/**************************************************************************/
    /*!
      \brief
       The method which tells how the program to respond when a mouse button is
	   pressed. This was provided by UOL.
      
      \return
        Not Applicable
    */
    /**************************************************************************/
	public void mousePressed(MouseEvent e) {}
	/**************************************************************************/
    /*!
      \brief
       The method which tells how the program to respond when a mouse button is
	   released after being pressed. This was provided by UOL.
      
      \return
        Not Applicable
    */
    /**************************************************************************/
	public void mouseReleased(MouseEvent e) {}
	/**************************************************************************/
    /*!
      \brief
       The method which tells how the program to respond when a mouse enters a
	   certain area. This was provided by UOL.
      
      \return
        Not Applicable
    */
    /**************************************************************************/
	public void mouseEntered(MouseEvent e) {}
	/**************************************************************************/
    /*!
      \brief
       The method which tells how the program to respond when a mouse leaves a 
	   certain area. This was provided by UOL.
      
      \return
        Not Applicable
    */
    /**************************************************************************/
	public void mouseExited(MouseEvent e) {}
	//all interface methods must be implemented
	
	/**************************************************************************/
    /*!
      \brief
       The method which gets the current x and y coordinate of the mouse cursor
	   and puts that into the global X and Y variables for redrawing the JPanel.
      
      \return
        Not Applicable
    */
    /**************************************************************************/
	public void mouseClicked(MouseEvent e) {
	  X = e.getX();
	  Y = e.getY();
	  drawPanel.repaint();
	


	//the placing of the circle depends on the X and Y instance variables. CircleDrawPanel can access the X and Y variables, as inner classes have unrestricted access to their containing classes instance variables.
	class CircleDrawPanel extends JPanel{
	/**************************************************************************/
    /*!
      \brief
       The method is called whenever the program need to draw all of the objects
	   in the window.
	   
	   \param g
        A Graphics object which tells the JVM how to draw the window
      
      \return
        Does not return anything
    */
    /**************************************************************************/
		public void paintComponent (Graphics g){
			super.paintComponent(g);
			Graphics2D g2=(Graphics2D)g;
			g2.setColor(color);
			int center = (int)(diameter *0.5);
			g2.fillOval((X - center),(Y - center),diameter,diameter);
		}
	}
	
	private class DiameterListener implements ActionListener
	{
	/**************************************************************************/
    /*!
      \brief
       The method is called whenever the diameterBut button is pressed in order 
	   to resize the circle.
	   
	   \param event
        A trigger caused by the clicking of a button with an attached actionListener
      
      \return
        Does not return anything
    */
    /**************************************************************************/
		public void actionPerformed(ActionEvent event)
		{
			Random randomDiameter = new Random();
			diameter = randomDiameter.nextInt(frame.getWidth()) + 1;
			drawPanel.repaint();
		}
	}
	
	private class RandomColourListener implements ActionListener
	{
	/**************************************************************************/
    /*!
      \brief
       The method is called whenever the diameterBut button is pressed in order 
	   to change the color of the circle.
	   
	   \param event
        A trigger caused by the clicking of a button with an attached actionListener
      
      \return
        Does not return anything
    */
    /**************************************************************************/
		public void actionPerformed(ActionEvent event)
		{
			Random randomColour = new Random();
			color = new Color(randomColour.nextInt(255),randomColour.nextInt(255),randomColour.nextInt(255));
			drawPanel.repaint();
		}
	}
}
