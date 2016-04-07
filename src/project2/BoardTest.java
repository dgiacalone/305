//package tests;
package project2;

import project2.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	RoundupModel model = new RoundupModel(7, 7);
	RoundupImages images = new RoundupImages();
	
	@Test
	public void testConstructor() 
	{
		Board board = new Board(7, 7, model, images);
		assertNotNull(board);
	}
	
	

}
