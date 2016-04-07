package project2;

import java.util.*;
import java.io.*;
import java.util.Observable;

public class RoundupModel extends Observable{

    private Robot[][] myBoard;
    private int boardNum = 1;
    private int numMoves = 0;
    private ArrayList<String> moves = new ArrayList<String>();
    private boolean won = false;
    private boolean lost = false;
    private boolean isSetBoard = false;
    BoardOptions boardOptions = new BoardOptions();
    
    public RoundupModel(int row, int col){
        myBoard = new Robot[row][col];
    }

    public Robot[][] getBoard()
    {
    	return myBoard;
    }
    
    public int getDimensions()
    {
        return myBoard.length;
    }

    public Robot getValueAt(int row, int col)
    {
        return myBoard[row][col];
        
    }
      
    public void setValueAt(int row, int col, Robot value)
    {
        myBoard[row][col] = value;
    }
    
    public int getBoardNum()
    {
        return boardNum;
    }
    
    public void setBoardNum(int num)
    {
    	boardNum = num;
    }
    
    /*public int getNumMoves()
    {
    	return numMoves;
    }
    
    public void setNumMoves(int num){
    	numMoves = num;
    }*/
    
    public boolean ifSet(){
    	return isSetBoard;
    }
    
    public boolean getWon()
    {
    	return won;
    }
    
    public boolean getLost()
    {
    	return lost;
    }
    
    public void clearBoard()
    {
    	for(int i = 0; i < getDimensions(); i++){
    		for(int j = 0; j < getDimensions(); j++){
    			if(i == 0 || i == 6 || j == 0 || j == 6){
    				setValueAt(i, j, new Robot(BoardCell.NONEWHITE, null));
    			}
    			else{
    				setValueAt(i, j, new Robot(BoardCell.NONEWHITE, null));
    			}
    		}
    	}
    }

    public void restartGame(String setUpInput)
    {
    	
    	clearBoard();
		numMoves = 0;
		won = false;
		lost = false;
		if(boardNum != 0){
			isSetBoard = false;
		}
		moves.clear();
		if(boardNum == 0){
			setUpBoard(setUpInput);
		}
		else{
			setUpBoard(null);
		}
		setChanged();
		notifyObservers();
    }
    
    public void newGame()
    {	
		if(boardNum != 18)
		{
			boardNum++;
		}
		else
		{
			boardNum = 1;
		}
		clearBoard();
		numMoves = 0;
		won = false;
		lost = false;
		if(boardNum != 0){
			isSetBoard = false;
		}
		moves.clear();
		setUpBoard(null);
		setChanged();
		notifyObservers();
    }
    
    public void select(int input)
    {
    	boardNum = input;
    	clearBoard();
		numMoves = 0;
		won = false;
		lost = false;
		if(boardNum != 0){
			isSetBoard = false;
		}
		moves.clear();
		setUpBoard(null);
		setChanged();
		notifyObservers();
    	
    }
    
    public void setGame(String setUpInput)
    {
    	boardNum = 0;
    	clearBoard();
    	numMoves = 0;
		won = false;
		lost = false;
		isSetBoard = true;
		moves.clear();
		setUpBoard(setUpInput);
		setChanged();
		notifyObservers();
    	
    }
    
    public boolean selectGame(String next)
    {
    	boolean valid = true;
		int num = Integer.parseInt(next);
		if(num < 19 && num > 0){
			select(num);
		}
		else
		{
			valid = false;
		}
    	return valid;
    }
    
