package project2;

import javax.swing.ImageIcon;

public class Robot {
	
	private BoardCell type;
	private Direction dir;
	
	public Robot()
	{
		type = BoardCell.GREENROBOT;
		dir = Direction.UP;
		
	}
	
	public Robot(BoardCell type, Direction dir)
	{
		this.type = type;
		this.dir = dir;
	}
	
	public ImageIcon getImage()
	{
		ImageIcon theImage = null;
		if(type.equals(BoardCell.GREENROBOT))
		{
			theImage = getGreenRobot();
		}
		else if(type.equals(BoardCell.REDROBOT))
		{
			theImage = getRedRobot();
		}
		else if(type.equals(BoardCell.DEADROBOT))
		{
			theImage = getDeadRobot();
		}
		return theImage;
	}
	
	private ImageIcon getGreenRobot()
	{
		if(dir.equals(Direction.UP))
		{
			return RoundupImages.greenUp;
		}
		else if(dir.equals(Direction.DOWN))
		{
			return RoundupImages.greenDown;
		}
		else if(dir.equals(Direction.RIGHT))
		{
			return RoundupImages.greenRight;	
		}
		else 
		{
			return RoundupImages.greenLeft;
		}
	}
	
	private ImageIcon getRedRobot()
	{
		if(dir.equals(Direction.UP))
		{
			return RoundupImages.redUp;
		}
		else if(dir.equals(Direction.DOWN))
		{
			return RoundupImages.redDown;
		}
		else if(dir.equals(Direction.RIGHT))
		{
			return RoundupImages.redRight;	
		}
		else 
		{
			return RoundupImages.redLeft;
		}	
	}
	
	private ImageIcon getDeadRobot()
	{
		if(dir.equals(Direction.UP))
		{
			return RoundupImages.deadUp;
		}
		else if(dir.equals(Direction.DOWN))
		{
			return RoundupImages.deadDown;
		}
		else if(dir.equals(Direction.RIGHT))
		{
			return RoundupImages.deadRight;	
		}
		else 
		{
			return RoundupImages.deadLeft;
		}	
	}
	
	public Direction getDirection()
	{
		return dir;
	}
	
	public BoardCell getType()
	{
		return type;
	}

}
