package project2;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class RoundupImages {
	
	public static ImageIcon background, deadDown, deadUp, deadLeft, deadRight;
	public static ImageIcon greenDown, greenUp, greenLeft, greenRight;
	public static ImageIcon redDown, redUp, redLeft, redRight;
	
	public void loadImages() 
    {
        Image two = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/" + "piecedeaddown.png"));
        deadDown = new ImageIcon(two);
        Image three = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/" + "piecedeadleft.png"));
        deadLeft = new ImageIcon(three);
        Image four = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/" + "piecedeadup.png"));
        deadUp = new ImageIcon(four);
        Image five = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/" + "piecedeadright.png"));
        deadRight = new ImageIcon(five);
        Image six = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/" + "piecegreendown.png"));
        greenDown = new ImageIcon(six);
        Image seven = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/" + "piecegreenleft.png"));
        greenLeft = new ImageIcon(seven);
        Image eight = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/" + "piecegreenup.png"));
        greenUp = new ImageIcon(eight);
        Image nine = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/" + "piecegreenright.png"));
        greenRight = new ImageIcon(nine);
        Image ten = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/" + "piecereddown.png"));
        redDown = new ImageIcon(ten);
        Image eleven = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/" + "pieceredleft.png"));
        redLeft = new ImageIcon(eleven);
        Image twelve = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/" + "pieceredup.png"));
        redUp = new ImageIcon(twelve);
        Image thirteen = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/" + "pieceredright.png"));
        redRight = new ImageIcon(thirteen);

    }   

}