    public boolean setBoard(String setUpInput)
    {
    	boolean valid = true;
    	ArrayList<String> robotPlacementsList = new ArrayList<String>();
		int count = 0;
		String[] tokens = setUpInput.trim().split("\\s+");
		for(int i = 0; i < tokens.length; i++)
		{
			robotPlacementsList.add(tokens[i]);
		}
		if(tokens.length == 6)
		{
			for(int i = 0; i < tokens.length; i++)
			{
				count += tokens[i].length();
				if(tokens[i].length() == 2)
				{
					int first = Character.getNumericValue(tokens[i].charAt(0));
					int second = Character.getNumericValue(tokens[i].charAt(1));
					if(first < 1 || first > 5 || second < 1 || second > 5)
					{
						valid = false;
					}
				}
				else if(tokens[i].length() == 3)
				{
					int first = Character.getNumericValue(tokens[i].charAt(0));
					int second = Character.getNumericValue(tokens[i].charAt(1));
					if(first < 1 || first > 5 || second < 1 || second > 5
							|| tokens[i].charAt(2) != 'R')
					{
						valid = false;
					}
				}
				
			}
			if(count != 13)
			{
				valid = false;
			}
		}
		else
		{
			valid = false;
		}
		Set<String> robotPlacementsSet = new HashSet<String>(robotPlacementsList);

		if(robotPlacementsSet.size() < robotPlacementsList.size()){
		    return false;
		}
		return valid;
		
    }
    
    public void setUpBoard(String setUpInput){
        
    	String tokens[];
		if(boardNum == 0)
		{
			tokens = setUpInput.trim().split("\\s+");
		}
		else
		{
			tokens = (boardOptions.getRobots(getBoardNum())).trim().split("\\s+");
		}
        int row = 0;
        int col = 0;
        for(int i = 0; i < getDimensions(); i++){
			for(int j = 0; j < getDimensions(); j++){
				if(i == 0 || j == 0 || i == 6 || j == 6){
					setValueAt(i, j, new Robot(BoardCell.NONEBLACK, null));
				}
				else
				{
					setValueAt(i, j, new Robot(BoardCell.NONEWHITE, null));
				}
			}
		}
        for(int i = 0; i < tokens.length; i++){
        	row = Character.getNumericValue(tokens[i].charAt(0));
			col = Character.getNumericValue(tokens[i].charAt(1));
    		if(tokens[i].length() == 2){
    			setValueAt(row, col, new Robot());
    		}
    		else if(tokens[i].length() == 3){
    			setValueAt(row, col, new Robot(BoardCell.REDROBOT, Direction.UP));
    		}
    		
    	}

    }
    
