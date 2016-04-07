//package tests;
package project2;

import static org.junit.Assert.*;

import org.junit.Test;

import project2.BoardCell;
import project2.Direction;
import project2.Robot;
import project2.RoundupModel;

public class RoundupModelTest {

	RoundupModel model = new RoundupModel(7, 7);
	String setUpInput = "11 15 32R 34 51 55";
	
	@Test
	public void RoundupModelTest()
	{
		assertNotNull(model);
	}
	
	@Test
	public void getBoardTest()
	{
		model.selectGame("1");
		Robot[][] board = model.getBoard();
		assertNotNull(board);
	}
	
	@Test
	public void restartTest() {
		model.restartGame(setUpInput);
	    assertEquals(model.getValueAt(1, 1).getType(), BoardCell.GREENROBOT);
	    model.setBoardNum(0);
	    model.restartGame(setUpInput);
	    assertEquals(model.getValueAt(1, 1).getType(), BoardCell.GREENROBOT);
	    assertFalse(model.ifSet());
	    
	}
	
	@Test
	public void newGameTest()
	{
		model.setBoardNum(1);
		model.newGame();
		assertEquals(model.getValueAt(1,1).getType(), BoardCell.NONEWHITE);
		assertEquals(model.getBoardNum(), 2);
		model.setBoardNum(18);
		model.newGame();
		assertEquals(model.getBoardNum(), 1);
	}

	@Test 
	public void selectTest()
	{
		model.select(3);
		assertEquals(model.getBoardNum(), 3);
		model.selectGameUpdate();
	}
	
	@Test
	public void setTest()
	{
		model.setGame(setUpInput);
		assertEquals(model.getBoardNum(), 0);
		assertEquals(model.getValueAt(1, 1).getType(), BoardCell.GREENROBOT);
		model.setGameUpdate();
	}
	
	@Test
	public void selectGameTest()
	{
		boolean testValid = model.selectGame("22");
		assertFalse(testValid);
		testValid = model.selectGame("3");
		assertTrue(testValid);
	}
	
	@Test	
	public void setBoardTest()
	{
		boolean testValid = model.setBoard(setUpInput);
		assertTrue(testValid);
		testValid = model.setBoard("fisudjiig");
		assertFalse(testValid);
		testValid = model.setBoard("11 22 55 77 22 14R");
		assertFalse(testValid);
		testValid = model.setBoard("11 31 41 51 25 14 44");
		assertFalse(testValid);
		testValid = model.setBoard("11 21 31 41RR 12 44");
		assertFalse(testValid);
		testValid = model.setBoard("17R 31 41 51 25 14");
		assertFalse(testValid);
		
	}
	
	@Test 
	public void getAboutTest()
	{
		String aboutTest = model.getAboutString();
		assertNotNull(aboutTest);
	}
	
	@Test
	public void moveTest()
	{
		model.selectGame("1");
		model.move(1, 1, 'D', "11D");
		assertEquals(model.getValueAt(4, 1).getType(), BoardCell.GREENROBOT);
		model.move(5, 1, 'd', "51d");
		assertEquals(model.getValueAt(6, 1).getType(), BoardCell.DEADROBOT);
		model.setGame("23R 11 22 44 55 43");
		model.move(2, 3, 'D', "23D");
		assertEquals(model.getValueAt(3, 3).getType(), BoardCell.REDROBOT);
		
		model.selectGame("1");
		model.move(1, 1, 'R', "11R");
		assertEquals(model.getValueAt(1, 4).getType(), BoardCell.GREENROBOT);
		model.move(1, 5, 'r', "51r");
		assertEquals(model.getValueAt(1, 6).getType(), BoardCell.DEADROBOT);
		model.setGame(setUpInput);
		model.move(3, 2, 'R', "32R");
		assertEquals(model.getValueAt(3, 3).getType(), BoardCell.REDROBOT);
		
		model.selectGame("1");
		model.move(5, 1, 'U', "51U");
		assertEquals(model.getValueAt(2, 1).getType(), BoardCell.GREENROBOT);
		model.move(1, 1, 'u', "11u");
		assertEquals(model.getValueAt(0, 1).getType(), BoardCell.DEADROBOT);
		model.setGame("43R 23 11 44 55 21");
		model.move(4, 3, 'U', "43U");
		assertEquals(model.getValueAt(3, 3).getType(), BoardCell.REDROBOT);
		
		model.selectGame("1");
		model.move(1, 5, 'L', "15L");
		assertEquals(model.getValueAt(1, 2).getType(), BoardCell.GREENROBOT);
		model.move(1, 1, 'l', "11l");
		assertEquals(model.getValueAt(1, 0).getType(), BoardCell.DEADROBOT);
		model.setGame("35R 32 11 44 55 21");
		model.move(3, 5, 'L', "35L");
		assertEquals(model.getValueAt(3, 3).getType(), BoardCell.REDROBOT);
		assertEquals(model.getValueAt(3, 3).getDirection(), Direction.LEFT);
		assertTrue(model.getWon());
		assertFalse(model.getLost());
		
	}
	
	@Test
	public void wonTest()
	{
		model.selectGame("1");
		model.move(1, 1, 'R', "11R");
		model.move(2, 3, 'R', "23R");
		String wonTestString = model.wonString();
		assertNotNull(wonTestString);

	}
	
	@Test
	public void enteredMoveTest()
	{
		model.selectGame("1");
		model.enteredMove("11D");
		assertEquals(model.getValueAt(5, 1).getType(), BoardCell.GREENROBOT);
	}
	
}
