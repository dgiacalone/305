package project2;
import java.io.*;
import java.util.*;
/**
 * Write a description of class RoundupConsole here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RoundupConsole implements Observer
{
    //can i pass the board to the console?
	private Scanner console;
    private PrintWriter display;
    private RoundupModel model;
    private int printWon = 0;
    private boolean isGuiRunning = false;
    /**
     * Constructs the console view of the application. 
     * @param rdr a Reader from which to read the user's input,
     * usually <code>System.in</code>
     * @param wtr a Writer to which to display the output, 
     * usually <code>System.out</code>
     */
    public RoundupConsole(Reader rdr, Writer wtr, RoundupModel board, boolean isGuiRunning) 
    		throws IOException
    {
        console = new Scanner(rdr);
        display = new PrintWriter(wtr, true);
        model = board;
        this.isGuiRunning = isGuiRunning;
    }

    public RoundupModel getModel(){
    	return model;
    }
    
    public void update(Observable t, Object o) 
    {   
    	if(o == null)
    	{
    		printBoard();
    	}
    }

    public void printBoard()
    {

    	display.println("Roundup - board " + model.getBoardNum() + "\n");
    	//display.println("Moves: " + model.getNumMoves());
    	display.println("        1  2  3  4  5   ");
    	for(int i = 0; i < 7; i++)
    	{
    		if(i != 0 && i != 6)
    		{
    			display.print(" " + i + ":  ");
    		}
    		else
    		{
    			display.print("     ");
    		}
    		for(int j = 0; j < 7; j++)
    		{
    			Robot val = model.getValueAt(i, j);
    			getSymbol(val.getType());
    			if(j != 6){
    				display.print("  ");
    			}
    		}
    		display.println();
    	}
    	display.println(" -----------------------");
        display.println("1)Restart 2)New Game 3)Select Game 4)Set Game 5)Hall of Fame 6)About 7)Quit 8)Prefs");	
        if(model.getWon() && !isGuiRunning){
			display.println(model.wonString());
			display.println("Save your time of 0:00? (y/n)");
		}
    	
    }
    
    public void getSymbol(BoardCell val)
    {
    	if(val != BoardCell.NONEBLACK && val != BoardCell.NONEWHITE)
		{
			if(val.equals(BoardCell.GREENROBOT))
			{
				display.print("o");
			}
			else if(val.equals(BoardCell.REDROBOT))
			{
				display.print("*");
			}
			else if(val.equals(BoardCell.DEADROBOT))
			{
				display.print("X");
			}
			else{
				display.print(" ");
			}
		}
    	
		else{
			display.print(" ");
		}
    }
    
    public void run()
    {
        
        model.setUpBoard(null);
        printBoard();
        String input = "";
        String setUpInput = "";
        while (!(input.equals("7")))
        {
            input = console.nextLine();
            switch(input)
            {
            case "1":
                model.restartGame(setUpInput);
                break;
            case "2":
                model.newGame();
                break;
            case "3": 
                select();
                break;
            case "4":
                set(setUpInput);
                break;
            case "5":
                break;
            case "6":
                about();
                break;
            case "8":
                break;
            default:
                if (!model.getWon() && !model.getLost())
                {
                    model.enteredMove(input);

                }
            }
        }
    }
    
    private void select()
    {
        if (!isGuiRunning)
        {
            display.println("Select Game: Enter desired " +
                "game number (1 - 18):");    
            try
            {
                String next = console.nextLine();
                boolean valid = model.selectGame(next);
            }
            catch (Exception NumberFormatException)
            {
            }
        }
        else
        {
            model.selectGameUpdate();
        }
    }
    
    private void set(String setUpInput)
    {
        if (!isGuiRunning)
        {
            display.println("Set Game: Enter board configuration:");
        
            setUpInput = console.nextLine();
            boolean valid = model.setBoard(setUpInput);
            if (valid)
            {
                model.setGame(setUpInput);
            }
            else
            {
                if (!isGuiRunning)
                {
                    display.println("-- Error --\nNot a valid board " +
                        "configuration.");
                }   
            }
        }
        else
        {
            model.setGameUpdate();
        }
    }
    
    private void about()
    {
        String about = model.getAboutString();
        if (!isGuiRunning)
        {
            display.println(about);
    
        }
    }


}