    public String getAboutString()
    {
    	setChanged();
    	notifyObservers("about");
    	
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
    
    public void moveUp(Robot toMove, int row, int col, String input){
    	if(!(toMove.getType().equals(BoardCell.NONEWHITE))){
	    	numMoves++;
	    	moves.add(input);
			while(getValueAt(row, col).getType().equals(BoardCell.NONEWHITE)){
				row--;
				if(getValueAt(row, col).getType().equals(BoardCell.NONEBLACK)){
					lost = true;
					row--;
					break;
				}
				
   			}
			row++;
			if(lost)
			{
				setValueAt(row, col, new Robot(BoardCell.DEADROBOT, Direction.UP));
			}
			else
			{
				setValueAt(row, col, new Robot(toMove.getType(), Direction.UP));
			}
			Robot checkMiddle = getValueAt(3, 3);
			if(checkMiddle.getType().equals(BoardCell.REDROBOT)){
				won = true;
			}
		}
    }
    
    public void moveDown(Robot toMove, int row, int col, String input){
    	if(!(toMove.getType().equals(BoardCell.NONEWHITE))){
	    	numMoves++;
	    	moves.add(input);
			while(getValueAt(row, col).getType().equals(BoardCell.NONEWHITE)){
				row++;
				if(getValueAt(row, col).getType().equals(BoardCell.NONEBLACK)){
					lost = true;
					row++;
					break;
				}	
			}
			row--;
			if(lost)
			{
				setValueAt(row, col, new Robot(BoardCell.DEADROBOT, Direction.DOWN));
			}
			else
			{
				setValueAt(row, col, new Robot(toMove.getType(), Direction.DOWN));
			}
			Robot checkMiddle = getValueAt(3, 3);
			if(checkMiddle.getType().equals(BoardCell.REDROBOT)){
				won = true;
			}
		}
    }
    
    public void moveLeft(Robot toMove, int row, int col, String input){
    	if(!(toMove.getType().equals(BoardCell.NONEWHITE))){
	    	numMoves++;
	    	moves.add(input);
			while(getValueAt(row, col).getType().equals(BoardCell.NONEWHITE)){
				col--;
				if(getValueAt(row, col).getType().equals(BoardCell.NONEBLACK)){
					lost = true;
					col--;
					break;
				}
				
			}
			col++;
			if(lost)
			{
				setValueAt(row, col, new Robot(BoardCell.DEADROBOT, Direction.LEFT));
			}
			else
			{
				setValueAt(row, col, new Robot(toMove.getType(), Direction.LEFT));
			}
			Robot checkMiddle = getValueAt(3, 3);
			if(checkMiddle.getType().equals(BoardCell.REDROBOT)){
				won = true;
			}
		}
    }
    
    public void moveRight(Robot toMove, int row, int col, String input)
    {
    	if(!(toMove.getType().equals(BoardCell.NONEWHITE))){
	    	numMoves++;
	    	moves.add(input);
			while(getValueAt(row, col).getType().equals(BoardCell.NONEWHITE)){
				col++;
				if(getValueAt(row, col).getType().equals(BoardCell.NONEBLACK)){
					lost = true;
					col++;
					break;
				}
				
			}
			col--;
			if(lost)
			{
				setValueAt(row, col, new Robot(BoardCell.DEADROBOT, Direction.RIGHT));
			}
			else
			{
				setValueAt(row, col, new Robot(toMove.getType(), Direction.RIGHT));
			}
			Robot checkMiddle = getValueAt(3, 3);
			if(checkMiddle.getType().equals(BoardCell.REDROBOT)){
				won = true;
			}
		}
    	
    }
    public void move(int row, int col, char direction, String input){
    	Robot toMove = getValueAt(row, col);
    	setValueAt(row, col, new Robot(BoardCell.NONEWHITE, null)); 
    	if(direction  == 'u' || direction == 'U'){
    		moveUp(toMove, row, col, input);
    	}
    	else if(direction == 'd'  || direction == 'D'){
    		moveDown(toMove, row, col, input);
    	}
    	else if(direction == 'l' || direction == 'L'){
    		moveLeft(toMove, row, col, input);
    	}
    	else if(direction == 'r' || direction == 'R'){
    		moveRight(toMove, row, col, input);
    	}
    	setChanged();
    	notifyObservers();
    
    }
    
    public String wonString(){
    	return "Game Won Notification: You won game " + boardNum + "!" + "\n" + theMoves(); 
    }
    
    public String theMoves(){
    	String allTheMoves = "";
    	for(String move: moves)
    	{
    		allTheMoves += move;
    	}
    	allTheMoves = allTheMoves.toUpperCase();
    	return allTheMoves;
    }
    
    public void enteredMove(String input){
    	if(!lost && !won)
		{
			updateBoard(input);	
		}
		
    }
    
    public void updateBoard(String input)
    {
    	if(input.length() == 3){
    		if(Character.isDigit(input.charAt(0)) && Character.isDigit(input.charAt(1))){
	    		int row = Character.getNumericValue(input.charAt(0));
	    		int col = Character.getNumericValue(input.charAt(1));
	    				
	    		if(row < 6 && row > 0 && col < 6 && col > 0)
	    		{
		    		char direction = input.charAt(2);
		    		Robot val = getValueAt(row, col);
		    		if(isValidDirection(direction)
		    				&& (val.getType().equals(BoardCell.REDROBOT) || 
		    						val.getType().equals(BoardCell.GREENROBOT)))
		    		{
		    			move(row, col, direction, input);	
		    		}
	    		}
    		}
    	}
    	
    	
    }
    
    public boolean isValidDirection(char direction){
    	return (direction == 'r' || direction == 'R' || direction == 'l' || direction == 'L'
    			|| direction == 'u' || direction == 'U' || direction == 'd' || direction == 'D'); 	
    }
    
    public void selectGameUpdate()
    {
    	setChanged();
		notifyObservers("select");
    }
    
    public void setGameUpdate()
    {
    	setChanged();
    	notifyObservers("set");
    }
    
}
