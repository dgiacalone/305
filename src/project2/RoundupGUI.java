package project2;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
/**  Skeleton GUI for grid-based games.
 * 
 */
public class RoundupGUI extends JFrame implements Observer
{
    /* Main components of the GUI */
    // DO NOT CHANGE THE FOLLOWING THREE LINES
    private JTable table;
    private JLabel myStatus = null;
    private JMenuBar menuBar;
    private JPanel statusPane;
    // The underlying data model
    private Board board;
    // The images to be displayed
    private ImageIcon background;
    /* Image dimensions, in pixels */
    private int currentSelectedX = 0;
    private int currentSelectedY = 0;
    private String setUpInput = "";
    private int clickedRow = 0;
    private int clickedCol = 0;
    private RoundupImages images;
    private int winLose;
    private Image loseBackground;
    /** Create a GUI. 
     *  Will use the System Look and Feel when possible.
     */
    public RoundupGUI(Board board, RoundupImages images)
    {
        super();
        this.board = board;
        this.images = images;
        try
        {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex)
        {
            System.err.println(ex);
        }
    }
    
    public void update(Observable t, Object o)
    {
    	
    	if(o != null)
    	{
    		if(((String)o).equals("about"))
	    	{
	    		JOptionPane.showMessageDialog(null, board.getAbout(), "About", JOptionPane.DEFAULT_OPTION); 
	    	}
    		else if(((String)o).equals("select"))
    		{	
    			selectUpdate(o);
    		}
    		else if(((String)o).equals("set"))
    		{
    			setUpdate(o);
    		}
    		o = null;
    	}
    	loseBackground = null;
    	setTitle("Roundup Board - " + board.getBoardNum());
    	
    	if(board.getLost())
		{
    		//myStatus.setText("LOSE");
    		loseBackground = javax.swing.GrayFilter.createDisabledImage(background.getImage());
		}
		else if(board.getWon())
		{
			//myStatus.setText("Moves: " + board.getNumMoves());
			JOptionPane.showConfirmDialog(null, "You won game " + board.getBoardNum() + "!\n" + board.getMoves() + 
					"\nSave your time of 0:00? (y/n)\n", "Game Won Notification", JOptionPane.YES_NO_OPTION);	
			
		}
		else
		{
			//myStatus.setText("Moves: " + board.getNumMoves());
		}
    }
    
