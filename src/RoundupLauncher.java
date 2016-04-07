import java.io.*;

import project2.*;
//import jargs.gnu.CmdLineParser;
//import jargs.gnu.CmdLineParser.*;
/**
 * Write a description of class RoundupApp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RoundupLauncher
{
    // instance variables - replace the example below with your own
    /** Local main to launch the GUI.
     * @param args command line arguments (none expected)
     */
    public static void main(String[] args) throws IOException
    {
        
        boolean isGuiRunning = true;
        
    	CmdLineParser parser=new CmdLineParser();
		CmdLineParser.Option consoleOption=parser.addBooleanOption('c',"c");
		CmdLineParser.Option guiOption=parser.addBooleanOption('g',"g");
		CmdLineParser.Option inputOption=parser.addStringOption('i',"infile");
		CmdLineParser.Option outputOption=parser.addStringOption('o',"outfile");		 
		try
		{
		   parser.parse(args);
		}
		catch(CmdLineParser.OptionException e)
		{
		   System.exit(2);
		}
		Boolean optionConsole=(Boolean)parser.getOptionValue(consoleOption);
		Boolean optionGUI=(Boolean)parser.getOptionValue(guiOption);
		String optionInput=(String)parser.getOptionValue(inputOption);
		String optionOutput=(String)parser.getOptionValue(outputOption);
		    
        RoundupModel model = new RoundupModel(7, 7); 
    	if(optionConsole == null){
    		optionConsole = false;
    	}
    	if(optionGUI == null && args.length != 0){
    		optionGUI = false;
    		isGuiRunning = false;
    	}
    	else if(optionGUI == null)
    	{
    		optionGUI = false;
    	}
        if(optionConsole && !optionGUI)
        {
        	onlyConsole(optionOutput, optionInput, model, isGuiRunning);
        }
        else if(!optionConsole && optionGUI)
        {
    		RoundupImages images = new RoundupImages();
            images.loadImages();
        	onlyGUI(model, images);
        }
        else
        {
    		RoundupImages images = new RoundupImages();
            images.loadImages();
        	consoleAndGUI(model, images, isGuiRunning);
        }
    }

	public static void onlyConsole(String optionOutput, String optionInput, RoundupModel model, boolean isGuiRunning) throws IOException
	{
		InputStreamReader consoleInput = new InputStreamReader(System.in);
       	OutputStreamWriter consoleOutput = new OutputStreamWriter(System.out);
       	if(optionOutput != null) 
       	{
       		File file = new File(optionOutput);
       		if(!file.exists()) {
       		    file.createNewFile();
       		}
       		OutputStream outputStream = new FileOutputStream(file);
       		consoleOutput = new OutputStreamWriter(outputStream);
       	}
       	if(optionInput != null)
       	{
       		try
    		{
        		InputStream inputStream = new FileInputStream(optionInput);
        		consoleInput = new InputStreamReader(inputStream);
    		}
    		catch(FileNotFoundException e)
    		{
    			System.out.println("Input File Not Found");
    		}
       	}
       	RoundupConsole console = new RoundupConsole(consoleInput, 
                consoleOutput, model, isGuiRunning);
    	model.addObserver(console);
        console.run();	
	}
    
    public static void onlyGUI(RoundupModel model, RoundupImages images)
	{
		Board board = new Board(7, 7, model, images);
	    RoundupGUI frame = new RoundupGUI(board, images);
		model.addObserver(frame);
	    frame.layoutGUI();
	    frame.pack();
	    frame.setVisible(true);
	}
	
	public static void consoleAndGUI(RoundupModel model, RoundupImages images, boolean isGuiRunning) throws IOException
	{
		Board board = new Board(7, 7, model, images);
    	RoundupConsole console = new RoundupConsole(new InputStreamReader(System.in), 
                new OutputStreamWriter(System.out), model, isGuiRunning);
    	RoundupGUI frame = new RoundupGUI(board, images);
    	model.addObserver(console);
    	model.addObserver(frame);
        frame.layoutGUI(); 
        frame.pack();
        frame.setVisible(true);
        console.run();
	}
}
