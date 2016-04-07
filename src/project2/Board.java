package project2;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.table.*;

/**
 * Write a description of class Board here.
 * 
 * @author Delaney Giacalone
 * @version (a version number or a date)
 */
public class Board extends AbstractTableModel
{
    private String[] columns = {"", "", "", "", "", "", "", };
    private Robot [][] myBoard;
    private RoundupModel model;
    private RoundupImages images;

    public Board(int row, int col, RoundupModel model, RoundupImages images){
        myBoard = new Robot[row][col];
        this.model = model;
        //images.loadImages();
        myBoard = model.getBoard();
        this.images = images;
        model.setUpBoard(null);
        
    }

    public int getColumnCount()
    {
        return columns.length;
    }

    public int getRowCount()
    {
        return myBoard.length;
    }

    public String getColumnName(int col)
    {
        return columns[col];
    }
    
    public String[] getColumns()
    {
        return columns;
    }
    
    public Robot[][] getBoard()
    {
        return myBoard;
    }
    
    public void setColumnName(int col, String name)
    {
        columns[col] = name;
    }

    public ImageIcon getValueAt(int row, int col)
    {
        Robot val = model.getValueAt(row, col);
    	//return pawnEnum;
        return val.getImage();
        
    }
        
    public void setValueAt(int row, int col, Robot val)
    {
       myBoard[row][col] = val;	
    }
   
    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c)
    {
        return getValueAt(0, c).getClass();
    }
    
    public void restartGame(String setUpInput)
    {
    	model.restartGame(setUpInput);   	
    }
    
    public void newGame()
    {
    	model.newGame();   	
    }
    
    public boolean selectGame(String gameNum)
    {
    	boolean valid = true;
		int num = Integer.parseInt(gameNum);
		if(num < 19 && num > 0){
			model.select(num);
		}
		else
		{
			valid = false;
		}
		return valid;
    }
    
    public boolean setUpGame(String setUpInput)
    {
    	if(model.setBoard(setUpInput))
    	{
    		model.setGame(setUpInput);
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    public String getAbout()
    {
    	return "-- About --\n" +
    			"Robot Roundup (Level 1)\n\n"+
    			"Click on a robot, then press a cursor key to move the robot. \n"+
    			"The robot will move in a straight line in the direction \n"+
    			"specified and can only stop when it runs into another robot \n"+
    			"or walks off the board. Your goal is to guide the red robot \n"+
    			"to end up positioned on the center square. \n"+
    			"There are 18 puzzles that can be played in any order. \n"+ 
    			"Android Icon by http://madeliniz.deviantart.com";
    }
    
    public int getBoardNum()
    {
    	return model.getBoardNum();
    }
    
    /*public int getNumMoves()
    {
    	return model.getNumMoves();
    }*/
    
    public void move(int row, int col, char dir)
    {
    	String input = row + "" + col + dir;
    	model.enteredMove(input);
    }
    
    public boolean getWon()
    {
    	return model.getWon();
    }
    
    public boolean getLost()
    {
    	return model.getLost();
    }
    
    public String getMoves()
    {
    	return model.theMoves();
    }
    
}