    public void selectUpdate(Object o)
    {
    	try{
    		String gameNum = JOptionPane.showInputDialog(null, "Enter Desired Game Number (1-18):",
        				"Select Game", JOptionPane.QUESTION_MESSAGE);
    		o = null;
    		if(gameNum != null)
    		{
        		if(board.selectGame(gameNum))
                {
                	//loseBackground = null;
                	setTitle("Roundup Board - " + board.getBoardNum());
                	//myStatus.setText("Moves: " + board.getNumMoves());
                	repaint();
                }
                else
                {
                	JOptionPane.showMessageDialog(null, "Not a valid board.",
            				"Error", JOptionPane.ERROR_MESSAGE);	
                }
    		}
    	}
    	catch(Exception NumberFormatException){
    		JOptionPane.showMessageDialog(null, "Not a valid board.",
    				"Error", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    public void setUpdate(Object o)
    {
    	setUpInput = JOptionPane.showInputDialog(null, "Enter board configuration:",
				"Set Game", JOptionPane.QUESTION_MESSAGE);
		o = null;
    	if(setUpInput != null)
    	{
        	if(board.setUpGame(setUpInput))
        	{
        		//loseBackground = null;
        		setTitle("Roundup Board - " + board.getBoardNum());
        		//myStatus.setText("Moves: " + board.getNumMoves());
        		repaint();
        	}
        	else
        	{
        		JOptionPane.showMessageDialog(null, "Not a valid board configuration.",
        				"Error", JOptionPane.ERROR_MESSAGE);
        	}
    	}
    }
    
    /** Place all the Swing widgets in the frame of this GUI.
     */
    public void layoutGUI()
    {
        setTitle("Roundup Board - " + board.getBoardNum());
        Image one = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/" + "backgroundbkgd.png"));
        background = new ImageIcon(one);

        table = new ImageJTable(board);

        // Define the layout manager that will control order of components
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        layoutMenus();

        // Create a panel for the status information
        statusPane = new JPanel();
        myStatus = new JLabel("Moves: 0 Time: 0:00");
        statusPane.add(myStatus);
        statusPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        getContentPane().add(statusPane);

        /* Define the characteristics of the table that shows the game board    */
        // Set the dimensions for each column in the board to match the image 
        for (int col = 0; col < board.getColumnCount(); col++)
        {
            TableColumn column = table.getColumnModel().getColumn(col);
            column.setMaxWidth(background.getIconHeight()/board.getColumnCount());
            column.setMinWidth(background.getIconHeight()/board.getColumnCount());
        }
        // remove editor makes table not editable
        table.setDefaultEditor(Object.class, null);  
        // define how cell selection works
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setCellSelectionEnabled(false);
        // other miscellaneous settings
        table.setRowHeight(background.getIconHeight()/board.getRowCount());
        table.setOpaque(false);
        table.setShowGrid(false);
        table.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Add a custom mouse listener that will handle player's clicks.
        table.addMouseListener(myMouseListener);
        table.addKeyListener(myKeyAdapter);
        //setUpActionListeners();
        // finally, add the table to the content pane
        getContentPane().add(table);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    } // end layout GUI

    /* Setup the menubar and submenus */
    private void layoutMenus()
    {
    	menuBar = new javax.swing.JMenuBar();
        JMenu mnuGame = new JMenu("Game");
        menuBar.add(mnuGame);
        
        JMenu mnuEdit = new JMenu("Edit");
        menuBar.add(mnuEdit);
        
        restartMenu(mnuGame);
        newGameMenu(mnuGame);
        selectGameMenu(mnuGame);
        setGameMenu(mnuGame);
        hallOfFameMenu(mnuGame);   
        aboutMenu(mnuGame);
        quitMenu(mnuGame);
        preferencesMenu(mnuEdit);
        
        setJMenuBar(menuBar); 
    } // end layout Menus
      
    private void restartMenu(JMenu mnuGame)
    {
    	JMenuItem mnuRestart = new JMenuItem("Restart");
        mnuRestart.setMnemonic('R');
        mnuRestart.setAccelerator(
                KeyStroke.getKeyStroke('R', ActionEvent.ALT_MASK));
        mnuRestart.addActionListener(new ActionListener()
        {
            // Anonymous inner classes are used here for brevity, but should be
            // named classes in production code.
            public void actionPerformed(ActionEvent e)
            {
            	//loseBackground = null;
            	board.restartGame(setUpInput);
            	//myStatus.setText("Moves: " + board.getNumMoves());
                repaint();                
            }
        });
        mnuGame.add(mnuRestart);
    }
    
    private void newGameMenu(JMenu mnuGame)
    {
    	JMenuItem mnuNew = new JMenuItem("New Game");
        mnuNew.setMnemonic('N');
        mnuNew.setAccelerator(
                KeyStroke.getKeyStroke('N', ActionEvent.ALT_MASK));
        mnuNew.addActionListener(new ActionListener()
        {
            // Anonymous inner classes are used here for brevity, but should be
            // named classes in production code.
            public void actionPerformed(ActionEvent e)
            {
            	//loseBackground = null;
            	board.newGame();
            	setTitle("Roundup Board - " + board.getBoardNum());
            	//myStatus.setText("Moves: " + board.getNumMoves());
            	repaint();                
            }
        });
        mnuGame.add(mnuNew);
    }
    
    private void selectGameMenu(JMenu mnuGame)
    {
    	JMenuItem mnuSelect = new JMenuItem("Select Game");
        mnuSelect.setMnemonic('S');
        mnuSelect.setAccelerator(
                KeyStroke.getKeyStroke('S', ActionEvent.ALT_MASK));
        mnuSelect.addActionListener(new ActionListener()
        {
            // Anonymous inner classes are used here for brevity, but should be
            // named classes in production code.
            public void actionPerformed(ActionEvent e)
            {
            	try{
            		String gameNum = JOptionPane.showInputDialog(null, "Enter Desired Game Number (1-18):",
	            				"Select Game", JOptionPane.QUESTION_MESSAGE);
	                if(gameNum != null)
	                {
	            		if(board.selectGame(gameNum))
		                {
		                	//loseBackground = null;
		                	setTitle("Roundup Board - " + board.getBoardNum());
		                	//myStatus.setText("Moves: " + board.getNumMoves());
		                	repaint();
		                }
		                else
		                {
		                	JOptionPane.showMessageDialog(null, "Not a valid board.",
		            				"Error", JOptionPane.ERROR_MESSAGE);	
		                }
	                }
            	}
            	catch(Exception NumberFormatException){
            		JOptionPane.showMessageDialog(null, "Not a valid board.",
            				"Error", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });
        mnuGame.add(mnuSelect);
    }
    
    private void setGameMenu(JMenu mnuGame)
    {
    	JMenuItem mnuSet = new JMenuItem("Set");
        mnuSet.setMnemonic('T');
        mnuSet.setAccelerator(
                KeyStroke.getKeyStroke('T', ActionEvent.ALT_MASK));
        mnuSet.addActionListener(new ActionListener()
        {
            // Anonymous inner classes are used here for brevity, but should be
            // named classes in production code.
            public void actionPerformed(ActionEvent e)
            {
            	setUpInput = JOptionPane.showInputDialog(null, "Enter board configuration:",
        				"Set Game", JOptionPane.QUESTION_MESSAGE);
            	if(setUpInput != null)
            	{
	            	if(board.setUpGame(setUpInput))
	            	{
	            		//loseBackground = null;
	            		setTitle("Roundup Board - " + board.getBoardNum());
	            		//myStatus.setText("Moves: " + board.getNumMoves());
	            		repaint();
	            	}
	            	else
	            	{
	            		JOptionPane.showMessageDialog(null, "Not a valid board configuration.",
	            				"Error", JOptionPane.ERROR_MESSAGE);
	            	}
            	}
            }
        });
        mnuGame.add(mnuSet);
    }
    
    private void hallOfFameMenu(JMenu mnuGame)
    {
        JMenuItem mnuHallOfFame = new JMenuItem("Hall of Fame");
        mnuHallOfFame.setMnemonic('H');
        mnuHallOfFame.setAccelerator(
                KeyStroke.getKeyStroke('H', ActionEvent.ALT_MASK));
        mnuHallOfFame.addActionListener(new ActionListener()
        {
            // Anonymous inner classes are used here for brevity, but should be
            // named classes in production code.
            public void actionPerformed(ActionEvent e)
            {
            	
            }
        });
        mnuGame.add(mnuHallOfFame);
    }
    
    private void aboutMenu(JMenu mnuGame)
    {
    	JMenuItem mnuAbout = new JMenuItem("About");
        mnuAbout.setMnemonic('A');
        mnuAbout.setAccelerator(
                KeyStroke.getKeyStroke('A', ActionEvent.ALT_MASK));
        mnuAbout.addActionListener(new ActionListener()
        {
            // Anonymous inner classes are used here for brevity, but should be
            // named classes in production code.
            public void actionPerformed(ActionEvent e)
            {
            	JOptionPane.showMessageDialog(null, board.getAbout(), "About", JOptionPane.DEFAULT_OPTION);              
            }
        });
        mnuGame.add(mnuAbout);
    }
    
    private void quitMenu(JMenu mnuGame)
    {
    	JMenuItem mnuQuit = new JMenuItem("Quit");
        mnuQuit.setMnemonic('Q');
        mnuQuit.setAccelerator(
                KeyStroke.getKeyStroke('Q', ActionEvent.ALT_MASK));
        mnuQuit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
        mnuGame.add(mnuQuit);
    }
    
    private void preferencesMenu(JMenu mnuEdit)
    {
    	JMenuItem mnuPreferences = new JMenuItem("Preferences");
        mnuPreferences.setMnemonic('F');
        mnuPreferences.setAccelerator(
                KeyStroke.getKeyStroke('F', ActionEvent.ALT_MASK));
        mnuPreferences.addActionListener(new ActionListener()
        {
            // Anonymous inner classes are used here for brevity, but should be
            // named classes in production code.
            public void actionPerformed(ActionEvent e)
            {
                //repaint();                
            }
        });
        mnuEdit.add(mnuPreferences);
    }
    
    /* Listener to respond to mouse clicks on the table */
    private MouseAdapter myMouseListener = new MouseAdapter()
    {
        public void mouseReleased(MouseEvent ev)
        {
            // obtain the selected cell coordinates

            clickedRow = (int) (ev.getPoint().getY() / (background.getIconHeight()/7));
            clickedCol = (int) (ev.getPoint().getX() / (background.getIconHeight()/7));
            currentSelectedX = clickedRow;
            currentSelectedY = clickedCol;

            //repaint();
        }
    };  // end mouse listener

    private KeyAdapter myKeyAdapter = new KeyAdapter()
    {
		public void keyPressed(KeyEvent e) 
		{
			if(!board.getLost() && !board.getWon())
			{
				if (e.getKeyCode() == KeyEvent.VK_LEFT  || e.getKeyChar() == 's')
		            board.move(clickedRow, clickedCol, 'L');
		        else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyChar() == 'f')
		        	board.move(clickedRow, clickedCol, 'R');
		        else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == 'e')
		        	board.move(clickedRow, clickedCol, 'U');
		        else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyChar() == 'd')
		        	board.move(clickedRow, clickedCol, 'D');
				repaint();
			}
		}
    };
    
    
    /** Our custom JTable has special features for displaying images and 
     *  a background.
     */
    private class ImageJTable extends JTable
    {
        public ImageJTable(AbstractTableModel board)
        {
        	super(board);
        }
    	
    	public ImageJTable(Robot[][] rowData, String[] columnNames)
        {
            super(rowData, columnNames);
        }
        //  Tell JTable it should expect each column to contain IconImages,
        //  and should select the corresponding renderer for displaying it.
        public Class getColumnClass(int column)
        {
            return ImageIcon.class;
        }
        //  Allow the background to be displayed
        public Component prepareRenderer(TableCellRenderer renderer, int row,
                int column)
        {
            Component component = super.prepareRenderer(renderer, row, column);
            // We want renderer component to be
            // transparent so background image is visible
            if (component instanceof JComponent)
            {
                ((JComponent) component).setOpaque(false);
            }
            return component;
        }

        // Override paint so as to show the table background
        public void paint(Graphics gfx)
        {
        	// paint an image in the table background
        	if(loseBackground != null){
        		gfx.drawImage(loseBackground, 0, 0, null, null);
        	}
        	else if (background != null)
            {
                gfx.drawImage(background.getImage(), 0, 0, null, null);
            }
            // Now let the paint do its usual work
            super.paint(gfx);
            repaint();
        }

    } // end ImageJTable


}  // end GUIDemo