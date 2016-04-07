//package tests;
package project2;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

import project2.BoardCell;
import project2.RoundupConsole;
import project2.RoundupModel;

public class RoundupConsoleTest {
	
	RoundupModel board = new RoundupModel(7, 7);

	@Test
	public void constructorTest() throws IOException {
		Reader rdr = new StringReader("");
		Writer wtr = new StringWriter();
		RoundupConsole console = new RoundupConsole(rdr, wtr, board, false);
		assertNotNull(console);
	}
	
	@Test
	public void TestRestart() throws IOException
	{
		Reader rdr = new StringReader("1\n7\n");
		Writer wtr = new StringWriter();
		RoundupConsole console = new RoundupConsole(rdr, wtr, board, false);
		console.run();
		assertEquals(console.getModel().getValueAt(1, 1).getType(), BoardCell.GREENROBOT);
	}
	
	@Test
	public void TestNewGame() throws IOException
	{
		Reader rdr = new StringReader("2\n7\n");
		Writer wtr = new StringWriter();
		RoundupConsole console = new RoundupConsole(rdr, wtr, board, false);
		console.run();
		assertEquals(console.getModel().getValueAt(1, 1).getType(), BoardCell.NONEWHITE);
	}
	
	@Test
	public void TestSetGame() throws IOException
	{
		Reader rdr = new StringReader("3\n4\n7\n");
		Writer wtr = new StringWriter();
		RoundupConsole console = new RoundupConsole(rdr, wtr, board, false);
		console.run();
		assertEquals(console.getModel().getValueAt(1, 1).getType(), BoardCell.GREENROBOT);
	}
	
	@Test
	public void TestSelectGame() throws IOException
	{
		Reader rdr = new StringReader("4\n11 22 33 44 14 23R\n7\n");
		Writer wtr = new StringWriter();
		RoundupConsole console = new RoundupConsole(rdr, wtr, board, false);
		console.run();
		assertEquals(console.getModel().getValueAt(1, 1).getType(), BoardCell.GREENROBOT);
	}
	
	@Test
	public void TestAbout() throws IOException
	{
		Reader rdr = new StringReader("6\n7\n");
		Writer wtr = new StringWriter();
		RoundupConsole console = new RoundupConsole(rdr, wtr, board, false);
		console.run();
		assertNotNull(board.getAboutString());
	}
	
	@Test
	public void TestWinning() throws IOException
	{
		Reader rdr = new StringReader("32R\n7\n");
		Writer wtr = new StringWriter();
		RoundupConsole console = new RoundupConsole(rdr, wtr, board, false);
		console.run();
		assertEquals(console.getModel().getValueAt(3, 3).getType(), BoardCell.REDROBOT);
	}
	
	@Test
	public void TestLosing() throws IOException
	{
		Reader rdr = new StringReader("1\n11U\n7\n");
		Writer wtr = new StringWriter();
		RoundupConsole console = new RoundupConsole(rdr, wtr, board, false);
		console.run();
		assertEquals(console.getModel().getValueAt(0, 1).getType(), BoardCell.DEADROBOT);
	}
	
	@Test
	public void TestBoardZero() throws IOException
	{
		Reader rdr = new StringReader("4\n11 22 33 44 14 23R\n11D\n7\n");
		Writer wtr = new StringWriter();
		RoundupConsole console = new RoundupConsole(rdr, wtr, board, false);
		console.run();
		assertEquals(console.getModel().getValueAt(6, 1).getType(), BoardCell.DEADROBOT);
	}
}