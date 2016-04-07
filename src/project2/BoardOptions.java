package project2;

public class BoardOptions {

	private String[] boards = {
            "",
/* 1 */     "11 15 32R 34 51 55",
            "22R 14 31 42 44 55",
            "11 21 31R 51 15 45",
            "11 22R 31 35 51 54",
/* 5 */     "11 23 25 31 41R 44",
            "21 22 13R 33 42 43",
            "11 14R 31 33 34 44",
            "11 13R 15 21 45 51",
            "11 15 21 33 41R 44",
/* 10 */    "11 13 21 25R 31 54",
            "13 15 25R 31 44 52",
            "11 15 21 23R 45 51",
            "11 13 15 31 51 55R",
            "11R 15 41 44 53 54",
/* 15 */    "13 15 21R 25 52 55",
            "11R 25 41 51 54 55",
            "14 21R 34 41 45 52",
            "11 15 21 43R 45 51",
            };
	private String difficulty = "EEMMEEMMMDHMDHHHEM";
	
	public String getRobots(int row)
    {	
    	return boards[row];
    }
	
	public String getDifficulty(){
		return difficulty;
	}
}
